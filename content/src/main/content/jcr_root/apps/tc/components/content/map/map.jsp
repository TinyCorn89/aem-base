<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%>
<cq:includeClientLib categories="tc.components.map"/>
<action:action actionClassName="com.tc.action.MapAction" bean="mapBean" actionName="getMapInfo" />

<div class="google-maps" style="width:${mapBean.width}px; height:${mapBean.height}px">
    <iframe width="${mapBean.width}" scrolling="no" height="${mapBean.height}" frameborder="0" marginheight="0" marginwidth="0" src="http://maps.google.com/maps?f=q&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=${mapBean.location}&amp;aq=0&amp;sll=37.0625,-95.677068&amp;sspn=47.704107,79.013672&amp;ie=UTF8&amp;<c:if test="${mapBean.near ne ''}">,hnear=${mapBean.near}</c:if>;ll=40.649974,-73.949919&amp;spn=0.01628,0.028238&amp;z=14&amp;iwloc=A&amp;output=embed"></iframe>
</div>
<%@include file="/apps/tc/components/content/map/tracking-js.jsp"%>