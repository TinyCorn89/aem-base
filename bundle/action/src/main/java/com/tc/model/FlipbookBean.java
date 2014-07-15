/**
 * 
 */
package com.tc.model;

import java.io.Serializable;
import java.util.List;

/**
 * The Class FlipbookBean.
 * 
 * @author gdinakar
 */
public class FlipbookBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The images path list. */
	private List<String> imagesPathList;

	/** The images meta data list. */
	private List<String> imagesMetaDataList;

	/**
	 * @return the imagesPathList
	 */
	public List<String> getImagesPathList() {
		return imagesPathList;
	}

	/**
	 * @param imagesPathList
	 *            the imagesPathList to set
	 */
	public void setImagesPathList(List<String> imagesPathList) {
		this.imagesPathList = imagesPathList;
	}

	/**
	 * @return the imagesMetaDataList
	 */
	public List<String> getImagesMetaDataList() {
		return imagesMetaDataList;
	}

	/**
	 * @param imagesMetaDataList
	 *            the imagesMetaDataList to set
	 */
	public void setImagesMetaDataList(List<String> imagesMetaDataList) {
		this.imagesMetaDataList = imagesMetaDataList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FlipbookBean [imagesPathList=" + imagesPathList
				+ ", imagesMetaDataList=" + imagesMetaDataList + "]";
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
				+ ((imagesMetaDataList == null) ? 0 : imagesMetaDataList
						.hashCode());
		result = prime * result
				+ ((imagesPathList == null) ? 0 : imagesPathList.hashCode());
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
		FlipbookBean other = (FlipbookBean) obj;
		if (imagesMetaDataList == null) {
			if (other.imagesMetaDataList != null) {
				return false;
			}
		} else if (!imagesMetaDataList.equals(other.imagesMetaDataList)) {
			return false;
		}
		if (imagesPathList == null) {
			if (other.imagesPathList != null) {
				return false;
			}
		} else if (!imagesPathList.equals(other.imagesPathList)) {
			return false;
		}
		return true;
	}

}
