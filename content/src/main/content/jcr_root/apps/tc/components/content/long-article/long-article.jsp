<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@page session="false"%>
<%@include file="/apps/tc/global/global.jsp"%>

<action:action actionClassName="com.tc.action.LongArticlePropertiesAction" bean="longArticlePropertiesBean" actionName="getLongArticleProperties" />

<div>
	<c:if test="${longArticlePropertiesBean.imageHasContent}">
        <img  src="${longArticlePropertiesBean.imageLocation}" alt="" style="width:100%;">
    </c:if> 

    <h2>${longArticlePropertiesBean.title}</h2>

    <p>${longArticlePropertiesBean.description}</p>

</div>
<%@include file="/apps/tc/components/content/long-article/tracking-js.jsp"%>