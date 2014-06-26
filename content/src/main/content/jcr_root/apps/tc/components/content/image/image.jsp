<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@page session="false"%>
<%@ page import="com.day.cq.commons.Doctype,
    com.day.cq.wcm.api.components.DropTarget,
    com.day.cq.wcm.foundation.Image, com.day.cq.wcm.foundation.Placeholder" %>
<%@include file="/apps/tc/global/global.jsp"%>
<cq:includeClientLib css="tc.components.image" />
<action:action actionClassName="com.tc.action.ImageAction" bean="imageBean" actionName="getImageDetails" />

<div id="${imageBean.divId}">${func:draw(imageBean.image,imageBean.printWriter)}</div>

<cq:text property="jcr:description" placeholder="" tagName="small" escapeXml="true"/>
<div id="imgDiv" divId="${imageBean.divId}" imageLink="${imageBean.imageLink}" imageAsset="${imageBean.imageAsset}" imageTitle="${imageBean.imageTitle}" componentPath="${imageBean.componentPath}" style="{display:none}">
<%@include file="/apps/tc/components/content/image/tracking-js.jsp"%>