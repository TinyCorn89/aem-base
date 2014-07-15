/**
 * 
 */
package com.tc.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tc.model.FlipbookBean;

/**
 * The Class DynamicFlipbookAction.
 *
 * @author gdinakar
 */
public class DynamicFlipbookAction extends BaseAction {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(DynamicFlipbookAction.class);
	
	/**
	 * Gets the images paths.
	 *
	 * @return the images paths
	 * @throws RepositoryException the repository exception
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("deprecation")
	public FlipbookBean getImagesPaths() throws RepositoryException, UnsupportedEncodingException {
		LOG.info("Entered getImagesPaths method of DynamicFlipbookAction");
		
		FlipbookBean flipbookBean = null;
		List<String> imagesPathList = null;
		List<String> textFilesPathList = null;
		List<String> imagesMetaDataList = null;
		String currentPageURL = getSlingRequest().getRequestURL().toString();
		Node currentNode = getCurrentNode();
		String pdfPath = null;
		if(currentNode != null) {
			
			int lastIndexOfSlash = currentPageURL.lastIndexOf("/");
			String tempURL = currentPageURL.substring((lastIndexOfSlash+1), currentPageURL.length()).replace(".html", "");
			tempURL = tempURL.replaceAll("\\.", "/");
			tempURL = tempURL.substring(tempURL.indexOf("/"), tempURL.length());
			tempURL = tempURL.replace("/pdf", ".pdf");
			pdfPath = "/content"+tempURL;
			if(pdfPath.contains("%20")) {
				pdfPath = pdfPath.replaceAll("%20", " ");
			} 
			Node pdfNode = getSlingRequest().getResourceResolver().getResource(pdfPath).adaptTo(Node.class);
			String pdfName = pdfNode.getName().replace(".pdf", "");
			final String pdfImagePath = pdfPath+"/jcr:content/renditions/"+pdfName+"_image";
			if(pdfNode.hasNode("jcr:content") && pdfNode.getNode("jcr:content").hasNode("renditions")) {
				Node renditionsNode = pdfNode.getNode("jcr:content").getNode("renditions");
				if(renditionsNode.hasNodes()) {
					NodeIterator imageNodes = renditionsNode.getNodes();
					Node imageNode = null;
					String imageName = null;
					String imagePath = null;
					flipbookBean = new FlipbookBean();
					imagesPathList = new ArrayList<String>();
					textFilesPathList = new ArrayList<String>();
					String textFilePath = null;
					while(imageNodes.hasNext()) {
						imageNode = imageNodes.nextNode();
						imageName = imageNode.getName();
						if(imageName.endsWith(".jpg")) {
							imagePath = imageNode.getPath();
							imagesPathList.add(imagePath);
							textFilePath = imageNode.getPath().replace(".jpg", ".txt");
							textFilesPathList.add(textFilePath);
						}
					}
					// Sorting images based on image number
					Collections.sort(imagesPathList, new Comparator<String>() {

						@Override
						public int compare(String imagePath1, String imagePath2) {
							String tempImagePath1 = imagePath1.replaceAll(pdfImagePath, "");
							String finalImagePath1 = tempImagePath1.replaceAll(".jpg", "");
							String tempImagePath2 = imagePath2.replaceAll(pdfImagePath, "");
							String finalImagePath2 = tempImagePath2.replaceAll(".jpg", "");
							return new Integer(finalImagePath1).compareTo(new Integer(finalImagePath2));
						}
						
					});
					
					// Sorting text files based on image number
					Collections.sort(textFilesPathList, new Comparator<String>() {

						@Override
						public int compare(String textFilePath1, String textFilePath2) {
							String tempTextFilePath1 = textFilePath1.replaceAll(pdfImagePath, "");
							String finalTextFilePath1 = tempTextFilePath1.replaceAll(".txt", "");
							String tempTextFilePath2 = textFilePath2.replaceAll(pdfImagePath, "");
							String finalTextFilePath2 = tempTextFilePath2.replaceAll(".txt", "");
							return new Integer(finalTextFilePath1).compareTo(new Integer(finalTextFilePath2));
						}
						
					});
					
					
					imagesMetaDataList = new ArrayList<String>();
					
					// Reading the text from AEM .txt files
					for (String temp: textFilesPathList) {
						Resource textFileResource = getSlingRequest().getResourceResolver().getResource(temp);
						Node textFileNode = textFileResource.adaptTo(Node.class);
						InputStream inputStream = null;
						if(textFileNode.hasNode("jcr:content") && textFileNode.getNode("jcr:content").hasProperty("jcr:data")) {
							inputStream = (InputStream) textFileNode.getNode("jcr:content").getProperty("jcr:data").getStream();
							InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
							BufferedReader reader = null;
							reader = new BufferedReader(inputStreamReader);
					        String line = null;
					        String imageMetaData = "";
							try {
								while ((line = reader.readLine()) != null) {
								    imageMetaData += line;
								}
								imagesMetaDataList.add(imageMetaData);
								reader.close();
								LOG.info("imageMetaData:temp:"+imageMetaData);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
					flipbookBean.setImagesPathList(imagesPathList);
					flipbookBean.setImagesMetaDataList(imagesMetaDataList);
				}
			} else {
				LOG.info(pdfNode+" node might not contain /jcr:content/renditions node");
			}
		}
		return flipbookBean;
	}
}
