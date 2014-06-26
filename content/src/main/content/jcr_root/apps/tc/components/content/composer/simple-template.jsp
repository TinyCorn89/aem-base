<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

  Comments component sub-script

  Draws a form for new comments.

--%><%@ page session="false" import="com.adobe.cq.social.commons.CollabUser,
                     com.adobe.cq.social.commons.Comment,
                     com.adobe.cq.social.commons.CommentSystem,
                     com.day.cq.i18n.I18n,
                     com.day.cq.wcm.api.WCMMode,
                     com.day.cq.wcm.foundation.forms.FormsHelper,
                     java.util.List,
                     java.util.Locale,
                     java.util.ResourceBundle,
				     com.day.cq.personalization.UserPropertiesUtil,
                     org.apache.sling.api.resource.ResourceUtil" %><%
%><%@taglib uri="http://www.day.com/taglibs/cq/personalization/1.0" prefix="personalization" %><%
%><%@include file="/libs/social/commons/commons.jsp"%>
<!-- <cq:includeClientLib categories="tc.components.composer" /> -->
<cq:setContentBundle source="page"/>
<%

    final String targetResourceType = resourceResolver.resolve(resource.getPath()).getResourceType();
    CommentSystem cs = resource.adaptTo(CommentSystem.class);
    // @Todo - find out how to do this without a request attribute.
    //boolean isRenderByForm  = request.getAttribute("isRenderByForm")==null ? false : ((Boolean)request.getAttribute("isRenderByForm")).booleanValue();
    final Boolean allowFileUploads = cs.getProperty("allowFileUploads", Boolean.class) != null ?
       cs.getProperty("allowFileUploads", Boolean.class) :false;
	String divID = (String)request.getAttribute("divID");
	String fbPath = (String)request.getAttribute("fbPagePath");
	String fbCfgId = (String)request.getAttribute("fbConfigId");
	String loginSfx = (String)request.getAttribute("loginSuffix");
	String ctxPath = (String)request.getAttribute("contextPath");


    Resource commentResource = resource;
    if (cs == null) {
        // form called directly
        final List<Resource> editResources = FormsHelper.getFormEditResources(slingRequest);
        if (null != editResources && editResources.size() > 0) {
            cs = editResources.get(0).adaptTo(CommentSystem.class);
            commentResource = (null != cs) ? cs.getResource() : commentResource;
        } else {
            cs = resource.adaptTo(CommentSystem.class);
        }
    }
    if (cs == null) {
        log.error("failed to retrieve comment system for " + resource.getPath());
        return;
    }

    String defaultMessage = cs.getProperty("defaultMessage", i18n.get("Type your comment here."));
    String id = cs.getId();

    if (request.getParameter("idPrefix") != null) {
        id = xssAPI.getValidJSToken(request.getParameter("idPrefix"), "XSS");
    }

// In nested replies, when there are muliple composer from the same comment system, the taglib:
// contextProfileHtmlInput fails to select the right thing because it only takes in an ID so,
// the page must give us how many composers are already on the page to make the ID unique.
    String composerCount = request.getParameter("composerCount");
    if (composerCount != null) {
        id += "_" + composerCount;
    }

//    String loginId = id + "-login";
    String formId = id + "-form";
    String textId = id +  "-text";
    String nameId = id + "-" + CollabUser.PROP_NAME;
    String mailId = id +"-" + CollabUser.PROP_EMAIL;
    String webId = id + "-" + CollabUser.PROP_WEBSITE;
    String formAction = commentResource.getPath() + ".social.createcomment.json";

/*     if(isRenderByForm){
        formAction = (String) request.getAttribute("commentsRoot") + ".createcomment.html";
    } */

    //ValueMap props = commentResource.adaptTo(ValueMap.class);
    //boolean requireLogin = props.get("requireLogin", Boolean.FALSE);
    boolean requireLogin = false;

//    boolean isDisabled = WCMMode.fromRequest(request).equals(WCMMode.DISABLED);
    String authorizableId = loggedInUserID;
    boolean anonymous = isAnonymous;

    String commentBlockClass = "comment-block-author";
    if(anonymous){
        commentBlockClass = "comment-block-publish";
    }
    if (allowFileUploads) {
        %><script type="text/javascript">
			function cancelPostFileUpload() {
            	var formID = "#"+ "formFileUploadDiv";
              	var div = $CQ(formID).html();
              	$CQ(formID).html($CQ(formID).html());
          	}
//cancelPostFileUpload();
          </script><%
    }%>

<div id="composerId" path="<%=fbPath%>" loginnsfx="<%=loginSfx%>" fbCfgId="<%=fbCfgId%>" ctxPath="<%=ctxPath%>" style="{display:none}"/>

<script type="text/javascript">
    var urlSocial ;
    var path = "<%= fbPath%>"; 
    var lgnsfx =  "<%= loginSfx%>";
    var fbCfgId =  "<%= fbCfgId%>";
    var ctxPth = "<%= ctxPath%>";
    urlSocial = path+'.login.html'+lgnsfx+'?configid='+fbCfgId;
    if(ctxPth){
        urlSocial = ctxPth + urlSocial;
    }
    console.log("url "+urlSocial);
    //setSocialUrl();	
    function validateSubmitWrapper<%= formId.replaceAll("[^a-zA-Z0-9]","") %>(id) {
        var validationResult = CQ_collab_comments_validateSubmit(id);
        if (validationResult && CQ_Analytics.Sitecatalyst){
            CQ_Analytics.record({event: 'postComment',
                                values: {commenterName: document.getElementById("<%= nameId %>").value},
                                componentPath: '<%=resource.getResourceType()%>'
                              });
        }
        return <% if (WCMMode.fromRequest(request).equals(WCMMode.DISABLED)) { %>validationResult<% } else { %>(CQ_Analytics.ClickstreamcloudUI.isVisible() ? false : validationResult)<% } %>;
    }
    function recordPostCommentEvent(name, user, component, category) {
        CQ_Analytics.record({
            event: 'postComment',
            values: {
                commenterName: user,
                topic: name,
                category: category
            },
            componentPath: component
        });
    }
    //recordPostCommentEvent();
</script>
<form
    class="comment"
    action="<%= formAction %>"
    onsubmit="return CQ.soco.comments.validateCommentForm(this)"
    method="POST"
    name="comment"
    enctype="multipart/form-data"
    id="<%= formId %>">
    <div id="composerId3" defaultMessage='<%= defaultMessage.replaceAll("\\\"", "\\\\\"") %>' requireLogin="<%=requireLogin%>" enterComment='<%= i18n.get("Please enter a comment") %>' commentActivated='<%=i18n.get("Comment activated")%>' unableToActivate='<%=i18n.get("Unable to activate comment")%>' style="{display:none}"/>

    <script type="text/javascript" charset="utf-8">
        CQ_collab_comments_defaultMessage = "<%= defaultMessage.replaceAll("\\\"", "\\\\\"") %>";
        CQ_collab_comments_requireLogin = <%= requireLogin %>;
        CQ_collab_comments_enterComment = "<%= i18n.get("Please enter a comment") %>";
        CQ_collab_comments_commentActivated = "<%= i18n.get("Comment activated") %>";
        CQ_collab_comments_unableToActivate = "<%= i18n.get("Unable to activate comment") %>";
        //setCQproperties();        
    </script>
    <div class="comment-error" id="<%= id %>-error"></div>
        <%
        	if(anonymous){
		%>
        <div style="font: bold 12px arial;">
            <fmt:message key="Login to comment"/> &nbsp;&nbsp;
            <img src="/etc/designs/tc/components/comments-clientlibs/images/fb.jpg"
            onclick='window.open(urlSocial,"_BLANK","width=1024,height=630,status=0,menubar=0,toolbar=0,scrollbars=1");'/>

        </div>
        <%
            }
		%>
    <label for="<%= textId %>" class="comment-text-label"><!--<%= i18n.get("Comment") %>--></label>
    <textarea
            name="<%= Comment.PROP_MESSAGE %>"
            id="<%= textId %>"
            class="comment-text"
            onfocus="CQ.soco.commons.handleOnFocus(this, '<%= defaultMessage%>');"
            onblur="CQ.soco.commons.handleOnBlur(this, '<%= defaultMessage%>');"
            rows="10"
            cols="10"><%= defaultMessage %></textarea>
    <div id="formFileUploadDiv" >
        <% if (allowFileUploads) {
             String fileFilter = cs.getProperty(CommentSystem.PROP_FILE_UPLOAD_TYPES, String.class);
             fileFilter = StringUtils.isNotBlank(fileFilter) ?
       "accept='" + fileFilter + "'" :"";
        %>
            <input type="hidden" name="_charset_" value="<%= response.getCharacterEncoding() %>"/>
            <input class="submit" type="file" name="file" <%=fileFilter %> value="<%= i18n.get("Upload", "Upload a file") %>"/>
        <%}%>
    </div>

    <div class="comment-info">
        <div>
            <div class="<%= commentBlockClass %>" id="<%= nameId %>-comment-block" >
                <span class="comment-error" id="<%= nameId %>-displayName-error"></span>
                <label for="<%= nameId %>" class="comment-text-label"><%= i18n.get("Name", "Label for commenter's name") %></label>
                <personalization:contextProfileHtmlInput id="<%= nameId %>" clazz="comment-text" type="text" name="<%= CollabUser.PROP_NAME %>" propertyName="authorizableId"/>
            </div>
            <div class="<%= commentBlockClass %>" id="<%= mailId %>-comment-block" >
                <span class="comment-error" id="<%= mailId %>-email-error"></span>
                <label for="<%= mailId %>" class="comment-text-label"><%= i18n.get("Email", "Label for commenter's email") %></label>
                <personalization:contextProfileHtmlInput id="<%= mailId %>" clazz="comment-text" type="text" name="<%= CollabUser.PROP_EMAIL %>" propertyName="email"/>
            </div>
            <div class="<%= commentBlockClass %>" id="<%= webId %>-comment-block" >
                <span class="comment-error" id="<%= webId %>-url-error"></span>
                <label for="<%= webId %>" class="comment-text-label"><%= i18n.get("Website (optional)", "Label for commenter's website") %></label>
                <personalization:contextProfileHtmlInput id="<%= webId %>" clazz="comment-text" type="text" name="<%= CollabUser.PROP_WEBSITE %>" propertyName="url"/>
            </div>
            <div class="submit-block" style="width:300%">
                <input type="hidden" name="_charset_" value="<%= response.getCharacterEncoding() %>"/>
                <input class="submit" type="submit" name="submit" id="<%= id %>-submit" value="<%= i18n.get("Post Comment", "Form submit action button") %>"
                onclick="recordPostCommentEvent('<%= commentResource.getPath() %>','<%= authorizableId %>','tc/components/content/composer' , 'forum')"/>
				<%
        			if(!anonymous){

            	%>
                <input type="checkbox" value="true" id="fbCbox" style="margin-left:40%"/>
                <img src="/etc/designs/tc/components/comments-clientlibs/images/fbShare.jpg" style="height:27px;margin-top: 8px;"/>
                <%
        }%>
            </div>
        </div>
    </div>
</form>

<script type="text/javascript">
    $CQ(function(){
        CQ.soco.commons.fillInputFromClientContext($CQ("<%=formId%> #XSS-userIdentifier"), "<%= CollabUser.PROP_NAME %>");
    });
</script>

<div id="composerId4" formId="<%=formId%>" collabUsePropName="<%=CollabUser.PROP_NAME%>" style="{display:none}"/>
