<%@ include file="/apps/company/global.jsp"%>
-PLACEHOLDER-
<c:set var="tagName"><cq:text property="tag"/></c:set>
<domain:getPagesByTag tag="${tagName}" />
<c:forEach var="page" items="${taggedPages}" varStatus="currentPage">
    ${page.title}
</c:forEach>