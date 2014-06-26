<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%><%
%><%@page session="false"%><%
%>
<cq:setContentBundle source="page"/>
<cq:includeClientLib categories="tc.components.navigation"/>

<!-- Brand and Search -->
<cq:include path="search-par" resourceType="/apps/tc/components/content/simple-search" />

  <nav class="navbar navbar-inverse" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only"><fmt:message key="toggleNav"/></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>

    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

        <cq:include path="top-nav" resourceType="foundation/components/iparsys" /> 
        <ul class="nav navbar-nav navbar-right">
            <!-- <li><a href="#">Sign Up</a></li> -->
            <li><cq:include path="login" resourceType="/apps/tc/components/content/social-login" /></li>
        </ul>
      </div><!-- /.navbar-collapse -->
   </div><!-- /.container-fluid -->
</nav>
<cq:include path="breadcrumb" resourceType="/apps/tc/components/content/breadcrumb" />