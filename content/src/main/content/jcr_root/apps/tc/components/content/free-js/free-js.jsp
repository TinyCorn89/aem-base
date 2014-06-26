<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

  Free js component.

  This component will allow the author to enter free JavaScript into the dialog box and component will render it on the page.

--%>
<%@include file="/apps/tc/global/global.jsp"%>
<%@page session="false" %>
<cq:setContentBundle source="page"/>
<action:action actionClassName="com.tc.action.FreeJavaScriptAction" bean="freeJavaScriptBean" actionName="getJavaScript" />

<c:if test="${empty freeJavaScriptBean}">
    <p align="center"><font color="red" size="+1"><fmt:message key="configError"/></font> <p>
</c:if>
<c:if test="${not empty freeJavaScriptBean}">       
    <script type="text/javascript">
        ${freeJavaScriptBean.javaScript}
    </script>
</c:if>
<%@include file="/apps/tc/components/content/free-js/tracking-js.jsp"%>