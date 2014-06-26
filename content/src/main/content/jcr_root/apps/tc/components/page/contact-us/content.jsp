<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%><%
%><%@page session="false"%><%
%>
<cq:setContentBundle source="page"/>
<cq:includeClientLib categories="tc.components.contactus"/>
<div class="row">
    <div class="contact-us">
        <c:choose>
            <c:when test="${param.email eq null}">
                <cq:include path="contact-us-form" resourceType="/apps/tc/components/content/contact-us-form" />
            </c:when>
            <c:otherwise>
                <fmt:message key="contactUsMessage"/>. <fmt:message key="delayMessage"/>.
            </c:otherwise>
        </c:choose>
    </div>
    <!-- Address -->
    <div class="address-contact-us">

        <cq:include path="address" resourceType="foundation/components/parsys" />

    </div>
    <!-- Address -->
</div>
<!-- Contact us Form end -->



