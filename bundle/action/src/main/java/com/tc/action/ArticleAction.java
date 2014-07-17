/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import javax.jcr.Node;
import javax.jcr.Value;

import org.apache.sling.api.resource.Resource;

import com.day.cq.wcm.foundation.Image;

import com.tc.action.BaseAction;
import com.tc.model.ArticleBean;
import com.tc.util.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArticleAction extends BaseAction {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory
            .getLogger(ArticleAction.class);
    
	/**
	 * The method is used to retrieve the article details.
	 * @return
	 */
	public ArticleBean getArticleInfo() {
		ArticleBean articleBean = new ArticleBean();

		try {
			Resource resource = getCurrentResource();
			Node node = getCurrentNode();
			
			Image image = new Image(resource, "image");

			if (image.hasContent()) {
				articleBean.setImageUrl(image.getHref());
			}

			if (node.hasProperty(Constants.ARTICLE.TITLE)) {
				articleBean.setTitle(node.getProperty(Constants.ARTICLE.TITLE).getString());
			}

			if (node.hasProperty(Constants.ARTICLE.DESCRIPTION)) {
				articleBean.setDescription(node.getProperty(Constants.ARTICLE.DESCRIPTION)
						.getString());
			}
		} catch(Exception e) {

		}

		return articleBean;

	}

	public ArticleBean getCPArticleInfo(){
		ArticleBean articleBean = new ArticleBean();
		 
		try {
			
			Node node = getCurrentNode();
			LOG.debug("***** Current NODE :" +  node.getPath());
			
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
		} catch(Exception e) {

		}
		return articleBean;

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
        String time = dateAndTime.substring(dateAndTime.indexOf("T") + 1, dateAndTime.length());
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
