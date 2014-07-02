/**
 * 
 */
package com.tc.action;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFormatException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tc.model.FlipbookBean;

/**
 * The Class FlipbookAction.
 *
 * @author gdinakar
 */
public class FlipbookAction extends BaseAction {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(FlipbookAction.class);
	
	public FlipbookBean getImagesPaths() {
		LOG.info("Entered getImagesPaths method");
		FlipbookBean flipbookBean = null;
		Node currentNode = getCurrentNode();
		String parentPagePath = null;
		List<String> imagesPathList = null;
		if(currentNode != null) {
			try {
				if(currentNode.hasProperty("parentPage")) {
					parentPagePath = currentNode.getProperty("./parentPage").getString();
					Node imagesParentNode = getSlingRequest().getResourceResolver().getResource(parentPagePath).adaptTo(Node.class);
					NodeIterator childNodes = imagesParentNode.getNodes();
					Node imageNode = null;
					String imageName = null;
					String imagePath = null;
					flipbookBean = new FlipbookBean();
					imagesPathList = new ArrayList<String>();
					while(childNodes.hasNext()) {
						imageNode = childNodes.nextNode();
						imageName = imageNode.getName();
						if(!imageName.equals("jcr:content")) {
							imagePath = imageNode.getPath();
							imagesPathList.add(imagePath);
						}
					}
					flipbookBean.setImagesPathList(imagesPathList);
				}
			} catch (ValueFormatException e) {
				e.printStackTrace();
			} catch (PathNotFoundException e) {
				e.printStackTrace();
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
		}
		return flipbookBean;
	}
}
