/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tc.action.BaseAction;
import com.tc.model.LatestArticlesBean;
import com.tc.model.ListOfArticlesBean;

/**
 * The Class LatestArticlesAction.
 *
 * @author gdinakar
 */
public class LatestArticlesAction extends BaseAction {
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(LatestArticlesAction.class);
	
	/**
	 * Gets the latest articles.
	 *
	 * @return the latest articles
	 * @throws RepositoryException the repository exception
	 */
	public ListOfArticlesBean getLatestArticles() throws RepositoryException {
		LOG.info("Entered getLatestArticles method");
		
		LatestArticlesBean latestArticlesBean = null;
		List<LatestArticlesBean> listOfArticles = new ArrayList<LatestArticlesBean>();
		ListOfArticlesBean listOfArticlesBean = new ListOfArticlesBean();
		List<LatestArticlesBean> listOfLatestArticles = new ArrayList<LatestArticlesBean>();
		int count = 0;
		
		String articleTitle = null;
		String articlePath = null;
		Date dateOfCreation = null;
		
		Node currentNode = getCurrentNode();
		
		String parentPagePath = null;
		int numberOfItems = 0;
		String seeAllLinkText = null;
		
		if(currentNode != null) {
			if(currentNode.hasProperty("parentPage")) {
				parentPagePath =currentNode.getProperty("./parentPage").getString();
			}
			
			if(currentNode.hasProperty("numberOfItems")) {
				numberOfItems = Integer.parseInt(currentNode.getProperty("./numberOfItems").getString());
			}
			
			if(currentNode.hasProperty("seeAllLinkText")) {
				seeAllLinkText = currentNode.getProperty("./seeAllLinkText").getString();
			}
			Node parentNode = getSlingRequest().getResourceResolver().getResource(parentPagePath).adaptTo(Node.class);
			if(parentNode.hasNodes()) {
				NodeIterator childNodes = parentNode.getNodes();
				Node childNode = null;
				Node jcrNode = null;
				while(childNodes.hasNext()){
					childNode = (Node) childNodes.next();
					if(!childNode.getName().equals("jcr:content")) {
						latestArticlesBean = new LatestArticlesBean();
						if(childNode.hasNode("jcr:content")) {
							jcrNode = childNode.getNode("jcr:content");
							if(jcrNode.hasProperty("jcr:title")) {
								articleTitle = jcrNode.getProperty("jcr:title").getString();
							}
						}
						articlePath = childNode.getPath()+".html";
						if(childNode.hasProperty("jcr:created")) {
							String timeStamp = childNode.getProperty("jcr:created").getString();
							String date = timeStamp.substring(0, 10);
							String time = timeStamp.substring(11, 19);
							String year = date.substring(0, 4);
							String month = date.substring(5, 7);
							String day = date.substring(8, 10);
							int hours = Integer.parseInt(time.substring(0, 2));
							String minutes = time.substring(3, 5);
							String seconds = time.substring(6, 8);
							String marker;
							if(hours >= 12) {
								marker = "PM";
								hours = hours - 12;
							} else {
								marker = "AM";
							}
							String dateInString = month+" "+day+", "+year+" "+hours+":"+minutes+":"+seconds+" "+marker;
							SimpleDateFormat formatter = new SimpleDateFormat("MM dd, yyyy HH:mm:ss a");
							try {
								dateOfCreation = formatter.parse(dateInString);
							} catch (ParseException e) {
								e.printStackTrace();
							}
							latestArticlesBean.setArticleTitle(articleTitle);
							latestArticlesBean.setArticlePath(articlePath);
							latestArticlesBean.setDateOfCreation(dateOfCreation);
							listOfArticles.add(latestArticlesBean);
						}
					}
				}
			}
			Collections.sort(listOfArticles, LatestArticlesBean.DateComparator);
			for (Object temp : listOfArticles) {
				if(count < numberOfItems) {
					LatestArticlesBean latestArticlesBean2 = (LatestArticlesBean) temp;
					listOfLatestArticles.add(latestArticlesBean2);
				}
				else{
					break;
				}
				count++;
			}
			listOfArticlesBean.setParentPagePath(parentPagePath+".html");
			listOfArticlesBean.setSeeAllLinkText(seeAllLinkText);
			listOfArticlesBean.setListOfLatestArticles(listOfLatestArticles);
		}
		return listOfArticlesBean;
	}
}
