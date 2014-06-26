<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
   <%@include file="/apps/tc/global/global.jsp"%>
<%@include file="/apps/tc/components/content/iframe/tracking-js.jsp"%>
<action:action actionClassName="com.tc.action.IframeAction" bean="iframeBean" actionName="getIframeInfo"  />
<iframe src="${iframeBean.srcPath}"  width="${iframeBean.width}" height="${iframeBean.height}" align="${iframeBean.alignment}" frameborder="${iframeBean.frameborder}" scrolling="${iframeBean.scrolling}"></iframe> 


