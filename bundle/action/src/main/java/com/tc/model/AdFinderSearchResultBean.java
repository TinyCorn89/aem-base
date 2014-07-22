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
	private String adID;
	private String title;
	private String publicationDate;
	private Collection<String> journal;
	private String imagePath;
	Collection<String> keywords;
	private String advertiserID;

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
	 * @param journal
	 *            the journal to set
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

	/**
	 * @return the adID
	 */
	public String getAdID() {
		return adID;
	}

	/**
	 * @param adID
	 *            the adID to set
	 */
	public void setAdID(String adID) {
		this.adID = adID;
	}

	/**
	 * @return the advertiserID
	 */
	public String getAdvertiserID() {
		return advertiserID;
	}

	/**
	 * @param advertiserID the advertiserID to set
	 */
	public void setAdvertiserID(String advertiserID) {
		this.advertiserID = advertiserID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adID == null) ? 0 : adID.hashCode());
		result = prime * result
				+ ((advertiserID == null) ? 0 : advertiserID.hashCode());
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

	/* (non-Javadoc)
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
		if (adID == null) {
			if (other.adID != null) {
				return false;
			}
		} else if (!adID.equals(other.adID)) {
			return false;
		}
		if (advertiserID == null) {
			if (other.advertiserID != null) {
				return false;
			}
		} else if (!advertiserID.equals(other.advertiserID)) {
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
