<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%>
<%@page session="false" %>
<cq:setContentBundle source="page"/>
<action:action actionClassName="com.tc.action.LatestArticlesAction" bean="listOfArticlesBean" actionName="getLatestArticles" />

<h4> <fmt:message key="The latest"/> ${fn:length(listOfArticlesBean.listOfLatestArticles)} <fmt:message key="Articles"/></h4>

<ul class="list-group">
    <c:forEach items="${listOfArticlesBean.listOfLatestArticles}" var="bean">
        <li class="list-group-item">
            <a href="${bean.articlePath}" target="_blank"> 
                ${bean.articleTitle}
            </a>
        </li>
    </c:forEach>
    <li class="list-group-item">
        <a href="${listOfArticlesBean.parentPagePath}" target="_blank"> 
            <b>${listOfArticlesBean.seeAllLinkText}</b>
        </a>
    </li>
</ul>
<%@include file="/apps/tc/components/content/latest/tracking-js.jsp"%>