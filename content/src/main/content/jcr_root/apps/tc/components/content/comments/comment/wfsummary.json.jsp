<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

  Workflow payload summary for comments

--%><%@ page session="false" import="
  org.apache.sling.commons.json.io.JSONWriter,
  com.adobe.cq.social.commons.Comment,
  com.adobe.cq.social.commons.CollabUser
 " %><%
%><%@include file="/libs/foundation/global.jsp"%><%

  final Comment c = slingRequest.getResource().adaptTo(Comment.class);
  if(c == null) {
      throw new IllegalArgumentException("Resource does not adapt to Comment: " + slingRequest.getResource().getPath());
  }
  final CollabUser u = c.getAuthor();

  response.setContentType(slingRequest.getResponseContentType());
  response.setCharacterEncoding("UTF-8");

  final JSONWriter w = new JSONWriter(response.getWriter());
  w.object();

  final StringBuilder sb = new StringBuilder();
  if(u != null) {
      // Output author name + first MAXLEN chars of message
      final String name = u.getName();
      final String msg = c.getMessage();

      if(name != null && name.length() > 0) {
          sb.append(name);
      }

      if(msg != null && msg.length() > 0) {
          final int MAXLEN = 147;
          if(sb.length() > 0) {
              sb.append(": ");
          }
          if(msg.length() > MAXLEN) {
              sb.append(msg.substring(0, MAXLEN) + "...");
          } else {
              sb.append(msg);
          }
      }
  }

  w.key("description").value(sb.toString().trim());
  w.endObject();
%>
