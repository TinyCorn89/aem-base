/**
 * 
 */
package com.tc.model;

import java.io.Serializable;
import java.util.Collection;

/**
 * The Class AdFinderSearchResultBean.
 * 
 * @author graja
 */
public class AdFinderSearchResultBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	private long advertiserId;
	private String title;
	private String publicationDate;
	private Collection<String> journal;
	private String imagePath;
	Collection<String> keywords;

	/**
	 * @return the advertiserId
	 */
	public long getAdvertiserId() {
		return advertiserId;
	}

	/**
	 * @param advertiserId
	 *            the advertiserId to set
	 */
	public void setAdvertiserId(long advertiserId) {
		this.advertiserId = advertiserId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the publicationDate
	 */
	public String getPublicationDate() {
		return publicationDate;
	}

	/**
	 * @param publicationDate
	 *            the publicationDate to set
	 */
	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	/**
	 * @return the journal
	 */
	public Collection<String> getJournal() {
		return journal;
	}

	/**
	 * @param journal the journal to set
	 */
	public void setJournal(Collection<String> journal) {
		this.journal = journal;
	}

	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * @param imagePath
	 *            the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * @return the keywords
	 */
	public Collection<String> getKeywords() {
		return keywords;
	}

	/**
	 * @param keywords
	 *            the keywords to set
	 */
	public void setKeywords(Collection<String> keywords) {
		this.keywords = keywords;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AdFinderSearchResultBean [advertiserId=" + advertiserId
				+ ", title=" + title + ", publicationDate=" + publicationDate
				+ ", journal=" + journal + ", imagePath=" + imagePath
				+ ", keywords=" + keywords + "]";
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
		result = prime * result + (int) (advertiserId ^ (advertiserId >>> 32));
		result = prime * result
				+ ((imagePath == null) ? 0 : imagePath.hashCode());
		result = prime * result + ((journal == null) ? 0 : journal.hashCode());
		result = prime * result
				+ ((keywords == null) ? 0 : keywords.hashCode());
		result = prime * result
				+ ((publicationDate == null) ? 0 : publicationDate.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		AdFinderSearchResultBean other = (AdFinderSearchResultBean) obj;
		if (advertiserId != other.advertiserId) {
			return false;
		}
		if (imagePath == null) {
			if (other.imagePath != null) {
				return false;
			}
		} else if (!imagePath.equals(other.imagePath)) {
			return false;
		}
		if (journal == null) {
			if (other.journal != null) {
				return false;
			}
		} else if (!journal.equals(other.journal)) {
			return false;
		}
		if (keywords == null) {
			if (other.keywords != null) {
				return false;
			}
		} else if (!keywords.equals(other.keywords)) {
			return false;
		}
		if (publicationDate == null) {
			if (other.publicationDate != null) {
				return false;
			}
		} else if (!publicationDate.equals(other.publicationDate)) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}

}
