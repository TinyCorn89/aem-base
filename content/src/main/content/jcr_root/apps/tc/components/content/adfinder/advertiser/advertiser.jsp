<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%>
<%@page session="false" %>
<cq:setContentBundle source="page"/>
<action:action actionClassName="com.tc.action.AdvertiseAction" bean="advertiseBean" actionName="getAdvertiserInfo" />

Name1 <p>${advertiseBean.name1}</p>
 Name2 <p>${advertiseBean.name2}</p>
Address<p>${advertiseBean.address}</p>
city<p>${advertiseBean.city}</p>
Province<p>${advertiseBean.province}</p>
country<p>${advertiseBean.country}</p>
zipcode<p>${advertiseBean.zipcode}</p>
telephone<p>${advertiseBean.telephone}</p>
clientNumber<p>${advertiseBean.clientNumber}</p>