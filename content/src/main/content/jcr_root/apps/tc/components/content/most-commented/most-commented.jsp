<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>

<%@include file="/apps/tc/global/global.jsp"%>

<action:action actionClassName="com.tc.action.MostCommentedArticleAction" bean="mostCommentedBean" actionName="getMostCommentedArticle"  />
<h4>
    ${mostCommentedBean.header}
</h4>

<ul class="list-group">

    <c:forEach var="MostViewedArticleBean" items="${mostCommentedBean.mostviewedArticleList}" varStatus="loop">
        <li class="list-group-item">
            <a href="${MostViewedArticleBean.articlePath}" >${MostViewedArticleBean.title}</a>
        </li>
    </c:forEach>
    <li class="list-group-item">
        <a href="${mostCommentedBean.articleParent}">
            <b>${mostCommentedBean.seeAllText}</b>
        </a>
    </li>
</ul>
<%@include file="/apps/tc/components/content/most-commneted/tracking-js.jsp"%>