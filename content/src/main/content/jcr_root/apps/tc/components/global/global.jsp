<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/libs/foundation/global.jsp"%>
<%
    final I18n i18n = new I18n(slingRequest);
%>

<%@page session="false" import="com.day.cq.commons.Doctype,
    com.day.cq.commons.inherit.InheritanceValueMap,
    com.day.cq.wcm.api.components.DropTarget,
    com.day.cq.wcm.api.WCMMode,
    com.day.cq.wcm.foundation.Image,
    com.day.cq.wcm.foundation.List,
    org.apache.commons.lang3.StringEscapeUtils,
    org.apache.sling.api.resource.ResourceResolver,
    org.apache.sling.api.SlingHttpServletRequest,
	com.day.cq.i18n.I18n,
	javax.jcr.Node,
	com.adobe.granite.xss.XSSAPI,
    com.day.text.Text" %>
<%
	boolean enableEdit = WCMMode.fromRequest(request) == WCMMode.EDIT;
	request.setAttribute("enableEdit",enableEdit);
%>
<%@taglib prefix="action" uri="http://com.tc/taglibs/action/1.0" %>
<%@taglib prefix="func" uri="http://com.tc/taglibs/func/1.0" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<cq:defineObjects />