<%@ include file="/apps/company/global.jsp"%>
<cq:includeClientLib css="apps.company.base" />
<c:if test="${isEditMode or isDesignMode}">
    <!-- [Client Lib Edit/Design mode enabled] -->
    <cq:includeClientLib css="apps.company.cq" />
</c:if>
