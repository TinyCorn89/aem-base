<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

  Comment component

  Draws a comment.

--%><%@ page session="false" import="com.adobe.cq.social.commons.Comment" %><%
%><%@include file="/libs/foundation/global.jsp"%><%
    final Comment comment = resource.adaptTo(Comment.class);
    if (null != comment) {
        %><sling:include path="." replaceSelectors="listitem-template"/><%
    } else {
        log.error("comment could not be retrieved for [{}]: ", resource.getPath());
    }
%>
