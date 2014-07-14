<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%>
<%@page session="false" %>
<cq:setContentBundle source="page"/>
<action:action actionClassName="com.tc.action.LatestArticlesAction" bean="listOfCPArticlesBean" actionName="getCPLatestArticles" />

<h4> <fmt:message key="The latest"/> ${fn:length(listOfCPArticlesBean.listOfLatestArticles)} <fmt:message key="Articles"/></h4>

<h1>
<c:if test="${not empty properties.titleurl}">
    <a href="${properties.titleUrl}" target="${properties.target eq '' ? '_top': properties.target}">
</c:if>
<span>${properties.label}</span>
<c:if test="${not empty properties.titleurl}">
    </a>
</c:if>
</h1>


<div class="widget-content">

    <ul class="list-group">
        <c:forEach items="${listOfCPArticlesBean.listOfLatestArticles}" var="bean">
            <li class="list-group-item">
                <a href="#" target="_blank"> 
                    ${bean.title} 
                </a>
                <br/>${bean.publicationDate}
                <!--
                <p><strong>Sections:</strong></p>
                <ul>
                    <c:forEach items="${bean.sections}" var="sections">
                        <li>${sections}</li>
                    </c:forEach>
                </ul>
                <p><strong>Provider:</strong></p>
                <ul>
                    <c:forEach items="${bean.share}" var="share">
                        <li>${share}</li>
                    </c:forEach>
                </ul>
				-->
            </li>
        </c:forEach>
    </ul>

</div>


 
                   


 