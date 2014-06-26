<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%>
<cq:setContentBundle source="page"/>
<action:action bean="eventPreviewBean" actionClassName="com.tc.action.EventPreviewAction" actionName="getEventPreviewDetails"/>

<div class="posts-list no-meta events ">
    <c:if test="${not empty eventPreviewBean.title}">
        <h2 class="title">${eventPreviewBean.title}</h2>
    </c:if>    
    <c:forEach var="EventPreviewSnapshotBean" items="${eventPreviewBean.eventPreviewDetails}" varStatus="loop">
        <c:if test="${loop.index lt eventPreviewBean.noOfEvents}"> 
            <div class="row-fluid">
                <article class="span12 post__holder">
                    <figure class="featured-thumbnail thumbnail ">
                        <a title="Lorem ipsum dolor sit amet conse ctetur…" href="#">

                            <img alt="image not found" src="${EventPreviewSnapshotBean.imagePath}" height="68" width="125">

                        </a>
                    </figure>
                    <header class="post-header">
                        <h2 class="post-title">
                            <c:if test="${not empty EventPreviewSnapshotBean.linkToDetailsPath}">
                                <a href="${EventPreviewSnapshotBean.linkToDetailsPath}">
                                    <c:if test="${not empty EventPreviewSnapshotBean.title}">
                                        ${EventPreviewSnapshotBean.title}
                                    </c:if>   
                                </a>
                            </c:if>
                        </h2>
                        <div class="post_meta_even">
                            <span class="post_date">
                                <c:if test="${not empty EventPreviewSnapshotBean.date}">
                                    <time datetime="2013-02-14T20:31:03">${EventPreviewSnapshotBean.fromDate}</time>
                                    </c:if>
                            </span>
                        </div>
                    </header>
                    <c:if test="${not empty EventPreviewSnapshotBean.venue}">
                        <span class="location">${EventPreviewSnapshotBean.venue}..</span>
                    </c:if>
                    <c:if test="${not empty EventPreviewSnapshotBean.shortDescription}">
                        <p class="description">${EventPreviewSnapshotBean.shortDescription}..</p>
                    </c:if>
                </article>
            </div>
        </c:if>
    </c:forEach>		  
</div>
<c:if test="${not empty eventPreviewBean.eventPreviewDetails}">
    <a  class="btn btn-link btn-normal btn-inline " href="${eventPreviewBean.eventAllPath}">
        <fmt:message key="View all"/>
    </a>
</c:if>

<%@include file="/apps/tc/components/content/event-preview/tracking-js.jsp"%>