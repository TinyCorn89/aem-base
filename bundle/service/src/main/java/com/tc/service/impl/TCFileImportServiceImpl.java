package com.tc.service.impl;

import java.io.IOException;
import java.io.InputStream;

import javax.jcr.ImportUUIDBehavior;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.commons.lang.StringUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.jcr.contentloader.ContentImporter;
import org.apache.sling.jcr.contentloader.ImportOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tc.service.api.BaseService;
import com.tc.service.api.TCFileImportService;


@Component(label = "TC Content Import Service", description = "TC Content Import Service either imports individual xml files or zip files containing multiple xml files", immediate = true, metatype = true)
@Properties({
		@Property(name = "service.pid", value = "com.tc.service.api.TCFileImportService", propertyPrivate = false),
		@Property(name = "service.description", value = "Imports xml and zip files", propertyPrivate = false) })
@Service({ TCFileImportService.class })
public class TCFileImportServiceImpl extends BaseService implements TCFileImportService {
	private static final Logger LOG = LoggerFactory
			.getLogger(TCFileImportServiceImpl.class);
	
	@Reference(referenceInterface = ContentImporter.class, bind = "setContentImporter", unbind = "unSetContentImporter", cardinality = ReferenceCardinality.MANDATORY_UNARY)
	private ContentImporter contentImporter;
	@Override
	public void importFile(String parentPath, String filePath) {
		LOG.info("Importing file {}", filePath);
		Session session;
		InputStream content = null;
		try {
			session = getSession();
			Node parentNode = null;
			Node fileNode = session.getNode(filePath);
			if (StringUtils.contains(filePath, ".xml")) {
				LOG.info("File path ends with .xml so importing the xml in " + parentPath);
				parentNode = session.getNode(parentPath); 
			} else if (StringUtils.contains(filePath, ".zip")){
				//parentPath = parentPath.replaceAll("/content/", "/content/dam/");
				parentPath = "/content/dam";
				parentNode = session.getNode(parentPath);
			}
			Node jcrContent = fileNode.getNode("jcr:content");
			String fileName = fileNode.getName(); 
			
			LOG.info("fileName {}", fileName);
			boolean isZipFileImport = false;
			
			
			/*
			 * There are two possibilities where the stream of the file 
			 * jcr:data might exist 
			 * 1. it could be either under renditions/original/jcr:content
			 *    node
			 *                   or
			 *                   
			 * 2. it could be directly under jcr:content node
			 */
			try {
				content = jcrContent.getNode("renditions").getNode("original").getNode("jcr:content").getProperty("jcr:data").getStream();
				if (StringUtils.contains(filePath, ".zip")) {
					isZipFileImport = true;
				}
			} catch (PathNotFoundException pnfe) {
				LOG.info("jcr:data node does not exist under /jcr:content/renditions/original/jcr:content node, it might be under jcr:content directly, checking for it");
				content = jcrContent.getProperty("jcr:data").getStream();
			} 
			LOG.info("Importing file {} at parent {}", fileName, parentNode.getPath()); 
			contentImporter.importContent(parentNode, fileName, content, getImportOptions(), null);
			
			/*
			 * if the file that got imported is a zip file then get all XML files 
			 * from that folder and import individual XML files using content importer
			 * 
			 * this approach will eliminate workflow trigger for each xml file
			 */
			
			/*if (isZipFileImport) {
				String contentParentPath = parentPath.replaceAll("/content/dam/", "/content/");
				LOG.info("contentParentPath =" + contentParentPath);
				Node contentParentNode = session.getNode(contentParentPath);
				importAllXMLFiles(parentNode, contentParentNode, fileName, session);
			}*/
			
		} catch (Exception e) {
			LOG.error("Error while importing" ,e);
		} finally {
			if (content != null) {
				try {
					content.close();
				} catch (IOException e) {
					LOG.error("Error while closing the content stream", e);
				}
			}
		}

	}
	
	private void importAllXMLFiles(Node parentNode, Node contentParentNode, String zipFile, Session session) {
		// all xml files might got imported in parentPath + zipFile (without .zip)
		String zipFolder = zipFile.substring(0,  zipFile.indexOf(".zip"));
		Node zipFolderNode = null;
		LOG.info("ZipFolder is {}", zipFolder);
		try {
			zipFolderNode = session.getNode(parentNode.getPath() + "/" + zipFolder);
			NodeIterator iterator = zipFolderNode.getNodes();
			if (iterator != null) {
				while (iterator.hasNext()) {
					Node node = iterator.nextNode();
					String nodePath = node.getPath();
					if (StringUtils.contains(nodePath, ".xml")) {
						LOG.info("Importing " + nodePath);
						InputStream jcrData = node.getNode("jcr:content").getProperty("jcr:data").getStream();
						try {
							contentImporter.importContent(contentParentNode, node.getName(), jcrData, getImportOptions(), null);
							/*session.importXML(parentNode.getPath(), jcrData, ImportUUIDBehavior.IMPORT_UUID_CREATE_NEW);
							session.save();*/

						} catch (Exception e) {
							LOG.error("Error in importing " + node.getPath(), e);
						} finally {
							if (jcrData != null) {
								try { 
									jcrData.close();
								} catch (IOException e) {
									LOG.error("Error closing stream", e);
								}
							}
						}
						LOG.info("Importing " + node.getPath() + " complete");
					}
				}
			}
		} catch (PathNotFoundException e) {
			LOG.error("Unable to get zipFolder " + zipFolder, e);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private ImportOptions getImportOptions() {
		return new ImportOptions() {

            @Override
            public boolean isCheckin() {
                return false;
            }

			@Override
			public boolean isAutoCheckout() {
				return true;
			}

			@Override
            public boolean isIgnoredImportProvider(String extension) {
                return false;
            }

            @Override
            public boolean isOverwrite() {
                return true;
            }
            
            @Override
			public boolean isPropertyOverwrite() {
				return true;
			} 
           };
	}

	protected void setContentImporter(ContentImporter contentImporter) {
		if (this.contentImporter == null) {
			this.contentImporter = contentImporter;
		}
	}

	protected void unSetContentImporter(ContentImporter contentImporter) {
		if (this.contentImporter != null) {
			this.contentImporter = null;
		}
	}
	
}
