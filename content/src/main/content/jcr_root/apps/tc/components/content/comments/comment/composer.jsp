<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
    
<%--
This makes the composer for a comment available via a URL.  Enables the UI to "GET" the composer on demand,
 for nested replies.
 --%>
<%@page session="false" %><%@include file="/libs/foundation/global.jsp"%>
<sling:include resourceType="social/commons/components/composer" replaceSelectors="simple-template"/>
