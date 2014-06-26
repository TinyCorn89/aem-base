<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

  Atom feed entry renderer for comment nodes

  Draws the current comment node as a feed entry.

--%><%@ page session="false" %><%
%><%@ page import="java.util.Date,
                   com.adobe.cq.social.commons.Comment,
                   com.day.cq.wcm.api.WCMMode"%><%
%><%@ taglib prefix="sling" uri="http://sling.apache.org/taglibs/sling/1.0" %><%
%><%@ taglib prefix="cq" uri="http://www.day.com/taglibs/cq/1.0" %><%
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%
%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><%
%><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><%
%><%@ taglib prefix="atom" uri="http://sling.apache.org/taglibs/atom/1.0" %><%
%><%@ taglib prefix="media" uri="http://sling.apache.org/taglibs/mediarss/1.0" %><%
%><cq:defineObjects /><%

    try {
        WCMMode.DISABLED.toRequest(request);
        Comment comment = resource.adaptTo(Comment.class);
        String url = comment.getFullUrl(slingRequest);
        String author = comment.getAuthor().getName();
        Date date = comment.getDate();
        String content = comment.getMessage();
        %><atom:entry
                id="<%= url %>"
                updated="<%= date %>"
                published="<%= date %>"><%
            %><atom:title><%= author %></atom:title><%
            %><atom:link href="<%= url %>"/><%
            if (content != null) {
                %><atom:content><%= content %></atom:content><%
            }
        %></atom:entry><%

    } catch (Exception e) {
        log.error("error while rendering default feed entry", e);
    }
%>
