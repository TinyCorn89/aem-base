/**
 * 
 */
package com.tc.model;

import java.io.Serializable;

/**
 * The Class AdFinderSearch.
 * 
 * @author gdinakar
 */
public class AdFinderSearchBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	
	/** The keywords. */
	private String keywords;

	/** The category. */
	private String category;

	/** The newspapaers. */
	private String newspapaers;

	/** The date. */
	private String timePeriod;

	/**
	 * @return the keywords
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * @param keywords
	 *            the keywords to set
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the newspapaers
	 */
	public String getNewspapaers() {
		return newspapaers;
	}

	/**
	 * @param newspapaers
	 *            the newspapaers to set
	 */
	public void setNewspapaers(String newspapaers) {
		this.newspapaers = newspapaers;
	}

	/**
	 * @return the timePeriod
	 */
	public String getTimePeriod() {
		return timePeriod;
	}

	/**
	 * @param timePeriod
	 *            the timePeriod to set
	 */
	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AdFinderSearchBean [keywords=" + keywords + ", category="
				+ category + ", newspapaers=" + newspapaers + ", timePeriod="
				+ timePeriod + "]";
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
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result
				+ ((keywords == null) ? 0 : keywords.hashCode());
		result = prime * result
				+ ((newspapaers == null) ? 0 : newspapaers.hashCode());
		result = prime * result
				+ ((timePeriod == null) ? 0 : timePeriod.hashCode());
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
		AdFinderSearchBean other = (AdFinderSearchBean) obj;
		if (category == null) {
			if (other.category != null) {
				return false;
			}
		} else if (!category.equals(other.category)) {
			return false;
		}
		if (keywords == null) {
			if (other.keywords != null) {
				return false;
			}
		} else if (!keywords.equals(other.keywords)) {
			return false;
		}
		if (newspapaers == null) {
			if (other.newspapaers != null) {
				return false;
			}
		} else if (!newspapaers.equals(other.newspapaers)) {
			return false;
		}
		if (timePeriod == null) {
			if (other.timePeriod != null) {
				return false;
			}
		} else if (!timePeriod.equals(other.timePeriod)) {
			return false;
		}
		return true;
	}

}
