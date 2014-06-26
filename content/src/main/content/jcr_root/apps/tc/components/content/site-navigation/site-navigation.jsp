<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%>
<cq:includeClientLib categories="tc.components.footer"/>
<action:action bean="siteNavigationBean"
actionClassName="com.tc.action.SiteNavigationAction"
actionName="getSiteNavigationInfo" />

<div class="container footer">
    <div class="row">
        <div class="col-lg-12">
            <c:forEach var="parentPageList" items="${siteNavigationBean.pageList}" varStatus="parentloop" >
                <c:if test="${fn:length(parentPageList) gt 0}">
                        <c:forEach var="mapentry" varStatus="childloop" items="${parentPageList}">
                            <c:if test="${childloop.count==1}">
                                <div class="col-md-2">
                                    <ul class="list-unstyled">
                                        <h4 class="widget-title"><a href="${mapentry.value}.html"> ${mapentry.key}</a><h4>
                            </c:if>
                                    <c:if test="${childloop.count>1}">
                                        <li><a href="${mapentry.value}.html"> ${mapentry.key}</a></li>
                                    </c:if>
                            <c:if test="${childloop.last}">
                                    </ul>
                                </div>
                            </c:if>

                        </c:forEach>
                </c:if>
            </c:forEach>
        </div>
    </div>
</div>

<%@include file="/apps/tc/components/content/site-navigation/tracking-js.jsp"%>