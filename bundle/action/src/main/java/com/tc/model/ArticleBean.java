/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;

/**
 * Bean to hold the article details.
 */
public class ArticleBean implements Serializable {

	private String title = null;
	private String description = null;
	private String imageUrl = null;
	private String publicationDate = null;
	private String expirationDate = null;
	Collection<String> sections = null;
	Collection<String> categories = null;
	Collection<String> share = null;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public String getPublicationDate(){
		
		return this.publicationDate;
	}
	
	public void setPublicationDate(String publicationDate){
		this.publicationDate = publicationDate;
	}
	
	public String getExpirationDate(){
		
		return this.expirationDate;
	}
	
	public void setExpirationDate(String expirationDate){
		this.expirationDate = expirationDate;
	}
	
	public Collection<String> getSections(){
		return this.sections;
	}
	
	public void setSections(Collection<String> sections){
		this.sections = sections;
	}
	
	public Collection<String> getCategories(){
		return this.categories;
	}
	
	public void setCategories(Collection<String> categories){
		this.categories = categories;
	}
	
	public Collection<String> getShare(){
		return this.share;
	}
	
	public void setShare(Collection<String> share){
		this.share = share;
	}
	
	public static final Comparator<ArticleBean> DateComparator = new Comparator<ArticleBean>(){

        @Override
        public int compare(ArticleBean o1, ArticleBean o2) {
        	return o2.publicationDate.compareTo(o1.publicationDate);
        }
      
    };
    
}
