<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

  ==============================================================================

  Generic 404 error handler

  Important note:  
  Since Sling uses the user from the request (depending on the authentication
  handler but typically HTTP basic auth) to login to the repository and JCR/CRX
  will simply say "resource not found" if the user does not have a right to
  access a certain node, everything ends up in this 404 handler, both access
  denied ("401", eg. for non-logged in, anonymous users) and really-not-existing
  scenarios ("404", eg. logged in, but does not exist in repository).
  
--%>
<%@ page session="false" %>
<%@ page import="
    java.net.URLEncoder,
    org.apache.sling.api.scripting.SlingBindings,
    org.apache.sling.engine.auth.Authenticator,
    org.apache.sling.engine.auth.NoAuthenticationHandlerException,
    com.day.cq.wcm.api.WCMMode" %><%!

    private boolean isAnonymousUser(HttpServletRequest request) {
        return request.getAuthType() == null
            || request.getRemoteUser() == null;
    }

    private boolean isBrowserRequest(HttpServletRequest request) {
        // check if user agent contains "Mozilla" or "Opera"
        final String userAgent = request.getHeader("User-Agent");
        return userAgent != null
            && (userAgent.indexOf("Mozilla") > -1
                || userAgent.indexOf("Opera") > -1);
    }
    
%><%

    // decide whether to redirect to the (wcm) login page, or to send a plain 404
    if (isAnonymousUser(request)
            && isBrowserRequest(request)) {
        
        SlingBindings bindings = (SlingBindings) request.getAttribute("org.apache.sling.api.scripting.SlingBindings");
        Authenticator auth = bindings.getSling().getService(Authenticator.class);
        if (auth != null) {
            try {
                auth.login(request, response);
                
                // login has been requested, nothing more to do
                return;
            } catch (NoAuthenticationHandlerException nahe) {
                bindings.getLog().warn("Cannot login: No Authentication Handler is willing to authenticate");
            }
        } else {
            bindings.getLog().warn("Cannot login: Missing Authenticator service");
        }
        
    }

    // get here if authentication should not take place or if
    // no Authenticator service is available or if no
    // AuthenticationHandler is willing to authenticate
    // So we fall back to plain old 404/NOT FOUND    

String redirectURL = "/content/tc/us/en/404.html";
    response.sendRedirect(redirectURL);
%>