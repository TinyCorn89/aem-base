<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

  Facebook Feed component.

  Facebook Feed

--%>
<%@include file="/apps/tc/global/global.jsp"%>
<%@page session="false" %>

<cq:includeClientLib categories="tc.social.facebookfeed" />

<action:action actionClassName="com.tc.action.FacebookFeedAction" bean="facebookFeedBean" actionName="getFacebookFeedProperties" />

<div id="fb-root"></div>
<div class="fb-like-box" data-href="https://www.facebook.com/${facebookFeedBean.pageName}" data-width="${facebookFeedBean.width}" data-height="${facebookFeedBean.height}" data-colorscheme="${facebookFeedBean.colorScheme}" data-show-faces="${facebookFeedBean.showFaces}" data-header="${facebookFeedBean.showHeader}" data-stream="${facebookFeedBean.showPosts}" data-show-border="${facebookFeedBean.showBorder}"></div>
<%@include file="/apps/tc/components/content/facebook-feed/tracking-js.jsp"%>