package com.tc.process.unis;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.tc.aem.importer.AEMPackageImporter;
import com.tc.process.handler.TCTransformerHandler;
import com.tc.process.pressfeed.TCCanadianPressFeedProcessor;

public class UnisImporter {
	static Logger LOG = Logger.getLogger(UnisImporter.class);
	static Properties properties = null;
	public static void main(String args[]) throws Exception {
		if (args.length == 0) {
			LOG.error("Please specify the import type...exiting");
			return;
		}
		
		Properties properties = MigrationUtils.loadProperties("unis.properties");
		LOG.info(properties);
		
		TCTransformerHandler transformer = new TCTransformerHandler();
		String sourceDir = properties.getProperty("article.sourcedir");
		String xslFile = properties.getProperty("article.xslfile");
		String contentDir = properties.getProperty("article.contentdir");
		String metaInfDir = properties.getProperty("article.metainfdir");
		String uniqueId = properties.getProperty("article.uniqueid");
		String workingDir = properties.getProperty("article.workingdir");
		String packageName = properties.getProperty("article.packagename");
		String photoXmlDir = properties.getProperty("photo.sourcedir");
		String photoJpgDir = properties.getProperty("image.sourcedir");
		String photoWorkingDir = properties.getProperty("photo.workingdir");
		String photoXslFile = properties.getProperty("photo.xslfile");
		
		cleanDir(workingDir);
		cleanDir(photoWorkingDir);
		
				
		/*
		 * send some parameters to the xsl file so that these parameters
		 * are used by ArticleImageProcessor to process photos referenced
		 * by article xml
		 */
		Map<String, String> params = new HashMap<String, String>();
		params.put("photoSourceDir", photoXmlDir);
		params.put("imageSourceDir", photoJpgDir);
		params.put("photoWorkingDir", photoWorkingDir);
		params.put("photoXslFile", photoXslFile);
		
		transformer.tranform(sourceDir, xslFile, contentDir, metaInfDir, uniqueId, workingDir, packageName, params);
		InputStream aemInputSt = TCCanadianPressFeedProcessor.class.getClassLoader()
				.getResourceAsStream("aem.properties");
		Properties aemProps = new Properties();
		aemProps.load(aemInputSt);
		
		// upload package
		AEMPackageImporter importer = new AEMPackageImporter();
		String repoURL = aemProps.getProperty("aem.url") + "/crx/server";
		String userName = aemProps.getProperty("aem.userid");
		String password = aemProps.getProperty("aem.password");
		String packagePath = workingDir + File.separator + packageName;
		
		importer.importPackage(repoURL, userName, password, packagePath, true);
		//DamAssetUploadHandler uploader = new DamAssetUploadHandler();
		
		// upload image zip file if exist
		/*File imageZip = new File(workingDir
						+ File.separator 
						+ "tc.zip");
		if (imageZip.exists()) {
			if (uploader.uploadToAEM(imageZip.getAbsolutePath(), aemProps)) {
				LOG.info("uploaded to cq successfully");
			} else {
				LOG.error("uploaded to cq failed");
			}
		}*/
	}
	protected static void cleanDir(String workingDir) {
		File workingDirFile = new File(workingDir);
		if (workingDirFile.exists()) {
			workingDirFile.delete();
		}
	}
}
