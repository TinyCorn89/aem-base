/**
 * 
 */
package com.tc.action;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFormatException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tc.model.AdFinderSearchBean;

/**
 * The Class AdFinderSearchAction.
 *
 * @author gdinakar
 */
public class AdFinderSearchAction extends BaseAction {
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(AdFinderSearchAction.class);
	
	public AdFinderSearchBean getAdFinderSearchDetails() {
		LOG.info("Entered getAdFinderSearchDetails method");
		AdFinderSearchBean adFinderSearchBean = null;
		Node currentNode = getCurrentNode();
		
		String keywords = null;
		String category = null;
		String newspapaers = null;
		String timePeriod = null;
		
		if(currentNode != null){
			 try {
				if(currentNode.hasProperty("keywords")) {
					 keywords = currentNode.getProperty("./keywords").getString();
				 }
				if(currentNode.hasProperty("category")) {
					category = currentNode.getProperty("./category").getString();
				 }
				if(currentNode.hasProperty("newspapaers")) {
					newspapaers = currentNode.getProperty("./newspapaers").getString();
				 }
				if(currentNode.hasProperty("timePeriod")) {
					timePeriod = currentNode.getProperty("./timePeriod").getString();
				 }
				adFinderSearchBean = new AdFinderSearchBean();
				adFinderSearchBean.setKeywords(keywords);
				adFinderSearchBean.setCategory(category);
				adFinderSearchBean.setNewspapaers(newspapaers);
				adFinderSearchBean.setTimePeriod(timePeriod);
			} catch (ValueFormatException e) {
				LOG.error("ValueFormatException:"+e);
			} catch (PathNotFoundException e) {
				e.printStackTrace();
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
		}

		return adFinderSearchBean;
	}
}
