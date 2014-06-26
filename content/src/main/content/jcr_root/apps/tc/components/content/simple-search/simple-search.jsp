<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%>

<cq:includeClientLib categories="tc.search"/>
<cq:setContentBundle source="page"/>
<action:action bean="simpleSearchBean" actionClassName="com.tc.action.SearchAction" actionName="getSimpleSearchDialogInfo" />

<div class="container">
    <a class="navbar-brand" href="/content/tc/us/en/home.html" ><img src="/etc/designs/tc/images/logo.png" class="brandLogo"/></a>
    <form id="search-header" role="search" class="navbar-form pull-right"  action="/bin/searchResultsServlet" accept-charset="utf-8" method="get">
        <div class="form-group">
            <input type="text" placeholder="Enter Keyword(s)" name="q" id="q" class="form-control">
            <input type="hidden" value="${simpleSearchBean.searchIn}" id="simpleContentPath" name="simpleContentPath">
            <input type="hidden" value="${simpleSearchBean.resultPath}" id="resultPath" name="resultPath">
            <input type="hidden" value="simplesearch" id="fromPage" name="fromPage">
            <input type="hidden" value="0" id="currentIndex" name="currentIndex">
        </div>
        <button type="submit" id="submit" class="btn btn-default" onclick="return getSimplePageSubmit()"><fmt:message key="Submit"/></button>
        <a href="/content/tc/us/en/search.html" class="btn-link"><fmt:message key="Advanced Search"/></a>
    </form>
</div>
