/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Value;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tc.action.BaseAction;
import com.tc.model.GalleryAlbumIteamBean;
import com.tc.model.GalleryBean;
import com.tc.model.GalleryItemBean;
import com.tc.util.SchedulerUtil;

/**
 * The Class GalleryAction.
 */
public class GalleryAction extends BaseAction {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory
            .getLogger(GalleryAction.class);

    /**
     * The item category.
     */
    List<String> itemCategory = null;

    /**
     * The album item category.
     */
    List<String> albumItemCategory = null;

    /**
     * The heading.
     */
    String heading = null;

    /**
     * The gallery type.
     */
    String galleryType = null;

    /**
     * The selector.
     */
    String selector = null;

    /**
     * The no of items to be displayed.
     */
    Integer noOfItemsToBeDisplayed = null;

    /**
     * The index.
     */
    Integer index = null;

    /**
     * The time difference.
     */
    Long timeDifference = null;

    /**
     * The time interval.
     */
    Long timeInterval = null;

    /**
     * The date1.
     */
    Calendar date1 = null;

    /**
     * The album path.
     */
    String albumPath = null;

    /**
     * The time unit.
     */
    String timeUnit = null;

    /**
     * Gets the gallery.
     *
     * @return the gallery
     */
    public GalleryBean getGallery() {

        GalleryBean galleryBean = new GalleryBean();

        try {
            Node cuNode = getCurrentNode();

            if (cuNode.hasProperty("albumpath")
                    && StringUtils.isNotBlank(cuNode.getProperty("albumpath")
                            .getString())) {

                albumPath = cuNode.getProperty("albumpath").getString();

                LOG.debug("Album Path" + albumPath);
            }

            if (cuNode.hasProperty("selector")
                    && StringUtils.isNotBlank(cuNode.getProperty("selector")
                            .getString())) {
                selector = cuNode.getProperty("selector").getString();

                if (StringUtils.isNotBlank(albumPath)
                        && selector.equalsIgnoreCase("advanced")) {
                    galleryBean = getAdvancedGallery(albumPath);
                } else {
                    galleryBean = getSimpleGallery();
                }

                galleryBean.setSelector(selector);

                LOG.debug("Selector" + selector);

            }

            if (cuNode.hasProperty("saveRestrict")
                    && StringUtils.isNotBlank(cuNode
                            .getProperty("saveRestrict").getString())) {

                galleryBean.setSaveRestrict(cuNode.getProperty("saveRestrict")
                        .getString());

                LOG.debug("saveRestrict :"
                        + cuNode.getProperty("saveRestrict").getString());
            }

            if (cuNode.hasProperty("heading")
                    && StringUtils.isNotBlank(cuNode.getProperty("heading")
                            .getString())) {

                galleryBean.setHeading(cuNode.getProperty("heading")
                        .getString());

                LOG.debug("Heading :"
                        + cuNode.getProperty("heading").getString());
            }

            if (cuNode.hasProperty("radio")
                    && StringUtils.isNotBlank(cuNode.getProperty("radio")
                            .getString())) {

                galleryBean.setGalleryType(cuNode.getProperty("radio")
                        .getString());

                LOG.debug("Gallery Type :"
                        + cuNode.getProperty("radio").getString());
            }

        } catch (RepositoryException e) {
            LOG.error("Repository Exception in getGallery method" + e);
        }

        return galleryBean;

    }

    /**
     * Gets the simple gallery.
     *
     * @return the simple gallery
     */
    public GalleryBean getSimpleGallery() {

        /**
         * The gallery list.
         */
        List<GalleryItemBean> galleryList = new ArrayList<GalleryItemBean>();

        Set<String> set = new LinkedHashSet<String>();
        GalleryBean galleryBean = new GalleryBean();
        final Resource resource = getCurrentResource();
        Node currentNode = resource.adaptTo(Node.class);
        try {
            if (currentNode.hasNode("items")) {

                String path = currentNode.getPath() + "/items";

                Node node = getSlingRequest().getResourceResolver()
                        .getResource(path).adaptTo(Node.class);
                NodeIterator nodeiterator = node.getNodes();

                while (nodeiterator.hasNext()) {
                    GalleryItemBean galleryItemBean = new GalleryItemBean();
                    Node childNode = nodeiterator.nextNode();
                    String itemPath = null;
                    String title = null;
                    String description = null;
                    String caption = null;

                    if (childNode.hasProperty("itemPath")) {
                        itemPath = childNode.getProperty("itemPath")
                                .getString();
                        if (StringUtils.isNotBlank(itemPath)) {
                            galleryItemBean.setItemPath(itemPath);
                            itemCategory = getTags(itemPath);
                            for (int i = 0; i < itemCategory.size(); i++) {
                                String itemCategoryElement = itemCategory
                                        .get(i);
                                set.add(itemCategoryElement);

                            }
                            galleryItemBean.setItemCategory(itemCategory);

                        }
                        LOG.debug("Item Path :" + itemPath);
                    }

                    if (childNode.hasProperty("title")) {

                        title = childNode.getProperty("title").getString();
                        if (StringUtils.isNotBlank(title)) {
                            galleryItemBean.setTitle(title);
                        }
                        LOG.debug("Title :" + title);
                    }

                    if (childNode.hasProperty("description")) {
                        description = childNode.getProperty("description")
                                .getString();
                        if (StringUtils.isNotBlank(description)) {
                            galleryItemBean.setDescription(description);
                        }
                        LOG.debug("Description :" + description);
                    }

                    if (childNode.hasProperty("caption")) {
                        caption = childNode.getProperty("caption").getString();
                        if (StringUtils.isNotBlank(caption)) {
                            galleryItemBean.setCaption(caption);
                        }
                        LOG.debug("Caption :" + caption);
                    }

                    galleryList.add(galleryItemBean);
                }

                galleryBean.setGalleryItemList(galleryList);

                LOG.debug("Set :" + set);
                galleryBean.setTabNav(set);

            }

        } catch (RepositoryException e) {
            LOG.error("Repository Exception in execute method" + e);
        }
        return galleryBean;

    }

    /**
     * Gets the advanced gallery.
     *
     * @param albumPath the album path
     * @return the advanced gallery
     */
    @SuppressWarnings("deprecation")
    public GalleryBean getAdvancedGallery(String albumPath) {

        Set<String> set = new LinkedHashSet<String>();
        GalleryBean galleryBean = new GalleryBean();

        Node albumNode = getSlingRequest().getResourceResolver()
                .getResource(albumPath).adaptTo(Node.class);

        try {
            Calendar date = Calendar.getInstance();
            Node nodc = getCurrentNode();
            if (!nodc.hasProperty("date") || nodc.hasProperty("modifiedDate")) {
                nodc.setProperty("date", date);
                nodc.setProperty("modifiedDate", (Value) null);
                nodc.save();

            }
            date1 = nodc.getProperty("date").getDate();

            timeDifference = date.getTimeInMillis() - date1.getTimeInMillis();

            if (nodc.hasProperty("timeInterval")
                    && StringUtils.isNotBlank(nodc.getProperty("timeInterval")
                            .getString())) {

                timeInterval = Long.parseLong(nodc.getProperty("timeInterval")
                        .getString());

            }

            if (nodc.hasProperty("timeUnit")
                    && StringUtils.isNotBlank(nodc.getProperty("timeUnit")
                            .getString())) {
                timeUnit = nodc.getProperty("timeUnit").getString();

            }

            LOG.debug("timeDifference" + timeDifference + "timeInterval "
                    + timeInterval + "timeUnit" + timeUnit);

            if (StringUtils.isNotBlank(nodc.getProperty("displayitems")
                    .getString())) {
                noOfItemsToBeDisplayed = (int) Long.parseLong(nodc.getProperty(
                        "displayitems").getString());

                LOG.debug("NoOfItemsToBeDisplayed :" + noOfItemsToBeDisplayed);
            }

            if (albumNode.hasNodes()) {
                NodeIterator albumIterator = albumNode.getNodes();
                GalleryItemBean galleryItemBean = new GalleryItemBean();
                List<GalleryAlbumIteamBean> mainFolderList = new ArrayList<GalleryAlbumIteamBean>();
                List<GalleryAlbumIteamBean> mainSchedluedList = new ArrayList<GalleryAlbumIteamBean>();
                while (albumIterator.hasNext()) {

                    GalleryAlbumIteamBean galleryAlbumIteamBean = new GalleryAlbumIteamBean();

                    Node childAlbumNode = albumIterator.nextNode();
                    String childAlbumName = null;

                    if (!childAlbumNode.getName().equalsIgnoreCase(
                            "jcr:content")) {
                        childAlbumName = childAlbumNode.getName();
                        LOG.debug("child album name" + childAlbumName);
                        set.add(childAlbumName);
                    }

                    if (!childAlbumNode.getName().equalsIgnoreCase(
                            "jcr:content")) {

                        List<GalleryItemBean> childItemist = new ArrayList<GalleryItemBean>();
                        if (childAlbumNode.hasNodes()) {
                            NodeIterator childAlbumIterator = childAlbumNode
                                    .getNodes();

                            while (childAlbumIterator.hasNext()) {

                                Node childItem = childAlbumIterator.nextNode();
                                String childItemPath = childItem.getPath();
                                GalleryItemBean galleryItemBean2 = new GalleryItemBean();

                                if (!childItemPath.contains("jcr:content")) {

                                    if (StringUtils
                                            .isNotBlank(getDamTitle(childItemPath))) {
                                        String damTitle = getDamTitle(childItemPath);

                                        galleryItemBean2.setDamTitle(damTitle);

                                        LOG.debug("DamTitle :" + damTitle);
                                    }

                                    if (StringUtils.isNotBlank(childItemPath)) {

                                        galleryItemBean2
                                                .setItemPath(childItemPath);
                                        LOG.debug("ChildItemPath :"
                                                + childItemPath);
                                    }

                                    childItemist.add(galleryItemBean2);

                                }
                                galleryItemBean
                                        .setItemCategory(albumItemCategory);
                            }
                            galleryAlbumIteamBean.setIteamList(childItemist);
                            galleryAlbumIteamBean.setAlbumTag(childAlbumName);

                        }

                        mainFolderList.add(galleryAlbumIteamBean);

                        galleryBean.setTabNav(set);
                    }
                }

                index = SchedulerUtil
                        .getIndex(timeDifference, timeInterval, timeUnit,
                                mainFolderList.size(), noOfItemsToBeDisplayed);

                if ((index * noOfItemsToBeDisplayed + noOfItemsToBeDisplayed) <= mainFolderList
                        .size()) {
                    for (int i = index * noOfItemsToBeDisplayed; i < (index
                            * noOfItemsToBeDisplayed + noOfItemsToBeDisplayed); i++) {
                        mainSchedluedList.add(mainFolderList.get(i));
                    }
                } else {
                    for (int i = index * noOfItemsToBeDisplayed; i < mainFolderList
                            .size(); i++) {
                        mainSchedluedList.add(mainFolderList.get(i));
                    }
                }

                LOG.debug("timeDifference :" + timeDifference
                        + "timeInterval :" + timeInterval + "timeUnit :"
                        + timeUnit + "index :" + index
                        + "mainFolderList size :" + mainFolderList.size()
                        + "mainSchedluedList size :" + mainSchedluedList.size()
                        + "mainSchedluedList :" + mainSchedluedList);

                galleryBean.setAlbumList(mainSchedluedList);

            }
        } catch (RepositoryException e) {

            e.printStackTrace();
        }

        return galleryBean;
    }

    /**
     * Gets the tags.
     *
     * @param resourcePath the resource path
     * @return the tags
     */
    public List<String> getTags(String resourcePath) {

        String damPath = resourcePath + "/jcr:content/metadata";
        List<String> tabGroup = new ArrayList<String>();
        Value[] values = null;
        try {

            Node node = getSlingRequest().getResourceResolver()
                    .getResource(damPath).adaptTo(Node.class);

            if (node.getProperty("cq:tags").getValues() != null) {
                values = node.getProperty("cq:tags").getValues();
                for (Value tagValue : values) {
                    String ValueString = tagValue.getString();
                    String delimiter = ":";
                    String[] temp = ValueString.split(delimiter);
                    tabGroup.add(temp[1]);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return tabGroup;
    }

    /**
     * Gets the dam title.
     *
     * @param resourcePath the resource path
     * @return the dam title
     */
    public String getDamTitle(String resourcePath) {

        String damPath = resourcePath + "/jcr:content/metadata";
        String damTitle = null;
        try {

            Node node = getSlingRequest().getResourceResolver()
                    .getResource(damPath).adaptTo(Node.class);

            if (node.hasProperty("dc:title")
                    && StringUtils.isNotBlank(node.getProperty("dc:title")
                            .getString())) {

                damTitle = node.getProperty("dc:title").getString();

            }
            LOG.debug("DamTitle :" + damTitle);

        } catch (Exception e) {

            e.printStackTrace();
        }
        return damTitle;
    }

}
