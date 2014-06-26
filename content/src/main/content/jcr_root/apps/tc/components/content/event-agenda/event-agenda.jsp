<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@ page contentType="text/html;encoding=UTF-8" %>
<%@include file="/apps/tc/global/global.jsp"%>
<cq:setContentBundle source="page"/>
<action:action actionClassName="com.tc.action.EventAgendaAction" bean="eventAgendaHeaderBean" actionName="getEventAgendaHeaderInfo" />
<cq:includeClientLib categories="tc.components.event-agenda"/>
<cq:includeClientLib categories="tc.custom.widgets"/>
<div class="tabs-wrapper">
    <ul class="nav nav-tabs events">
        <c:set var="count" value="0" scope="page" />
        <c:forEach var="detail" items="${eventAgendaHeaderBean.eventDetailsBeans}">
            <c:set var="count" value="${count + 1}" scope="page"/>
            <li class="">
                <a href="#${count}">${detail.eventTitle} </a>
            </li>
        </c:forEach>
    </ul>
</div>
<c:set var="count" value="0" scope="page" />
<c:forEach var="detail" items="${eventAgendaHeaderBean.eventDetailsBeans}">
    <c:set var="count" value="${count + 1}" scope="page"/>
    <div id="${count}" class="eventAgendaHeight">&nbsp;</div>
    <h2 class="event_title">${detail.eventTitle}</h2>
    <p><strong><c:if test="${detail.fromDate ne null}"><fmt:message key="From"/> ${detail.fromDate} </c:if><c:if test="${detail.toDate ne null}"><fmt:message key="to"/>  ${detail.toDate} </c:if> </strong></p>
    ${detail.eventPresentation}
    <ul class="event_links">
        <c:if test="${detail.pdfLink ne null}">
        	<li class=""><a class="btn-link" href="${detail.pdfLink}"><fmt:message key="Download PDF"/></a></li>
        </c:if>
        <c:if test="${detail.htmlLink ne null}">
        	<li><a class="btn-link" href="${detail.htmlLink}" target="_blank"><fmt:message key="Go to google"/></a></li>
        </c:if>
    </ul>
</c:forEach>
<%@include file="/apps/tc/components/content/event-agenda/tracking-js.jsp"%>