<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%><%
%><%@page session="false"%><%
%>
<div class="motopress-wrapper content-holder clearfix">
    <div class="container-fluid">
        <div class="row">
            <div class="span12">
                <div class="row">
                    <div class="span8 right" id="content">
                        <cq:include path="leftpar" resourceType="foundation/components/parsys" />
                    </div>
                    <div class="span4 sidebar" id="sidebar">                        
                        <cq:include path="rightpar" resourceType="foundation/components/parsys" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>