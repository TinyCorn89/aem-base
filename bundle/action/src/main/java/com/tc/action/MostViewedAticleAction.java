/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFormatException;

import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.WCMException;
import com.day.cq.wcm.core.stats.PageViewStatistics;
import com.tc.action.BaseAction;
import com.tc.model.MostViewedArticleBean;
import com.tc.model.MostViewedBean;
import com.tc.util.Constants;

public class MostViewedAticleAction extends BaseAction {
 private static final Logger LOG = LoggerFactory.getLogger(MostViewedAticleAction.class);
	public MostViewedBean getMostViewedArticles() {
		MostViewedBean mostviewedBean = new MostViewedBean();
		int noOfArticle = 0;
		try {
			Node node = getCurrentNode();
			if (node.hasProperty("noOfArticles")) {
				noOfArticle = Integer.parseInt(node.getProperty("noOfArticles")
						.getString());
			}
			if (node.hasProperty("articlePath")) {
				mostviewedBean.setArticleParent(node.getProperty("articlePath").getString()+".html");
				Page page = getPage(node.getProperty("articlePath").getString());
				if (null != page) {
					mostviewedBean.setMostviewedArticleList(getArticleList(page, noOfArticle));
				}
			}
			if(node.hasProperty("header")){
				mostviewedBean.setHeader(node.getProperty("header").getString());
			}
			if(node.hasProperty("seeAllText")){
				mostviewedBean.setSeeAllText(node.getProperty("seeAllText").getString());
			}

		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}

		return mostviewedBean;

	}

	public int pageImpression(Page page) {
		final PageViewStatistics pwSvc = getSling().getService(
				PageViewStatistics.class);
		Object pageStatistics[] = null;
		String month = null;
		Node statsNode = null;
		int impression = 0;
		try {
			if (null != pwSvc) {
				if (null != pwSvc.report(page)) {
					pageStatistics = pwSvc.report(page);
					String pageStatisticsPath = pageStatistics[0].toString();
					String monthString = pageStatisticsPath.substring(0,
							pageStatisticsPath.length() - 3);
					Resource resource = getResource(monthString);
					if (resource != null) {
						statsNode = resource.adaptTo(Node.class);
						try {
							month = statsNode.getProperty(
									Constants.SHORTARTICLE.VIEWS).getString();
							impression = Integer.parseInt(month);
						} catch (ValueFormatException e) {
							LOG.error(e.getMessage(),e);
						} catch (PathNotFoundException e) {
							LOG.error(e.getMessage(),e);
						} catch (RepositoryException e) {
							LOG.error(e.getMessage(),e);
						}
					}
				}
			}
		} catch (WCMException e) {
			LOG.error(e.getMessage(),e);
		}
		return impression;
	}

	public Page getPage(String path) {
		Resource rootResource = getResource(path);
		Page rootPage = rootResource == null ? null : rootResource
				.adaptTo(Page.class);
		return rootPage;
	}

	public Resource getResource(String path) {
		Resource resource = getSlingRequest().getResourceResolver()
				.getResource(path);
		return resource;

	}

	public List<MostViewedArticleBean> getArticleList(Page rootPage, int noOfArticle) {
		List<MostViewedArticleBean> mostviewedArticleList = new ArrayList<MostViewedArticleBean>();
		Map<Page, Integer> map = new HashMap<Page, Integer>();
		Iterator<Page> children = rootPage.listChildren();
		Page childPage = null;
		while (children.hasNext()) {
			childPage = children.next();
			map.put(childPage, pageImpression(childPage));
		}
		if (noOfArticle < 1) {
			noOfArticle = map.size();
		}
		Map<Page, Integer> map1 = sortByValues(map);
		int temp = 0;
		MostViewedArticleBean mostveiViewedArticleBean = null;
		for (Map.Entry<Page, Integer> entry : map1.entrySet()) {
			if (temp < noOfArticle) {
				mostveiViewedArticleBean = new MostViewedArticleBean();
				Page page = entry.getKey();
				mostveiViewedArticleBean.setTitle(page.getTitle());
				mostveiViewedArticleBean.setArticlePath(page.getPath()
						+ ".html");
				mostviewedArticleList.add(mostveiViewedArticleBean);
				temp++;

			} else {
				break;
			}

		}

		return mostviewedArticleList;
	}

	private static HashMap<Page, Integer> sortByValues(Map<Page, Integer> map) {
		List list = new LinkedList(map.entrySet());
		// Defined Custom Comparator here
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o2)).getValue())
						.compareTo(((Map.Entry) (o1)).getValue());
			}
		});

		// Here I am copying the sorted list in HashMap
		// using LinkedHashMap to preserve the insertion order
		HashMap sortedHashMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedHashMap.put(entry.getKey(), entry.getValue());
		}
		return sortedHashMap;
	}
}
