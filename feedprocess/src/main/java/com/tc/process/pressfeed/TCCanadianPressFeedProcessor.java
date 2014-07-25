package com.tc.process.pressfeed;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.tc.aem.importer.AEMPackageImporter;
import com.tc.process.handler.TCFTPCPProcessHandler;
import com.tc.process.handler.TCTransformerHandler;

public class TCCanadianPressFeedProcessor {
	static Logger LOG = Logger.getLogger(TCCanadianPressFeedProcessor.class
			.getName());

	public static void main(String ar[]) throws Exception {

		LOG.info("Starting the Canadian feed process");
		TCCanadianPressFeedProcessor tc = new TCCanadianPressFeedProcessor();
		InputStream pressInputst = tc.getClass().getClassLoader()
				.getResourceAsStream("canadianpressfeed.properties");

		Properties pressProps = new Properties();
		pressProps.load(pressInputst);
		// download the press feed
		TCFTPCPProcessHandler process = new TCFTPCPProcessHandler(
				pressProps);

		if (process.process()) {
			LOG.info("Completed the Ftp files Sucessfully");
			InputStream aemInputSt = TCCanadianPressFeedProcessor.class.getClassLoader()
					.getResourceAsStream("aem.properties");
			Properties aemProps = new Properties();
			aemProps.load(aemInputSt);

			// run the trasformer
			// zip
			TCTransformerHandler transformer = new TCTransformerHandler();
			String metaInfFolder = pressProps.getProperty("canadianpressfeed.metainffolder");
			String contentFolder = pressProps.getProperty("canadianpressfeed.contentfolder");
			String localDir = pressProps.getProperty("ftp.localDirectory");
			String xslFile = pressProps.getProperty("canadianpressfeed.xslfile");
			String uniqueId = pressProps.getProperty("canadianpressfeed.uniqueid");
			String workingDir = pressProps.getProperty("canadianpressfeed.workingdir");
			String packageName = pressProps.getProperty("canadianpressfeed.packagename");
			
			if (transformer.tranform(
					localDir,
					xslFile, contentFolder, metaInfFolder, uniqueId, workingDir, packageName)) {
				LOG.info("Completed the transformations Sucessfully");
				

				/*DamAssetUploadHandler uploader = new DamAssetUploadHandler();
				
				// upload image zip file if exist
				File imageZip = new File(workingDir
								+ File.separator 
								+ "tc.zip");
				if (imageZip.exists()) {
					if (uploader.uploadToAEM(imageZip.getAbsolutePath(), aemProps)) {
						LOG.info("uploaded to cq successfully");
					} else {
						LOG.error("uploaded to cq failed");
					}
				}*/
				
				// upload package
				AEMPackageImporter importer = new AEMPackageImporter();
				String repoURL = aemProps.getProperty("aem.url") + "/crx/server";
				String userName = aemProps.getProperty("aem.userid");
				String password = aemProps.getProperty("aem.password");
				String packagePath = workingDir + File.separator + packageName;
				
				importer.importPackage(repoURL, userName, password, packagePath, true);
				
			} else {
				LOG.error("transform failed");
			}

		} else {
			LOG.error("ftp process  failed . check the logs for info . It may be possibe that no files are present for processing ");
		}
	}

}
