<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%><%
%><%@page session="false" import="com.adobe.granite.xss.XSSAPI"%><%

    /* Services */
    final XSSAPI xss = sling.getService(XSSAPI.class);

    /* Page Properties */
    final String pageTitle = currentPage.getTitle();
    final String pageDescription = currentPage.getDescription();
%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title><%= currentPage.getTitle() == null ? StringEscapeUtils.escapeHtml4(currentPage.getName()) : StringEscapeUtils.escapeHtml4(currentPage.getTitle()) %></title>
    <meta name="author" content="Virtusa LaunchPad" />
    <meta name="keywords" content="<%= StringEscapeUtils.escapeHtml4(WCMUtils.getKeywords(currentPage, false)) %>" />
    <meta name="description" content="<%= StringEscapeUtils.escapeHtml4(properties.get("jcr:description", "")) %>" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="index, follow" />
    
    <cq:include script="headlibs.jsp"/>
    <cq:include script="/libs/wcm/core/components/init/init.jsp"/>
    <cq:include script="/libs/wcm/mobile/components/simulator/simulator.jsp"/>


</head>
