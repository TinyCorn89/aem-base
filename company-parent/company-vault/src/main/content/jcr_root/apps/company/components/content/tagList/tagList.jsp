<%@ include file="/apps/company/global.jsp"%>

<domain:getTagsForPage />
<c:forEach var="tag" items="${tagList}" varStatus="currentTag">
    ${tag.title}
</c:forEach>