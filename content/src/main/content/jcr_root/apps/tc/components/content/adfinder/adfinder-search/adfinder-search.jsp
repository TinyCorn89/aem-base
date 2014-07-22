  <%--

  Copyright (C) 2014 Virtusa Corporation

  AdFinder Search component.

  AdFinder Search

--%>
<%@include file="/apps/tc/global/global.jsp"%>
<%@page session="false" %>

<action:action actionClassName="com.tc.action.AdFinderSearchAction" bean="adFinderSearchBean" actionName="getAdFinderSearchDetails" />
<cq:includeClientLib categories="tc.components.adfindersearch"/>
<div id="wrapper">
	<div id="announce_block" class="advertisers form single">
		<!-- Nos annonceurs block -->
		<h1>Our advertisers</h1>
		<form id="adFinderSearchForm" name="adFinderSearchForm" method="get" action="/bin/adFinderSearchResultsServlet" accept-charset="utf-8" enctype="multipart/form-data">

        <fieldset>                                      

             <label for="advertiser_category">Keywords:</label>  

            <input type="text" id="advertisersKeywords" name="advertisersKeywords" value="">

            <label for="advertiser_category">Date:</label>  

             <select id="daterange" name="daterange" class="large">
                 <option value="7">In the last 7 days</option>
                <option value="14">Since the last 14 days</option>
               <option value="28">Since the last 28 days</option>
           </select>




            <input type="hidden" name="resultPath" id="resultPath" value="${currentPage.path}">

            <button id="submit" class="btn submit" title="Search" value="Search" name="submit" type="submit" onclick="">Search</button>

	  	</fieldset>


		</form>

 <c:forEach items="${requestScope.adFinderSearchResults}" var="bean">
    <c:if test="${not empty bean}">
        <h2>Advertiser information</h2>
         <div class="informations">
             <div class="advertisers-info">
               <span class="title">${bean.name}</span>
                 <p>${bean.adress}<br>${bean.city} <br><strong>${bean.zipcode}</strong></p>
		     </div>
          </div>
         <h2>Ads published by this advertiser</h2>
    <c:forEach items="${bean.listOfAds}" var="adFinderSearchResultBean">

            <c:if test="${not empty adFinderSearchResultBean}">
            	<section class="advertisers ">
				<figure>
					<a title="5607714" href="http://annonces.transcontinentalmedia.com/annonces/5607714.jpg" id="unique-ad-5607714" class="entry-title lightbox">
					<img title="${bean.name}" alt="${bean.name}" src="${adFinderSearchResultBean.imagePath}"></a>
				</figure>

				<div class="inner">
					<p class="updated"><span>${adFinderSearchResultBean.publicationDate}</span></p>

					<p class="pdf">
					<a target="_blank" href="http://annonces.transcontinentalmedia.com/pdf/AW/${adFinderSearchResultBean.adID}.pdf">Download the PDF version</a></p>
				</div>
		</section>


             </c:if>
       </c:forEach>

     </c:if>
	</c:forEach>

	</div>

</div>


<script type="text/javascript">
    window.onload = function()
    {        
<%
    	if(request.getAttribute("advertisersKeywords")!=null){
%>
       		document.getElementById("advertisersKeywords").value = '${advertisersKeywords}';
<%
		}

%>
    <%
    	if(request.getAttribute("daterange")!=null){
%>
       		document.getElementById("daterange").value = '${daterange}';
<%
		}

%>

    }
</script>        