/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

$CQ(document).ready(function () {

	var socialLoginDiv = document.getElementById('socialLoginId');

    var redirectTo = socialLoginDiv.getAttribute("redirectTo");
    var divID = socialLoginDiv.getAttribute("divID");

    //listening for this global event - triggered from SocialAuth.js - $CQ.SocialAuth.oauthCallbackComplete
    //any element can subscribe to this event to perform custom functionality post-oauth-callback completion
    //the social login component will listen for it here to perform the appropriate redirect as configured

    $CQ('.dropdown').bind('oauthCallbackComplete', function (ev, userId) {
        //oauthCallbackComplete happened
        CQ_Analytics.ProfileDataMgr.loadProfile(userId);
        if (redirectTo != null && redirectTo.length() > 0) {
            CQ.shared.Util.reload(null, redirectTo);
        } else {
            CQ.shared.Util.reload();
        }
    });

    $CQ(".sociallogin-signin-"+divID).on('click', function (ev) {
        ev.preventDefault();
		var config = socialLoginDiv.getAttribute("config");
        $CQ.SocialAuth.sociallogin.showDialog(divID, config);
    });

});


function logout() {

    var socialLoginDiv2 = document.getElementById('socialLoginId2');

    var isDisabled = socialLoginDiv2.getAttribute("isDisabled");
	var isDisabledStatus;

    if(isDisabled == "true") {
        isDisabledStatus = true;
    } else {
        isDisabledStatus = false;
    }

    var commerceCookieName = socialLoginDiv2.getAttribute("commerceCookieName");
	var loadURL = socialLoginDiv2.getAttribute("loadURL");

    if (_g && _g.shared && _g.shared.ClientSidePersistence) {
        _g.shared.ClientSidePersistence.clearAllMaps();
    }

    if (!isDisabledStatus) {
        if (CQ_Analytics && CQ_Analytics.CCM) {
            CQ_Analytics.ProfileDataMgr.loadProfile("anonymous");
            CQ.shared.Util.reload();
        }
    } else {
        if (CQ_Analytics && CQ_Analytics.CCM) {
            CQ_Analytics.ProfileDataMgr.clear();
            CQ_Analytics.CCM.reset();
        }
        CQ.shared.HTTP.clearCookie(commerceCookieName, "/");
        CQ.shared.Util.load(loadURL+".html");
    }
}