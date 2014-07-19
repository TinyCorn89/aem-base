<%--

  Copyright (C) 2014 Virtusa Corporation

  AdFinder Search component.

  AdFinder Search

--%>
<%@include file="/apps/tc/global/global.jsp"%>
<%@page session="false" %>
<action:action actionClassName="com.tc.action.AdFinderSearchAction" bean="adFinderSearchBean" actionName="getAdFinderSearchDetails" />
<h1 align="center">AdFinder Search</h1>
<center>
    <form id="adFinderSearchForm" name="adFinderSearchForm" method="get" action="/bin/adFinderSearchResultsServlet" accept-charset="utf-8" enctype="multipart/form-data">
        <label>Advertiser:</label>
        <select id="advertiserId" name="advertiserId">
            <option value=""></option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="5607714">5607714</option>
        </select>
        <br/><br/>
        <input type="hidden" name="resultPath" id="resultPath" value="${currentPage.path}">
        <input type="submit" name="submit" id="submit" onclick="" value="Search">
    </form>
</center>

<script type="text/javascript">
    window.onload = function()
    {        
<%
    	if(request.getAttribute("advertiserId")!=null){
%>
       		document.getElementById("advertiserId").value = '${advertiserId}';
<%
		}
%>
    }
</script>        