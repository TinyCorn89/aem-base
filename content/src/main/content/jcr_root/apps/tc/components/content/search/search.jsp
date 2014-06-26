<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%>
<cq:includeClientLib categories="tc.custom.widgets"/>
<cq:includeClientLib categories="tc.search"/>
<cq:setContentBundle source="page"/>
<action:action bean="searchBean" actionClassName="com.tc.action.SearchAction" actionName="getSearchDialogInfo" />

<c:set var="simpleSearch" value="${searchBean.simpleSearchBean}" />
<c:set var="advanceSearch" value="${searchBean.advancedSearchBean}" />

<%
    System.out.println("    "+request.getAttribute("fromPage"));
%>
<form id="searchForm" name="searchForm" method="get" action="/bin/searchResultsServlet" accept-charset="utf-8" enctype="multipart/form-data">

    <input type="hidden" name="spellcheckText" id="spellcheckText" value="${simpleSearch.spellcheckText}">
    <input type="hidden" name="similarPagesText" id="similarPagesText" value="${simpleSearch.similarPagesText}">
    <input type="hidden" name="relatedSearchesText" id="relatedSearchesText" value="${simpleSearch.relatedSearchesText}">

    <input type="hidden" name="resultPagesText" id="resultPagesText" value="${simpleSearch.resultPagesText}">
    <input type="hidden" name="tagKeyword" id="tagKeyword" >

    <input type="hidden" name="simpleContentPath" id="simpleContentPath" value="${simpleContentPath}">
    <input type="hidden" name="fromPage" id="fromPage" value="${fromPage}">

    <input type="hidden" name="contentPath" id="contentPath" value="${searchBean.contentPath}">
    <input type="hidden" name="articlePath" id="articlePath" value="${searchBean.articlePath}">
    <input type="hidden" name="damPath" id="damPath" value="${searchBean.damPath}">
    <input type="hidden" name="resultPath" id="resultPath" value="${currentPage.path}">
    <input type="hidden" name="currentIndex" id="currentIndex" value="${searchBean.currentIndex}">


    <%if(request.getAttribute("fromPage")!=null && request.getAttribute("fromPage").toString().equals("simplesearch")){}else{%>
    <div id="commentform" class="advanced_search">
        <c:choose>
            <c:when test="${advanceSearch.section == true || advanceSearch.keyword == true || advanceSearch.author == true  || advanceSearch.contentType == true || advanceSearch.timeRange == true}" >
                <h1 class="title-header"><fmt:message key="Advanced Search"/></h1>
                <c:if test="${advanceSearch.section == true}">
                    <p>
                        <select class="select_menu" id="sectionTab" name="sectionTab">
                            <option value=""><fmt:message key="Section"/></option>
                            <c:forEach var="map" items="${advanceSearch.sectionMenus}">
                                <option value="${map.path}">${map.name}</option>
                            </c:forEach>
                        </select>
                    </p>
                </c:if>

                <c:if test="${advanceSearch.keyword == true}">
                    <p class="field">
                        <input type="text" name="keyword" id="keyword" placeholder='<fmt:message key="Keyword"/>' size="22" tabindex="1" aria-required="true">
                    </p>
                </c:if>
                <c:if test="${advanceSearch.author == true}">
                    <p class="field">
                        <input type="text" name="author" id="author" placeholder='<fmt:message key="Author"/>' size="22" tabindex="1" aria-required="true">
                    </p>
                </c:if>

                <c:if test="${advanceSearch.contentType == true}">
                    <p>
                        <select class="select_menu" name="contentType" id="contentType">
                            <option value=""><fmt:message key="Content Type"/></option>
                            <option value="article"><fmt:message key="Articles"/></option>
                            <option value="image"><fmt:message key="Images"/></option>
                            <option value="video"><fmt:message key="Videos"/></option>
                        </select>
                    </p>
                </c:if>

                <c:if test="${advanceSearch.timeRange == true}">
                    <div class="range">
                        <h3><fmt:message key="Range"/>: </h3>
                        <div>
                            <span><input type="radio" onclick="cmbo(1)" value="-7d" name="timeRange" id="timeRange7D" >&nbsp;<fmt:message key="Within past week"/></span>
                            <span><input type="radio" onclick="cmbo(1)" value="-1M" name="timeRange" id="timeRange1M" >&nbsp;<fmt:message key="Within past 30 days"/></span>
                            <span><input type="radio" onclick="cmbo(1)" value="-3M" name="timeRange" id="timeRange3M" >&nbsp;<fmt:message key="Within past 90 days"/></span>
                        </div>
                        <div>
                            <span><input type="radio" onclick="cmbo(1)" value="1Y" name="timeRange" id="timeRange1Y" >&nbsp;<fmt:message key="Within past year"/></span>
                            <span><input type="radio" onclick="cmbo(2)" value="DD" name="timeRange" id="timeRangeDD" >&nbsp;<fmt:message key="Date range"/>:</span>
                            <span><input type="radio" onclick="cmbo(1)" value="-15Y" name="timeRange" id="timeRange5Y" >&nbsp;<fmt:message key="Since beginning"/></span>
                        </div>
                        <div id="dterge">
                            <div>
                                <div>
                                    <div class="from"><fmt:message key="From"/>:</div>
                                    <div>
                                        <select onclick="" name="dateFromMM" id="dateFromMM">
                                            <option value="01"><fmt:message key="Jan"/></option>
                                            <option value="02"><fmt:message key="Feb"/></option>
                                            <option value="03"><fmt:message key="Mar"/></option>
                                            <option value="04"><fmt:message key="Apr"/></option>
                                            <option value="05"><fmt:message key="May"/></option>
                                            <option value="06"><fmt:message key="Jun"/></option>
                                            <option value="07"><fmt:message key="Jul"/></option>
                                            <option value="08"><fmt:message key="Aug"/></option>
                                            <option value="09"><fmt:message key="Sep"/></option>
                                            <option value="10"><fmt:message key="Oct"/></option>
                                            <option value="11"><fmt:message key="Nov"/></option>
                                            <option value="12"><fmt:message key="Dec"/></option>
                                        </select>
                                        <select onclick="" name="dateFromDD" id="dateFromDD">
                                            <option value="01">01</option>
                                            <option value="02">02</option>
                                            <option value="03">03</option>
                                            <option value="04">04</option>
                                            <option value="05">05</option>
                                            <option value="06">06</option>
                                            <option value="07">07</option>
                                            <option value="08">08</option>
                                            <option value="09">09</option>
                                            <option value="10">10</option>
                                            <option value="11">11</option>
                                            <option value="12">12</option>
                                            <option value="13">13</option>
                                            <option value="14">14</option>
                                            <option value="15">15</option>
                                            <option value="16">16</option>
                                            <option value="17">17</option>
                                            <option value="18">18</option>
                                            <option value="19">19</option>
                                            <option value="20">20</option>
                                            <option value="21">21</option>
                                            <option value="22">22</option>
                                            <option value="23">23</option>
                                            <option value="24">24</option>
                                            <option value="25">25</option>
                                            <option value="26">26</option>
                                            <option value="27">27</option>
                                            <option value="28">28</option>
                                            <option value="29">29</option>
                                            <option value="30">30</option>
                                            <option value="31">31</option>
                                        </select>
                                        <select onclick="" name="dateFromYYYY" id="dateFromYYYY">
                                            <option value="2001">2001</option>
                                            <option value="2002">2002</option>
                                            <option value="2003">2003</option>
                                            <option value="2004">2004</option>
                                            <option value="2005">2005</option>
                                            <option value="2005">2006</option>
                                            <option value="2007">2007</option>
                                            <option value="2008">2008</option>
                                            <option value="2009">2009</option>
                                            <option value="2010">2010</option>
                                            <option value="2011">2011</option>
                                            <option value="2012">2012</option>
                                            <option value="2013">2013</option>
                                            <option value="2014">2014</option>
                                            <option value="2015">2015</option>
                                        </select>
                                    </div>
                                </div>
                                <div>
                                    <div class="to"><fmt:message key="to2"/>:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
                                    <div>
                                        <select onclick="" name="dateToMM" id="dateToMM">
                                            <option value="01"><fmt:message key="Jan"/></option>
                                            <option value="02"><fmt:message key="Feb"/></option>
                                            <option value="03"><fmt:message key="Mar"/></option>
                                            <option value="04"><fmt:message key="Apr"/></option>
                                            <option value="05"><fmt:message key="May"/></option>
                                            <option value="06"><fmt:message key="Jun"/></option>
                                            <option value="07"><fmt:message key="Jul"/></option>
                                            <option value="08"><fmt:message key="Aug"/></option>
                                            <option value="09"><fmt:message key="Sep"/></option>
                                            <option value="10"><fmt:message key="Oct"/></option>
                                            <option value="11"><fmt:message key="Nov"/></option>
                                            <option value="12"><fmt:message key="Dec"/></option>
                                        </select>
                                        <select onclick="" name="dateToDD" id="dateToDD">
                                            <option value="01">01</option>
                                            <option value="02">02</option>
                                            <option value="03">03</option>
                                            <option value="04">04</option>
                                            <option value="05">05</option>
                                            <option value="06">06</option>
                                            <option value="07">07</option>
                                            <option value="08">08</option>
                                            <option value="09">09</option>
                                            <option value="10">10</option>
                                            <option value="11">11</option>
                                            <option value="12">12</option>
                                            <option value="13">13</option>
                                            <option value="14">14</option>
                                            <option value="15">15</option>
                                            <option value="16">16</option>
                                            <option value="17">17</option>
                                            <option value="18">18</option>
                                            <option value="19">19</option>
                                            <option value="20">20</option>
                                            <option value="21">21</option>
                                            <option value="22">22</option>
                                            <option value="23">23</option>
                                            <option value="24">24</option>
                                            <option value="25">25</option>
                                            <option value="26">26</option>
                                            <option value="27">27</option>
                                            <option value="28">28</option>
                                            <option value="29">29</option>
                                            <option value="30">30</option>
                                            <option value="31">31</option>
                                        </select>
                                        <select onclick="" name="dateToYYYY" id="dateToYYYY">
                                            <option value="2001">2001</option>
                                            <option value="2002">2002</option>
                                            <option value="2003">2003</option>
                                            <option value="2004">2004</option>
                                            <option value="2005">2005</option>
                                            <option value="2005">2006</option>
                                            <option value="2007">2007</option>
                                            <option value="2008">2008</option>
                                            <option value="2009">2009</option>
                                            <option value="2010">2010</option>
                                            <option value="2011">2011</option>
                                            <option value="2012">2012</option>
                                            <option value="2013">2013</option>
                                            <option value="2014">2014</option>
                                            <option value="2015">2015</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <p>&nbsp;</p>
                    </div>
                </c:if>
                <p>
                    <input type="submit" name="submit" class="btn btn-primary" id="submit" tabindex="5" onclick="return getPageSubmit()" value="${simpleSearch.searchButtonText}">
                </p>
            </c:when>
        </c:choose>
    </div>
    <%}%>
    <c:choose>
        <c:when test="${not empty resultlist}">
            <h2 class="header_text">${simpleSearch.statisticsText}</h2>
            <div class="span5" id="iresults">
                <div id="iresults">
                    <ul id="rso">
                        <c:forEach var="node" items="${resultlist}">
                            <li class="g">
                                <div class="rc">
                                    <c:choose>
                                        <c:when test="${contentType eq 'video' || contentType eq 'image'}">
                                            <a href="${node.path}" class="btn-link">${node.name}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="${node.path}.html" class="btn-link">${node.name}</a>
                                        </c:otherwise>
                                    </c:choose>
                                    <cite class="vurls">${node.path}</cite>
                                    <div class="st">${node.description}</div>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div></div>
                <c:if test="${currentIndex >= 1}">
                    <button onclick="getInfo('currentIndex',${currentIndex-1})"  >${simpleSearch.previousText}</button>
                </c:if>
                <c:if test="${numberOfPages > 1}">
                    <c:forEach begin="1" end="${numberOfPages}" varStatus="loop">
                        <c:choose>
                            <c:when test="${(loop.index-1) == currentIndex}">
                                <button class="current_page" disabled="true">${loop.index}</button>
                            </c:when>
                            <c:otherwise>
                                <button onclick="getInfo('currentIndex',${loop.index-1})">${loop.index}</button>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:if>
                <c:if test="${numberOfPages > (currentIndex+1)}">
                    <button onclick="getInfo('currentIndex',${currentIndex+1})" >${simpleSearch.nextText}</button>
                </c:if>
            </div>


            <div class="search_right span2">
                <c:if test="${not empty trends}">
                    <c:if test="${fn:length(trends.queries) > 0}">
                        <h3>${simpleSearch.searchTrendsText}</h3>
                        <div class="searchTrends">
                            <c:forEach var="query" items="${trends.queries}">
                                <button onclick="getInfo('keyword', '${query.query}')">${query.query}</button>
                            </c:forEach>
                        </div>
                    </c:if>
                </c:if>

                <c:if test="${result.facets.languages.containsHit}">
                    <h3><fmt:message key="Languages"/></h3>
                    <c:forEach var="bucket" items="${result.facets.languages.buckets}">
                        <c:set var="bucketValue" value="${bucket.value}"/>
                        <c:set var="label" value='<%= new java.util.Locale((String) pageContext.getAttribute("bucketValue")).getDisplayLanguage(request.getLocale()) %>'/>
                        <c:choose>
                            <c:when test="${param.language != null}">${label} (${bucket.count}) - <a href="<cq:requestURL><cq:removeParam name="start"/><cq:removeParam name="language"/></cq:requestURL>"><fmt:message key="remove filter"/></a></c:when>
                            <c:otherwise><a title="filter results" href="<cq:requestURL><cq:removeParam name="start"/><cq:addParam name="language" value="${bucket.value}"/></cq:requestURL>">${label} (${bucket.count})</a></c:otherwise>
                        </c:choose><br/>
                    </c:forEach>
                </c:if>

                <c:if test="${not empty tags}">
                    <h3><fmt:message key="Tags"/></h3>
                    <c:forEach var="tag" items="${tags}">
                        <c:set var="label" value="${tag.title}"/>
                        <c:choose>
                            <c:when test="${tagKeyword != null && tagKeyword eq tag.tagID}">${label} (${tag.count})<button onclick="getInfo('tagKeyword', '')"> - remove filter</button></c:when>
                            <c:otherwise><button onclick="getInfo('tagKeyword', '${tag.tagID}')">${label} (${tag.count})</button></c:otherwise>
                        </c:choose>
                        <br/>
                    </c:forEach>
                </c:if>
            </div>

        </c:when>
        <c:otherwise>
            <c:if test="${param.keyword ne null}">
                <h3>${simpleSearch.noResultsText}</h3>
            </c:if>
        </c:otherwise>
    </c:choose>

</form>

<script type="text/javascript">

    window.onload = function()
    {
    <%if(request.getAttribute("tagKeyword")!=null){%>
        document.getElementById("tagKeyword").value = '${tagKeyword}';
    <%}%>
    <%if(request.getAttribute("sectionTab")!=null){%>
        document.getElementById("sectionTab").value = '${sectionTab}';
    <%}%>
    <%if(request.getAttribute("keyword")!=null){%>
        document.getElementById("keyword").value = '${keyword}';
    <%}%>
    <%if(request.getAttribute("author")!=null){%>
        document.getElementById("author").value = '${author}';
    <%}%>
    <%if(request.getAttribute("contentType")!=null){%>
        document.getElementById("contentType").value = '${contentType}';
    <%}%>
    <%if(request.getAttribute("timeRange")!=null){%>
        radioAutoselected('${timeRange}');
    <%}%>
    <%if(request.getAttribute("dateFromMM")!=null){%>
        document.getElementById("dateFromMM").value = '${dateFromMM}';
    <%}%>
    <%if(request.getAttribute("dateFromDD")!=null){%>
        document.getElementById("dateFromDD").value = '${dateFromDD}';
    <%}%>
    <%if(request.getAttribute("dateFromYYYY")!=null){%>
        document.getElementById("dateFromYYYY").value = '${dateFromYYYY}';
    <%}%>
    <%if(request.getAttribute("dateToMM")!=null){%>
        document.getElementById("dateToMM").value = '${dateToMM}';
    <%}%>
    <%if(request.getAttribute("dateToDD")!=null){%>
        document.getElementById("dateToDD").value = '${dateToDD}';
    <%}%>
    <%if(request.getAttribute("dateToYYYY")!=null){%>
        document.getElementById("dateToYYYY").value = '${dateToYYYY}';
    <%}%>

    }

</script>