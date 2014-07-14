/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Value;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import javax.jcr.Session;

import org.apache.jackrabbit.commons.JcrUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tc.action.BaseAction;
import com.tc.model.LatestArticlesBean;
import com.tc.model.ListOfArticlesBean;
import com.tc.model.ArticleBean;
import com.tc.model.ListOfCPArticlesBean;

import com.tc.util.Constants;

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
	
	public ListOfCPArticlesBean getCPLatestArticles() throws RepositoryException {
		LOG.info("Entered getCPLatestArticles method");
		
		ArticleBean articleBean = null;
		List<ArticleBean> listOfArticles = new ArrayList<ArticleBean>();
		ListOfCPArticlesBean listOfArticlesBean = new ListOfCPArticlesBean();
		List<ArticleBean> listOfLatestArticles = new ArrayList<ArticleBean>();
		Node currentNode = getCurrentNode();
		int itemsPerPage = 0;
		int count = 0;
		String sections = null;
		String provider = null;
		if(currentNode != null) {
			if(currentNode.hasProperty("itemsPerPage")) {
				itemsPerPage = Integer.parseInt(currentNode.getProperty("./itemsPerPage").getString());
			}
			if(currentNode.hasProperty("sections")) {
				sections =currentNode.getProperty("sections").getString();
			}
			if(currentNode.hasProperty("provider")) {
				provider =currentNode.getProperty("provider").getString();
			}
			
			Session session = getSlingRequest().getResourceResolver().adaptTo(Session.class);
			QueryManager queryManager = session.getWorkspace().getQueryManager();
			
			StringBuilder queryString = new StringBuilder("SELECT p.* FROM [nt:base] AS p WHERE ISDESCENDANTNODE(p,[/content/dam/tc/cp])");
			//share on 
			queryString.append(" AND (p.[tagShare] LIKE '%"+provider+"%' OR p.[tagShare] LIKE '%share:global%')");
			// Article Section
			if (sections!=null) {
				queryString.append(" AND p.[tagSections] = '" + sections + "'");
			}			
			queryString.append(" AND p.[sling:resourceType] = 'tc/components/content/cp/news-article'");

			queryString.append(" ORDER BY p.[publicationDate] DESC");
			LOG.info(queryString.toString());
			Query query = queryManager.createQuery(queryString.toString(), Query.JCR_SQL2);
			QueryResult result = query.execute();
			if (result != null) {
				for (Node node : JcrUtils.getNodes(result)) {
					articleBean = new ArticleBean();
					if (node.hasProperty(Constants.ARTICLE.TITLE)) {
						articleBean.setTitle(node.getProperty(Constants.ARTICLE.TITLE).getString());
					}
					if (node.hasProperty(Constants.ARTICLE.SHARE)) {
						articleBean.setShare(getTags(Constants.ARTICLE.SHARE));
					}
					if (node.hasProperty(Constants.ARTICLE.CATEGORIES)) {
						articleBean.setCategories(getTags(Constants.ARTICLE.CATEGORIES));
					}
					if (node.hasProperty(Constants.ARTICLE.SECTIONS)) {
						articleBean.setSections(getTags(Constants.ARTICLE.SECTIONS));
					}
					if (node.hasProperty(Constants.ARTICLE.PUBLICATION_DATE)) {
						Date pubDate = parseDate(convert(node.getProperty(Constants.ARTICLE.PUBLICATION_DATE).getString()));
						articleBean.setPublicationDate(getDateAsString(pubDate));
					}
					if (node.hasProperty(Constants.ARTICLE.EXPIRATION_DATE)) {
						Date expDate = parseDate(convert(node.getProperty(Constants.ARTICLE.EXPIRATION_DATE).getString()));
						articleBean.setExpirationDate(getDateAsString(expDate));
					}
					listOfArticles.add(articleBean);
				}
				Collections.sort(listOfArticles, ArticleBean.DateComparator);
				for (Object temp : listOfArticles) {
					if(count < itemsPerPage) {
						ArticleBean articleBean2 = (ArticleBean) temp;
						listOfLatestArticles.add(articleBean2);
					}
					else{
						break;
					}
					count++;
				}
				listOfArticlesBean.setListOfLatestArticles(listOfLatestArticles);
				
				
			}
		}
		
		
		return listOfArticlesBean;
	}
	
    private Collection<String> getTags(String property) {
    	
	    Node node = getCurrentNode();
	    Collection<String> tags = new ArrayList<String>();
	    Value[] values = null;
	    try {
			values = node.getProperty(property).getValues();
			 for (Value tagValue : values) {
                 tags.add(tagValue.getString());
			 }
	
        } catch (Exception e) {

            e.printStackTrace();
        }
	
		return tags;
    }
   
    private String convert(String dateAndTime) {
        String date = dateAndTime.substring(0, dateAndTime.indexOf("T"));
        String time = dateAndTime.substring(dateAndTime.indexOf("T") + 1, dateAndTime.indexOf("."));
        String fromDate = date.concat(" ").concat(time);
        return fromDate;
    }
    
    private Date parseDate(String date) {

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.parse(date);
        } catch (java.text.ParseException e) {
            return new Date(0);
        }
    }
    private String getDateAsString(Date date) {

		if (date == null){
			return "";
		}
		DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.CANADA);
		return df.format(date);
	}
		
}
