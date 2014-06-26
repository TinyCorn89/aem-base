<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>
<%@include file="/apps/tc/global/global.jsp"%><%
%><%@page session="false"%><%
%>
<%@page contentType="text/html; charset=utf-8"
        import="com.day.cq.i18n.I18n,com.day.cq.wcm.webservicesupport.ConfigurationManager,
        com.day.cq.wcm.webservicesupport.Configuration,
        org.apache.sling.api.resource.Resource"
%>
<body>
<%
  	//ResourceResolver resourceResolver = slingRequest.getResourceResolver();

	//ValueMap campaignProperties = campaignResource.adaptTo(ValueMap.class);
	ConfigurationManager cfgMgr = sling.getService(ConfigurationManager.class);
    Configuration facebookConfiguration = null;
    Configuration twitterConfiguration = null;
    String[] services = pageProperties.getInherited("cq:cloudserviceconfigs", new String[]{});
    String clientId = "236468446517030";
    if (cfgMgr != null) {
        facebookConfiguration = cfgMgr.getConfiguration("facebookconnect", services);
        twitterConfiguration = cfgMgr.getConfiguration("twitterconnect", services);
        if (facebookConfiguration != null){
            Resource configResource = facebookConfiguration.getResource();
            Page configPage = configResource.adaptTo(Page.class);
            Node configPageNode = configResource.adaptTo(Node.class);
			Item configChildItem = configPageNode.getPrimaryItem();
            ValueMap configNodeProp = resourceResolver.getResource(configChildItem.getPath()).adaptTo(ValueMap.class);
            clientId = configNodeProp.get("oauth.client.id",clientId);
			System.out.println("fb client id "+clientId);
        }
        
    }
%>

<div id="fb-root"></div>
<script>
window.fbAsyncInit = function() {
FB.init({appId: '<%= clientId%>', status: true, cookie: true,
xfbml: true});
};
(function() {
var e = document.createElement('script'); e.async = true;
e.src = 'http://connect.facebook.net/en_US/all.js';
document.getElementById('fb-root').appendChild(e);
}());
</script>
<script type="text/javascript">
function postToFacebook(commentText){
	FB.login(function(response) {
   if (response.authResponse) {
     console.log('Welcome!  Fetching your information.... ');
     FB.api('/me', function(response) {
       console.log('Good to see you, ' + response.name + '.');
     });
   } else {
     console.log('User cancelled login or did not fully authorize.');
   }
 });
FB.api('/me', function(response) {
  console.log(response.name);
});
    var body = commentText;
    var link = window.location.origin + window.location.pathname;    
    console.log('path '+link);
    link = "https://www.google.com/finance";
    FB.api('/me/feed', 'post', { link : link, message : body }, function(response) {
  if (!response || response.error) {
    console.log('Error occured' +response.error);
  } else {
    console.log('Post ID: ' + response.id);
  }
});
}
$(document).ready(function(){
$('#share_button').click(function(e){
e.preventDefault();
	postToFacebook("reading js sdk");
    
});
});
</script>
    <cq:include path="clientcontext" resourceType="cq/personalization/components/clientcontext"/>
    <cq:include script="header.jsp"/>
    <cq:include script="content.jsp"/>
    <cq:include script="footer.jsp"/>
    <cq:include script="stats.jsp"/>
    
    <cq:includeClientLib js="tc.all"/>
</body>