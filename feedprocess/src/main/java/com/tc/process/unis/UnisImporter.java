package com.tc.process.unis;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.tc.aem.importer.AEMPackageImporter;
import com.tc.process.handler.TCNewLetterTransformerHandler;
import com.tc.process.pressfeed.TCCanadianPressFeedProcessor;

public class UnisImporter {
	static Logger LOG = Logger.getLogger(UnisImporter.class);
	static Properties properties = null;
	public static void main(String args[]) throws Exception {
		if (args.length == 0) {
			LOG.error("Please specify the import type...exiting");
			return;
		}
		
		String importType = args[0];
		MigrationUtils.loadProperties("unis.properties");
		Properties properties = MigrationUtils.getProperties(importType);
		LOG.info(properties);
		
		TCNewLetterTransformerHandler transformer = new TCNewLetterTransformerHandler();
		String sourceDir = properties.getProperty(importType + ".sourcedir");
		String xslFile = properties.getProperty(importType + ".xslfile");
		String contentDir = properties.getProperty("article.contentdir");
		String metaInfDir = properties.getProperty("article.metainfdir");
		String uniqueId = properties.getProperty("article.uniqueid");
		String workingDir = properties.getProperty("article.workingdir");
		String packageName = properties.getProperty("article.packagename");
		
		transformer.tranform(sourceDir, xslFile, contentDir, metaInfDir, uniqueId, workingDir, packageName);
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

	}
}
