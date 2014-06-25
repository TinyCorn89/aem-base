<%--
  Copyright 1997-2010 Day Management AG
  Barfuesserplatz 6, 4001 Basel, Switzerland
  All Rights Reserved.

  This software is the confidential and proprietary information of
  Day Management AG, ("Confidential Information"). You shall not
  disclose such Confidential Information and shall use it only in
  accordance with the terms of the license agreement you entered into
  with Day.

  ==============================================================================

  Default head script.

  Draws the HTML head with some default content:
  - includes the WCML init script
  - includes the head libs script
  - includes the favicons
  - sets the HTML title
  - sets some meta data

  ==============================================================================

--%><%@include file="/apps/company/global.jsp" %><%
%><%@ page import="com.day.cq.commons.Doctype,
org.apache.commons.lang3.StringEscapeUtils" %><%
    String xs = Doctype.isXHTML(request) ? "/" : "";
    String favIcon = currentDesign.getPath() + "/favicon.ico";
    if (resourceResolver.getResource(favIcon) == null) {
        favIcon = null;
    }
%><head>
    <meta charset="utf-8"<%=xs%>>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />

    <meta name="title" content="<%= currentPage.getTitle() == null ? StringEscapeUtils.escapeHtml4(currentPage.getName()) : StringEscapeUtils.escapeHtml4(currentPage.getTitle()) %>" />
    <meta name="keywords" content="<%= StringEscapeUtils.escapeHtml4(WCMUtils.getKeywords(currentPage, false)) %>"<%=xs%>>
    <meta name="description" content="<%= StringEscapeUtils.escapeHtml4(properties.get("jcr:description", "")) %>"<%=xs%>>

    <cq:include script="clientlibHead.jsp" />
    <cq:include script="/libs/wcm/core/components/init/init.jsp"/>
    <cq:include script="stats.jsp"/>
    <% if (favIcon != null) { %>
        <link rel="icon" type="image/vnd.microsoft.icon" href="<%= favIcon %>"<%=xs%>>
        <link rel="shortcut icon" type="image/vnd.microsoft.icon" href="<%= favIcon %>"<%=xs%>>
    <% } %>
    <title><%= currentPage.getTitle() == null ? StringEscapeUtils.escapeHtml4(currentPage.getName()) : StringEscapeUtils.escapeHtml4(currentPage.getTitle()) %></title>


</head>
