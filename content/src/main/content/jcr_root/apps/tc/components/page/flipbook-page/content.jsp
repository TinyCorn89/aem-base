<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%>
<%@page session="false"%>

<cq:include path="content-region" resourceType="foundation/components/parsys" />

<%
    String url = request.getRequestURL().toString();
    if(url.contains("dam")) {
%>
		<cq:include path="dynamic-flipbook-component" resourceType="/apps/tc/components/content/dynamic-flipbook" />
<%
    }
%>