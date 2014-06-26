<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
    
<%@include file="/apps/tc/global/global.jsp"%>
<cq:setContentBundle source="page"/>
<action:action bean="galleryBean" actionClassName="com.tc.action.GalleryAction" actionName="getGallery" />
<cq:includeClientLib categories="tc.components.gallery" />
<cq:includeClientLib categories="tc.custom.widgets"/>
<h2>${galleryBean.heading}</h2>
<div id="nav">
    <ul id="videoNav" class="videoNav">
        <!--<li><a href="" data-filter="*">All</a></li>-->
            <c:choose>
                <c:when test="${galleryBean.selector == 'simple'}">
                    <c:forEach var="itemList" items="${galleryBean.tabNav}" varStatus="rowCounter">
                    <li><a href="" data-filter=".${itemList}">${itemList}</a></li>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach var="albumList" items="${galleryBean.albumList}" varStatus="rows">
                        <c:forEach var="itemCategory" items="${albumList.albumTag}" varStatus="rowCounter">

                        <li><a href="" data-filter=".${itemCategory}">${itemCategory}</a></li>
                        </c:forEach>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
    </ul>
</div>
<c:choose>
    <c:when test="${galleryBean.selector == 'simple'}">
        <div id="content" class="simple_gallery">
            <c:forEach var="itemList" items="${galleryBean.galleryItemList}" varStatus="rowCounter">
                <div class="box posts-grid <c:forEach var="categoryList" items="${itemList.itemCategory}" varStatus="rowCounter">${categoryList} </c:forEach>">
                    <c:choose>
                        <c:when test="${galleryBean.galleryType == 'video'}">
                            <ul class="posts-grid row-fluid unstyled videos">
                                <li class="span6">
                                    <figure class="featured-thumbnail thumbnail">
                                        <a class="popup-video" title="${itemList.caption}" href="${itemList.itemPath}"> <span href="#" class="gal-type"> <span class="acbt-txt"> ${itemList.title}
                                                </span>
                                            </span> <img src="${itemList.itemPath}/jcr:content/renditions/cq5dam.thumbnail.140.100.png"> <span class="thumb-border"> </span>
                                        </a>
                                    </figure>
                                    <!--<div class="clear"></div> <span class="post_category"> <a href="#"> ${itemList.title} </a>-->
                                    </span>
                                    <h5>
                                        <a href="#"> ${itemList.description}</a>
                                    </h5>
                                </li>
                            </ul>
                        </c:when>
                        <c:otherwise>
                            <ul class="posts-grid row-fluid unstyled photos">
                                <li class="span6">
                                    <figure class="featured-thumbnail thumbnail">
                                        <a href="${itemList.itemPath}" title="${itemList.caption}" rel="prettyPhoto[gallery1]"> <span class="gal-type" href="#"> <span class="acbt-txt">
                                                    <fmt:message key="Image"/> </span>
                                            </span> <img oncontextmenu="alert('log');return false;" src="${itemList.itemPath}/jcr:content/renditions/cq5dam.thumbnail.140.100.png" alt="Slideshow Format" /> <span
                                                class="zoom-icon"> </span>
                                        </a>
                                    </figure>
                                    <div class="clear"></div> <span class="post_category"> <a href="#"> ${itemList.title} </a>
                                    </span>
                                    <h5>
                                        <a href="#"> ${itemList.description} </a>
                                    </h5>
                                </li>
                            </ul>
                        </c:otherwise>
                    </c:choose>
                </div>
            </c:forEach>
        </div>
    </c:when>
    <c:otherwise>
        <div id="content" class="advanced_gallery">
            <c:forEach var="albumList" items="${galleryBean.albumList}" varStatus="rows">
                <c:choose>
                    <c:when test="${galleryBean.galleryType == 'video'}">
                        <div class="box  posts-grid ${albumList.albumTag} videos">
                            <c:forEach var="itemList" items="${albumList.iteamList}" varStatus="rowCounter">
                                <c:forEach var="itemPath" items="${itemList.itemPath}" varStatus="x">
                                    <c:choose>
                                        <c:when test="${rowCounter.index != '0'}">
                                            <figure class="featured-thumbnail videoalbum" style="display: none;">
                                                <a rel="prettyVideo" href="${itemPath}"> <span href="#" class="gal-type"> <span class='acbt-txt'> ${itemList.title} </span>
                                                    </span> <img src="${itemPath}/jcr:content/renditions/cq5dam.thumbnail.140.100.png"> <span class="thumb-border"> </span>
                                                </a>
                                            </figure>
                                        </c:when>
                                        <c:otherwise>
                                            <figure class="featured-thumbnail videoalbum">
                                                <a rel="prettyVideo" href="${itemPath}"> <span href="#" class="gal-type"> <span class='acbt-txt'> ${itemList.title} </span>
                                                    </span> <img src="${itemPath}/jcr:content/renditions/cq5dam.thumbnail.140.100.png"> <span class="thumb-border"> </span>
                                                </a>
                                            </figure>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </c:forEach>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="box ${albumList.albumTag} photos">
                            <c:forEach var="itemList" items="${albumList.iteamList}" varStatus="rowCounter">
                                <c:forEach var="itemPath" items="${itemList.itemPath}" varStatus="x">
                                    <c:choose>
                                        <c:when test="${rowCounter.index != '0'}">
                                            <figure class="featured-thumbnail thumbnail" style="display: none;">
                                                <a href="${itemPath}" title="${itemList.damTitle}" rel="prettyPhoto[gallery1]"> <span class="gal-type" href="#"> <span class="acbt-txt">
                                                            <fmt:message key="Image"/> </span>
                                                    </span> <img src="${itemPath}/jcr:content/renditions/cq5dam.thumbnail.140.100.png" alt="Slideshow Format" /> <span class="zoom-icon"> </span>
                                                </a>
                                            </figure>
                                        </c:when>
                                        <c:otherwise>
                                            <figure class="featured-thumbnail thumbnail">
                                                <a href="${itemPath}" title="${itemList.damTitle}" rel="prettyPhoto[gallery1]"> <span class="gal-type" href="#"> <span class="acbt-txt">
                                                            <fmt:message key="Image"/> </span>
                                                    </span> <img src="${itemPath}/jcr:content/renditions/cq5dam.thumbnail.140.100.png" alt="Slideshow Format" /> <span class="zoom-icon"> </span>
                                                </a>
                                            </figure>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </c:forEach>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:otherwise>
</c:choose>

<c:if test="${galleryBean.saveRestrict == 'true'}">
</c:if>
<%@include file="/apps/tc/components/content/gallery/tracking-js.jsp"%>
