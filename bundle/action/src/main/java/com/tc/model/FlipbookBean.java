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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FlipbookBean [imagesPathList=" + imagesPathList + "]";
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
