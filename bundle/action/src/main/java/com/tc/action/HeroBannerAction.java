/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Value;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.day.cq.wcm.api.WCMMode;
import com.tc.action.BaseAction;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.model.HeroBannerBean;
import com.tc.model.HeroBean;
import com.tc.util.Constants;
import com.tc.util.SchedulerUtil;

/**
 * The Class HeroAdvanceRendering.
 */
public class HeroBannerAction extends BaseAction {

    /**
     * The Constant LOG.
     */
    private Logger LOG = FrameworkLogger.getLogger();

    /**
     * Hero banner.
     *
     * @return the hero banner bean
     */
    public HeroBannerBean heroBanner() {
        LOG.info("entered into the heroBanner method of HeroBannerAction:");
        HeroBannerBean heroBannerBean = new HeroBannerBean();
        String choosenNodename = null;
        List<HeroBean> newList = new ArrayList<HeroBean>();
        List<HeroBean> heroBeanList = new ArrayList<HeroBean>();
        try {
            heroBannerBean.setMode(WCMMode.fromRequest(getSlingRequest()).toString());
            final Node currentNode = getCurrentNode();
            if (currentNode != null) {
                if (currentNode.hasProperty(Constants.HERO.HERO_SELECTED_TAB_RADIO)) {
                    choosenNodename = currentNode.getProperty(Constants.HERO.HERO_SELECTED_TAB_RADIO).getValue().getString();
                    heroBannerBean.setType(choosenNodename);
                }
                if (choosenNodename != null) {
                    if (choosenNodename.equalsIgnoreCase(Constants.HERO.HERO_VIDEOS_NODE)) {
                        heroBeanList = getVideoDetails(currentNode);
                        newList = getSchedulerList(currentNode, heroBeanList);
                        HeroBean mainBean = heroBeanList.get(heroBeanList.size() - 1);
                        HeroBean newBean = newList.get(newList.size() - 1);
                        if (mainBean.getVideoPath().equalsIgnoreCase(newBean.getVideoPath())) {
                            heroBannerBean.setLastList(true);
                        }
                        if (newList.size() == Constants.ONE) {
                            heroBannerBean.setMultiple(false);
                        } else {
                            heroBannerBean.setMultiple(true);
                        }
                        heroBannerBean.setBannersList(newList);
                    } else if (choosenNodename.equalsIgnoreCase(Constants.HERO.HERO_BANNERS_NODE)) {
                        if (currentNode.getNodes() != null) {
                            NodeIterator heroNodesIterator = currentNode.getNodes();
                            while (heroNodesIterator.hasNext()) {
                                Node heroChildNode = (Node) heroNodesIterator.next();
                                if (heroChildNode.getName().equals(Constants.HERO.HERO_BANNERS_NODE)) {
                                    heroBeanList = getNodeProperties(heroChildNode);
                                    if (heroBeanList.size() == Constants.ONE) {
                                        heroBannerBean.setMultiple(false);
                                    } else {
                                        heroBannerBean.setMultiple(true);
                                    }
                                    heroBannerBean.setBannersList(heroBeanList);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOG.info("exception occured in heroBanner method of HeroBannerAction:");
        }
        return heroBannerBean;
    }

    /**
     * Gets the video details.
     *
     * @param currentNode the current node
     * @param resourceResolver the resource resolver
     * @param heroBeanList the hero bean list
     * @return the video details
     */
    private List<HeroBean> getVideoDetails(Node currentNode) {
        HeroBean heroBean = null;
        ResourceResolver resourceResolver = getSlingRequest().getResourceResolver();
        List<HeroBean> heroBeanList = new ArrayList<HeroBean>();
        try {
            if (currentNode.hasProperty(Constants.HERO.HERO_VIDEOS_NODE)) {
                String videopath = currentNode.getProperty(Constants.HERO.HERO_VIDEOS_NODE).getValue().getString();
                if (videopath != null) {
                    Resource resource = resourceResolver.getResource(videopath);
                    Node resourceNode = resource.adaptTo(Node.class);
                    if (resourceNode.hasNodes()) {
                        NodeIterator videoNodeIterator = resourceNode.getNodes();
                        while (videoNodeIterator.hasNext()) {
                            heroBean = new HeroBean();
                            Node videoNode = (Node) videoNodeIterator.next();
                            String nodeName = videoNode.getName();
                            if (!nodeName.equals(Constants.HERO.HERO_JCR_CONTENT_NODE)) {
                                if (videoNode.hasProperty(Constants.HERO.HERO_PRIMARY_TYPE_PROPERTY)) {
                                    String primaryType = videoNode.getProperty(Constants.HERO.HERO_PRIMARY_TYPE_PROPERTY)
                                            .getValue().getString();
                                    if (primaryType != null) {
                                        if (primaryType.equals(Constants.HERO.HERO_DAM_ASSET_PROPERTY)) {
                                            heroBean = getVideoHeroBean(heroBean, videopath, nodeName, resourceResolver);
                                            heroBeanList.add(heroBean);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOG.info("exception occured in getVideoDetails method of HeroBannerAction:");
        }
        return heroBeanList;
    }

    /**
     * Gets the video hero bean.
     *
     * @param heroBean the hero bean
     * @param videopath the video path
     * @param nodeName the node name
     * @param resourceResolver the resource resolver
     * @return the video hero bean
     */
    private HeroBean getVideoHeroBean(HeroBean heroBean, String videopath, String nodeName, ResourceResolver resourceResolver) {
        String renditionPath = videopath + Constants.HERO.HERO_BACKWARD_SLASH + nodeName;
        String imagePath = videopath + Constants.HERO.HERO_BACKWARD_SLASH + nodeName
                + Constants.HERO.HERO_JCR_CONTENT_PATH
                + Constants.HERO.HERO_RENDITIONS_PATH;
        String metaDataPath = videopath + Constants.HERO.HERO_BACKWARD_SLASH + nodeName
                + Constants.HERO.HERO_JCR_CONTENT_PATH + Constants.HERO.HERO_META_DATA_PATH;
        Resource renditionsResource = resourceResolver.getResource(renditionPath);
        Resource metaDataResource = resourceResolver.getResource(metaDataPath);
        heroBean = getMetaData(heroBean, metaDataResource, renditionPath);
        heroBean = getRenditions(heroBean, renditionsResource, imagePath);
        return heroBean;
    }

    /**
     * Gets the scheduler list.
     *
     * @param currentNode the current node
     * @param heroBeanList the hero bean list
     * @param newList the new list
     * @return the scheduler list
     */
    private List<HeroBean> getSchedulerList(Node currentNode, List<HeroBean> heroBeanList) {
        Long timeDifference = null;
        Integer index = null;
        Integer listSize = null;
        List<HeroBean> newList = new ArrayList<HeroBean>();
        try {
            if (currentNode.hasProperty(Constants.HERO.HERO_LIST_SIZE)) {
                listSize = Integer.parseInt(currentNode.getProperty(Constants.HERO.HERO_LIST_SIZE).getString());
            }
            if (listSize == null) {
                listSize = Constants.FIVE;
            }
            timeDifference = getTimeDifference(currentNode);
            index = getIndex(currentNode, timeDifference, listSize, index, heroBeanList);
            if ((index * listSize + listSize) <= heroBeanList.size()) {
                for (int i = index * listSize; i < (index * listSize + listSize); i++) {
                    newList.add(heroBeanList.get(i));
                }
            } else {
                for (int i = index * listSize; i < heroBeanList.size(); i++) {
                    newList.add(heroBeanList.get(i));
                }
            }
        } catch (Exception e) {
            LOG.info("exception occured in getSchedulerList method of HeroBannerAction:");
        }
        return newList;
    }

    /**
     * Gets the time difference.
     *
     * @param currentNode the current node
     * @param timeDifference the time difference
     * @return the time difference
     * @throws Exception the exception
     */
    private long getTimeDifference(Node currentNode) throws Exception {
        Long timeDifference = null;
        Calendar date1 = null;
        Calendar date = Calendar.getInstance();
        if (!currentNode.hasProperty(Constants.HERO.HERO_DATE_PROPERTY) || currentNode.hasProperty(Constants.HERO.HERO_MODIFY_DATE_PROPERTY)) {
            currentNode.setProperty(Constants.HERO.HERO_DATE_PROPERTY, date);
            currentNode.setProperty(Constants.HERO.HERO_MODIFY_DATE_PROPERTY, (Value) null);
            currentNode.save();
            date1 = currentNode.getProperty(Constants.HERO.HERO_DATE_PROPERTY).getDate();
        } else {

            date1 = currentNode.getProperty(Constants.HERO.HERO_DATE_PROPERTY).getDate();
        }
        timeDifference = date.getTimeInMillis() - date1.getTimeInMillis();
        return timeDifference;
    }

    /**
     * Gets the index.
     *
     * @param currentNode the current node
     * @param timeDifference the time difference
     * @param listSize the list size
     * @param index the index
     * @param heroBeanList the hero bean list
     * @return the index
     * @throws Exception the exception
     */
    private int getIndex(Node currentNode, Long timeDifference, Integer listSize, Integer index, List<HeroBean> heroBeanList) throws Exception {
        String rotationUnits = null;
        Long rotationRate = null;
        if (currentNode.hasProperty(Constants.HERO.HERO_ROTATION_RATE_PROPERTY)) {
            rotationRate = Long.parseLong(currentNode.getProperty(Constants.HERO.HERO_ROTATION_RATE_PROPERTY)
                    .getString());
        }
        if (currentNode.hasProperty(Constants.HERO.HERO_ROTATION_UNITS_PROPERTY)) {
            rotationUnits = currentNode.getProperty(Constants.HERO.HERO_ROTATION_UNITS_PROPERTY).getString();
        }
        if (rotationUnits != null && rotationRate != null) {
            index = SchedulerUtil.getIndex(timeDifference, rotationRate, rotationUnits, heroBeanList.size(), listSize);
        }
        return index;
    }

    /**
     * Gets the meta data.
     *
     * @param heroBean the hero bean
     * @param metaDataResource the meta data resource
     * @param renditionPath the rendition path
     * @return the meta data
     */
    private HeroBean getMetaData(HeroBean heroBean, Resource metaDataResource, String renditionPath) {
        LOG.info("entered into the getMetaData method of HeroBannerAction:");
        try {
            ValueMap meataDataMap = metaDataResource.adaptTo(ValueMap.class);
            heroBean.setBannerDescription(meataDataMap.get(Constants.HERO.HERO_META_DESCRIPTION, ""));
            heroBean.setBannerTitle(meataDataMap.get(Constants.HERO.HERO_META_TITLE, ""));
            heroBean.setAssetFormat(meataDataMap.get(Constants.HERO.HERO_META_FORMAT, ""));
            heroBean.setVideoPath(renditionPath);
            // at present every banner having video. because we are selecting video's path only.
            heroBean.setVideoFlag("on");
            // at present no asset path is configured
            heroBean.setAssetPath("");
        } catch (Exception e) {
            LOG.info("exception occured in getMetaData method of HeroBannerAction:");
        }
        return heroBean;
    }

    /**
     * Gets the renditions.
     *
     * @param heroBean the hero bean
     * @param renditionsResource the renditions resource
     * @param imagePath the image path
     * @return the renditions
     */
    private HeroBean getRenditions(HeroBean heroBean, Resource renditionsResource, String imagePath) {
        LOG.info("entered into the getRenditions method of HeroBannerAction:");
        try {
            Asset videoAsset = renditionsResource.adaptTo(Asset.class);
            List<Rendition> renditions = videoAsset.getRenditions();
            Iterator<Rendition> iterator = renditions.iterator();
            while (iterator.hasNext()) {
                String name = iterator.next().getName();
                String format = heroBean.getAssetFormat();
                if (format.contains("image")) {
                    // at present original rendition of the image will be added to the bean.
                    if (name.equalsIgnoreCase(Constants.HERO.HERO_ORIGINAL_RENDITION)) {
                        heroBean.setBannerImage(imagePath + name);
                    }
                } else {
                    String thumbnail = "cq5dam.thumbnail.319.319.png";
                    heroBean.setBannerImage(imagePath + thumbnail);
                }
            }
        } catch (Exception e) {
            LOG.info("exception occured in getRenditions method of HeroBannerAction:");
        }
        return heroBean;
    }

    /**
     * Gets the node properties.
     *
     * @param outerNode the outer node
     * @return the node properties
     */
    private List<HeroBean> getNodeProperties(Node outerNode) {
        LOG.info("entered into the getNodeProperties method of HeroBannerAction:");
        List<HeroBean> nodeList = new ArrayList<HeroBean>();
        try {
            NodeIterator nodeit = outerNode.getNodes();
            while (nodeit.hasNext()) {
                HeroBean bannerBean = new HeroBean();
                Node innerNode = (Node) nodeit.next();
                Resource resource = getSlingRequest().getResourceResolver().getResource(innerNode.getPath());
                ValueMap nodePropertiesMap = resource.adaptTo(ValueMap.class);
                bannerBean.setVideoPath(nodePropertiesMap.get(Constants.HERO.HERO_SELECTED_VIDEO, ""));
                bannerBean.setBannerImage(nodePropertiesMap.get(Constants.HERO.HERO_BANNER_IMAGEE, ""));
                bannerBean.setBannerCategory(nodePropertiesMap.get(Constants.HERO.HERO_BANNER_CATEGORY, ""));
                bannerBean.setBannerTitle(nodePropertiesMap.get(Constants.HERO.HERO_BANNER_TITLE, ""));
                bannerBean.setButtonText(nodePropertiesMap.get(Constants.HERO.HERO_CTA_BUTTON_TITLE, ""));
                bannerBean.setBannerDescription(nodePropertiesMap.get(Constants.HERO.HERO_BANNER_DESCRIPTION, ""));
                String assetPath = nodePropertiesMap.get(Constants.HERO.HERO_ASSET_PATH, "#");

                if (assetPath != "" && assetPath != "#") {
                    String assetlink = null;
                    if (assetPath.contains("/content")) {
                        assetlink = assetPath + ".html";
                    } else {
                        assetlink = assetPath;
                    }
                    bannerBean.setAssetPath(assetlink);
                } else {
                    bannerBean.setAssetPath(assetPath);
                }
                bannerBean.setVideoFlag(nodePropertiesMap.get(Constants.HERO.HERO_IS_VIDEO, ""));
                bannerBean.setAssetFormat(Constants.HERO.HERO_IMAGE_VIDEO);
                nodeList.add(bannerBean);
            }
        } catch (Exception exception) {
            LOG.info("exception occured in getNodeProperties method of HeroBannerAction:");
        }
        return nodeList;
    }
}
