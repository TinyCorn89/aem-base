<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@page session="false"%>
<%@include file="/apps/tc/global/global.jsp"%>
<cq:setContentBundle source="page"/>
<action:action actionClassName="com.tc.action.ArticleAction" bean="articleBean" actionName="getArticleInfo"  />

<div class="col-lg-4" style="text-align: center;">
    <c:if test="${not empty articleBean.imageUrl}">
        <img class="img-circle" src="${articleBean.imageUrl}" alt="" class="articleImage">
    </c:if>
    <h2><cq:text property="title" tagClass="text" escapeXml="true" placeholder="Title Displays Here"/></h2>
    <p><cq:text property="desciption" tagClass="text" escapeXml="true" placeholder="Description Displays Here "/></p>
    <c:if test='${not empty properties["link"]}'>
        <p><a class="btn btn-default" href="${properties['link']}" role="button"><fmt:message key="viewDetails"/> &raquo;</a></p>  
    </c:if>  
</div><!-- /.col-lg-4 -->

<%@include file="/apps/tc/components/content/article/tracking-js.jsp"%>