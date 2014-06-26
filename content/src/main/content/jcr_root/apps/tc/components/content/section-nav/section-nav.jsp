<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%>
<%@ page import="com.day.cq.i18n.I18n,
         java.util.Calendar,
         java.util.Locale,
         java.util.ResourceBundle,
         org.apache.commons.lang.StringUtils" %>

<cq:includeClientLib categories="tc.sectionnav" />

<action:action actionClassName="com.tc.action.SectionNavigationAction" bean="sectionTabBean" actionName="sectionNavigation" />

<div id="homepage">
    <div id="container_nav"></div>
    <ul class="treenav_top">
        <li class="toggle-call pos_fix"></li>
            <c:set var="count" value="0" />
            <c:forEach var="tabBean" items="${sectionTabBean.listOfTabs}">
                <c:set var="count" value="${count+1}" />
                <c:if test="${count eq 1}">
                <li class="treenav_tt">
                    <c:out value="${tabBean.pageTitle}" /></li>
                </c:if>
            </c:forEach>
    </ul>
    <div id="menu_wrapper">
        <div id="main_menu" class="sidetogglemenu nav nav__primary">
            <div class="seclist_carousel">
                <a class="sec_prev" id="secnav_prev" href="#"> </a>
                <a class="sec_next" id="secnav_next" href="#"> </a>
                <div class="sec_nav">
                    <ul id="sec_navul">
                        <c:set var="count" value="0" />
                        <c:forEach var="tabBean" items="${sectionTabBean.listOfTabs}">
                            <c:set var="count" value="${count+1}" />
                            <li id="${tabBean.scrollingId}2">
                                <a href="#<c:out value="${tabBean.scrollingId}" />"
                                   class="menuitem"><c:out value="${tabBean.pageTitle}" /></a>
                                <c:if test="${not empty tabBean.listOfTabs }">
                                    <div id="${tabBean.scrollingId}2-sub-menu" class="sub-menu"
                                         style="width: 100%; margin: 0 auto;">
                                        <div>
                                            <ul>
                                                <c:forEach var="bean1" items="${tabBean.listOfTabs}">
                                                    <c:set var="subtabbean" scope="request" value="${bean1}" />
                                                    <li id="${subtabbean.pageTitle}"><a href="#"><c:out
                                                                value="${subtabbean.pageTitle}" /> </a>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                </c:if>
                            </li>	
                        </c:forEach>
                    </ul>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
    <c:forEach var="tabBean" items="${sectionTabBean.listOfTabs}">
        <c:if test="${tabBean.pagePath!=null}">
            <div class="centeredContent">
                <div class="jumpAnchor">
                    <a class="jumpAnchor"
                       name="<c:out value="${tabBean.scrollingId}" />"
                       id="<c:out value="${tabBean.scrollingId}" />">&nbsp;</a>
                </div>
                <div class="content" id="<c:out value="${tabBean.scrollingId}" />_content">
                    <c:set var="scroll" value="${sectionTabBean.path}${tabBean.scrollingId}${tabBean.pagePath}" />
                    <sling:include path="${scroll}" />
                </div>
            </div>
        </c:if>
        <c:if test="${tabBean.pagePath==null}">
            <div class="centeredContent nocontent">
                <div class="jumpAnchor">
                    <a class="jumpAnchor"
                       name="<c:out value="${tabBean.scrollingId}" />"
                       id="<c:out value="${tabBean.scrollingId}" />">&nbsp;</a>
                </div>
            </div>
        </c:if>		
    </c:forEach>
</div>
<%@include file="/apps/tc/components/content/section-nav/tracking-js.jsp"%>        