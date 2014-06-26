<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>

<%@include file="/apps/tc/global/global.jsp"%>
<cq:setContentBundle source="page"/>
<action:action actionClassName="com.tc.action.FeaturedAction" bean="featuredBean" actionName="getFeatures"  />
<h4>
    ${featuredBean.header}
</h4>

<ul class="list-group">
    <c:if test="${empty featuredBean.mostviewedArticleList }">
        <h3> <fmt:message key="tagError"/></h3>

    </c:if>
    <c:if test="${not empty featuredBean.mostviewedArticleList }">
        <c:forEach var="MostViewedArticleBean" items="${featuredBean.mostviewedArticleList}" varStatus="loop">
            <li class="list-group-item">
                <a href="${MostViewedArticleBean.articlePath}" >${MostViewedArticleBean.title}</a>
            </li>
        </c:forEach>
    </c:if>
    <li class="list-group-item">
        <a href="${featuredBean.parentPath}">
            <b>${featuredBean.seeAllText}</b>
        </a>
    </li>
</ul>
<%@include file="/apps/tc/components/content/featured/tracking-js.jsp"%>