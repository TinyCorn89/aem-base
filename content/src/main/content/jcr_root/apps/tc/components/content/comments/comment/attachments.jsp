<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
    
<%@page session="false" import="java.io.IOException,
                java.text.SimpleDateFormat,
                javax.jcr.Node,
                javax.jcr.NodeIterator,
                javax.jcr.RepositoryException,
                org.apache.sling.api.SlingHttpServletRequest,
                com.adobe.cq.social.commons.CollabUser,
                com.adobe.cq.social.commons.CollabUtil,
                com.adobe.cq.social.commons.Comment,
                com.adobe.cq.social.commons.CommentSystem,
                com.day.cq.xss.XSSProtectionService,
                com.day.cq.i18n.I18n,
                com.day.text.Text,
                java.util.ResourceBundle,
                java.util.Locale,
                org.slf4j.Logger,
                com.adobe.granite.xss.XSSAPI" %>
<%@include file="/libs/foundation/global.jsp"%>
<cq:setContentBundle source="page"/>
<%
    Comment comment = resource.adaptTo(Comment.class);
        Node node = resource.adaptTo(Node.class);
    String path = comment.getPath();
    String label = Text.getName(path);
    if (node.hasNode(CommentSystem.NN_COMMENT_ATTACHMENTS)) {
%><h3><fmt:message key="Attachments"/>:</h3><div class="comment-body">
        <%
            dumpAttachments(slingRequest, out, node.getNode(CommentSystem.NN_COMMENT_ATTACHMENTS), xssAPI, log);
        %>
    </div><%
    }

%><%!

    public static void dumpAttachments(SlingHttpServletRequest req, JspWriter out, Node parent, XSSAPI xssAPI, Logger log)
            throws RepositoryException, IOException {
        NodeIterator iter = parent.getNodes();
        final I18n i18n = new I18n(req);
        while (iter.hasNext()) {
            Node att = iter.nextNode();
            String name = Text.getName(att.getPath());
            name = xssAPI.encodeForHTML(name);
            if (att.isNodeType("nt:unstructured")) {
                dumpAttachments(req, out, att, xssAPI, log);
            } else if (att.getNode("jcr:content").getProperty("jcr:mimeType").getValue().getString().contains("image")) {
                out.write("<div class=\"comment-attachment\"><img src=\"" + req.getContextPath() + att.getPath()  + ".thumb.100.140.png\"/><label>"+ name +"</label><br/><a href=\"" + req.getContextPath() + att.getPath()  + "\">"  + i18n.get("download") + "</a></div>");
            } else if (att.isNodeType("nt:file")) {
                out.write("<div class=\"comment-attachment\"><img src=\"/etc/clientlibs/foundation/comments/images/documenticon.png\"/><label>"+ name +"</label><br/><a href=\"" + req.getContextPath() + att.getPath()  + "\">"  + i18n.get("download") + "</a></div>");
            } else {
                // ignore
            }
        } // while
    }
%>
