<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%>
<cq:includeClientLib categories="tc.components.youtube-video"/>
<!-- ================================ Youtube Video Start ================================ -->
<c:set var="caption"><cq:text property="caption" placeholder="" /></c:set>
<c:set var="youTubeID"><cq:text property="youTubeLink" placeholder="" /></c:set>
<c:set var="displayHeight"><cq:text property="displayHeight" placeholder="" /></c:set>
<c:set var="displayWidth"><cq:text property="displayWidth" placeholder="" /></c:set>
    <div class="video-container">
        <iframe class="videoContainer aspect-4-3" width="${displayWidth}" height="${displayHeight}"
            src="http://www.youtube.com/embed/${youTubeID}">
    </iframe>
</div>
<c:if test="${caption ne '' and caption ne Constants.EMPTY and caption ne null}">
    <p class="caption"><c:out value="${caption}" /></p>
</c:if>
<%@include file="/apps/tc/components/content/youtube-video/tracking-js.jsp"%>
<!-- ================================ Youtube Video End ================================ -->