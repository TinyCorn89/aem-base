/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.Value;

import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.tagging.Tag;
import com.day.cq.wcm.api.Page;
import com.tc.action.BaseAction;
import com.tc.model.FeaturedBean;
import com.tc.model.MostViewedArticleBean;

public class FeaturedAction  extends BaseAction{
	
	private static final Logger LOG = LoggerFactory.getLogger(FeaturedAction.class);
	public FeaturedBean getFeatures(){
		FeaturedBean featuredBean =  new FeaturedBean();
		String tagName="";
		int noOfArticles = 0;
		try{
			Node node = getCurrentNode();
			if (node.hasProperty("tagName")) {
				 Property references = node.getProperty("tagName");   
				Value[] values = references.getValues();
				String[] parts = values[0].getString().split(":");
				tagName= parts[1];  
			}
			if(node.hasProperty("noOfArticles")){
				noOfArticles = Integer.parseInt(node.getProperty("noOfArticles")
						.getString());
			}
			if (node.hasProperty("rootPath")) {
				Page page = getPage(node.getProperty("rootPath").getString());
				featuredBean.setParentPath(node.getProperty("rootPath").getString()+".html");
				if (null != page) {
					featuredBean.setMostviewedArticleList(getFeaturedPages(page,tagName,noOfArticles));
				}
			}
			if(node.hasProperty("header")){
				featuredBean.setHeader(node.getProperty("header").getString());
			}
			if(node.hasProperty("seeAllText")){
				featuredBean.setSeeAllText(node.getProperty("seeAllText").getString());
			}
			
		}catch(Exception e){
			LOG.error(e.getMessage(), e);
		}
		return featuredBean;
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
	public boolean tagContains(Page page, String tagName){
		boolean flag = false;
		Tag[] Tags = page.getTags();
		 for(int i = 0; i < Tags.length; i++) {
			 Tag tag=Tags[i];
			 if(tag.getName().equals(tagName)){
				 flag = true;
				 break;
			 }
		 }
		return flag;
	}

	
	public List<MostViewedArticleBean> getFeaturedPages(Page rootPage,String tagName, int noOfArticles){
		List<MostViewedArticleBean> mostviewedArticleList = new ArrayList<MostViewedArticleBean>();
		Iterator<Page> children = rootPage.listChildren();
		Page childPage ;
		MostViewedArticleBean mostveiViewedArticleBean = null;
		int temp = 0;
		while (children.hasNext()) {
			childPage = children.next();
			if(tagContains(childPage,tagName)){
				if(temp < noOfArticles){
				mostveiViewedArticleBean = new MostViewedArticleBean();
				mostveiViewedArticleBean.setTitle(childPage.getTitle());
				mostveiViewedArticleBean.setArticlePath(childPage.getPath()+".html");
				mostviewedArticleList.add(mostveiViewedArticleBean);
				temp++;
			}else{
				break;
			}
			}
			
		}
		return mostviewedArticleList;
		
	}
	
}
