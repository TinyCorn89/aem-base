<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%>
<%@page import="java.util.Random"%>
<cq:setContentBundle source="page"/>
<cq:includeClientLib categories="tc.components.news" />
<cq:includeClientLib categories="tc.custom.widgets"/>
<action:action bean="newsBean" actionClassName="com.tc.action.NewsAction" actionName="getNews" />

<c:set var="id" value="${func:getNextRandomNumber()}" />
<c:set var="strId" value="a${id}c"/>

<div id="top_headlines_news_module" class="news_scroll${strId} component secondary_stories headlines_module">
	<a class="rssprev" id="rss_prev" href="#"><fmt:message key="prev"/> </a>
	<div id="top_headlines_module" data-section-accessor="headlines" class="top_headlines_news_module editable">
		<h2 class="header_text">
			<c:if test="${not empty newsBean.header}">
                                ${newsBean.header}
                        </c:if>
		</h2>
		<ul>
            <div class="" id="feed${id}"></div>

		</ul>

	</div>
	<a class="rssnext" id="rss_next" href="#"><fmt:message key="next"/> </a>
</div>

<script type="text/javascript">
    var externalFeedUrl = "${newsBean.url}";
    var externalShowDescription = "${newsBean.showDescription}";
    var externalShowDescriptionStatus;
    var externalShowDate = "${newsBean.showDate}";
    var externalShowDateStatus;
    var externalNewsId = "${id}";

    if (externalShowDescription == "true") {
        externalShowDescriptionStatus = true;
    } else {
        externalShowDescriptionStatus = false;
    }

    if (externalShowDate == "true") {
        externalShowDateStatus = true;
    } else {
        externalShowDateStatus = false;
    }

    $(".allnews").click(function () {
        $("#feed" + externalNewsId).FeedEk({
            FeedUrl: externalFeedUrl,
            ShowDesc: externalShowDescriptionStatus,
            ShowPubDate: externalShowDateStatus,
        });
    });

    $(document).ready(function () {

        var feedUrl = "${newsBean.url}";
        var showDescription = "${newsBean.showDescription}";
        var showDescriptionStatus;
        var showDate = "${newsBean.showDate}";
        var showDateStatus;
        var noOfItems = parseInt("${newsBean.noOfItems}");
        var strId = "${strId}";
        var newsId = "${id}";

        if (showDescription == "true") {
            showDescriptionStatus = true;
        } else {
            showDescriptionStatus = false;
        }

        if (showDate == "true") {
            showDateStatus = true;
        } else {
            showDateStatus = false;
        }

        $("#feed" + newsId).FeedEk({
            FeedUrl: feedUrl,
            ShowDesc: showDescriptionStatus,
            ShowPubDate: showDateStatus,
        });

        var test = noOfItems;        
        newsFeedScroll(strId, test);

    });
</script>
<%@include file="/apps/tc/components/content/news/tracking-js.jsp"%>        