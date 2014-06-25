<%@ include file="/apps/company/global.jsp" %>
--PLACEHOLDER--
<domain:getStickyNavigation />
<div class="sticky-wrapper">
    <nav id="sticky">
        <c:forEach items="${sections}" var="current" varStatus="item">
            <a class="scroll-to scroll-to-child-${item.index}" href="#${current[1]}">${current[2]}</a>
        </c:forEach>
    </nav>
</div>
