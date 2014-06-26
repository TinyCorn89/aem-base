/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.managers.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.jcr.Node;
import javax.jcr.Session;

import org.apache.commons.lang.StringUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;

import com.day.cq.commons.date.DateUtil;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.SimpleSearch;
import com.day.cq.search.Trends;
import com.day.cq.search.result.SearchResult;
import com.day.cq.tagging.Tag;
import com.day.cq.wcm.api.Page;
import com.tc.framework.api.ManagerProvider;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.managers.SearchService;

// TODO: Auto-generated Javadoc
/**
 * The Class SearchServiceImpl.
 */
@Component(label = "Search Service", description = "Provides the Application config variables", immediate = true, metatype = true)
@Properties({
    @Property(name = "service.pid", value = "org.virtusa.launchpad.framework.api.SearchServiceImpl", propertyPrivate = false),
    @Property(name = "service.description", value = "Provides the Search Service", propertyPrivate = false)})
@Service({SearchService.class})

public class SearchServiceImpl implements SearchService {

    /**
     * The logger.
     */
    private Logger logger = FrameworkLogger.getLogger();

    /**
     * The results per page.
     */
    private static String resultsPerPage = "10";

    /* (non-Javadoc)
     * @see org.virtusa.launchpad.managers.SearchService#advancedsearch(org.apache.sling.api.SlingHttpServletRequest)
     */
    public Map<String, Object> advancedsearch(SlingHttpServletRequest request) throws Exception {
        logger.info("Enetered in to advancedsearch method ");
        Map<String, Object> responseMap = new HashMap<String, Object>();
        QueryBuilder builder = ManagerProvider.getManager(QueryBuilder.class);
        Map<String, Object> predicateMap = new LinkedHashMap<String, Object>();
        String resultsPerPage = "10";

        predicateMap.put("type", "cq:Page");
        /* Section Starts Here */
        if (StringUtils.isNotBlank(request.getParameter("sectionTab"))) {
            predicateMap.put("path", request.getParameter("sectionTab"));
        } /* Content Path Setting */ else if (StringUtils.isNotBlank(request.getParameter("contentPath"))) {
            predicateMap.put("path", request.getParameter("contentPath"));
        } else {
            predicateMap.put("path", "/content/launchpad/us/en");
        }

        /* Search Keyword Starts Here */
        if (StringUtils.isNotBlank(request.getParameter("keyword"))) {
            predicateMap.put("fulltext", request.getParameter("keyword"));
        }

        /* Author Starts Here */
        if (StringUtils.isNotBlank(request.getParameter("author"))) {
            predicateMap.put("1_property", "jcr:content/cq:lastModifiedBy");
            predicateMap.put("1_property.value", request.getParameter("author"));
        }

        /* Time Range Starts Here */
        if (request.getParameter("timeRange") != null) {
            if (!request.getParameter("timeRange").equals("DD")) {
                predicateMap.put("1_daterange.property", "jcr:content/cq:lastReplicated");
                /*predicateMap.put("1_daterange",Long.parseLong(request.getParameter("timeRange")));*/

                DateUtil dates = new DateUtil();

                Calendar endOfToday = dates.getToday();
                endOfToday.add(10, 24);

                if (request.getParameter("timeRange").equals("-7d")) {
                    predicateMap.put("1_daterange.lowerBound", DateUtil.getISO8601DateNoTime(dates.getWeekStart()));
                    predicateMap.put("1_daterange.lowerOperation", ">=");
                    predicateMap.put("1_daterange.upperBound", DateUtil.getISO8601DateNoTime(endOfToday));
                    predicateMap.put("1_daterange.upperOperation", "<=");
                }
                if (request.getParameter("timeRange").equals("-1M")) {
                    predicateMap.put("1_daterange.lowerBound", DateUtil.getISO8601DateNoTime(dates.getMonthStart()));
                    predicateMap.put("1_daterange.lowerOperation", ">=");
                    predicateMap.put("1_daterange.upperBound", DateUtil.getISO8601DateNoTime(endOfToday));
                    predicateMap.put("1_daterange.upperOperation", "<=");
                }
                if (request.getParameter("timeRange").equals("-3M")) {
                    predicateMap.put("1_daterange.lowerBound", DateUtil.getISO8601DateNoTime(dates.getThreeMonthsAgo()));
                    predicateMap.put("1_daterange.lowerOperation", ">=");
                    predicateMap.put("1_daterange.upperBound", DateUtil.getISO8601DateNoTime(endOfToday));
                    predicateMap.put("1_daterange.upperOperation", "<=");
                }

                if (request.getParameter("timeRange").equals("1Y")) {
                    predicateMap.put("1_daterange.lowerBound", DateUtil.getISO8601DateNoTime(dates.getYearStart()));
                    predicateMap.put("1_daterange.lowerOperation", ">=");
                    predicateMap.put("1_daterange.upperBound", DateUtil.getISO8601DateNoTime(endOfToday));
                    predicateMap.put("1_daterange.upperOperation", "<=");
                }

                if (request.getParameter("timeRange").equals("-15Y")) {
                    predicateMap.put("1_daterange.lowerBound", "2001-01-01");
                    predicateMap.put("1_daterange.lowerOperation", ">=");
                    predicateMap.put("1_daterange.upperBound", DateUtil.getISO8601DateNoTime(endOfToday));
                    predicateMap.put("1_daterange.upperOperation", "<=");
                }

            } else if (StringUtils.isNotBlank(request.getParameter("dateFromMM"))
                    && StringUtils.isNotBlank(request.getParameter("dateFromDD"))
                    && StringUtils.isNotBlank(request.getParameter("dateFromYYYY"))
                    && StringUtils.isNotBlank(request.getParameter("dateToMM"))
                    && StringUtils.isNotBlank(request.getParameter("dateToDD"))
                    && StringUtils.isNotBlank(request.getParameter("dateToYYYY"))) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String sFromDate = request.getParameter("dateFromYYYY") + "-" + request.getParameter("dateFromMM") + "-" + request.getParameter("dateFromDD");
                String sToDate = request.getParameter("dateToYYYY") + "-" + request.getParameter("dateToMM") + "-" + request.getParameter("dateToDD");
                Date dFromDate = sdf.parse(sFromDate);
                Date dToDate = sdf.parse(sToDate);
                Date fromDate = new Date(dFromDate.getTime() + (24 * 60 * 60 * 1000));
                Date toDate = new Date(dToDate.getTime() + (24 * 60 * 60 * 1000));
                predicateMap.put("1_daterange.property", "jcr:content/cq:lastReplicated");
                predicateMap.put("1_daterange.lowerBound", DateUtil.getISO8601Date(fromDate));
                predicateMap.put("2_daterange.property", "jcr:content/cq:lastReplicated");
                predicateMap.put("2_daterange.upperBound", DateUtil.getISO8601Date(toDate));
            }
        }

        /* ContentType Starts Here */
        if (StringUtils.isNotBlank(request.getParameter("contentType"))) {
            if (request.getParameter("contentType").equals("article")) {
                predicateMap.put("path", request.getParameter("articlePath"));
            } else {
                predicateMap.put("type", "dam:Asset");
                predicateMap.put("path", request.getParameter("damPath"));
                predicateMap.put("group.p.or", "true");
                String imageFileFormats[];
                if (request.getParameter("contentType").toString().equals("image")) {
                    imageFileFormats = new String[]{"jpeg", "png", "bmp", "gif"};

                } else {
                    imageFileFormats = new String[]{"wmv", "mp4", "avi", "flv", "mpg", "swf", "asf", "MPEG", "x-ms-wmv"};
                }

                for (int i = 0; i < imageFileFormats.length; i++) {
                    predicateMap.put("group." + (i + 1) + "_property", "jcr:content/metadata/dc:format");
                    predicateMap.put("group." + (i + 1) + "_property.value", request.getParameter("contentType") + "/" + imageFileFormats[i]);
                }
            }
        }

        /* Tag Search Starts Here */
        if (StringUtils.isNotBlank(request.getParameter("tagKeyword"))) {
            predicateMap.put("3_property", "jcr:content/cq:tags");
            predicateMap.put("3_property.value", request.getParameter("tagKeyword"));
        }

        /* MIME Starts Here */
        /*if(StringUtils.isNotBlank(request.getParameter("mimeType"))){
         predicateMap.put("4_property", "jcr:content/jcr:mimeType");
         predicateMap.put("4_property.value",request.getParameter("mimeType"));
         }*/

        /* Pagination index filtering */
        predicateMap.put("orderby.sort", "asc");
        if (StringUtils.isNotBlank(request.getParameter("currentIndex"))) {
            predicateMap.put("p.offset", String.valueOf((Integer.parseInt(request.getParameter("currentIndex")) * Integer.parseInt(resultsPerPage))));
        } else {
            predicateMap.put("p.offset", "0");
        }
        predicateMap.put("p.limit", resultsPerPage);

        logger.info(" queryMap    " + predicateMap);

        ResourceResolver resolver = request.getResourceResolver();
        Session session = resolver.adaptTo(Session.class);

        Query query = builder.createQuery(PredicateGroup.create(predicateMap), session);

        SearchResult searchResult = query.getResult();
        responseMap.put("SearchResult", searchResult);


        /* Tagging Work Starts Here */
        Set<Tag> tagList = null;
        try {
            tagList = getPageTags(searchResult, request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        responseMap.put("numberOfPages", getPaginationCalculation(searchResult));
        responseMap.put("tags", tagList);
        responseMap.put("trends", getPageTrends(request));
        return responseMap;
    }


    /* (non-Javadoc)
     * @see org.virtusa.launchpad.managers.SearchService#simpleSearch(org.apache.sling.api.SlingHttpServletRequest)
     */
    public Map<String, Object> simpleSearch(SlingHttpServletRequest request) throws Exception {
        logger.info("Enetered in to simpleSearch method ");
        Map<String, Object> responseMap = new HashMap<String, Object>();
        QueryBuilder builder = ManagerProvider.getManager(QueryBuilder.class);
        Map<String, Object> predicateMap = new LinkedHashMap<String, Object>();
        String resultsPerPage = "10";
        predicateMap.put("type", "cq:Page");
        /* Path Setting */
        if (StringUtils.isNotBlank(request.getParameter("simpleContentPath"))) {
            predicateMap.put("path", request.getParameter("simpleContentPath"));
        } else {
            predicateMap.put("path", "/content/launchpad/us/en");
        }

        /* Search Keyword Starts Here */
        if (StringUtils.isNotBlank(request.getParameter("q"))) {
            predicateMap.put("fulltext", request.getParameter("q"));
        }

        /* Pagination index filtering */
        predicateMap.put("orderby.sort", "asc");
        if (StringUtils.isNotBlank(request.getParameter("currentIndex"))) {
            predicateMap.put("p.offset", String.valueOf((Integer.parseInt(request.getParameter("currentIndex").toString()) * Integer.parseInt(resultsPerPage))));
        } else {
            predicateMap.put("p.offset", "0");
        }
        predicateMap.put("p.limit", resultsPerPage);

        ResourceResolver resolver = request.getResourceResolver();
        Session session = resolver.adaptTo(Session.class);
        logger.info(":::: searchMap :::: " + predicateMap);
        Query query = builder.createQuery(PredicateGroup.create(predicateMap), session);

        SearchResult searchResult = query.getResult();
        responseMap.put("SearchResult", searchResult);


        /* Tagging Work Starts Here */
        Set<Tag> tagList = null;
        try {
            tagList = getPageTags(searchResult, request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        responseMap.put("numberOfPages", getPaginationCalculation(searchResult));
        responseMap.put("tags", tagList);
        responseMap.put("trends", getPageTrends(request));
        return responseMap;
    }

    /**
     * Gets the page tags.
     *
     * @param searchResult the search result
     * @param request the request
     * @return the page tags
     * @throws Exception the exception
     */
    private Set<Tag> getPageTags(SearchResult searchResult, SlingHttpServletRequest request) throws Exception {
        Set<Tag> tagList = new HashSet<Tag>();
        Iterator<Node> pageNodes = searchResult.getNodes();
        Resource resource = null;
        Page page = null;
        while (pageNodes.hasNext()) {
            Node pageNode = pageNodes.next();
            resource = request.getResourceResolver().getResource(pageNode.getPath());
            if (resource != null) {
                page = resource.adaptTo(Page.class);
                if (page != null) {
                    Tag[] pagetags = page.getTags();
                    for (Tag tag : pagetags) {
                        tagList.add(tag);
                    }
                }
            }
        }
        return tagList;
    }

    /**
     * Gets the page trends.
     *
     * @param request the request
     * @return the page trends
     * @throws Exception the exception
     */
    private Trends getPageTrends(SlingHttpServletRequest request) throws Exception {
        SimpleSearch simpleSearch = ((SimpleSearch) request.getResource().adaptTo(SimpleSearch.class));
        return simpleSearch.getTrends();
    }

    /**
     * Gets the pagination calculation.
     *
     * @param searchResult the search result
     * @return the pagination calculation
     * @throws Exception the exception
     */
    private long getPaginationCalculation(SearchResult searchResult) throws Exception {
        logger.debug(":::::::::: hitsPerPage ::::::::   " + searchResult.getHits().size());
        long totalMatches = searchResult.getTotalMatches();
        logger.debug(":::::::::: totalMatches ::::::::   " + totalMatches);

        int fullyFilledPages = (int) totalMatches / Integer.parseInt(resultsPerPage);
        int isPartialPage = (((int) totalMatches % Integer.parseInt(resultsPerPage)) > 0) ? (1) : (0);
        long numberOfPages = (long) (fullyFilledPages + isPartialPage);
        logger.debug(":::::::::: numberOfPages ::::::::   " + numberOfPages);
        return numberOfPages;
    }

}
