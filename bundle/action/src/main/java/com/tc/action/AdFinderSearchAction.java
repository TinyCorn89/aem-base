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
	private static final Logger LOG = LoggerFactory
			.getLogger(AdFinderSearchAction.class);

	public AdFinderSearchBean getAdFinderSearchDetails() {
		LOG.info("Entered getAdFinderSearchDetails method");
		AdFinderSearchBean adFinderSearchBean = new AdFinderSearchBean();
		Node currentNode = getCurrentNode();

		if (currentNode != null) {
			try {
				if (currentNode.hasProperty("titleLabel")) {
					adFinderSearchBean.setTitle(currentNode.getProperty(
							"titleLabel").getString());
				}
				if (currentNode.hasProperty("keywordsLabel")) {
					adFinderSearchBean.setKeywordsLabel(currentNode
							.getProperty("keywordsLabel").getString());
				}
				if (currentNode.hasProperty("dateLabel")) {
					adFinderSearchBean.setDateLabel(currentNode.getProperty(
							"dateLabel").getString());
				}
				if (currentNode.hasProperty("buttonLabel")) {
					adFinderSearchBean.setButtonLabel(currentNode.getProperty(
							"buttonLabel").getString());
				}
				if (currentNode.hasProperty("advertiserLabel")) {
					adFinderSearchBean.setAdvertiserLabel(currentNode
							.getProperty("advertiserLabel").getString());
				}
				if (currentNode.hasProperty("adLabel")) {
					adFinderSearchBean.setAdLabel(currentNode.getProperty(
							"adLabel").getString());
				}
				if (currentNode.hasProperty("searchPath")) {
					adFinderSearchBean.setSearchPath(currentNode.getProperty(
							"searchPath").getString());
				}
			} catch (RepositoryException e) {
				LOG.error("Error is", e);
			}

		}
		return adFinderSearchBean;
	}
}
