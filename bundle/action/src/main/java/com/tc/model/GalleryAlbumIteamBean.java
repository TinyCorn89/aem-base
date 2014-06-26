/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.util.ArrayList;
import java.util.List;

public class GalleryAlbumIteamBean {

    private List<GalleryItemBean> iteamList = new ArrayList<GalleryItemBean>();
    private String albumTag;

    public String getAlbumTag() {
        return albumTag;
    }

    public void setAlbumTag(String albumTag) {
        this.albumTag = albumTag;
    }

    public List<GalleryItemBean> getIteamList() {
        return iteamList;
    }

    public void setIteamList(List<GalleryItemBean> iteamList) {
        this.iteamList = iteamList;
    }

}
