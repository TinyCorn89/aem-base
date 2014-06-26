<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%><%
%><%@page session="false"%><%
%>
<%@page import="java.util.Iterator,org.apache.sling.api.resource.ValueMap,
                com.day.cq.wcm.api.Page,
                com.day.cq.wcm.core.stats.PageViewStatistics,
                com.day.cq.wcm.core.stats.PageView,
                org.slf4j.Logger,
                org.slf4j.LoggerFactory" %>
<%!
    private final Logger log = LoggerFactory.getLogger(getClass());
%><%

// write page statistics
final PageViewStatistics pwSvc = sling.getService(PageViewStatistics.class);
String trackURL = null;
if(pwSvc != null && pwSvc.getTrackingURI() != null) {
	trackURL = pwSvc.getTrackingURI().toString();
}

if (trackURL != null && currentPage != null) {
    %><script type="text/javascript">
    {
        window.setTimeout(function() {
            $CQ.getScript("<%=trackURL%>.js?path=<%=currentPage.getPath()%>");
        }, 1);
    }
    </script><%
}
%>

<cq:include script="/libs/foundation/components/mvt/mvt_stats.jsp"/>