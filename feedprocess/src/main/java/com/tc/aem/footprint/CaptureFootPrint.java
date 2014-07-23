package com.tc.aem.footprint;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.tc.aem.importer.AEMPackageImporter;
import com.tc.poolparty.PoolPartyTagsImporter;
import com.tc.process.pressfeed.TCCanadianPressFeedProcessor;
import com.tc.process.unis.UnisImporter;

public class CaptureFootPrint {
	private static String quickStartFolder = null;

	static Logger LOG = Logger.getLogger("footprint");
	static Properties aemProperties = null;
	static Properties footPrintProperties = null;
	static Properties unisProperties = null;
	static Properties cpProperties = null;
	static Properties poolPartyProperties = null;
	static AEMPackageImporter aemPackageImporter = null;
	static String repoURL = null;
	static String aemUserName = null;
	static String aemPassword = null;
	static int count = 24;

	public static void main(final String a[]) throws Exception {
		if (a == null || a.length < 1) {
			System.err
					.println("Please specify the quickStartFolder location as input parameter");
			return;
		}
		if (a.length > 1) {
			count = Integer.parseInt(a[1]);
		}
		aemPackageImporter = new AEMPackageImporter();

		InputStream aemInputStream = CaptureFootPrint.class.getClassLoader()
				.getResourceAsStream("aem.properties");
		aemProperties = new Properties();
		try {
			aemProperties.load(aemInputStream);
		} catch (IOException e) {
			LOG.error(e);
		}
		repoURL = aemProperties.getProperty("aem.url") + "/crx/server";
		aemUserName = aemProperties.getProperty("aem.userid");
		aemPassword = aemProperties.getProperty("aem.password");

		InputStream cpInputStream = CaptureFootPrint.class.getClassLoader()
				.getResourceAsStream("canadianpressfeed.properties");
		cpProperties = new Properties();
		try {
			cpProperties.load(cpInputStream);
		} catch (IOException e) {
			LOG.error(e);
		}

		InputStream unisInputStream = CaptureFootPrint.class.getClassLoader()
				.getResourceAsStream("unis.properties");
		unisProperties = new Properties();
		try {
			unisProperties.load(unisInputStream);
		} catch (IOException e) {
			LOG.error(e);
		}

		InputStream poolPartyInputStream = CaptureFootPrint.class
				.getClassLoader().getResourceAsStream("poolparty.properties");
		poolPartyProperties = new Properties();
		try {
			poolPartyProperties.load(poolPartyInputStream);
		} catch (IOException e) {
			LOG.error(e);
		}

		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				try {

					LOG.info("Running CaptureFootPrint");
					doIt(a[0]);
					count--;
					if (count == 0) {
						this.cancel();
					}
					LOG.info("Running CaptureFootPrint finished");
				} catch (Exception e) {
					LOG.error("Error in timer ", e);
				}
			}
		};
		timer.schedule(task, 0, 30000);

	}
	static long initialSize = 0;
	static long finalSize = 0;
	
	protected static void doIt(String a) throws Exception {
		quickStartFolder = a;
		

		LOG.info("crx-quickstart folder = " + quickStartFolder);
		File quickStartFolderFile = new File(quickStartFolder);

		initialSize += folderSize(quickStartFolderFile);

		LOG.info("quickStartFolder size in MB before execution = "
				+ (initialSize / 1024) / 1024);
		LOG.info("-------------------- INSTALLING POOL PARTY --------------------");
		installPoolParty();
		long sizeAfterPoolPartyInstall = folderSize(quickStartFolderFile);
		LOG.info("quickStartFolder size in MB after installing poolparty tags = "
				+ (sizeAfterPoolPartyInstall / 1024) / 1024);
		LOG.info("-------------------- INSTALLING POOL PARTY COMPLETED --------------------");

		LOG.info("-------------------- INSTALLING CP --------------------");
		installCP();
		long sizeAfterCPInstall = folderSize(quickStartFolderFile);
		LOG.info("quickStartFolder size in MB after installing CP articles = "
				+ (sizeAfterCPInstall / 1024) / 1024);
		LOG.info("-------------------- INSTALLING CP COMPLETED --------------------");
		LOG.info("-------------------- INSTALLING UNIS ARTICLES --------------------");
		installUnis();
		long sizeAfterUnisInstall = folderSize(quickStartFolderFile);
		LOG.info("quickStartFolder size in MB after installing unis articles = "
				+ (sizeAfterUnisInstall / 1024) / 1024);
		LOG.info("-------------------- INSTALLING UNIS ARTICLES COMPLETED --------------------");

		LOG.info("-------------------- UN-INSTALLING POOL PARTY --------------------");
		unInstallPoolParty();
		long sizeAfterPoolPartyUnInstall = folderSize(quickStartFolderFile);
		LOG.info("quickStartFolder size in MB after poolparty uninstalled = "
				+ (sizeAfterPoolPartyUnInstall / 1024) / 1024);
		LOG.info("-------------------- UN-INSTALLING POOL PARTY COMPLETED --------------------");
		LOG.info("-------------------- UN-INSTALLING CP --------------------");
		unInstallCP();
		long sizeAfterCPUnInstall = folderSize(quickStartFolderFile);
		LOG.info("quickStartFolder size in MB after CP uninstalled = "
				+ (sizeAfterCPUnInstall / 1024) / 1024);
		LOG.info("-------------------- UN-INSTALLING CP COMPLETED --------------------");
		LOG.info("-------------------- UN-INSTALLING UNIS ARTICLES --------------------");
		unInstallUnis();
		long sizeAfterUnisUnInstall = folderSize(quickStartFolderFile);
		LOG.info("quickStartFolder size in MB after unis uninstalled = "
				+ (sizeAfterUnisUnInstall / 1024) / 1024);
		LOG.info("-------------------- UN-INSTALLING UNIS ARTICLES COMPLETED --------------------");
		finalSize += folderSize(quickStartFolderFile);
		LOG.info("delta of quickStartFolder in MB = "
				+ ((finalSize - initialSize) / 1024) / 1024);
	}

	public static void installPoolParty() {
		String en[] = { "en" };
		String fr[] = { "fr" };
		PoolPartyTagsImporter.main(en);
		PoolPartyTagsImporter.main(fr);
	}

	public static void installCP() throws Exception {
		TCCanadianPressFeedProcessor.main(null);
	}

	public static void installUnis() throws Exception {
		UnisImporter.main(null);
	}

	public static void unInstallUnis() {
		String packageFolder = unisProperties.getProperty("article.workingdir");
		String packageZipFileName = unisProperties
				.getProperty("article.packagename");
		uninstallPackage(packageFolder, packageZipFileName);
	}

	public static void unInstallCP() {
		String packageFolder = cpProperties
				.getProperty("canadianpressfeed.workingdir");
		String packageZipFileName = cpProperties
				.getProperty("canadianpressfeed.packagename");
		uninstallPackage(packageFolder, packageZipFileName);

	}

	public static void unInstallPoolParty() {
		String packageFolder = poolPartyProperties
				.getProperty("poolparty.jcrRootParentDir");
		String packageZipFileName = "PoolParty.zip";
		uninstallPackage(packageFolder, packageZipFileName);
	}

	public static void uninstallPackage(String packageFolder,
			String packageZipFileName) {
		if (!packageFolder.endsWith(File.separator)) {
			packageFolder = packageFolder + File.separator;
		}
		boolean uninstallationStatus = aemPackageImporter.uninstallPackage(
				repoURL, aemUserName, aemPassword, packageFolder
						+ packageZipFileName);
		if (uninstallationStatus) {
			LOG.info("Package uninstalled successfully...");
		} else {
			LOG.info("Failed to uninstall the Package...");
		}

	}

	public static long folderSize(File directory) {
		long length = 0;
		for (File file : directory.listFiles()) {
			if (file.isFile())
				length += file.length();
			else
				length += folderSize(file);
		}
		return length;
	}
}
