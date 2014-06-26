<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%>
<cq:setContentBundle source="page"/>
<action:action actionClassName="com.tc.action.HelloWorldAction" bean="helloWorld" actionName="helloWorld"  />
<fmt:message key="Hello"/> ${helloWorld.message} ! <fmt:message key="welcomeMessage"/>...
<br/>
<fmt:message key="serviceMessage"/> = ${helloWorld.appConfigMessage} 
<%@include file="/apps/tc/components/content/hello-world/tracking-js.jsp"%>