<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

  Comment component sub-script

  Draws the replies to a comment.

--%><%@ page session="false" import="com.adobe.cq.social.commons.Comment,
                     com.adobe.cq.social.commons.CommentSystem,
                     com.day.cq.commons.Externalizer,
                     com.day.cq.i18n.I18n,
                     com.day.cq.wcm.api.components.IncludeOptions,
                     java.util.Iterator,
                     java.util.Locale,
                     com.day.cq.wcm.api.WCMMode,
                     java.util.ResourceBundle" %><%
%><%@include file="/libs/foundation/global.jsp"%><%

    I18n i18n = new I18n(slingRequest.getResourceBundle(currentPage.getLanguage(false)));

    Comment comment = resource.adaptTo(Comment.class);
    CommentSystem cs = comment.getCommentSystem();
    final Resource commentResource = resourceResolver.getResource(cs.getPath());
  final ValueMap values = (commentResource != null) ? cs.getResource().adaptTo(ValueMap.class) : null;
    final Boolean isRTEenabled = (values != null) ? values.get("rteEnabled", false) : false;

    String id = comment.getId();
    boolean allowReplies = cs.getProperty("allowRepliesToComments", Boolean.class) != null ?
            cs.getProperty("allowRepliesToComments", Boolean.class) : false;
    boolean flatten = cs.getProperty("displayCommentsAsTree", Boolean.class) != null ?
            !cs.getProperty("displayCommentsAsTree", Boolean.class) : false;
    boolean fileUpload = cs.getProperty("allowFileUploads", Boolean.class) != null ?
            cs.getProperty("allowFileUploads", Boolean.class) :false;

%><div class="comment-replies<%= !flatten ? " tree" : "" %>" id="<%= id %>-replies" data-numreplies="<%=comment.countComments()%>"><%
if (allowReplies) {
    int replies = comment.countComments();
    Externalizer ext = sling.getService(Externalizer.class);
    String formAction = resource.getPath() + "/";
    String url = ext.relativeLink(slingRequest, resource.getPath()) + ".composer.html?action=" + formAction + "&idPrefix=" + id;
    %><div id="<%= id %>-reply"><%

    if (!flatten) {
        // display number of replies
        %><span class="numReplies"><%= replies %> <%= replies == 1 ? i18n.get("Reply") : i18n.get("Replies") %></span> <%
    }
    %><span id="<%= id %>-reply-arrow">&raquo;</span> <a
                href="#<%= id %>"
                id="<%= id %>-reply-button"
                onclick="CQ.soco.comments.toggleReplyForm((event.currentTarget) ? event.currentTarget : event.srcElement, '<%= url %>', <%=isRTEenabled%>);"
                title="<%=i18n.get("Reply") %>"><%=i18n.get("Reply") %></a></div><%
    %><div id="<%= id %>-reply-form" class="reply-form" style="display:none">&nbsp;</div><%

    if (comment.hasComments()) {
         for (Iterator<Comment> iterator = comment.getComments(); iterator.hasNext();) {
            Comment reply = iterator.next();
      if(reply != null){
              if (editContext != null) {
                  editContext.setAttribute("currentResource", reply.getResource());
              }
              // include comment
              IncludeOptions.getOptions(request, true).getCssClassNames().add("comment");
              %><sling:include resource="<%= reply.getResource() %>"/><%
      }
        }
    }
}

%></div>
