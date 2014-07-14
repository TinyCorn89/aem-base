/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;
import java.util.List;

/**
 * The Class ListOfArticlesBean.
 * 
 * @author gdinakar
 */
public class ListOfCPArticlesBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The parent page path. */
	private String parentPagePath;

	/** The see all link text. */
	private String seeAllLinkText;

	/** The list of latest articles. */
	private List<ArticleBean> listOfLatestArticles;

	/**
	 * @return the parentPagePath
	 */
	public String getParentPagePath() {
		return parentPagePath;
	}

	/**
	 * @param parentPagePath
	 *            the parentPagePath to set
	 */
	public void setParentPagePath(String parentPagePath) {
		this.parentPagePath = parentPagePath;
	}

	/**
	 * @return the seeAllLinkText
	 */
	public String getSeeAllLinkText() {
		return seeAllLinkText;
	}

	/**
	 * @param seeAllLinkText
	 *            the seeAllLinkText to set
	 */
	public void setSeeAllLinkText(String seeAllLinkText) {
		this.seeAllLinkText = seeAllLinkText;
	}

	/**
	 * @return the listOfLatestArticles
	 */
	public List<ArticleBean> getListOfLatestArticles() {
		return listOfLatestArticles;
	}

	/**
	 * @param listOfLatestArticles
	 *            the listOfLatestArticles to set
	 */
	public void setListOfLatestArticles(
			List<ArticleBean> listOfLatestArticles) {
		this.listOfLatestArticles = listOfLatestArticles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ListOfArticlesBean [parentPagePath=" + parentPagePath
				+ ", seeAllLinkText=" + seeAllLinkText
				+ ", listOfLatestArticles=" + listOfLatestArticles + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((listOfLatestArticles == null) ? 0 : listOfLatestArticles
						.hashCode());
		result = prime * result
				+ ((parentPagePath == null) ? 0 : parentPagePath.hashCode());
		result = prime * result
				+ ((seeAllLinkText == null) ? 0 : seeAllLinkText.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ListOfCPArticlesBean other = (ListOfCPArticlesBean) obj;
		if (listOfLatestArticles == null) {
			if (other.listOfLatestArticles != null) {
				return false;
			}
		} else if (!listOfLatestArticles.equals(other.listOfLatestArticles)) {
			return false;
		}
		if (parentPagePath == null) {
			if (other.parentPagePath != null) {
				return false;
			}
		} else if (!parentPagePath.equals(other.parentPagePath)) {
			return false;
		}
		if (seeAllLinkText == null) {
			if (other.seeAllLinkText != null) {
				return false;
			}
		} else if (!seeAllLinkText.equals(other.seeAllLinkText)) {
			return false;
		}
		return true;
	}

}
