<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%>
<%@page session="false" %>
<cq:setContentBundle source="page"/>
<action:action actionClassName="com.tc.action.ArticleAction" bean="articleBean" actionName="getCPArticleInfo" />
<h2>${articleBean.title}</h2>

<p>Publication Date: ${articleBean.publicationDate}</p>

<p><strong>Sections:</strong></p>
<ul>
    <c:forEach items="${articleBean.sections}" var="sections">
        <li>${sections}</li>
    </c:forEach>
</ul>
<p><strong>Share:</strong></p>
<ul>
    <c:forEach items="${articleBean.share}" var="share">
        <li>${share}</li>
    </c:forEach>
</ul>


</div>


 
                   


