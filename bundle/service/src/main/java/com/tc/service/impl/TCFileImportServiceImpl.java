package com.tc.service.impl;

import java.io.InputStream;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
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
		try {
			session = getSession();
			Node parentNode = null;
			Node fileNode = session.getNode(filePath);
			if (StringUtils.contains(filePath, ".xml")) {
				LOG.info("File path ends with .xml so importing the xml in " + parentPath);
				parentNode = session.getNode(parentPath); 
			} else if (StringUtils.contains(filePath, ".zip")){
				parentPath = parentPath.replaceAll("/content/", "/content/dam/");
				parentNode = session.getNode(parentPath);
			}
			Node jcrContent = fileNode.getNode("jcr:content");
			String fileName = fileNode.getName(); 
			
			LOG.info("fileName {}", fileName);
			InputStream content = null;
			
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
			} catch (PathNotFoundException pnfe) {
				LOG.info("jcr:data node does not exist under /jcr:content/renditions/original/jcr:content node, it might be under jcr:content directly, checking for it");
				content = jcrContent.getProperty("jcr:data").getStream();
			} 
			LOG.info("Importing file {} at parent {}", fileName, parentNode.getPath()); 
			contentImporter.importContent(parentNode, fileName, content, getImportOptions(), null);
		} catch (Exception e) {
			LOG.error("Error while importing" ,e);
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
