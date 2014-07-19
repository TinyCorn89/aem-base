<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%>
<%@page session="false" %>
<cq:setContentBundle source="page"/>
<action:action actionClassName="com.tc.action.AdAction" bean="adBean" actionName="getAdInfo" />


publicationDate ==<p>${adBean.publicationDate}</p>
expirationDate<p>${adBean.expirationDate}</p>
advertiserId<p>${adBean.advertiserId}</p>
adCategory<p>${adBean.adCategory}</p>
keywords are
<c:forEach items="${adBean.keywords}" var="tag">
    <p>${tag}</p>
                </c:forEach>

adFinder<p>${adBean.adFinder}</p>
startDate<p>${adBean.startDate}</p>
endDate<p>${adBean.endDate}</p>
displayName<p>${adBean.displayName}</p>
sharedSites are
<c:forEach items="${adBean.sharedSites}" var="sharedSites1">
    <p>${sharedSites1}</p>
                </c:forEach>