<%@ include file="/apps/company/global.jsp"%>

<domain:getYouTubeID />
<c:set var="caption"><cq:text property="caption" placeholder="" /></c:set>

    <iframe class="videoContainer aspect-4-3" src="http://www.youtube.com/embed/${youTubeID}">
</iframe>
<c:if test="${caption ne '' and caption ne Constants.EMPTY and caption ne null}">
    <p class="caption"><c:out value="${caption}" /></p>
</c:if>