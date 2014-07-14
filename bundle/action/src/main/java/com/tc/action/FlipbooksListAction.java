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

import com.tc.model.FlipbooksListBean;
import com.tc.model.PDFBean;

/**
 * @author gdinakar
 *
 */
public class FlipbooksListAction extends BaseAction {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(FlipbooksListAction.class);
	
	/**
	 * Gets the flipbooks.
	 *
	 * @return the flipbooks
	 */
	public FlipbooksListBean getFlipbooks() {
		LOG.info("Entered getFlipbooks method");
		FlipbooksListBean flipbooksListBean = null;
		List<PDFBean> listOfPDFs = null;
		PDFBean pdfBean = null;
		Node currentNode = getCurrentNode();
		String categoryName = null;
		String flipbookPath = null;
		if(currentNode != null) {
			try {
				flipbooksListBean = new FlipbooksListBean();
				if(currentNode.hasProperty("categoryName")) {
					categoryName = currentNode.getProperty("./categoryName").getString();
					flipbooksListBean.setCategoryName(categoryName);
				}
				if(currentNode.hasProperty("flipbookPath")) {
					flipbookPath = currentNode.getProperty("./flipbookPath").getString();
				}
				if(currentNode.hasNode("flipbooks")){
					Node flipbooksNode = currentNode.getNode("flipbooks");
					NodeIterator flipbooksNodes = flipbooksNode.getNodes();
					listOfPDFs = new ArrayList<PDFBean>();
					Node flipbookNode = null;
					while(flipbooksNodes.hasNext()){
						flipbookNode =  flipbooksNodes.nextNode();
						pdfBean = new PDFBean();
						if(flipbookNode.hasProperty("title")) {
							pdfBean.setTitle(flipbookNode.getProperty("title").getString());
						}
						if(flipbookNode.hasProperty("subTitle")) {
							pdfBean.setSubTitle(flipbookNode.getProperty("subTitle").getString());
						}
						if(flipbookNode.hasProperty("pdfPath")) {
							String pdfPath = flipbookNode.getProperty("pdfPath").getString();
							String tempPath = pdfPath;
							tempPath = tempPath.replace("/content", "");
							tempPath = tempPath.replaceAll("/", ".");
							tempPath = flipbookPath+tempPath+".html";
							pdfBean.setPdfPath(tempPath);
							Node pdfNode = getSlingRequest().getResourceResolver().getResource(pdfPath).adaptTo(Node.class);
							if(pdfNode.hasNode("jcr:content") && pdfNode.getNode("jcr:content").hasNode("renditions") && pdfNode.getNode("jcr:content").getNode("renditions").hasNode("cq5dam.thumbnail.48.48.png")) {
								pdfBean.setPdfThumbnail(pdfNode.getNode("jcr:content").getNode("renditions").getNode("cq5dam.thumbnail.48.48.png").getPath().toString());
							}
						}
						listOfPDFs.add(pdfBean);
					}
					flipbooksListBean.setListOfPDFs(listOfPDFs);
				}
				
				
			} catch (ValueFormatException e) {
				e.printStackTrace();
			} catch (PathNotFoundException e) {
				e.printStackTrace();
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
		}
		return flipbooksListBean;
	}
}
