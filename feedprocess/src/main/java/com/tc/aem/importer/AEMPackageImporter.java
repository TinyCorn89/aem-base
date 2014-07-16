package com.tc.aem.importer;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.apache.jackrabbit.commons.JcrUtils;
import org.apache.log4j.Logger;

import com.day.jcr.vault.fs.api.ImportMode;
import com.day.jcr.vault.packaging.ImportOptions;
import com.day.jcr.vault.packaging.JcrPackage;
import com.day.jcr.vault.packaging.JcrPackageManager;
import com.day.jcr.vault.packaging.impl.JcrPackageManagerImpl;

public class AEMPackageImporter {
	static Logger LOG = Logger.getLogger(AEMPackageImporter.class.getName());

	public boolean importPackage(String repoURL, String username,
			String password, String packagePath, boolean merge) {
		boolean isSuccess = true;
		// Getting instance of Jcr repository connection
		Session session = getRepositorySession(repoURL, username, password); 

		InputStream inStream = null;
		try {
			if (null != session) {
				LOG.info("Session created");
				JcrPackageManager pkgManager = new JcrPackageManagerImpl(
						session);
				inStream = new FileInputStream(new File(packagePath));
				JcrPackage jcrPackage = pkgManager.upload(inStream, true);
				// check if the package is valid
				if (jcrPackage.isValid()) {
					// ImportOptions importOptions =
					// createPackageImportOptions();
					ImportOptions importOptions = new ImportOptions();
					if(merge) {
						importOptions.setImportMode(ImportMode.MERGE);
					}
					jcrPackage.install(importOptions); // package imported to CQ
														// instance
				}
			} else {
				LOG.info("jcr session is not created");
			}
		} catch (Exception e) {
			LOG.error("Error while uploading package", e);
			isSuccess = false;
		} finally {
			if (isSuccess) {
				// logging out
				LOG.info("Session ends");
				session.logout();
			}
			// no need of explicitly closing the input streams here.
			// they were automatically closed by the jcr package manager
		}
		if (isSuccess) {
			LOG.info("Package imported successfully");	
		}
		
		return isSuccess;
	}
	
	

	private Session getRepositorySession(String url, String userName,
			String password) {
		try {
			Repository repository = JcrUtils.getRepository(url);
			return repository.login(new SimpleCredentials(userName, password
					.toCharArray()));
		} catch (RepositoryException re) {
			re.printStackTrace();
		} catch (Exception ex) {
		}
		return null;
	}

}
