<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%>

<action:action actionClassName="com.tc.action.MostViewedAticleAction" bean="mostviewedBean" actionName="getMostViewedArticles"  />

<h4>
    ${mostviewedBean.header}
</h4>

<ul class="list-group">

    <c:forEach var="MostViewedArticleBean" items="${mostviewedBean.mostviewedArticleList}" varStatus="loop">
        <li class="list-group-item">
            <a href="${MostViewedArticleBean.articlePath}" >${MostViewedArticleBean.title}</a>
        </li>
    </c:forEach>
    <li class="list-group-item">
        <a href="${mostviewedBean.articleParent}">
            <b>${mostviewedBean.seeAllText}</b>
        </a>
    </li>
</ul>
<%@include file="/apps/tc/components/content/most-viewed/tracking-js.jsp"%>