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
import javax.jcr.NodeIterator;
import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.day.cq.wcm.api.Page;
import com.tc.action.BaseAction;
import com.tc.model.MostCommentedArticleBean;
import com.tc.model.MostViewedArticleBean;

public class MostCommentedArticleAction extends BaseAction {
	private static final Logger LOG = LoggerFactory
			.getLogger(MostCommentedArticleAction.class);
	private String NO_OF_ARTICLES = "noOfArticles";
	private String ROOT_PATH = "rootPath";
	private String HTML = ".html";
	private String HEADER_TEXT = "header";
	private String FOOTER_TEXT = "seeAllText";
	private String USER_AGENT_PATH = "/content/usergenerated";
	private String JCRCONTENT = "jcr:content";
	private String INDEX = "index";
	private String KEY = "key";
	private String COUNTER = "counter";
	private String TOTAL_COUNT = "totalCount";

	public MostCommentedArticleBean getMostCommentedArticle() {
		MostCommentedArticleBean mostCommentedBean = new MostCommentedArticleBean();
		String pagePath = " ";
		int noOfArticle = 0;
		try {
			Node node = getCurrentNode();
			if (node.hasProperty(NO_OF_ARTICLES)) {
				noOfArticle = Integer.parseInt(node.getProperty(NO_OF_ARTICLES)
						.getString());
			}
			if (node.hasProperty(ROOT_PATH)) {
				pagePath = node.getProperty(ROOT_PATH).getString();
				mostCommentedBean.setArticleParent(pagePath + HTML);
				Page page = getPage(pagePath);
				if (null != page) {
					mostCommentedBean.setMostviewedArticleList(getArticleList(
							page, noOfArticle));
				}
			}
			if (node.hasProperty(HEADER_TEXT)) {
				mostCommentedBean.setHeader(node.getProperty(HEADER_TEXT)
						.getString());
			}
			if (node.hasProperty(FOOTER_TEXT)) {
				mostCommentedBean.setSeeAllText(node.getProperty(FOOTER_TEXT)
						.getString());
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return mostCommentedBean;

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

	public int commentsCount(String pagePath) {
		int count = 0;
		try {
			Page page = getPage(USER_AGENT_PATH + pagePath);
			Node pageNode = page.adaptTo(Node.class);
			if (pageNode.hasNode(JCRCONTENT)) {
				Node jcrNode = pageNode.getNode(JCRCONTENT);
				NodeIterator nodeIterator = jcrNode.getNodes();
				while (nodeIterator.hasNext()) {
					Node node = nodeIterator.nextNode();
					if (node.getName().contains(INDEX)) {
						if (node.hasNode(KEY)) {
							Node keyNode = node.getNode(KEY);
							if (keyNode.hasNode(COUNTER)) {
								Node cunterNode = keyNode.getNode(COUNTER);
								if (null != cunterNode) {
									count = Integer.parseInt(cunterNode
											.getProperty(TOTAL_COUNT)
											.getString());
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return count;
	}

	public List<MostViewedArticleBean> getArticleList(Page rootPage,
			int noOfArticle) {
		List<MostViewedArticleBean> mostviewedArticleList = new ArrayList<MostViewedArticleBean>();
		Map<Page, Integer> map = new HashMap<Page, Integer>();
		Iterator<Page> children = rootPage.listChildren();
		Page childPage = null;
		while (children.hasNext()) {
			childPage = children.next();
			map.put(childPage, commentsCount(childPage.getPath()));
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
						+ HTML);
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
