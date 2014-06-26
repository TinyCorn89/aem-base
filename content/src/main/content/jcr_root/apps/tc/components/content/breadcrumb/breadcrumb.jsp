<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

  Breadcrumb component

  Draws the breadcrumb

--%>
<%@page session="false"%>
<%@include file="/apps/tc/global/global.jsp"%>
<action:action actionClassName="com.tc.action.BreadCrumbAction" bean="breadCrumbBean" actionName="getbreadCrumb"  />



<ol class="breadcrumb">

<c:forEach var="element" items="${breadCrumbBean.levellist}" varStatus="theCount">





<li>
<a href="<c:out value="${element.path}"/>" onclick="CQ_Analytics.record({event:'followBreadcrumb',values: { breadcrumbPath: '<c:out value="${element.title}"/>' },collect: false,options: { obj: this },componentPath: '<%=resource.getResourceType()%>'})">
       <c:out value="${element.title}"/>


    <c:if test="${!theCount.last}">

        <c:out value="${breadCrumbBean.delim}"/>

    </c:if>   

</a>
<span class="right_arrow">
</span>  </li>



   </c:forEach>

<c:out value="${breadCrumbBean.trailStr}"/>

</ol>
