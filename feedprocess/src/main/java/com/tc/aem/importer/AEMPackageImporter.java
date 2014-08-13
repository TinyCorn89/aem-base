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

import org.apache.jackrabbit.vault.fs.api.ImportMode;
import org.apache.jackrabbit.vault.fs.io.ImportOptions;

import org.apache.jackrabbit.vault.packaging.JcrPackageManager;
import org.apache.jackrabbit.vault.packaging.impl.JcrPackageManagerImpl;
import org.apache.jackrabbit.vault.packaging.JcrPackage;

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
            System.out.println("Error while uploading package " + e.getMessage());
            isSuccess = false;
            session.logout();
            LOG.info("Session ends");
        } finally {
            if (isSuccess) {
                // logging out
                LOG.info("Session ends");
                if(session != null) {
                    session.logout();
                }
            }
            // no need of explicitly closing the input streams here.
            // they were automatically closed by the jcr package manager
        }
        if (isSuccess) {
            LOG.info("Package imported successfully");
            System.out.println("Package imported successfully");
        }

        return isSuccess;
    }

    /**
     * Uninstall package.
     *
     * @param repoURL the repo url
     * @param username the username
     * @param password the password
     * @param packagePath the package path
     * @return true, if successful
     */
    public boolean uninstallPackage(String repoURL, String username,
                                    String password, String packagePath) {
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
                    ImportOptions importOptions = new ImportOptions();
                    jcrPackage.uninstall(importOptions);
                }
            } else {
                LOG.info("jcr session is not created");
            }
        } catch (Exception e) {
            LOG.error("Error while uninstalling package", e);
            isSuccess = false;
            session.logout();
            LOG.info("Session ends");
        } finally {
            if (isSuccess) {
                // logging out
                LOG.info("Session ends");
                if(session != null) {
                    session.logout();
                }
            }
            // no need of explicitly closing the input streams here.
            // they were automatically closed by the jcr package manager
        }
        if (isSuccess) {
            LOG.info("Package uninstalled successfully");
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
