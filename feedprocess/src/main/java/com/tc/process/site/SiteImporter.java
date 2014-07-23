package com.tc.process.site;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.zip.ZipOutputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.tc.aem.importer.AEMPackageImporter;
import com.tc.process.handler.TCTransformerHandler;
import com.tc.process.pressfeed.CanadianFeedEntityResolver;
import com.tc.process.unis.MigrationUtils;


public class SiteImporter {

	static Logger LOG = Logger.getLogger(SiteImporter.class);
	static Properties properties = null;

	public static void main(String args[]) throws Exception {

		Properties properties = MigrationUtils
				.loadProperties("site.properties");
		SiteImporter SiteImporter =new SiteImporter();
		SiteImporter.transform(properties.getProperty("site.sourcedir"),
				properties.getProperty("site.workingdir"),
				properties.getProperty("site.xslfile"),
				properties.getProperty("site.packageinfdir"));
		SiteImporter.zipandUpload(properties);
	}

	private  boolean zipandUpload(Properties properties) {

		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		String aemPackagePath = properties.getProperty("site.workingdir")
				+ File.separator + "tcsiteconfig.zip";
		try {
			fos = new FileOutputStream(aemPackagePath);
			zos = new ZipOutputStream(fos);
			TCTransformerHandler tcNewLetterTransformerHandler = new TCTransformerHandler();
			tcNewLetterTransformerHandler.addDirToZipArchive(zos, new File(
					properties.getProperty("site.workingdir") + File.separator
							+ "jcr_root"), null, false);
			tcNewLetterTransformerHandler.addDirToZipArchive(zos, new File(
					properties.getProperty("site.workingdir") + File.separator
							+ "META-INF"), null, false);
		} catch (FileNotFoundException fileNotFoundException) {
			LOG.error(fileNotFoundException);
		} catch (IOException ioException) {
			LOG.error(ioException);
		} catch (Exception exception) {
			LOG.error(exception);
		} finally {
			try {
				zos.close();
			} catch (IOException e) {
				LOG.error(e);
			}
		}
		//
		AEMPackageImporter aemPackageImporter = new AEMPackageImporter();
		InputStream aemInputStream = SiteImporter.class.getClassLoader()
				.getResourceAsStream("aem.properties");
		Properties aemProperties = new Properties();
		try {
			aemProperties.load(aemInputStream);
		} catch (IOException e) {
			LOG.error(e);
		}
		String repoURL = aemProperties.getProperty("aem.url") + "/crx/server";
		String aemUserName = aemProperties.getProperty("aem.userid");
		String aemPassword = aemProperties.getProperty("aem.password");

		boolean installationStatus = aemPackageImporter.importPackage(repoURL,
				aemUserName, aemPassword, aemPackagePath, false);
		if (installationStatus) {
			LOG.info("Package installed successfully...");
		} else {
			LOG.info("Failed to install the package...");
		}
		//
		return true;

	}

	private boolean transform(String inputDir, String workingDir,
			String xslFile, String packageDir) throws Exception {

		File directory = new File(inputDir);
		File workingDirectory = new File(workingDir);
		if (workingDirectory.exists()) {
			cleanDir(workingDirectory.getAbsolutePath());
		}
		workingDirectory.mkdirs();

		File metaInfDir = new File(packageDir + File.separator + "META-INF");
		
		if (metaInfDir.exists()) {
			
			FileUtils.copyDirectoryToDirectory(metaInfDir, workingDirectory);
		}
		File jcrDir = new File(packageDir + File.separator + "jcr_root");
		if (jcrDir.exists()) {
			FileUtils.copyDirectoryToDirectory(jcrDir, workingDirectory);
		}

		File[] directoryListing = directory.listFiles();

		for (File child : directoryListing) {
			if (!child.getName().contains(".xml")) {
				continue;
			}
			//
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();
			CanadianFeedEntityResolver entityResolver = new CanadianFeedEntityResolver();
			xmlReader.setEntityResolver(entityResolver);

			InputSource inputSource = new InputSource(
					new FileInputStream(child));
			SAXSource saxSource = new SAXSource(xmlReader, inputSource);

			FileInputStream xslFileAsStream = new FileInputStream(xslFile);
			StreamSource styleSource = new StreamSource(xslFileAsStream);
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(styleSource);
			File directoryName = new File(workingDirectory + File.separator
					+ "jcr_root" + File.separator + "etc" + File.separator
					+ "tcsiteconfig" + File.separator
					+ child.getName().replace(".xml", ""));
			directoryName.mkdir();
			StreamResult result = new StreamResult(directoryName
					+ File.separator + ".content.xml");
			transformer.transform(saxSource, result);
		}

		return true;
	}

	protected static void cleanDir(String workingDir) {
		File workingDirFile = new File(workingDir);
		if (workingDirFile.exists()) {
			MigrationUtils.clearDirectory(workingDirFile);
		}
	}
}
