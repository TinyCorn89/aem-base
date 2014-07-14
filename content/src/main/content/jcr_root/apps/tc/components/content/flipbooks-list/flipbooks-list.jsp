<%--

  Flipbooks List component.

  It lists the given PDFs and provide the links to Flipbook related to each PDF.

--%><%
%><%@include file="/apps/tc/global/global.jsp"%>
<%@page session="false" %>
<cq:includeClientLib categories="tc.custom.widgets"/>
<action:action actionClassName="com.tc.action.FlipbooksListAction" bean="flipbooksListBean" actionName="getFlipbooks" />
<cq:includeClientLib categories="tc.components.flipbooks-list" />

<c:if test="${empty flipbooksListBean}">
    <p align="center"><font color="red" size="+1">Please Configure The Component</font> <p>
</c:if>

<c:if test="${not empty flipbooksListBean}">
    <div id="wrapper">
		<div class="content">
            <h1>${flipbooksListBean.categoryName}</h1>
            <article>
                <c:forEach items="${flipbooksListBean.listOfPDFs}" var="pdfBean">
                    <div class="inner">
                        <h2><a target="_blank" title="${pdfBean.title}" href="${pdfBean.pdfPath}">${pdfBean.title}</a></h2>
                        <p>${pdfBean.subTitle}</p>
                    </div>
                    <figure>
                    	<a target="_blank" title="${pdfBean.title}" href="${pdfBean.pdfPath}"><img alt="${pdfBean.title}" src="${pdfBean.pdfThumbnail}"></a>
                    </figure>
			    </c:forEach>
            </article>
        </div>
    </div>
</c:if>