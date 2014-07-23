/**
 * 
 */
package com.tc.model;

import java.io.Serializable;


public class AdFinderSearchBean implements Serializable {
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String title;
 private String keywordsLabel;
 private String dateLabel;
 private String buttonLabel;
 private String advertiserLabel;
 private String adLabel;
 private String searchPath;
/**
 * @return the title
 */
public String getTitle() {
	return title;
}
/**
 * @param title the title to set
 */
public void setTitle(String title) {
	this.title = title;
}
/**
 * @return the keywordsLabel
 */
public String getKeywordsLabel() {
	return keywordsLabel;
}
/**
 * @param keywordsLabel the keywordsLabel to set
 */
public void setKeywordsLabel(String keywordsLabel) {
	this.keywordsLabel = keywordsLabel;
}
/**
 * @return the dateLabel
 */
public String getDateLabel() {
	return dateLabel;
}
/**
 * @param dateLabel the dateLabel to set
 */
public void setDateLabel(String dateLabel) {
	this.dateLabel = dateLabel;
}
/**
 * @return the buttonLabel
 */
public String getButtonLabel() {
	return buttonLabel;
}
/**
 * @param buttonLabel the buttonLabel to set
 */
public void setButtonLabel(String buttonLabel) {
	this.buttonLabel = buttonLabel;
}
/**
 * @return the advertiserLabel
 */
public String getAdvertiserLabel() {
	return advertiserLabel;
}
/**
 * @param advertiserLabel the advertiserLabel to set
 */
public void setAdvertiserLabel(String advertiserLabel) {
	this.advertiserLabel = advertiserLabel;
}
/**
 * @return the adLabel
 */
public String getAdLabel() {
	return adLabel;
}
/**
 * @param adLabel the adLabel to set
 */
public void setAdLabel(String adLabel) {
	this.adLabel = adLabel;
}
/**
 * @return the searchPath
 */
public String getSearchPath() {
	return searchPath;
}
/**
 * @param searchPath the searchPath to set
 */
public void setSearchPath(String searchPath) {
	this.searchPath = searchPath;
}
/* (non-Javadoc)
 * @see java.lang.Object#hashCode()
 */
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((adLabel == null) ? 0 : adLabel.hashCode());
	result = prime * result
			+ ((advertiserLabel == null) ? 0 : advertiserLabel.hashCode());
	result = prime * result
			+ ((buttonLabel == null) ? 0 : buttonLabel.hashCode());
	result = prime * result + ((dateLabel == null) ? 0 : dateLabel.hashCode());
	result = prime * result
			+ ((keywordsLabel == null) ? 0 : keywordsLabel.hashCode());
	result = prime * result
			+ ((searchPath == null) ? 0 : searchPath.hashCode());
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
	AdFinderSearchBean other = (AdFinderSearchBean) obj;
	if (adLabel == null) {
		if (other.adLabel != null) {
			return false;
		}
	} else if (!adLabel.equals(other.adLabel)) {
		return false;
	}
	if (advertiserLabel == null) {
		if (other.advertiserLabel != null) {
			return false;
		}
	} else if (!advertiserLabel.equals(other.advertiserLabel)) {
		return false;
	}
	if (buttonLabel == null) {
		if (other.buttonLabel != null) {
			return false;
		}
	} else if (!buttonLabel.equals(other.buttonLabel)) {
		return false;
	}
	if (dateLabel == null) {
		if (other.dateLabel != null) {
			return false;
		}
	} else if (!dateLabel.equals(other.dateLabel)) {
		return false;
	}
	if (keywordsLabel == null) {
		if (other.keywordsLabel != null) {
			return false;
		}
	} else if (!keywordsLabel.equals(other.keywordsLabel)) {
		return false;
	}
	if (searchPath == null) {
		if (other.searchPath != null) {
			return false;
		}
	} else if (!searchPath.equals(other.searchPath)) {
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
