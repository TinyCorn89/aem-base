<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@page session="false"%>
<%@include file="/apps/tc/global/global.jsp"%>
<cq:includeClientLib categories="tc.components.weather" />
<cq:setContentBundle source="page"/>
<c:set var="city"><cq:text property="city" escapeXml="true" placeholder="Placeholder"/></c:set>
<c:set var="state"><cq:text property="state" escapeXml="true" placeholder="Placeholder"/></c:set>
<c:set var="zip"><cq:text property="zip" escapeXml="true" placeholder="Placeholder"/></c:set>

<span class="weatherStyle">
    <a href="http://www.wunderground.com/cgi-bin/findweather/getForecast?query=${city}%2C+${state}" title='${city}, ${state} <fmt:message key="Weather Forecast"/>' target="_blank">
        <img src="http://weathersticker.wunderground.com/weathersticker/cgi-bin/banner/ban/wxBanner?bannertype=wu_blueglass&ForcedCity=${city}&ForcedState=${state}&zip=${zip}&language=EN" alt='<fmt:message key="Find more about Weather in"/> ${city}, ${state}' width="160" />
    </a>
</span>
<%@include file="/apps/tc/components/content/weather/tracking-js.jsp"%>