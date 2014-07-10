/**
 * 
 */
package com.tc.model;

import java.io.Serializable;
import java.util.List;

/**
 * The Class FlipbooksListBean.
 *
 * @author gdinakar
 */
public class FlipbooksListBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The category name. */
	private String categoryName;

	/** The list of pd fs. */
	private List<PDFBean> listOfPDFs;

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName
	 *            the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the listOfPDFs
	 */
	public List<PDFBean> getListOfPDFs() {
		return listOfPDFs;
	}

	/**
	 * @param listOfPDFs
	 *            the listOfPDFs to set
	 */
	public void setListOfPDFs(List<PDFBean> listOfPDFs) {
		this.listOfPDFs = listOfPDFs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FlipbooksListBean [categoryName=" + categoryName
				+ ", listOfPDFs=" + listOfPDFs + "]";
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
				+ ((categoryName == null) ? 0 : categoryName.hashCode());
		result = prime * result
				+ ((listOfPDFs == null) ? 0 : listOfPDFs.hashCode());
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
		FlipbooksListBean other = (FlipbooksListBean) obj;
		if (categoryName == null) {
			if (other.categoryName != null) {
				return false;
			}
		} else if (!categoryName.equals(other.categoryName)) {
			return false;
		}
		if (listOfPDFs == null) {
			if (other.listOfPDFs != null) {
				return false;
			}
		} else if (!listOfPDFs.equals(other.listOfPDFs)) {
			return false;
		}
		return true;
	}

}
