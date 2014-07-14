/**
 * 
 */
package com.tc.model;

import java.io.Serializable;

/**
 * The Class PDFBean.
 * 
 * @author gdinakar
 */
public class PDFBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The title. */
	private String title;

	/** The sub title. */
	private String subTitle;

	/** The pdf path. */
	private String pdfPath;

	/** The pdf thumbnail. */
	private String pdfThumbnail;

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
	 * @return the subTitle
	 */
	public String getSubTitle() {
		return subTitle;
	}

	/**
	 * @param subTitle
	 *            the subTitle to set
	 */
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	/**
	 * @return the pdfPath
	 */
	public String getPdfPath() {
		return pdfPath;
	}

	/**
	 * @param pdfPath
	 *            the pdfPath to set
	 */
	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}

	/**
	 * @return the pdfThumbnail
	 */
	public String getPdfThumbnail() {
		return pdfThumbnail;
	}

	/**
	 * @param pdfThumbnail
	 *            the pdfThumbnail to set
	 */
	public void setPdfThumbnail(String pdfThumbnail) {
		this.pdfThumbnail = pdfThumbnail;
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
		result = prime * result + ((pdfPath == null) ? 0 : pdfPath.hashCode());
		result = prime * result
				+ ((pdfThumbnail == null) ? 0 : pdfThumbnail.hashCode());
		result = prime * result
				+ ((subTitle == null) ? 0 : subTitle.hashCode());
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
		PDFBean other = (PDFBean) obj;
		if (pdfPath == null) {
			if (other.pdfPath != null) {
				return false;
			}
		} else if (!pdfPath.equals(other.pdfPath)) {
			return false;
		}
		if (pdfThumbnail == null) {
			if (other.pdfThumbnail != null) {
				return false;
			}
		} else if (!pdfThumbnail.equals(other.pdfThumbnail)) {
			return false;
		}
		if (subTitle == null) {
			if (other.subTitle != null) {
				return false;
			}
		} else if (!subTitle.equals(other.subTitle)) {
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
