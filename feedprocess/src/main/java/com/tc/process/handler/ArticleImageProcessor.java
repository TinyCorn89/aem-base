package com.tc.process.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.tc.process.pressfeed.CanadianFeedEntityResolver;

public class ArticleImageProcessor {
	static Logger LOG = Logger.getLogger(ArticleImageProcessor.class);
	static TransformerFactory factory = null;
	static Transformer transformer;
	static EntityResolver entityResolver;
	static XMLReader xmlReader;
	public static String parsePhoto(String articleId, String photoId, String photoSourceDir, String imageSourceDir, String photoXslFile, String photoWorkingDir, String damFolder, String createdDate) {
		LOG.info("photoId=" + photoId + ",photoSourceDir=" + photoSourceDir + ", imageSourceDir=" + imageSourceDir + ", phtoXslFile=" + photoXslFile + ", + photoWorkingDir" + photoWorkingDir);
		if (StringUtils.isEmpty(photoId)) {
			LOG.warn("Photo does not exist for the article " + articleId);
			return null;
		}
		String xmlFile = "export_photo_" + photoId + ".xml";
		String photoXmlFile = photoSourceDir + File.separator + xmlFile;
		FileInputStream xslFileAsStream = null;
		StringWriter sw = null;
		try {
			/*
			 * the photo xml is located at photoSourceDir/export_photo_<<photoId>>.xml file
			 * the photo JPG is located at imageSourceDir/export_photo_<<photoId>>.jpg file
			 */

			if (xmlReader == null) {
				xmlReader = XMLReaderFactory.createXMLReader();
			}
			if (entityResolver == null) {
				entityResolver = new CanadianFeedEntityResolver();
			}
			
			if (xmlReader == null) {
				xmlReader.setEntityResolver(entityResolver);
			}
			
			
			
			factory = TransformerFactory
					.newInstance();
			xslFileAsStream = new FileInputStream(photoXslFile);
			StreamSource styleSource = new StreamSource(xslFileAsStream);
			transformer = factory
					.newTransformer(styleSource);
			File photoWorkingDirFile = new File(photoWorkingDir);
			if (!photoWorkingDirFile.exists()) {
				photoWorkingDirFile.mkdirs();
			}
			String outputFile = photoWorkingDir + File.separator + xmlFile;
			outputFile = outputFile.replaceAll(".xml", ".jcr.xml");
			sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			InputSource inputSource = new InputSource(new FileInputStream(photoXmlFile));
			SAXSource saxSource = new SAXSource(xmlReader, inputSource);
			transformer.setParameter("damFolder", damFolder);
			transformer.setParameter("createdDate", createdDate);
			transformer.setParameter("articleId", articleId);
			transformer.transform(saxSource, result);
			
						
			String resultImageStr = result.getWriter().toString();
			LOG.debug(resultImageStr);
			return resultImageStr;
			
		} catch (Exception e) {
			LOG.error("error while transforming " + photoXmlFile, e);
		} finally {
			if (xslFileAsStream != null) {
				try {
					xslFileAsStream.close();
				} catch (IOException e) {
					LOG.error(e);
				}
			}
			
			if (sw != null) {
				try {
					sw.close();
				} catch (IOException e) {
					LOG.error(e);
				}
			}
		}
		
		return null;
	}

}
