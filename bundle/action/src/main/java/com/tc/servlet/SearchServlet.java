/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.slf4j.Logger;

import com.day.cq.search.result.SearchResult;
import com.tc.framework.api.ManagerProvider;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.managers.SearchService;
import com.tc.servlet.BaseSlingServlet;
import com.tc.util.CommonUtils;

@SlingServlet(paths = {"/bin/searchResultsServlet"})
@Properties({
    @Property(name = "service.pid", value = "org.virtusa.launchpad.servlets.SearchServlet", propertyPrivate = false),
    @Property(name = "service.description", value = "Search Servlet", propertyPrivate = false),
    @Property(name = "service.vendor", value = "launchpad", propertyPrivate = false)})
public class SearchServlet extends BaseSlingServlet {

    /**
     *
     */
    private static final long serialVersionUID = 7321799707289259451L;

    private Logger logger = FrameworkLogger.getLogger();

    protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     * Processes a POST request, sets cookies, and then redirects to the
     * viewcart.jsp page
     */
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        logger.info("Enetered in to SearchServlet class");

        SearchService searchService = ManagerProvider.getManager(SearchService.class);

        request.setAttribute("searchKeyword", request.getParameter("searchKeyword"));
        request.setAttribute("sectionTab", request.getParameter("sectionTab"));
        request.setAttribute("keyword", request.getParameter("keyword"));
        request.setAttribute("author", request.getParameter("author"));
        request.setAttribute("contentType", request.getParameter("contentType"));
        request.setAttribute("timeRange", request.getParameter("timeRange"));
        request.setAttribute("searchIn", request.getParameter("searchIn"));
        request.setAttribute("dateFromMM", request.getParameter("dateFromMM"));
        request.setAttribute("dateFromDD", request.getParameter("dateFromDD"));
        request.setAttribute("dateFromYYYY", request.getParameter("dateFromYYYY"));
        request.setAttribute("dateToMM", request.getParameter("dateToMM"));
        request.setAttribute("dateToDD", request.getParameter("dateToDD"));
        request.setAttribute("dateToYYYY", request.getParameter("dateToYYYY"));
        request.setAttribute("contentPath", request.getParameter("contentPath"));
        request.setAttribute("articlePath", request.getParameter("articlePath"));
        request.setAttribute("damPath", request.getParameter("damPath"));
        request.setAttribute("currentIndex", request.getParameter("currentIndex"));
        request.setAttribute("tagKeyword", request.getParameter("tagKeyword"));
        request.setAttribute("mimeType", request.getParameter("mimeType"));
        request.setAttribute("resultPath", request.getParameter("resultPath"));

        request.setAttribute("q", request.getParameter("q"));
        request.setAttribute("simpleContentPath", request.getParameter("simpleContentPath"));
        try {
            Map<String, Object> resultMap = null;
            if (request.getParameter("fromPage").equals("simple-search")) {
                resultMap = searchService.simpleSearch(request);
                request.setAttribute("fromPage", "simple-search");
            } else {
                resultMap = searchService.advancedsearch(request);
                request.setAttribute("fromPage", null);
            }
            SearchResult searchResult = null;
            List<Object> list = null;
            long numberOfPages = 0;
            if (resultMap.containsKey("SearchResult")) {
                searchResult = (SearchResult) resultMap.get("SearchResult");
                list = getSearchResults(searchResult);
                if (resultMap.containsKey("numberOfPages")) {
                    numberOfPages = (Long) resultMap.get("numberOfPages");
                }
                request.setAttribute("numberOfPages", numberOfPages);
                request.setAttribute("resultlist", list);
                request.setAttribute("result", searchResult);
                request.setAttribute("tags", resultMap.get("tags"));
                request.setAttribute("trends", resultMap.get("trends"));
            }
            CommonUtils.getRequestDispatcher(request, response, request.getParameter("resultPath") + ".html");

        } catch (Exception e) {
            logger.error("Exception in doPost of SearchServlet class", e);
        }

    }

    private List<Object> getSearchResults(SearchResult searchResult) throws Exception {
        List<Object> list = new ArrayList<Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        Iterator<Node> iterator = searchResult.getNodes();
        while (iterator.hasNext()) {
            Node node = (Node) iterator.next();
            map = new HashMap<String, Object>();
            map.put("name", node.getName());
            map.put("path", node.getPath());
            if (node.hasNode("jcr:content")) {
                Node jcrNode = node.getNode("jcr:content");
                if (jcrNode.hasProperty("jcr:description")) {
                    map.put("description", jcrNode.getProperty("jcr:description").getValue().getString());
                }
            }
            list.add(map);
        }

        return list;
    }
}
