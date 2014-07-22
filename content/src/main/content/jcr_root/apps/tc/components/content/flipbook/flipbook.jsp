<%--

  Flipbook component.

  It convert the given PDF into an HTML5Flipbook.

--%><%
%><%@include file="/apps/tc/global/global.jsp"%>
<%@page session="false" %>
<action:action actionClassName="com.tc.action.FlipbookAction" bean="flipbookBean" actionName="getImagesPaths" />

<cq:includeClientLib categories="tc.components.dynamic-flipbook" />
<c:if test="${empty flipbookBean}">
    <p align="center"><font color="red" size="+1">No PDF is requested</font> <p>
</c:if>
<c:set var="imageCount" value="0"/>
<c:set var="imageMetaDataCount" value="0"/>
<c:set var="flag" value="0"/>
<c:if test="${not empty flipbookBean}">
	<body class="flipbook">
        <div id="magazine">    
            <c:forEach items="${flipbookBean.imagesPathList}" var="imagePath">
                <c:set var="imageCount" value="${imageCount+1}"/>
                <c:set var="flag" value="0"/>
                <c:forEach items="${flipbookBean.imagesMetaDataList}" var="imagesMetaData">
                    <c:set var="imageMetaDataCount" value="${imageMetaDataCount+1}"/>
					<c:if test="${flag == 0}" >
                        <c:if test="${imageCount == imageMetaDataCount}" >
                            <div><img src='${imagePath}' width="580px" height="760px" style="position:relative;float:right;" alt="${imagesMetaData}"/></div>
                            <c:set var="flag" value="1"/>
                        </c:if>
                    </c:if>
                    <c:if test="${flag == 1}" >
						<c:set var="imageMetaDataCount" value="0"/>
                    </c:if>
                </c:forEach>
            </c:forEach>
        </div>
    </body>
</c:if>