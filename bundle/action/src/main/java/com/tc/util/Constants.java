/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.util;

/**
 * The Interface Constants.
 */
public interface Constants {

    /**
     * The Constant SIXTY.
     */
    public static final Integer SIXTY = 60;

    /**
     * The Constant THOUSAND.
     */
    public static final Integer THOUSAND = 1000;

    /**
     * The Constant FIVE.
     */
    public static final Integer FIVE = 5;

    /**
     * The Constant TWENTY_FOUR.
     */
    public static final Integer TWENTY_FOUR = 24;

    /**
     * The Constant ZERO.
     */
    public static final Integer ZERO = 0;

    /**
     * The Constant ONE.
     */
    public static final Integer ONE = 1;

    /**
     * The Constant THREE_HUNDRED.
     */
    public static final Integer THREE_HUNDRED = 300;

    /**
     * The Constant NINE_HUNDRED_AND_SIXTY.
     */
    public static final Integer NINE_HUNDRED_AND_SIXTY = 960;

    /**
     * The Constant NINE_HUNDRED_AND_SIXTY.
     */
    // todo: wrong. get from properties file
    public static final String ROOT_PAGE = "/content/launchpad/us/en";

    /**
     * The Interface HERO.
     */
    public interface HERO {

        /**
         * The Constant HERO_ROTATION_RATE.
         */
        public static final String HERO_ROTATION_RATE = "./rotationRate";

        /**
         * The Constant HERO_BANNER_IMAGEE.
         */
        public static final String HERO_BANNER_IMAGEE = "bannerImage";

        /**
         * The Constant HERO_BANNER_DESCRIPTION.
         */
        public static final String HERO_BANNER_DESCRIPTION = "bannerDescription";

        /**
         * The Constant HERO_BANNER_TITLE.
         */
        public static final String HERO_BANNER_TITLE = "bannerTitle";

        /**
         * The Constant HERO_BANNER_URL.
         */
        public static final String HERO_BANNER_URL = "bannerUrl";

        /**
         * The Constant HERO_IS_SHARE.
         */
        public static final String HERO_IS_SHARE = "isShare";

        /**
         * The Constant HERO_BANNER_URL_TEXT.
         */
        public static final String HERO_BANNER_URL_TEXT = "bannerUrlText";

        /**
         * The Constant HERO_CTA_BUTTON_TITLE.
         */
        public static final String HERO_CTA_BUTTON_TITLE = "ctabuttontitle";

        /**
         * The Constant HERO_ASSET_PATH.
         */
        public static final String HERO_ASSET_PATH = "assetPath";

        /**
         * The Constant HERO_VIDEO_THUMB_NAIL.
         */
        public static final String HERO_VIDEO_THUMB_NAIL = "videoThumbnail";

        /**
         * The Constant HERO_DISABLE_INTERNAL.
         */
        public static final String HERO_DISABLE_INTERNAL = "disableInternal";

        /**
         * The Constant HERO_DISABLE_BANNER.
         */
        public static final String HERO_DISABLE_BANNER = "disableBanner";

        /**
         * The Constant HERO_IS_VIDEO.
         */
        public static final String HERO_IS_VIDEO = "isVideo";

        /**
         * The Constant HERO_IS_VIDEO.
         */
        public static final String HERO_IMAGE_VIDEO = "image/video";

        /**
         * The Constant HERO_YES.
         */
        public static final String HERO_YES = "yes";

        /**
         * The Constant HERO_DOT_HTML.
         */
        public static final String HERO_DOT_HTML = ".html";

        /**
         * The hero one.
         */
        public int HERO_ONE = 1;

        /**
         * The Constant HERO_SELECTED_VIDEO.
         */
        public static final String HERO_SELECTED_VIDEO = "selectVideo";

        /**
         * The Constant HERO_SECS.
         */
        public static final String HERO_SECS = "seconds";

        /**
         * The Constant HERO_MINS.
         */
        public static final String HERO_MINS = "minutes";

        /**
         * The Constant HERO_HRS.
         */
        public static final String HERO_HRS = "hours";

        /**
         * The Constant HERO_DAYS.
         */
        public static final String HERO_DAYS = "days";

        /**
         * The Constant HERO_ORIGINAL_RENDITION.
         */
        public static final String HERO_ORIGINAL_RENDITION = "original";

        /**
         * The Constant HERO_META_DESCRIPTION.
         */
        public static final String HERO_META_DESCRIPTION = "dc:description";

        /**
         * The Constant HERO_META_TITLE.
         */
        public static final String HERO_META_TITLE = "dc:title";

        /**
         * The Constant HERO_META_TITLE.
         */
        public static final String HERO_META_FORMAT = "dc:format";

        /**
         * The Constant HERO_ROTATION_UNITS.
         */
        public static final String HERO_ROTATION_UNITS = "rotationUnits";

        /**
         * The Constant HERO_SELECTED_TAB_RADIO.
         */
        public static final String HERO_SELECTED_TAB_RADIO = "selectedTabRadio";

        /**
         * The Constant HERO_VIDEOS_NODE.
         */
        public static final String HERO_VIDEOS_NODE = "videos";

        /**
         * The Constant HERO_BANNERS_NODE.
         */
        public static final String HERO_BANNERS_NODE = "banners";

        /**
         * The Constant HERO_VIDEO_PATH_PROPERTY.
         */
        public static final String HERO_VIDEO_PATH_PROPERTY = "videoPath";

        /**
         * The Constant HERO_PRIMARY_TYPE_PROPERTY.
         */
        public static final String HERO_PRIMARY_TYPE_PROPERTY = "jcr:primaryType";

        /**
         * The Constant HERO_DAM_ASSET_PROPERTY.
         */
        public static final String HERO_DAM_ASSET_PROPERTY = "dam:Asset";

        /**
         * The Constant HERO_JCR_CONTENT_NODE.
         */
        public static final String HERO_JCR_CONTENT_NODE = "jcr:content";

        /**
         * The Constant HERO_BACKWARD_SLASH.
         */
        public static final String HERO_BACKWARD_SLASH = "/";

        /**
         * The Constant HERO_JCR_CONTENT_PATH.
         */
        public static final String HERO_JCR_CONTENT_PATH = "/jcr:content/";

        /**
         * The Constant HERO_RENDITIONS_PATH.
         */
        public static final String HERO_RENDITIONS_PATH = "renditions/";

        /**
         * The Constant HERO_META_DATA_PATH.
         */
        public static final String HERO_META_DATA_PATH = "metadata";

        /**
         * The Constant HERO_BANNER_CATEGORY.
         */
        public static final String HERO_BANNER_CATEGORY = "bannerCategory";

        /**
         * The Constant HERO_META_DATA_PATH.
         */
        public static final String HERO_DATE_PROPERTY = "date";

        /**
         * The Constant HERO_META_DATA_PATH.
         */
        public static final String HERO_MODIFY_DATE_PROPERTY = "modifyDate";

        /**
         * The Constant HERO_META_DATA_PATH.
         */
        public static final String HERO_ROTATION_RATE_PROPERTY = "rotationRate";

        /**
         * The Constant HERO_META_DATA_PATH.
         */
        public static final String HERO_ROTATION_UNITS_PROPERTY = "rotationUnits";

        /**
         * The Constant HERO_LIST_SIZE.
         */
        public static final String HERO_LIST_SIZE = "listSize";

    }

    /**
     * The Interface SMARTNAVIGATION.
     */
    public interface SMARTNAVIGATION {

        /**
         * The Constant SMART_NAVIGATION_PARENT_PAGE.
         */
        public static final String SMART_NAVIGATION_PARENT_PAGE = "./parentPage";

        /**
         * The Constant SMART_NAVIGATION_JCR_CONTENT.
         */
        public static final String SMART_NAVIGATION_JCR_CONTENT = "jcr:content";

        /**
         * The Constant SMART_NAVIGATION_PAR_NODE.
         */
        public static final String SMART_NAVIGATION_PAR_NODE = "par";

        /**
         * The Constant SMART_NAVIGATION_BACKWARD_SLASH.
         */
        public static final String SMART_NAVIGATION_BACKWARD_SLASH = "/";

        /**
         * The Constant SMART_NAVIGATION_PAGE_PATH.
         */
        public static final String SMART_NAVIGATION_PAGE_PATH = "/jcr:content/par.html";

    }

    /**
     * The Interface CAROUSEL.
     */
    public interface CAROUSEL {

        /**
         * The Constant SELECTED_TAB.
         */
        public static final String SELECTED_TAB = "selectedTabRadio";

        /**
         * The Constant CAROUSEL_NODE.
         */
        public static final String CAROUSEL_NODE = "banners";

        /**
         * The Constant SLIDER_IMAGE.
         */
        public static final String SLIDER_IMAGE = "sliderImage";

        /**
         * The Constant OVERLAY_TEXT.
         */
        public static final String CATEGORY = "category";

        /**
         * The Constant OVERLAY_DESCRIPTION.
         */
        public static final String DESCRIPTION = "description";

        /**
         * The Constant TITLE.
         */
        public static final String TITLE = "title";

        /**
         * The Constant LOT.
         */
        public static final String LOT = "./lot";

        /**
         * The Constant IMAGE_URL.
         */
        public static final String IMAGE_URL = "imageUrl";

        /**
         * The Constant IMAGES.
         */
        public static final String IMAGES = "images";

        /**
         * The Constant DATE.
         */
        public static final String DATE = "date";

        /**
         * The Constant TIME_INTERVAL.
         */
        public static final String TIME_INTERVAL = "timeInterval";

        /**
         * The Constant TIME_UNIT.
         */
        public static final String TIME_UNIT = "timeUnit";

        /**
         * The Constant SECONDS.
         */
        public static final String SECONDS = "seconds";

        /**
         * The Constant MINUTES.
         */
        public static final String MINUTES = "minutes";

        /**
         * The Constant HOURS.
         */
        public static final String HOURS = "hours";

        /**
         * The Constant DAYS.
         */
        public static final String DAYS = "days";

        /**
         * The Constant HERO_JCR_CONTENT_NODE.
         */
        public static final String CAROUSEL_JCR_CONTENT_NODE = "jcr:content";

    }

    /**
     * The Interface MAP.
     */
    public interface MAP {

        /**
         * The Constant HEIGHT.
         */
        public static final String HEIGHT = "./height";

        /**
         * The Constant WIDTH.
         */
        public static final String WIDTH = "./width";

        /**
         * The Constant LOCATION.
         */
        public static final String LOCATION = "./location";

        /**
         * The Constant COMMA.
         */
        public static final String COMMA = ",";

        /**
         * The Constant SPACE.
         */
        public static final String SPACE = " ";

        /**
         * The Constant PLUS.
         */
        public static final String PLUS = "+";

        /**
         * The Constant EMPTY_STRING.
         */
        public static final String EMPTY_STRING = "";
    }

    /**
     * The Interface SHORTARTICLE.
     */
    public interface SHORTARTICLE {

        /**
         * The Constant SELECTED_TAB.
         */
        public static final String SELECTED_TAB = "selectedTabRadio";

        /**
         * The Constant SHOW_IMPRESSIONS.
         */
        public static final String SHOW_IMPRESSIONS = "showImpressions";

        /**
         * The Constant SHOW_IMPRESSIONS_FOR.
         */
        public static final String SHOW_IMPRESSIONS_FOR = "showImpressionFor";

        /**
         * The Constant BUTTON_TEXT.
         */
        public static final String BUTTON_TEXT = "buttonText";

        /**
         * The Constant GENERAL.
         */
        public static final String GENERAL = "general";

        /**
         * The Constant LINK.
         */
        public static final String LINK = "link";

        /**
         * The Constant ADVANCED.
         */
        public static final String ADVANCED = "advanced";

        /**
         * The Constant IMAGTIMEE_URL.
         */
        public static final String TIME = "time";

        /**
         * The Constant ARTICLE_FOLDER_PATH.
         */
        public static final String ARTICLE_FOLDER_PATH = "articleFolderPath";

        /**
         * The Constant TIMEUNIT.
         */
        public static final String TIMEUNIT = "timeUnit";

        /**
         * The Constant TIME_INTERVAL.
         */
        public static final String TIME_INTERVAL = "timeInterval";

        /**
         * The Constant VIEWS.
         */
        public static final String VIEWS = "views";

        /**
         * The Constant DAY.
         */
        public static final String DAY = "day";

        /**
         * The Constant MONTH.
         */
        public static final String MONTH = "month";

        /**
         * The Constant YEAR.
         */
        public static final String YEAR = "year";

        /**
         * The Constant HERO_JCR_CONTENT_NODE.
         */
        public static final String lONGARTICLE_JCR_CONTENT_NODE = "/jcr:content/leftpar/long_article";

        /**
         * The Constant CATEGORY.
         */
        public static final String CATEGORY = "category";

        /**
         * The Constant TITLE.
         */
        public static final String TITLE = "title";

        /**
         * The Constant FILEREFERENCE.
         */
        public static final String FILEREFERENCE = "fileReference";

        /**
         * The Constant DESCRIPTION.
         */
        public static final String DESCRIPTION = "description";

        /**
         * The Constant ERROR_MESSAGE.
         */
        public static final String ERROR_MESSAGE = "There are no more articles to display";

        /**
         * The Constant SHORT_DOT_HTML.
         */
        public static final String SHORT_DOT_HTML = ".html";

        /**
         * The Constant SHORT_DOT_HTML.
         */
        public static final String JCR_CONTENT = "jcr:content";

        /**
         * The Constant LEFT_PAR.
         */
        public static final String LEFT_PAR = "leftpar";

        /**
         * The Constant LONG_ARTICLE.
         */
        public static final String LONG_ARTICLE = "long_article";

        /**
         * The Constant ITEM_BEGIN.
         */
        public static final String ITEM_BEGIN = "ITEM_BEGIN";

    }

    /**
     * The Interface TOPNAVIGATION.
     */
    public interface TOPNAVIGATION {

        /**
         * The Constant PARENTPAGE.
         */
        public static final String PARENTPAGE = "parentPage";
        /**
         * The Constant HTML.
         */
        public static final String HTML = ".html";
        /**
         * The Constant JCRCONTENT.
         */
        public static final String JCRCONTENT = "jcr:content";
        /**
         * The Constant HIDEINNAV.
         */
        public static final String HIDEINNAV = "hideInNav";

    }

    /**
     * The Interface LONGARTICLE.
     */
    public interface LONGARTICLE {

        /**
         * The Constant TITLE.
         */
        public static final String TITLE = "title";

        /**
         * The Constant DESCRIPTION.
         */
        public static final String DESCRIPTION = "description";

        /**
         * The Constant FILEREFERENCE.
         */
        public static final String FILEREFERENCE = "fileReference";

        /**
         * The Constant LISTFROM.
         */
        public static final String LISTFROM = "listFrom";

        /**
         * The Constant LISTFROM.
         */
        public static final String ASSET = "asset";
    }

    /**
     * The Interface SOCIALFOLLOW.
     */
    public interface SOCIALFOLLOW {

        /**
         * The Constant TITLE.
         */
        public static final String TITLE = "title";

        /**
         * The Constant FACEBOOK.
         */
        public static final String FACEBOOK = "facebook";

        /**
         * The Constant TWITTER.
         */
        public static final String TWITTER = "twitter";

        /**
         * The Constant GOOGLEPLUS.
         */
        public static final String GOOGLEPLUS = "googleplus";

        /**
         * The Constant PAGEFOLLOWERS.
         */
        public static final String PAGEFOLLOWERS = "pageFollowers";

    }

    /**
     * The Interface CATEGORIES.
     */
    public interface CATEGORIES {

        /**
         * The Constant LINKS.
         */
        public static final String LEVEL = "level";

        /**
         * The Constant SOCIAL_LINK.
         */
        public static final String JCR_CONTENT = "jcr:content";

        /**
         * The Constant ICON.
         */
        public static final String ICON = "icon";

        public static final String JCR_TITLE = "jcr:title";

        /**
         * The Constant HIDEINNAV.
         */
        public static final String HIDEINNAV = "hideInNav";

    }
    
    /**
     * The Interface ARTICLE.
     */
    public interface ARTICLE {
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
        public static final String SECTIONS = "tagSections";
        public static final String CATEGORIES = "tagCategories";
        public static final String SHARE = "tagShare";
        public static final String PUBLICATION_DATE = "publicationDate";
        public static final String EXPIRATION_DATE = "expirationDate";
    }

}
