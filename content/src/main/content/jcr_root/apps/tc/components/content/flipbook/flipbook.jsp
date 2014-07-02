<%--

  Flipbook component.

  It convert the given images into an HTML5Flipbook.

--%><%
%><%@include file="/apps/tc/global/global.jsp"%>
<%@page session="false" %>
<action:action actionClassName="com.tc.action.FlipbookAction" bean="flipbookBean" actionName="getImagesPaths" />

<cq:includeClientLib categories="tc.components.flipbook" />
<c:if test="${empty flipbookBean}">
    <p align="center"><font color="red" size="+1">Please Configure The Component</font> <p>
</c:if>

<c:if test="${not empty flipbookBean}">
	<body class="flipbook">
        <div id="magazine">    
            <c:forEach items="${flipbookBean.imagesPathList}" var="imagePath">
                <div style="background-image:url(${imagePath});"></div>            
            </c:forEach>
        </div>
    </body>
</c:if>