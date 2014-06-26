<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%>
<action:action actionClassName="com.tc.action.VideoAction" bean="videoBean" actionName="getVideoInfo" />

    <c:forEach var="videoSourceBean" items="${videoBean.videoSourceBeanList}" varStatus="loop">
 <source src="${videoSourceBean.srcValue}" type='${videoSourceBean.type}' />
        </c:forEach>