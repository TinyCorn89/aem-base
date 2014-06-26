<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%>
<cq:setContentBundle source="page"/>
<action:action actionClassName="com.tc.action.CategoryAction" bean="categoryListBean" actionName="getCategories"  />

<c:if test="${categoryListBean.listOfCategories != null }">
    <div class="widget" id="categories-3"><h3 class="widget-title"><fmt:message key="Categories"/></h3>		<ul>
            <c:forEach var="categoryBean" varStatus="loop" items="${categoryListBean.listOfCategories}">
                <li class="cat-item cat-item-33"><a title='<fmt:message key="View all posts filed under"/> ${categoryBean.categoryName}' href="${categoryBean.categoryUrl}.html">${categoryBean.categoryName}</a>
                </li>
            </c:forEach>
        </ul>
    </div>

</c:if>