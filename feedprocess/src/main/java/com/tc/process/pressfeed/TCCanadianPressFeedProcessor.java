package com.tc.process.pressfeed;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.tc.aem.importer.AEMPackageImporter;
import com.tc.process.handler.DamAssetUploadHandler;
import com.tc.process.handler.TCFTPNewsLetterProcessHandler;
import com.tc.process.handler.TCNewLetterTransformerHandler;

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
		TCFTPNewsLetterProcessHandler process = new TCFTPNewsLetterProcessHandler(
				pressProps);

		if (process.process()) {
			LOG.info("Completed the Ftp files Sucessfully");
			// run the trasformer
			// zip
			TCNewLetterTransformerHandler transformer = new TCNewLetterTransformerHandler();

			if (transformer.tranform(
					pressProps.getProperty("ftp.localDirectory"),
					pressProps.getProperty("canadianpressfeed.xslfile"))) {
				LOG.info("Completed the transformations Sucessfully");
				// upload the file to Dam
				InputStream aemPropsStr = tc.getClass().getClassLoader()
						.getResourceAsStream("aem.properties");

				DamAssetUploadHandler uploader = new DamAssetUploadHandler();
				Properties aemProps = new Properties();
				aemProps.load(aemPropsStr);
				// upload image zip file if exist
				File imageZip = new File(pressProps.getProperty("ftp.localDirectory")
								+ File.separator + "output" + File.separator
								+ "tc.zip");
				if (imageZip.exists()) {
					if (uploader.uploadToAEM(imageZip.getAbsolutePath(), aemProps)) {
						LOG.info("uploaded to cq successfully");
					} else {
						LOG.error("uploaded to cq failed");
					}
				}
				
				// upload package
				AEMPackageImporter importer = new AEMPackageImporter();
				String repoURL = aemProps.getProperty("aem.url") + "/crx/server";
				String userName = aemProps.getProperty("aem.userid");
				String password = aemProps.getProperty("aem.password");
				String packagePath = pressProps.getProperty("ftp.localDirectory") + File.separator + "output" + File.separator + "output.zip";
				
				importer.importPackage(repoURL, userName, password, packagePath, true);
				
			} else {
				LOG.error("tranform failed");
			}

		} else {
			LOG.error("ftp process  failed . check the logs for info . It may be possibe that no files are present for processing ");
		}
	}

}
