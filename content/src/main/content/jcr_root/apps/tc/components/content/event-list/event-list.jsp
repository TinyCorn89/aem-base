<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>

<%@include file="/apps/tc/global/global.jsp"%>
<action:action bean="eventPreviewBean" actionClassName="com.tc.action.EventPreviewAction" actionName="getEventListDetails"/>


<div class="posts-list no-meta events ">
    <h2 class="title">${eventPreviewBean.title}</h2>
    <c:forEach var="EventPreviewSnapshotBean" items="${eventPreviewBean.eventPreviewDetails}" varStatus="loop">

        <div class="row-fluid">
            <article class="span12 post__holder">
                <figure class="featured-thumbnail thumbnail ">
                    <a title="Lorem ipsum dolor sit amet conse ctetur…" href="#">
                        <img alt="image not found" src="${EventPreviewSnapshotBean.imagePath}" height="68" width="125">
                    </a>
                </figure>
                <header class="post-header">
                    <h2 class="post-title">
                        <a href="${EventPreviewSnapshotBean.linkToDetailsPath}">
                            ${EventPreviewSnapshotBean.title}
                        </a>
                    </h2>
                    <div class="post_meta_even">
                        <span class="post_date">
                            <time datetime="2013-02-14T20:31:03">${EventPreviewSnapshotBean.date}</time>
                        </span>
                    </div>
                </header>
                <span class="location">${EventPreviewSnapshotBean.shortAddress}</span>
                <p class="description">${EventPreviewSnapshotBean.shortDescription}</p>
            </article>
        </div>

    </c:forEach>		  
</div>
<%@include file="/apps/tc/components/content/event-list/tracking-js.jsp"%>