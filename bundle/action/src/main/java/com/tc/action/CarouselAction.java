/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.Value;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import com.tc.action.BaseAction;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.model.BannerBean;
import com.tc.model.CarouselBean;
import com.tc.util.Constants;
import com.tc.util.SchedulerUtil;


public class CarouselAction extends BaseAction {

    /**
     * The log.
     */
    private Logger log = FrameworkLogger.getLogger();

    /**
     * This method contains the logic to build CarouselBean which is going to be
     * displayed in carousel component.
     *
     * @return the t
     */
    public CarouselBean getCarouselInfo() {
        CarouselBean carouselBean = new CarouselBean();
        String selectedOption = null;

        log.info("inside execute");

        Node carouselNode = getCurrentNode();
        try {
            if (carouselNode.hasProperty(Constants.CAROUSEL.SELECTED_TAB)
                    && StringUtils.isNotBlank(carouselNode.getProperty(Constants.CAROUSEL.SELECTED_TAB).getString())) {
                selectedOption = carouselNode.getProperty(Constants.CAROUSEL.SELECTED_TAB).getString();

                if (selectedOption.equals(Constants.CAROUSEL.CAROUSEL_NODE)) {

                    carouselBean = fetchDetailsSimple(carouselNode);
                    carouselBean.setCarouselType("simple");

                } else if (selectedOption.equals(Constants.CAROUSEL.IMAGES)) {
                    carouselBean = fetchDetailsAdvanced(carouselNode);
                    carouselBean.setCarouselType("advanced");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return carouselBean;
    }

    /**
     * Fetch details simple.
     *
     * @param carouselNode the nod
     * @return the carousel bean
     */
    private CarouselBean fetchDetailsSimple(Node carouselNode) {

        Node itemNode = null;
        Node bannersNode = null;
        NodeIterator bannersNodeIterator = null;
        PropertyIterator propertyIterator = null;
        CarouselBean carouselBean = new CarouselBean();

        try {
            List<BannerBean> bannerList = new ArrayList<BannerBean>();

            if (carouselNode != null && carouselNode.getNodes().hasNext()) {

                NodeIterator carouselNodeIterator = carouselNode.getNodes();
                while (carouselNodeIterator.hasNext()) {

                    bannersNode = (Node) carouselNodeIterator.next();

                    bannersNodeIterator = bannersNode.getNodes();
                    while (bannersNodeIterator.hasNext()) {

                        itemNode = (Node) bannersNodeIterator.next();
                        propertyIterator = itemNode.getProperties();
                        BannerBean bannerBean = setPropertiesSimple(propertyIterator);
                        bannerList.add(bannerBean);

                        if (bannerList.size() == Constants.ONE) {
                            carouselBean.setMultiple(false);
                        } else {
                            carouselBean.setMultiple(true);
                        }

                    }

                }

            }
            carouselBean.setBannersList(bannerList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return carouselBean;

    }

    /**
     * Fetch details advanced.
     *
     * @param carouselNode the nod
     * @return the carousel bean
     */
    @SuppressWarnings("deprecation")
    private CarouselBean fetchDetailsAdvanced(Node carouselNode) {
        CarouselBean carouselBean = new CarouselBean();
        BannerBean bannerBean = null;
        Node node2 = null;
        String imagePath = null;
        String sliderImage = null;
        Long timeInterval = null;
        String timeUnit = null;
        Calendar date1 = null;
        Long timeDifference = null;
        Integer index = null;
        Integer lot = null;
        List<BannerBean> bannerList = new ArrayList<BannerBean>();
        try {

            if (carouselNode != null && carouselNode.hasProperty(Constants.CAROUSEL.IMAGES) && StringUtils.isNotBlank(carouselNode.getProperty(Constants.CAROUSEL.IMAGES).getString())) {
                imagePath = carouselNode.getProperty(Constants.CAROUSEL.IMAGES).getString();
                Node node1 = getSlingRequest().getResourceResolver().getResource(imagePath).adaptTo(Node.class);
                NodeIterator nodeIterator = node1.getNodes();
                while (nodeIterator.hasNext()) {
                    bannerBean = new BannerBean();
                    node2 = (Node) nodeIterator.next();

                    if (!(node2.getName().equals(Constants.CAROUSEL.CAROUSEL_JCR_CONTENT_NODE))) {
                        sliderImage = imagePath + "/" + node2.getName();
                        bannerBean.setSliderImage(sliderImage);
                        bannerList.add(bannerBean);
                    }

                }

                Calendar date = Calendar.getInstance();
                if (!carouselNode.hasProperty(Constants.CAROUSEL.DATE) || carouselNode.hasProperty("modifyDate")) {
                    carouselNode.setProperty(Constants.CAROUSEL.DATE, date);

                    date1 = carouselNode.getProperty(Constants.CAROUSEL.DATE).getDate();
                    carouselNode.setProperty("modifyDate", (Value) null);
                    carouselNode.save();
                } else {

                    date1 = carouselNode.getProperty(Constants.CAROUSEL.DATE).getDate();

                }

                timeDifference = date.getTimeInMillis() - date1.getTimeInMillis();
                if (carouselNode.hasProperty(Constants.CAROUSEL.TIME_INTERVAL)) {
                    timeInterval = Long.parseLong(carouselNode.getProperty(Constants.CAROUSEL.TIME_INTERVAL).getString());
                }
                if (carouselNode.hasProperty(Constants.CAROUSEL.TIME_UNIT)) {
                    timeUnit = carouselNode.getProperty(Constants.CAROUSEL.TIME_UNIT).getString();
                }
                if (carouselNode.hasProperty(Constants.CAROUSEL.LOT)) {
                    lot = Integer.parseInt(carouselNode.getProperty(Constants.CAROUSEL.LOT).getString());
                }

                if (lot == null) {
                    lot = 5;
                }
                index = SchedulerUtil.getIndex(timeDifference, timeInterval, timeUnit, bannerList.size(), lot);

                List<BannerBean> newList = new ArrayList<BannerBean>();

                if ((index * lot + lot) <= bannerList.size()) {
                    for (Integer i = index * lot; i < (index * lot + lot); i++) {
                        newList.add(bannerList.get(i));
                    }
                } else {
                    for (Integer i = index * lot; i < bannerList.size(); i++) {
                        newList.add(bannerList.get(i));
                    }
                }
                if (newList.size() == 1) {
                    carouselBean.setMultiple(false);
                } else {
                    carouselBean.setMultiple(true);
                }

                if (newList.get(newList.size() - 1).getSliderImage().equals(bannerList.get(bannerList.size() - 1).getSliderImage())) {
                    carouselBean.setLastSlide(true);
                }
                carouselBean.setBannersList(newList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return carouselBean;
    }

    /**
     * Sets the properties simple.
     *
     * @param propertyIterator the iterator
     * @return the banner bean
     */
    private BannerBean setPropertiesSimple(PropertyIterator propertyIterator) {
        String sliderImage = null;
        String category = null;
        String description = null;
        String title = null;

        String imageUrl = null;
        Property property = null;

        BannerBean bannerBean = new BannerBean();
        while (propertyIterator.hasNext()) {

            property = (Property) propertyIterator.next();

            try {
                if (property.getName().equals(Constants.CAROUSEL.SLIDER_IMAGE)) {
                    sliderImage = property.getValue().getString();
                    bannerBean.setSliderImage(sliderImage);
                }
                if (property.getName().equals(Constants.CAROUSEL.CATEGORY)) {
                    category = property.getValue().getString();
                    bannerBean.setCategory(category);
                }
                if (property.getName().equals(Constants.CAROUSEL.DESCRIPTION)) {
                    description = property.getValue().getString();
                    bannerBean.setDescription(description);
                }
                if (property.getName().equals(Constants.CAROUSEL.TITLE)) {
                    title = property.getValue().getString();
                    bannerBean.setTitle(title);
                }
                if (property.getName().equals(Constants.CAROUSEL.IMAGE_URL)) {
                    imageUrl = property.getValue().getString();

                    if (imageUrl != null && StringUtils.isNotEmpty(imageUrl)) {
                        bannerBean.setImageUrl(imageUrl + ".html");
                    } else {
                        bannerBean.setImageUrl("#");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return bannerBean;
    }
}
