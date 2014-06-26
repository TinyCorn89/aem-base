<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%>
<%@page session="false"%>

<cq:include path="content-region" resourceType="foundation/components/parsys" />

<cq:includeClientLib categories="tc.components.article"/>

 <div class="container marketing">

  <!-- Three columns of text below the carousel -->
  <div class="row">
    <cq:include path="article1" resourceType="/apps/tc/components/content/article" />
    
    <cq:include path="article2" resourceType="/apps/tc/components/content/article" />
    
    <cq:include path="article3" resourceType="/apps/tc/components/content/article" />

   </div><!-- /.row -->

 </div><!-- /.container -->


