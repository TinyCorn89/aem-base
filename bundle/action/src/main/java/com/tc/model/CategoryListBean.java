/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;
import java.util.List;

public class CategoryListBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<CategoryBean> listOfCategories;

    public List<CategoryBean> getListOfCategories() {
        return listOfCategories;
    }

    public void setListOfCategories(List<CategoryBean> listOfCategories) {
        this.listOfCategories = listOfCategories;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime
                * result
                + ((listOfCategories == null) ? 0 : listOfCategories.hashCode());
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
        CategoryListBean other = (CategoryListBean) obj;
        if (listOfCategories == null) {
            if (other.listOfCategories != null) {
                return false;
            }
        } else if (!listOfCategories.equals(other.listOfCategories)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CategoryListBean [listOfCategories=" + listOfCategories + "]";
    }

}
