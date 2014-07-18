package com.tc.process.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.tc.process.pressfeed.CanadianFeedEntityResolver;

public class ArticleImageProcessor {
	static Logger LOG = Logger.getLogger(ArticleImageProcessor.class);
	public static String parsePhoto(String articleId, String photoId, String photoSourceDir, String imageSourceDir, String photoXslFile, String photoWorkingDir, String damFolder, String createdDate) {
		LOG.info("photoId=" + photoId + ",photoSourceDir=" + photoSourceDir + ", imageSourceDir=" + imageSourceDir + ", phtoXslFile=" + photoXslFile + ", + photoWorkingDir" + photoWorkingDir);
		String xmlFile = "export_photo_" + photoId + ".xml";
		String photoXmlFile = photoSourceDir + File.separator + xmlFile;
		try {
			/*
			 * the photo xml is located at photoSourceDir/export_photo_<<photoId>>.xml file
			 * the photo JPG is located at imageSourceDir/export_photo_<<photoId>>.jpg file
			 */
			TransformerFactory factory = null;
			Transformer transformer;
			XMLReader xmlReader;
			EntityResolver entityResolver;

			xmlReader = XMLReaderFactory.createXMLReader();
			entityResolver = new CanadianFeedEntityResolver();
			xmlReader.setEntityResolver(entityResolver);
			factory = TransformerFactory
					.newInstance();
			FileInputStream xslFileAsStream = new FileInputStream(photoXslFile);
			StreamSource styleSource = new StreamSource(xslFileAsStream);
			transformer = factory
					.newTransformer(styleSource);
			File photoWorkingDirFile = new File(photoWorkingDir);
			if (!photoWorkingDirFile.exists()) {
				photoWorkingDirFile.mkdirs();
			}
			String outputFile = photoWorkingDir + File.separator + xmlFile;
			outputFile = outputFile.replaceAll(".xml", ".jcr.xml");
			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			InputSource inputSource = new InputSource(new FileInputStream(photoXmlFile));
			SAXSource saxSource = new SAXSource(xmlReader, inputSource);
			transformer.setParameter("damFolder", damFolder);
			transformer.setParameter("createdDate", createdDate);
			transformer.setParameter("articleId", articleId);
			transformer.transform(saxSource, result);
			
			/*DocumentBuilderFactory documentFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentFactory
					.newDocumentBuilder();
			Document doc = documentBuilder.parse(outputFile);
			doc.getDocumentElement().normalize();
			
			NodeList nodeList = doc.getElementsByTagName("image");
			LOG.info("nodeList.length=" + nodeList.getLength());
			Node imageNode = nodeList.item(0);
			NamedNodeMap attributes = imageNode.getAttributes();
			if (imageNode != null && attributes != null) {
				
				int attLength = attributes.getLength();
				for (int i = 0; i < attLength; i++) {
					LOG.debug(attributes.item(i).getNodeName() + " === " + attributes.item(i).getNodeValue());
				}
			}
			return imageNode;*/
			
			String resultImageStr = result.getWriter().toString();
			LOG.debug(resultImageStr);
			return resultImageStr;
			
		} catch (Exception e) {
			LOG.error("error while transforming " + photoXmlFile, e);
		}
		
		return null;
	}

}
