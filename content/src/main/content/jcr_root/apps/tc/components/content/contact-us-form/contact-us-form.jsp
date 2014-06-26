<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%>
<cq:setContentBundle source="page"/>
<!-- Contact us Form start -->
<div class="col-sm-8">
    <form method="get" action="/content/launchpad/en_US/contact-us.html">
        <div class="row form-group">
            <div class="col-xs-10">
                <input type="text" class="form-control" id="firstName"
                       name="firstName" placeholder="First Name" required="">
            </div>
        </div>
        <div class="row form-group">
            <div class="col-xs-10">
                <input type="text" class="form-control" id="middleName"
                       name="firstName" placeholder="Middle Name" required="">
            </div>
        </div>
        <div class="row form-group">
            <div class="col-xs-10">
                <input type="text" class="form-control" id="lastName"
                       name="lastName" placeholder="Last Name" required="">
            </div>
        </div>
        <div class="row form-group">
            <div class="col-xs-10">
                <input type="email" class="form-control" name="email"
                       placeholder="Email" required="">
            </div>
        </div>
        <div class="row form-group">
            <div class="col-xs-10">
                <input type="tel" class="form-control" name="phone"
                       placeholder="Phone" required="">
            </div>
        </div>
        <div class="row form-group">
            <div class="col-xs-10">
                <input type="homepage" class="form-control"
                       placeholder="Website URL" required="">
            </div>
        </div>
        <div class="row form-group">
            <div class="col-xs-10">
                <button class="btn btn-default pull-right"><fmt:message key="Contact Us"/></button>
            </div>
        </div>
    </form>
</div>
<!-- Contact us Form end -->