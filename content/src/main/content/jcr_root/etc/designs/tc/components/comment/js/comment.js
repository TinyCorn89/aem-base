/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

var commentDiv = document.getElementById('commentId');

var commentPath = commentDiv.getAttribute("commentPath");
var csResourcePath = commentDiv.getAttribute("csResourcePath");

CQ.WCM.onEditableReady(commentPath,
    function (editable) {
        editable.refreshCommentSystem = function () {
            /* todo: does't work as expected yet
                        var cs = CQ.WCM.getEditable(csResourcePath);
                        if (cs) {
                            console.log("cs", cs.path);
                            cs.refresh();
                        } else {
                        */
            CQ.wcm.EditBase.refreshPage();
            /* } */
        };
    }
);