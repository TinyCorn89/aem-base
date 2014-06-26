<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%>
<%@page import="com.day.cq.wcm.core.stats.PageViewStatistics,javax.jcr.Node"%>

<action:action bean="shortArticle" actionClassName="com.tc.action.ShortArticleAction" actionName="shortArticle"/>


<ul class="recent-posts  unstyled">
    <li class="recent-posts_li post-1911 post type-post status-publish format-standard hentry category-business cat-33-id">
        <c:if test="${not empty shortArticle.articles }">
            <c:forEach var="articlesList" items="${shortArticle.articles}" begin="${shortArticle.index}" end="${shortArticle.index}"> 
                <figure class="thumbnail featured-thumbnail">
                    <c:if test="${articlesList.show == 'asset'}">  
                        <a  href="${articlesList.link}">
                            <img src="${articlesList.asset}/jcr:content/renditions/cq5dam.thumbnail.319.319.png">
                        </a>
                    </c:if>
                    <c:if test="${articlesList.show == 'image'}">
                        <a  href="${articlesList.link}">
                            <img src="${articlesList.imagePath}">
                        </a>
                    </c:if>
                    <c:if test="${shortArticle.showImpressions eq true}">
                        <span class="post-comments"><a href="#">${articlesList.pageImpressionCount}</a></span>
                        </c:if>
                    <span class="category"><a href="${articlesList.link}">${articlesList.category}</a></span>
                </figure>
                <h5>
                    <a href="${articlesList.link}">${articlesList.title}</a>
                </h5>
                <span class="excerpt">${fn:substring(articlesList.description, 0, 100)}</span>
                <a  class="btn btn-link btn-normal" href="${articlesList.link}">${shortArticle.buttonText}</a>
                <div class="clear"> </div>
            </c:forEach>
        </c:if>

    </li>
</ul>
<%@include file="/apps/tc/components/content/short-article/tracking-js.jsp"%>