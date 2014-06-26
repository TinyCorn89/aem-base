<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation.

  Free html component.

  This is a free-html component that would allow the user to enter html including JS and CSS into the dialog and the component will render it.

--%>
<%@include file="/apps/tc/global/global.jsp"%>
<%@page session="false" %>
<cq:setContentBundle source="page"/>
<action:action actionClassName="com.tc.action.FreeHTMLAction" bean="freeHTMLBean" actionName="getHTMLCode" />

<c:if test="${empty freeHTMLBean}">
    <p align="center"><font color="red" size="+1"><fmt:message key="configError"/></font> <p>
</c:if>
<c:if test="${not empty freeHTMLBean}">    
    ${freeHTMLBean.htmlCode}
</c:if>
<%@include file="/apps/tc/components/content/free-html/tracking-js.jsp"%>