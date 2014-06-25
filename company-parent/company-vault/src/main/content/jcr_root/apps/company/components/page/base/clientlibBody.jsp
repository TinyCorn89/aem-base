<%@ include file="/apps/company/global.jsp"%>
<cq:includeClientLib js="apps.company.base" />
<c:if test="${isEditMode or isDesignMode}">
    <!-- [Client Lib Edit/Design mode enabled] -->
    <cq:includeClientLib js="apps.company.cq" />
</c:if>
