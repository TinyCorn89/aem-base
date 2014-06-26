<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>

<%@include file="/apps/tc/global/global.jsp"%>

<action:action bean="eventInformationBean" actionClassName="com.tc.action.EventInformationAction" actionName="getEventInfo"/>
<div class="motopress-wrapper content-holder clearfix">
    <div class="row">
        <div class="span12" data-motopress-type="static" data-motopress-static-file="static/static-title.php">
            <section class="title-section">
                <h1 class="title-header">
                    <c:if test="${not empty eventInformationBean.title}">
                        ${eventInformationBean.title}
                    </c:if>   
                </h1>
            </section><!-- .title-section -->
        </div>
    </div>
    <div class="row">
        <div class="span8 right" id="content" data-motopress-type="loop" data-motopress-loop-file="loop/loop-single.php">
            <article id="post-1908" class="post-1908 post type-post status-publish format-standard hentry category-lifestyle post__holder cat-34-id">
                <c:if test="${not empty eventInformationBean.image}">	
                    <figure class="featured-thumbnail thumbnail large" ><img src="${eventInformationBean.image}"  alt="Event Images" >
                    </figure>
                </c:if>   
                <div class="even_details">
                    <h1>
                        <c:if test="${not empty eventInformationBean.venueName}">
                            ${eventInformationBean.venueName}
                        </c:if>
                    </h1>
                    <p>
                        <c:if test="${not empty eventInformationBean.fromDate}">
                            ${eventInformationBean.fromDate}  
                        </c:if>
                        <c:if test="${not empty eventInformationBean.fromDate && not empty eventInformationBean.toDate}">
                            -
                        </c:if>
                        <c:if test="${not empty eventInformationBean.toDate}">
                            ${eventInformationBean.toDate}
                        </c:if>
                    </p>
                    <p>
                        <c:if test="${not empty eventInformationBean.city}">
                            ${eventInformationBean.city}
                        </c:if>
                        <c:if test="${not empty eventInformationBean.city && not empty eventInformationBean.state}">
                            , 
                        </c:if>
                        <c:if test="${not empty eventInformationBean.state}">
                            ${eventInformationBean.state} 
                        </c:if>
                        <c:if test="${not empty eventInformationBean.city && not empty eventInformationBean.country && empty eventInformationBean.state}">
                            , 
                        </c:if>
                        <c:if test="${not empty eventInformationBean.state && not empty eventInformationBean.country}">
                            , 
                        </c:if>
                        <c:if test="${not empty eventInformationBean.country}">
                            ${eventInformationBean.country}.
                        </c:if>
                    </p>
                </div>
                <!-- Post Content -->
                <div class="post_content">
                    <div class="event_map">
                        <c:if test="${not empty eventInformationBean.showDirections}">
                            <iframe  width="425" height="350" scrolling="no"  frameborder="0" marginheight="0" marginwidth="0" src="http://maps.google.com/maps?f=q&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=${eventInformationBean.addressForGoogle}&amp;aq=0&amp;sll=37.0625,-95.677068&amp;sspn=47.704107,79.013672&amp;ie=UTF8&amp;hnear=${eventInformationBean.addressForGoogle};ll=40.649974,-73.949919&amp;spn=0.01628,0.028238&amp;z=14&amp;iwloc=A&amp;output=embed"></iframe>
                        </c:if>
                    </div>
                    <c:if test="${not empty eventInformationBean.description}">
                        ${eventInformationBean.description}
                    </c:if>
                    <div class="clear"></div>
                </div>
                <!-- //Post Content -->	
            </article>
            <!-- .share-buttons -->
        </div>

    </div>
</div>
<%@include file="/apps/tc/components/content/event-info/tracking-js.jsp"%>