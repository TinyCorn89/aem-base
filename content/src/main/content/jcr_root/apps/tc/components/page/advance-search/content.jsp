<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%>
<%@page session="false"%>
<cq:includeClientLib categories="tc.custom.widgets"/>
<div class="container marketing">
    <div class="row">
        <div class="col-lg-8">
    		<cq:include path="advancesearchpar" resourceType="tc/components/content/search" />
        </div>
        <div class="col-lg-4">
    		<cq:include path="right-rail" resourceType="foundation/components/parsys" />
        </div>
    </div>
</div>
