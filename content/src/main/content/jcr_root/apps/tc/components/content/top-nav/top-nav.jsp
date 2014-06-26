<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%>
<cq:setContentBundle source="page"/>
<action:action bean="topNavigationBean" actionClassName="com.tc.action.TopNavigationAction" actionName="getListOfLevels"/>

<ul class="nav navbar-nav">
    <c:set var="currentPagePath" value="${currentPage.path}.html" />
    <c:set var="navPlaceholder" value="RIGHT CLICK TO EDIT" />
    <c:forEach var="firstLevelBean" items="${topNavigationBean.listOfTabs}" varStatus="forEachCount">
        <c:set var="activeClass" value="${firstLevelBean.path == currentPagePath ? 'active' : ''}" />
        <c:choose>
            <c:when test="${not empty firstLevelBean.listOfTabs}">
                <li class="dropdown ${activeClass}">
                    <a href="${firstLevelBean.path}" class="dropdown-toggle" data-toggle="dropdown">${firstLevelBean.title} <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <c:if test="${not empty firstLevelBean.listOfTabs }">
                            <c:forEach var="secondLevel" items="${firstLevelBean.listOfTabs}">
                                <li>
                                    <a href="${secondLevel.path}">${secondLevel.title}</a>
                                </li>
                            </c:forEach>
                        </c:if>
                    </ul>
                </li>
            </c:when>
            <c:otherwise>
                <li class="${activeClass}"><a href="${firstLevelBean.path}">${firstLevelBean.title}</a></li>
            </c:otherwise>
        </c:choose>
    <c:set var="navPlaceholder" value="" />
    </c:forEach>
</ul>
${navPlaceholder}
<%@include file="/apps/tc/components/content/top-nav/tracking-js.jsp"%>
