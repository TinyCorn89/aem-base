package com.tc.process.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.tc.process.pressfeed.CanadianFeedEntityResolver;



public class TCNewLetterTransformerHandler {
	static Logger LOG = Logger.getLogger(TCNewLetterTransformerHandler.class
			.getName());
	private TransformerFactory factory = null;
	private Transformer transformer;
	private XMLReader xmlReader;
	private EntityResolver entityResolver;

	public boolean tranform(String inputdir, String xslFileName)
			throws Exception {
		try {

			xmlReader = XMLReaderFactory.createXMLReader();
			entityResolver = new CanadianFeedEntityResolver();
			xmlReader.setEntityResolver(entityResolver);
			factory = TransformerFactory
					.newInstance();
			
			
			File directory = new File(inputdir);
			File OutDirectory = new File(directory.getAbsolutePath()
					+ File.separator + "output");
			if (!OutDirectory.exists()) {
				OutDirectory.mkdir();
			}
			// TODO below is a hack to avoid dtd errors .need to find a solution
			InputStream in = this.getClass().getClassLoader()
					.getResourceAsStream("CPNewsML.dtd");

			OutputStream os = new FileOutputStream(directory.getAbsolutePath()
					+ File.separator + File.separator + "CPNewsML.dtd");

			byte[] buffer = new byte[1024];
			int bytesRead;
			// read from is to buffer
			while ((bytesRead = in.read(buffer)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			in.close();
			os.close();
			
			// do the same thing for .xsl file as well .Some reason it is not
			// working if the file is not present in the processed dir.
			InputStream inXsl = this.getClass().getClassLoader()
					.getResourceAsStream("CPNewsML.xsl");

			OutputStream outXsl = new FileOutputStream(directory.getAbsolutePath()
					+ File.separator + File.separator + "CPNewsML.xsl");

			byte[] bufferXsl = new byte[1024];
			int bytesReadXsl;
			// read from is to buffer
			while ((bytesReadXsl = inXsl.read(bufferXsl)) != -1) {
				outXsl.write(bufferXsl, 0, bytesReadXsl);
			}
			inXsl.close();
			outXsl.close();
			
			FileInputStream xslFileAsStream = new FileInputStream(directory.getAbsolutePath()
					+ File.separator + File.separator + "CPNewsML.xsl");
			StreamSource stylesource = new StreamSource(xslFileAsStream);
			
			File[] directoryListing = directory.listFiles();
			File jcrRootDir = null;
			File metaInfDir = null;
			InputStream aemInputSt = this.getClass().getClassLoader()
					.getResourceAsStream("aem.properties");
			Properties aemProps = new Properties();
			aemProps.load(aemInputSt);

			String metaInfFolder = aemProps.getProperty("aem.metainffolder");
			String contentFolder = aemProps.getProperty("aem.contentfolder");
			
			transformer = factory
					.newTransformer(stylesource);
			if (directoryListing != null) {
				for (File child : directoryListing) {
					if (!child.getName().contains(".xml")) {
						continue;
					}
					
					LOG.info(" in the directory" + child.getName());
					String fileName = OutDirectory.getAbsolutePath()
							+ File.separator
							+ child.getName().replace(".xml", ".jcr.xml");
					FileOutputStream fos = new FileOutputStream(fileName);
					InputSource inputSource = new InputSource(new FileInputStream(child));
					SAXSource saxSource = new SAXSource(xmlReader, inputSource);
					StreamResult result = new StreamResult(fos);
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
					String newsId = jcrContentElement.getAttribute("newsId");
					String createdDate = jcrContentElement
							.getAttribute("createdDate");
					
					Node cpLinkNode = doc.getElementsByTagName("cplink").item(0);
					String imageName = null;
					if (cpLinkNode != null) {
						Element cpLinkElement = (Element) cpLinkNode;
						imageName = cpLinkElement.getAttribute("image");
						if (imageName.contains("/")) {
							imageName = imageName.substring(imageName.indexOf("/") + 1);	
						}
					}

					String year = createdDate.substring(0, 4);
					
					jcrRootDir = new File(OutDirectory.getAbsolutePath()
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
							+ File.separator + newsId);
					if (!newsIdDir.exists()) {
						newsIdDir.mkdir();
					}
					
					File newfile = new File(newsIdDir + File.separator + ".content.xml");
					xmlFile.renameTo(newfile);
					
					// create newsIdDir in /content/dam
					String contentDamFolder = newsIdDir.getPath();
					contentDamFolder = contentDamFolder.replaceAll("\\\\", "/");
					contentDamFolder = contentDamFolder.substring(contentDamFolder.indexOf("/jcr_root/") + 9);
					contentDamFolder = contentDamFolder.replaceAll("/content/", "/content/dam/");
					createContentStructure(jcrRootDir, contentDamFolder);
					
					// copy the image file from the output folder to the corresponding news folder in dam
					if (imageName != null) {
						File srcImage = new File(OutDirectory.getAbsolutePath() + File.separator + imageName);
						File dstImage = new File(jcrRootDir.getAbsolutePath() + File.separator + contentDamFolder + File.separator + imageName);
						LOG.info("moving "+ srcImage.getAbsolutePath() + " to " + dstImage.getAbsolutePath());
						if (srcImage.exists()) {
							srcImage.renameTo(dstImage);
						}
					}
				}
				// copy META-INF folder to outputdir
				metaInfDir = new File(metaInfFolder);
				if (metaInfDir.exists()) {
					FileUtils
							.copyDirectoryToDirectory(metaInfDir, OutDirectory);
				}
				if (jcrRootDir.exists()) {
					// move /content/dam folder and zip up only the images
					File contentDamFolder = new File(jcrRootDir.getAbsolutePath() + File.separator + "content" + File.separator + "dam");
					File imagesFolder = new File(OutDirectory.getAbsolutePath() + File.separator + "images");
					contentDamFolder.renameTo(imagesFolder);
					
					/*
					 * This needs to be refactored, the objective here is to 
					 * determine the zip file name and also the folder to be zipped
					 */
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
							OutDirectory.getAbsolutePath() + File.separator
									 + zipFileName + ".zip");
					ZipOutputStream imagesZos = new ZipOutputStream(imagesOs);
					addDirToZipArchive(imagesZos, imagesFolder, null, false);
					
					imagesZos.close();
					imagesOs.close();					
				}

				

			} else {
				LOG.info("no files in the directory");
			}
			try {

				FileOutputStream fos = new FileOutputStream(
						OutDirectory.getAbsolutePath() + File.separator
								+ OutDirectory.getName() + ".zip");

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
