<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@page session="false"%>
<%@include file="/apps/tc/global/global.jsp"%>
<cq:includeClientLib categories="tc.custom.responsive-image" />
<c:set var="desktopImage">
	<cq:text property="desktopImage" placeholder="Placeholder" />
</c:set>
<c:set var="tabletImage">
	<cq:text property="tabletImage" placeholder="Placeholder" />
</c:set>
<c:set var="mobileImage">
	<cq:text property="mobileImage" placeholder="Placeholder" />
</c:set>
<div desktopImagePath="${desktopImage}" tabletImagePath="${tabletImage}"
	mobileImagePath="${mobileImage}">
	<img src="${desktopImage}" />
</div>
<%@include file="/apps/tc/components/content/responsive-image/tracking-js.jsp"%>        