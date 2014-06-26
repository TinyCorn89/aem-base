/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;
import java.util.List;

public class MostCommentedArticleBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String articleParent;
	private int noOfArticles;
	private String header;
	private String seeAllText;
	private List<MostViewedArticleBean> mostviewedArticleList;
	public String getArticleParent() {
		return articleParent;
	}
	public void setArticleParent(String articleParent) {
		this.articleParent = articleParent;
	}
	public int getNoOfArticles() {
		return noOfArticles;
	}
	public void setNoOfArticles(int noOfArticles) {
		this.noOfArticles = noOfArticles;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getSeeAllText() {
		return seeAllText;
	}
	public void setSeeAllText(String seeAllText) {
		this.seeAllText = seeAllText;
	}
	public List<MostViewedArticleBean> getMostviewedArticleList() {
		return mostviewedArticleList;
	}
	public void setMostviewedArticleList(
			List<MostViewedArticleBean> mostviewedArticleList) {
		this.mostviewedArticleList = mostviewedArticleList;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((articleParent == null) ? 0 : articleParent.hashCode());
		result = prime * result + ((header == null) ? 0 : header.hashCode());
		result = prime
				* result
				+ ((mostviewedArticleList == null) ? 0 : mostviewedArticleList
						.hashCode());
		result = prime * result + noOfArticles;
		result = prime * result
				+ ((seeAllText == null) ? 0 : seeAllText.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MostCommentedArticleBean other = (MostCommentedArticleBean) obj;
		if (articleParent == null) {
			if (other.articleParent != null)
				return false;
		} else if (!articleParent.equals(other.articleParent))
			return false;
		if (header == null) {
			if (other.header != null)
				return false;
		} else if (!header.equals(other.header))
			return false;
		if (mostviewedArticleList == null) {
			if (other.mostviewedArticleList != null)
				return false;
		} else if (!mostviewedArticleList.equals(other.mostviewedArticleList))
			return false;
		if (noOfArticles != other.noOfArticles)
			return false;
		if (seeAllText == null) {
			if (other.seeAllText != null)
				return false;
		} else if (!seeAllText.equals(other.seeAllText))
			return false;
		return true;
	}
	

}
