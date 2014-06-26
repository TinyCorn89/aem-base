/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GalleryBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String heading;
    private String galleryType;
    private List<GalleryItemBean> galleryItemList;
    private Set<String> tabNav;
    private List<GalleryAlbumIteamBean> albumList = new ArrayList<GalleryAlbumIteamBean>();
    private String selector;
    private String saveRestrict;

    public String getSaveRestrict() {
        return saveRestrict;
    }

    public void setSaveRestrict(String saveRestrict) {
        this.saveRestrict = saveRestrict;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public List<GalleryAlbumIteamBean> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<GalleryAlbumIteamBean> albumList) {
        this.albumList = albumList;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getGalleryType() {
        return galleryType;
    }

    public void setGalleryType(String galleryType) {
        this.galleryType = galleryType;
    }

    public List<GalleryItemBean> getGalleryItemList() {
        return galleryItemList;
    }

    public void setGalleryItemList(List<GalleryItemBean> galleryItemList) {
        this.galleryItemList = galleryItemList;
    }

    public Set<String> getTabNav() {
        return tabNav;
    }

    public void setTabNav(Set<String> tabNav) {
        this.tabNav = tabNav;
    }

}
