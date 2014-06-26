/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;
import java.util.List;

public class FeaturedBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String parentPath;
	private  String cqTag;
	private String header;
	private String seeAllText;
	private List<MostViewedArticleBean> mostviewedArticleList;
	public String getParentPath() {
		return parentPath;
	}
	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}
	public String getCqTag() {
		return cqTag;
	}
	public void setCqTag(String cqTag) {
		this.cqTag = cqTag;
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
		result = prime * result + ((cqTag == null) ? 0 : cqTag.hashCode());
		result = prime * result + ((header == null) ? 0 : header.hashCode());
		result = prime
				* result
				+ ((mostviewedArticleList == null) ? 0 : mostviewedArticleList
						.hashCode());
		result = prime * result
				+ ((parentPath == null) ? 0 : parentPath.hashCode());
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
		FeaturedBean other = (FeaturedBean) obj;
		if (cqTag == null) {
			if (other.cqTag != null)
				return false;
		} else if (!cqTag.equals(other.cqTag))
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
		if (parentPath == null) {
			if (other.parentPath != null)
				return false;
		} else if (!parentPath.equals(other.parentPath))
			return false;
		if (seeAllText == null) {
			if (other.seeAllText != null)
				return false;
		} else if (!seeAllText.equals(other.seeAllText))
			return false;
		return true;
	}
	
	
	

}
