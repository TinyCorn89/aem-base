package com.tc.process.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.tc.poolparty.PoolPartyManager;
import com.tc.poolparty.impl.PoolPartyManagerImpl;
import com.tc.process.pressfeed.CanadianFeedEntityResolver;



public class TCTransformerHandler {
	static Logger LOG = Logger.getLogger(TCTransformerHandler.class
			.getName());
	private TransformerFactory factory = null;
	private Transformer transformer;
	private XMLReader xmlReader;
	private EntityResolver entityResolver;
	private PoolPartyManager poolPartyManager; 
	
	public String getTagsFromPoolParty(File xmlFile) {
		List<String> tags = null;
		StringBuffer tagsSb = new StringBuffer("[");
		InputStream poolPartyPropsStream = this.getClass().getClassLoader()
				.getResourceAsStream("poolparty.properties");
		
		Properties poolPartyProps = new Properties();
		try {
			poolPartyProps.load(poolPartyPropsStream);
		} catch (IOException ioe) {
			LOG.error("Unable to load poolparty.properties", ioe);
			return null;
		}
		
		if (poolPartyManager == null) {
			poolPartyManager = new PoolPartyManagerImpl(poolPartyProps);
		}
		String text;
		try {
			
			String xslFile = poolPartyProps.getProperty("poolparty.extractTextXml");
			text = extractTextFields(xslFile, xmlFile.getAbsolutePath());
			tags = poolPartyManager.crawlText(text);
			if (tags != null) {
				for (int i = 0; i < tags.size(); i++) {
					String tag = tags.get(i);
					tagsSb.append(tag);
					if ((i + 1) < tags.size()) {
						tagsSb.append(",");
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Error while extracting text ", e);
		}
		
		
		tagsSb.append("]");
		return tagsSb.toString();
	}
	
	
	public String extractTextFields(String xslFile, String xmlFile) throws Exception {
		LOG.info("Extract text from " + xmlFile);
		XMLReader srcXmlReader = XMLReaderFactory.createXMLReader();
		EntityResolver lclEntityResolver = new CanadianFeedEntityResolver();
		srcXmlReader.setEntityResolver(lclEntityResolver);
		
		FileInputStream xslFileAsStream = new FileInputStream(xslFile);
		StreamSource styleSource = new StreamSource(xslFileAsStream);
		Transformer lclTransformer = factory
				.newTransformer(styleSource);
		InputSource inputSource = new InputSource(new FileInputStream(xmlFile));
		SAXSource saxSource = new SAXSource(srcXmlReader, inputSource);
		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);
		lclTransformer.transform(saxSource, result);
		return sw.getBuffer().toString();
		
	}
	public boolean tranform(String inputdir, String xslFileName, String contentFolder, String metaInfFolder, String idName, String workingDir, String packageName, Map<String, String> params)
			throws Exception {
		try {

			xmlReader = XMLReaderFactory.createXMLReader();
			entityResolver = new CanadianFeedEntityResolver();
			xmlReader.setEntityResolver(entityResolver);
			factory = TransformerFactory
					.newInstance();
			
			
			File directory = new File(inputdir);
			File workingDirectory = new File(workingDir);
			if (!workingDirectory.exists()) {
				workingDirectory.mkdirs();
			}
						
			FileInputStream xslFileAsStream = new FileInputStream(xslFileName);
			StreamSource stylesource = new StreamSource(xslFileAsStream);
			
			File[] directoryListing = directory.listFiles();
			File jcrRootDir = null;
			File metaInfDir = null;

			
			
			transformer = factory
					.newTransformer(stylesource);
			if (directoryListing != null) {
				boolean imageFlag = false;
				for (File child : directoryListing) {
					if (!child.getName().contains(".xml")) {
						continue;
					}
					
					LOG.debug(" in the directory" + child.getName());
					String fileName = workingDirectory.getAbsolutePath()
							+ File.separator
							+ child.getName().replace(".xml", ".jcr.xml");
					FileOutputStream fos = new FileOutputStream(fileName);
					InputSource inputSource = new InputSource(new FileInputStream(child));
					SAXSource saxSource = new SAXSource(xmlReader, inputSource);
					StreamResult result = new StreamResult(fos);
					String tags = getTagsFromPoolParty(child);
					
					transformer.setParameter("tags", tags);
					String damFolder = contentFolder.replaceAll("\\\\", "/");
					damFolder = damFolder.replaceAll("/content/", "/content/dam/");
					transformer.setParameter("damFolder", damFolder);
					/*
					 * if there are any parameters to send iterate them and add them to the transformer
					 */
					if (params != null) {
						Iterator<String> keySetIterator = params.keySet().iterator();
						while (keySetIterator.hasNext()) {
							String key = keySetIterator.next();
							String value = params.get(key);
							
							transformer.setParameter(key, value);
						}
					}
					transformer.transform(saxSource, result);
					fos.close();

					File xmlFile = new File(fileName);
					DocumentBuilderFactory documentFactory = DocumentBuilderFactory
							.newInstance();
					DocumentBuilder documentBuilder = documentFactory
							.newDocumentBuilder();
					Document doc = documentBuilder.parse(xmlFile);

					doc.getDocumentElement().normalize();

					Node jcrContentNode = doc.getElementsByTagName("jcr:content").item(0);
					Element jcrContentElement = (Element) jcrContentNode;
					String uniqueId = jcrContentElement.getAttribute(idName);
					String createdDate = jcrContentElement
							.getAttribute("createdDate");

					String year = createdDate.substring(0, 4);
					
					jcrRootDir = new File(workingDirectory.getAbsolutePath()
							+ File.separator + "jcr_root");
					if (!jcrRootDir.exists()) {
						jcrRootDir.mkdir();
					}

					File contentDepthDir = createContentStructure(jcrRootDir,
							contentFolder);

					// create year, month, day folder and move the xml file
					File yearDir = new File(contentDepthDir.getAbsolutePath()
							+ File.separator + year);

					if (!yearDir.exists()) {
						yearDir.mkdir();
					}

					String month = createdDate.substring(5, 7);

					File monthDir = new File(yearDir.getAbsolutePath()
							+ File.separator + month);
					if (!monthDir.exists()) {
						monthDir.mkdir();
					}

					String day = createdDate.substring(8, 10);

					File dayDir = new File(monthDir.getAbsolutePath()
							+ File.separator + day);
					if (!dayDir.exists()) {
						dayDir.mkdir();
					}

					File newsIdDir = new File(dayDir.getAbsoluteFile()
							+ File.separator + uniqueId);
					if (!newsIdDir.exists()) {
						newsIdDir.mkdir();
					}
					
					File newfile = new File(newsIdDir + File.separator + ".content.xml");
					xmlFile.renameTo(newfile);
					
					List<Element> elements = new ArrayList<Element>();
					getElementByAttributeValue(elements, "sling:resourceType", jcrContentNode, "tc/components/content/image");
					
					boolean imageResultFlag = false;
					if (params != null && params.containsKey("imageSourceDir")) {
						File imageSrcDir = new File(params.get("imageSourceDir"));
						imageResultFlag = processImageNodes(elements, newsIdDir, imageSrcDir, jcrRootDir);
					} else {
						imageResultFlag = processImageNodes(elements, newsIdDir, directory, jcrRootDir);
					}
					imageFlag = imageFlag || imageResultFlag;
					
					
				}
				// copy META-INF folder to outputdir
				metaInfDir = new File(metaInfFolder);
				if (metaInfDir.exists()) {
					FileUtils
							.copyDirectoryToDirectory(metaInfDir, workingDirectory);
				}
				/*if (jcrRootDir.exists()) {
					if (imageFlag) {
						// move /content/dam folder and zip up only the images
						File contentDamFolder = new File(jcrRootDir.getAbsolutePath() + File.separator + "content" + File.separator + "dam");
						File imagesFolder = new File(workingDirectory.getAbsolutePath() + File.separator + "images");
						contentDamFolder.renameTo(imagesFolder);
						
						
						 * This needs to be refactored, the objective here is to 
						 * determine the zip file name and also the folder to be zipped
						 
						String folders[] = imagesFolder.list(new FilenameFilter() {
							
							@Override
							public boolean accept(File dir, String name) {
								return dir.isDirectory();
							}
						});
						String zipFileName = "images.zip";
						if (folders != null) {
							zipFileName = folders[0];
							imagesFolder = new File(imagesFolder.getAbsolutePath() + File.separator + folders[0]);
						}
						
						folders = imagesFolder.list(new FilenameFilter() {
							
							@Override
							public boolean accept(File dir, String name) {
								return dir.isDirectory();
							}
						});
						if (folders != null) {
							imagesFolder = new File(imagesFolder.getAbsolutePath() + File.separator + folders[0]);
						}
						
						FileOutputStream imagesOs = new FileOutputStream(
								workingDirectory.getAbsolutePath() + File.separator
										 + zipFileName + ".zip");
						ZipOutputStream imagesZos = new ZipOutputStream(imagesOs);
						addDirToZipArchive(imagesZos, imagesFolder, null, false);
						
						imagesZos.close();
						imagesOs.close();					
						
					}
				}*/

			} else {
				LOG.info("no files in the directory");
			}
			try {

				FileOutputStream fos = new FileOutputStream(
						workingDirectory.getAbsolutePath() + File.separator
								+ packageName);

				ZipOutputStream zos = new ZipOutputStream(fos);

				addDirToZipArchive(zos, jcrRootDir, null, false);
				addDirToZipArchive(zos, metaInfDir, null, false);

				
				zos.close();

			} catch (IOException ioe) {
				LOG.error("Error creating zip file: ", ioe);
			}

		} catch (Exception e) {
			LOG.error("error in transform", e);
			throw e;
		}
		return true;
	
		
		
	}
	
	public boolean tranform(String inputdir, String xslFileName, String contentFolder, String metaInfFolder, String idName, String workingDir, String packageName)
			throws Exception {
		return tranform(inputdir, xslFileName, contentFolder, metaInfFolder, idName, workingDir, packageName, null);
	}
	
	protected boolean processImageNodes(List<Element> images, File leafFolder, File inputDir, File jcrRootDir) {
		boolean returnImageFlag = false;
		for (Element imageElement : images) {
			boolean imageFlag = false;
			String imageName = null;
			
			imageName = imageElement.getAttribute("fileReference");
			
			if (!StringUtils.isEmpty(imageName) && imageName.contains("/")) {
				imageName = imageName.substring(imageName.lastIndexOf("/") + 1);	
			}
			
			if(!StringUtils.isEmpty(imageName)) {
				imageFlag = true;
				returnImageFlag = true;
			}
			
			
			// create newsIdDir in /content/dam
			
			// copy the image file from the output folder to the corresponding news folder in dam
			if (imageFlag) {
				String contentDamFolder = leafFolder.getPath();
				contentDamFolder = contentDamFolder.replaceAll("\\\\", "/");
				contentDamFolder = contentDamFolder.substring(contentDamFolder.indexOf("/jcr_root/") + 9);
				contentDamFolder = contentDamFolder.replaceAll("/content/", "/content/dam/");
				createContentStructure(jcrRootDir, contentDamFolder);

				File srcImage = new File(inputDir.getAbsolutePath() + File.separator + imageName);
				File dstImage = new File(jcrRootDir.getAbsolutePath() + File.separator + contentDamFolder + File.separator + imageName);
				
				if (srcImage.exists()) {
					LOG.debug("moving "+ srcImage.getAbsolutePath() + " to " + dstImage.getAbsolutePath());
					//srcImage.renameTo(dstImage);
					try {
						FileUtils.copyFile(srcImage, dstImage);
					} catch (IOException e) {
						LOG.error("Error copy a file"+e);
					}
					
				}
			}
			
			
		}
		return returnImageFlag;
		
	}
	public void getElementByAttributeValue(List<Element> elements, String attributeName, Node rootElement, String attributeValue) {

	    if (rootElement != null && rootElement.hasChildNodes()) {
	        NodeList nodeList = rootElement.getChildNodes();

	        for (int i = 0; i < nodeList.getLength(); i++) {
	            Node subNode = nodeList.item(i);

	            if (subNode.hasAttributes()) {
	                NamedNodeMap nnm = subNode.getAttributes();

	                for (int j = 0; j < nnm.getLength(); j++) {
	                    Node attrNode = nnm.item(j);

	                    if (attrNode.getNodeType() == Node.ATTRIBUTE_NODE) {
	                        Attr attribute = (Attr) attrNode;
	                        if (StringUtils.equalsIgnoreCase(attribute.getName(), attributeName) ) {
		                        if (attributeValue.equals(attribute.getValue())) {
		                        	elements.add((Element)subNode);
		                            
		                        } else {
		                            getElementByAttributeValue(elements, attributeName, subNode, attributeValue);
		                        }
	                        	
	                        }
	                    }
	                }               
	            }
	        }
	    }

	    
	}
	/**
	 * This method creates whole contentFolder path under jcrRoot folder
	 * 
	 * @param root
	 * @param contentFolder
	 * @return
	 */
	private File createContentStructure(File root, String contentFolder) {
		String fileSeperator = "/";
		if (contentFolder.contains("\\")) {
			fileSeperator = "\\\\";
		}
		
		String paths[] = StringUtils.split(contentFolder,
				fileSeperator);
		File parent = root;
		if (paths != null && paths.length > 0) {
			for (int i = 0; i < paths.length; i++) {
				File curPath = new File(parent.getAbsolutePath()
						+ File.separator + paths[i]);
				if (!curPath.exists()) {
					curPath.mkdir();
				}
				parent = curPath;
			}
		}
		return parent;
	}

	
	public void addDirToZipArchive(ZipOutputStream zos, File fileToZip,
			String parrentDirectoryName, boolean skipParent) throws Exception {
		if (fileToZip == null || !fileToZip.exists()) {
			return;
		}

		String zipEntryName = fileToZip.getName();
		if (parrentDirectoryName != null && !parrentDirectoryName.isEmpty() && !skipParent) {
			zipEntryName = parrentDirectoryName + "/" + fileToZip.getName();
		}

		if (fileToZip.isDirectory()) {
			LOG.debug("+" + zipEntryName);
			for (File file : fileToZip.listFiles()) {
				if (!file.getName().contains(".zip")) {
					addDirToZipArchive(zos, file, zipEntryName, false);
				}

			}
		} else {
			LOG.debug("   " + zipEntryName);
			byte[] buffer = new byte[1024];
			FileInputStream fis = new FileInputStream(fileToZip);
			zos.putNextEntry(new ZipEntry(zipEntryName));
			int length;
			while ((length = fis.read(buffer)) > 0) {
				zos.write(buffer, 0, length);
			}
			zos.closeEntry();
			fis.close();
		}
	}

	

}
