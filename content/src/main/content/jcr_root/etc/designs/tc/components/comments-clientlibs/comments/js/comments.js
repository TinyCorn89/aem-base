/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

$CQ(function(){    
    var refreshCommentCount = function (target, count) {
        if (count === 1) {
            target.text(CQ.I18n.getMessage("{0} comment", count));
        } else if (count === 0) {
            target.text(CQ.I18n.getMessage("No comments yet", count));
        } else {
            target.text(CQ.I18n.getMessage("{0} comments", count));
        }
    };

	// Getting JSP variable values

    var commentsId = document.getElementById('commentsId');

	var commentCount = commentsId.getAttribute("countComments");
	var isRTEenabled = commentsId.getAttribute("isRTEenabled");
	var csId = commentsId.getAttribute("csId");
	var loggedInUserID = commentsId.getAttribute("loggedInUserID");
    var componentPath = commentsId.getAttribute("componentPath");

    if( isRTEenabled) {
    	CQ.soco.commons.activateRTE($CQ("#"+csId).find("form").first());
    }
    CQ.soco.comments.attachToComposer($CQ("#"+csId).find("form").first(), $CQ("#"+csId), "comment");
    $CQ("#"+csId).bind(CQ.soco.comments.events.ADDED, function(event){
            CQ_Analytics.record({event: 'postComment',
                    values: {commenterName: loggedInUserID,
                            componentPath: componentPath
                            }
            });
            commentCount += 1;
            refreshCommentCount($CQ("#"+csId+"-count"), commentCount);
    });
    $CQ("#"+csId).bind(CQ.soco.comments.events.DELETE, function(event) {
        $CQ.post($CQ(event.target).closest("form").attr("action"), function(data, textStatus, jqXHR) {
            var parentComment = $CQ(event.target).closest(".comment").parent();
            var numReplies = +(parentComment.data("numreplies") || 0);
            parentComment.data("numreplies", (numReplies - 1));
            commentCount -= 1;
            refreshCommentCount($CQ("#"+csId+"-count"), commentCount);
            $CQ(event.target).closest(".comment").remove();
        });
       });

    CQ.soco.comments.bindOnAdded($CQ("#"+csId));
    $CQ(".comment").bind(CQ.soco.comments.events.DELETE, CQ.soco.comments.removeHandler);
    $CQ(".comment").bind(CQ.soco.comments.events.ADDED, CQ.soco.comments.addHandler);
});
