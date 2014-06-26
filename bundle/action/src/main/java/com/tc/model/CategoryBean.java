/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;

public class CategoryBean implements Serializable, Comparable<CategoryBean> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String categoryName;
    private String categoryUrl;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryUrl() {
        return categoryUrl;
    }

    public void setCategoryUrl(String categoryUrl) {
        this.categoryUrl = categoryUrl;
    }

    @Override
    public String toString() {
        return "CategoryBean [categoryName=" + categoryName + ", categoryUrl="
                + categoryUrl + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((categoryName == null) ? 0 : categoryName.hashCode());
        result = prime * result
                + ((categoryUrl == null) ? 0 : categoryUrl.hashCode());
        return result;
    }

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
        CategoryBean other = (CategoryBean) obj;
        if (categoryName == null) {
            if (other.categoryName != null) {
                return false;
            }
        } else if (!categoryName.equals(other.categoryName)) {
            return false;
        }
        if (categoryUrl == null) {
            if (other.categoryUrl != null) {
                return false;
            }
        } else if (!categoryUrl.equals(other.categoryUrl)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(CategoryBean categoryBean) {
        return categoryName.compareTo(categoryBean.categoryName);
    }

}
