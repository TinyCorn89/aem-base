<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

  Comments component sub-script

  Draws the header.

--%><%@ page session="false" import="com.adobe.cq.social.commons.CommentSystem,
                     com.day.cq.i18n.I18n,
                     com.day.cq.wcm.api.WCMMode,
                     java.util.Locale,
                     java.util.ResourceBundle" %><%
%><%@include file="/libs/foundation/global.jsp"%><%

    I18n i18n = new I18n(slingRequest.getResourceBundle(currentPage.getLanguage(false)));

    CommentSystem cs = (CommentSystem)request.getAttribute("cs");

    String idPrefix = cs.getId();
    int num = cs.countComments(WCMMode.fromRequest(request));

    %><div class="comments-count" id="<%= idPrefix %>-count"><%
        if (num == 0) {
            %><%= i18n.get("No comments yet") %><%
        } else {
            %><%= num %> <%= num == 1 ? i18n.get("comment", "Number of comments (singular)") : i18n.get("comments", "Number of comments (plural)") %><%
        }
    %></div>
