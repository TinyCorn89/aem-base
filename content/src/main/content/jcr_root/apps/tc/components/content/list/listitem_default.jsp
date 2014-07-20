<%--
  Copyright 1997-2008 Day Management AG
  Barfuesserplatz 6, 4001 Basel, Switzerland
  All Rights Reserved.

  This software is the confidential and proprietary information of
  Day Management AG, ("Confidential Information"). You shall not
  disclose such Confidential Information and shall use it only in
  accordance with the terms of the license agreement you entered into
  with Day.

  ==============================================================================

  List component sub-script

  Draws a list item as a default link.

  request attributes:
  - {com.day.cq.wcm.foundation.List} list The list
  - {com.day.cq.wcm.api.Page} listitem The list item as a page

--%><%
%><%@ page session="false" import="com.day.cq.wcm.api.Page" import="org.apache.commons.lang3.StringEscapeUtils" %>
<%@include file="/libs/foundation/global.jsp"%>
<%@ page import="javax.jcr.ValueFormatException,javax.jcr.PathNotFoundException,javax.jcr.RepositoryException,javax.jcr.Node,javax.jcr.NodeIterator" %>

<%

    Page listItem = (Page)request.getAttribute("listitem");
	String title= null;
    String subTitle = null;
    String pdfPath = null;
    String pdfThumbnail = null;
	String flipbookPath = null;
	try {
        if(currentNode.hasProperty("flipbookPath")) {
            flipbookPath = currentNode.getProperty("./flipbookPath").getString();        
        }	        
        String issuePath = listItem.getPath();
        Node issuePageNode = slingRequest.getResourceResolver().getResource(issuePath).adaptTo(Node.class);        
        if(issuePageNode.hasNode("jcr:content") && issuePageNode.getNode("jcr:content").hasNode("content-region") && issuePageNode.getNode("jcr:content").getNode("content-region").hasNode("issue")) {
            Node issueNode = issuePageNode.getNode("jcr:content").getNode("content-region").getNode("issue");            
            if(issueNode.hasProperty("title")) {
                title = issueNode.getProperty("./title").getString();                
            }
            if(issueNode.hasProperty("subTitle")) {
                subTitle = issueNode.getProperty("./subTitle").getString();                
            }
            if(issueNode.hasProperty("pdfPath")) {
                pdfPath = issueNode.getProperty("./pdfPath").getString();
                pdfPath = pdfPath.replace("/content", "");
                pdfPath = pdfPath.replaceAll("/", ".");                
                pdfPath = flipbookPath+pdfPath+".html";                
            }
            if(issueNode.hasProperty("thumbnail")) {
                pdfThumbnail = issueNode.getProperty("./thumbnail").getString();                
            }
        }
    } catch (ValueFormatException e) {
        e.printStackTrace();
    } catch (PathNotFoundException e) {
        e.printStackTrace();
    } catch (RepositoryException e) {
        e.printStackTrace();
    }

%>
<div height="outer"><%-- todo: create css to take thumbnail height into account --%>
<div class="inner">
    <h2><a target="_blank" title="<%=title%>" href="<%=pdfPath%>"><%=title%></a></h2>
    <p><%=subTitle%></p>
</div>
<figure>
    <a target="_blank" title="<%=title%>" href="<%=pdfPath%>"><img alt="<%=title%>" src="<%=pdfThumbnail%>"></a>
</figure>
</div
