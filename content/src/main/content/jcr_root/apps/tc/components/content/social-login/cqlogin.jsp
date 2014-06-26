<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@ page session="false" import="com.day.cq.i18n.I18n,
                 com.day.cq.wcm.api.WCMMode,
                 com.day.text.Text,
                 com.day.cq.wcm.foundation.forms.FormsHelper" %>

<%@include file="/libs/foundation/global.jsp"%>

<cq:includeClientLib categories="cq.social.connect"/>

<%
    final String id = Text.getName(resource.getPath());
    I18n i18n = new I18n(slingRequest);
    final String action = currentPage.getPath() + "/j_security_check";
    final String validationFunctionName = "cq5forms_validate_" + id;
    String defaultRedirect = currentPage.getPath();
    if(!defaultRedirect.endsWith(".html")) {
        defaultRedirect += ".html";
    }
    boolean isDisabled = WCMMode.fromRequest(request).equals(WCMMode.DISABLED);
%>

<script type="text/javascript">
    function <%=validationFunctionName%>() {
        if (CQ_Analytics) {
            var u = document.forms['<%=id%>']['j_username'].value;
            if (CQ_Analytics.Sitecatalyst) {
                CQ_Analytics.record({ event: "loginAttempt", values: {
                    username:u,
                    loginPage:"${currentPage.path}.html",
                    destinationPage:"<%= xssAPI.encodeForJSString(defaultRedirect) %>"
                }, componentPath: '<%=resource.getResourceType()%>'});
                if (CQ_Analytics.ClickstreamcloudUI && CQ_Analytics.ClickstreamcloudUI.isVisible()) {
                    return false;
                }
            }
        <% if ( !isDisabled ) {
            final String contextPath = slingRequest.getContextPath();
            final String authorRedirect = contextPath + defaultRedirect; %>
            if (CQ_Analytics.ProfileDataMgr) {
                if (u) {
                    /*
                     * AdobePatentID="B1393"
                     */
                    var loaded = CQ_Analytics.ProfileDataMgr.loadProfile(u);
                    if (loaded) {
                        var url = CQ.shared.HTTP.noCaching("<%= xssAPI.encodeForJSString(authorRedirect) %>");
                        CQ.shared.Util.load(url);
                    } else {
                         alert("<%=i18n.get("The user could not be found.")%>");
                    }
                    return false;
                }
            }
            return true;
        <% } else { %>
            if (CQ_Analytics.ProfileDataMgr) {
                CQ_Analytics.ProfileDataMgr.clear();
            }
            return true;
        <% } %>
        }
    }
</script>

<%
    final String jReason = request.getParameter("j_reason");
    if (null != jReason) {
%><div class="loginerror"><%=xssAPI.encodeForHTML(i18n.getVar(jReason))%></div>
<%
    }
%>
		 <li>
		   <div class="row">
			  <div class="col-md-12">
			 <form  method="POST"
					action="<%= xssAPI.getValidHref(action) %>"
					id="<%= xssAPI.encodeForHTMLAttr(id) %>"
					name="<%= xssAPI.encodeForHTMLAttr(id) %>"
					enctype="multipart/form-data"
					class="form"
					role="form"
					onsubmit="return <%=validationFunctionName%>();">
					
					<input type="hidden" name="resource" value="<%= xssAPI.encodeForHTMLAttr(defaultRedirect) %>">
					<input type="hidden" name="_charset_" value="UTF-8"/>
					
					<div class="form-group">
					   <label class="sr-only" for="<%= xssAPI.encodeForHTMLAttr(id + "_username")%>"><%= xssAPI.encodeForHTML(i18n.get("Username")) %></label>
					   <input id="<%= xssAPI.encodeForHTMLAttr(id + "_username")%>" tabindex="9990" class="form-control" placeholder="<%= xssAPI.encodeForHTML(i18n.get("Username")) %>"  autocomplete="off" name="j_username"/>
					</div>
					<div class="form-group">
					   <label class="sr-only" for="<%= xssAPI.encodeForHTMLAttr(id + "_password")%>"><%= xssAPI.encodeForHTML(i18n.get("Password")) %></label>
					   <input id="<%= xssAPI.encodeForHTMLAttr(id + "_password")%>" tabindex="9991" class="form-control" placeholder="<%= xssAPI.encodeForHTML(i18n.get("Password")) %>" type="password" autocomplete="off"
                       name="j_password"/>
					</div>
					<div class="form-group">
					   <button class="btn btn-success btn-block" tabindex="9992" onclick="$CQ('#<%= xssAPI.encodeForHTMLAttr(id) %>').submit()"> <%=i18n.get("Sign in")%>
					</div>
				 </form>
			  </div>
		   </div>
		</li>
