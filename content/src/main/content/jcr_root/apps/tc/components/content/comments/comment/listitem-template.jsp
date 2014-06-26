<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

  Comment component

  Draws a comment.

--%><%@ page session="false" import="com.adobe.cq.social.commons.Comment,
                     com.adobe.cq.social.commons.CommentSystem,
                     com.day.cq.wcm.api.WCMMode" %><%
%><%@include file="/libs/social/commons/commons.jsp"%><%


    final Comment comment = resource.adaptTo(Comment.class);
    final CommentSystem cs = comment.getCommentSystem();

    String message = comment.getMessage();
    // #26898 - line breaks in comments
    // xss protection will remove line breaks before we can format them
    // for the output, so it is done here a a workaround:
    // first remove the CR, then replace the LF with <BR>
    message = message.replaceAll("\\r", "");
    message = message.replaceAll("\\n", "<br>");

    %><cq:include script="header.jsp" /><%

    %><cq:include script="modtag-template"/>
    <div id="<%=xssAPI.encodeForHTMLAttr(comment.getId())%>" class="comment-body"><%
        %><%= xssAPI.filterHTML(message) %>
            <sling:include path="." replaceSelectors="attachments"/>
        <%
    %><cq:include script="toolbar-template.jsp"/></div><%

    if (!cs.isClosed()) {
        %><cq:include script="replies.jsp"/><%
    }
<div id="commentId" commentPath="<%=comment.getPath()%>" csResourcePath="<%=cs.getResource().getPath()%>" style="{display:none}"/>
    if (WCMMode.fromRequest(request) == WCMMode.EDIT) {
        %>
<cq:includeClientLib categories="tc.components.comment"/>
<%
    }
%>
