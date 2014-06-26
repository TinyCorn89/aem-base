/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;

public class MapBean implements Serializable {


    private static final long serialVersionUID = -3039225131471242381L;

    private int width;
    private int height;
    private String location;
    private String near;

    public String getNear() {
        return near;
    }

    public void setNear(String near) {
        this.near = near;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + height;
        result = prime * result
                + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((near == null) ? 0 : near.hashCode());
        result = prime * result + width;
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
        MapBean other = (MapBean) obj;
        if (height != other.height) {
            return false;
        }
        if (location == null) {
            if (other.location != null) {
                return false;
            }
        } else if (!location.equals(other.location)) {
            return false;
        }
        if (near == null) {
            if (other.near != null) {
                return false;
            }
        } else if (!near.equals(other.near)) {
            return false;
        }
        if (width != other.width) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MapBean [width=" + width + ", height=" + height + ", location="
                + location + ", near=" + near + "]";
    }

}
