/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

/**
 * The Class LatestArticlesBean.
 * 
 * @author gdinakar
 */
public class LatestArticlesBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The article title. */
	private String articleTitle;

	/** The article path. */
	private String articlePath;

	/** The date of creation. */
	private Date dateOfCreation;

	/**
	 * @return the articleTitle
	 */
	public String getArticleTitle() {
		return articleTitle;
	}

	/**
	 * @param articleTitle
	 *            the articleTitle to set
	 */
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	/**
	 * @return the articlePath
	 */
	public String getArticlePath() {
		return articlePath;
	}

	/**
	 * @param articlePath
	 *            the articlePath to set
	 */
	public void setArticlePath(String articlePath) {
		this.articlePath = articlePath;
	}

	/**
	 * @return the dateOfCreation
	 */
	public Date getDateOfCreation() {
		return dateOfCreation;
	}

	/**
	 * @param dateOfCreation
	 *            the dateOfCreation to set
	 */
	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	
	public static final Comparator<LatestArticlesBean> DateComparator = new Comparator<LatestArticlesBean>(){

        @Override
        public int compare(LatestArticlesBean o1, LatestArticlesBean o2) {
        	return o2.dateOfCreation.compareTo(o1.dateOfCreation);
        }
      
    };

}
