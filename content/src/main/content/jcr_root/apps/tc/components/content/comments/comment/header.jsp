<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

  Comment component sub-script

  Draws the comment header.

--%><%@ page session="false" import="java.text.DateFormat,
                     com.day.cq.commons.Externalizer,
                     com.day.cq.i18n.I18n,
                     com.adobe.cq.social.commons.Comment,
                     com.adobe.cq.social.commons.CommentSystem,
                     com.adobe.cq.social.commons.CollabUser,
                     com.day.cq.commons.date.DateUtil,
                     java.util.Locale,
                     com.adobe.cq.social.commons.CollabUtil,
                     javax.jcr.Session,
                     javax.jcr.Node,
                     org.apache.sling.api.resource.Resource,
                     org.apache.sling.api.scripting.SlingScriptHelper" %>
<%@include file="/libs/social/commons/commons.jsp"%><%


    SlingScriptHelper scriptHelper = bindings.getSling();

    Comment comment = resource.adaptTo(Comment.class);
    CommentSystem cs = comment.getCommentSystem();

    String id = comment.getId();

    CollabUser author = comment.getAuthor();

    //resolve name
    String authorName = resourceAuthorName;
    String authorImg = resourceAuthorAvatar;

    //resolve date
    DateFormat dateFormat = DateUtil.getDateFormat(cs.getProperty("dateFormat", String.class), DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT, pageLocale), pageLocale);
    String date = dateFormat.format(comment.getDate());

%><div class="comment-header">
    <div
        class="comment-header-avatar"
        onclick="document.getElementById('<%= xssAPI.encodeForHTMLAttr(id) %>-avatar').style.display='inline';"><%
        %><img
               id="<%= xssAPI.encodeForHTMLAttr(id) %>-avatar"
               src="<%= xssAPI.getValidHref(authorImg) %>"
               alt="<%= xssAPI.encodeForHTMLAttr(authorName) %>"
               title="<%= xssAPI.encodeForHTMLAttr(authorName) %>"
               style="width:auto;height:auto;display:none;position:absolute;float:right;"
               onmouseout="this.style.display='none';"><%
        %><img src="<%= xssAPI.getValidHref(authorImg) %>" alt="<%= xssAPI.encodeForHTMLAttr(authorName) %>"><%
    %></div>
    <cite><a href="<%= xssAPI.getValidHref(socialProfileUrl) %>" title=""><%= xssAPI.encodeForHTML(authorName) %></a></cite>
    <span class="comment-header-meta">
        <a href="<%= xssAPI.getValidHref(comment.getUrl()) %>"
           id="<%= xssAPI.encodeForHTMLAttr(id) %>"
           class="comment-header-time"
           title="<%= i18n.get("Permalink") %>"><%= xssAPI.encodeForHTML(date) %></a>
    </span>
</div>
