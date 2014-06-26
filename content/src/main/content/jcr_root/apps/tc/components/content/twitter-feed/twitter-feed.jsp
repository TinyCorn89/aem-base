<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

  Twitter Feed component.

  Twitter Feed

--%>
<%@include file="/apps/tc/global/global.jsp"%>
<%@page session="false" %>
<cq:setContentBundle source="page"/>
<action:action actionClassName="com.tc.action.TwitterFeedAction" bean="twitterFeedBean" actionName="getTwitterWidgetProperties" />

<a class="twitter-timeline" href="https://twitter.com/${twitterFeedBean.userName}" data-widget-id="${twitterFeedBean.widgetId}" data-theme="${twitterFeedBean.theme}" height="${twitterFeedBean.height}" width="${twitterFeedBean.width}"><fmt:message key="Tweets by"/> @${twitterFeedBean.userName}</a>

<cq:includeClientLib categories="tc.social.twitterfeed" />
<%@include file="/apps/tc/components/content/twitter-feed/tracking-js.jsp"%>