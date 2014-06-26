/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

function cancelPostFileUpload() {
    var formID = "#" + "formFileUploadDiv";
    var div = $CQ(formID).html();
    $CQ(formID).html($CQ(formID).html());
}

function setSocialUrl() {

    var composerDiv = document.getElementById('composerId');

    var urlSocial ;
	var path = composerDiv.getAttribute("path");
    var loginnsfx = composerDiv.getAttribute("loginnsfx");
	var fbCfgId = composerDiv.getAttribute("loginnsfx");
    var ctxPth = composerDiv.getAttribute("ctxPath");

    urlSocial = path+'.login.html'+loginnsfx+'?configid='+fbCfgId;

    if(ctxPth){
        urlSocial = ctxPth + urlSocial;
    }
    //console.log("url "+urlSocial);

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

function setCQproperties() {
	var composerDiv = document.getElementById('composerId3');

    var defaultMessage = composerDiv.getAttribute("defaultMessage");
    var requireLogin = composerDiv.getAttribute("requireLogin");
    var enterComment = composerDiv.getAttribute("enterComment");
    var commentActivated = composerDiv.getAttribute("commentActivated");
    var unableToActivate = composerDiv.getAttribute("unableToActivate");

	CQ_collab_comments_defaultMessage = defaultMessage;
    CQ_collab_comments_requireLogin = requireLogin;
    CQ_collab_comments_enterComment = enterComment;
    CQ_collab_comments_commentActivated = commentActivated;
    CQ_collab_comments_unableToActivate = unableToActivate;
}

$CQ(function () {
    var composerDiv = document.getElementById('composerId4');

	var formId = composerDiv.getAttribute("formId");
    var collabUsePropName = composerDiv.getAttribute("collabUsePropName");

    CQ.soco.commons.fillInputFromClientContext($CQ(formId+" #XSS-userIdentifier"), collabUsePropName);
});