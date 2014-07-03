CQ.mcm.utils.Newsletter.thindatasend = function(dialog, box, successCb, failureCb) {


    var formUrl = dialog.formUrl;
    console.log(dialog.formUrl);
    var mailList = dialog.items.get(0).items.get(0).getValue();
    var prefixUrl = dialog.items.get(0).items.get(1).getValue();
    dialog.items.get(0).items.get(3).setValue(curPageUrl);
    //curPageUrl = curPageUrl.replace(".emailclient.html", ".jcr_content.html");
    console.log(mailList + "---", prefixUrl + "---" + curPageUrl);

    //formUrl = formUrl + "?action=broadcast&mailListName=" + mailList  + "&url=" + curPageUrl
    console.log(formUrl)

    var dateStr = "";
    var dateItem = dialog.items.get(0).items.get(4);
    console.log(dateItem);
    if (dateItem != null &&  dateItem.get(0).items != null && dateItem.get(0).items.length > 4) {
		console.log("inside if");
		var d = dateItem.get(0).getValue();
        console.log(d);
    	dateStr = d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate()  + " " +  d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds();
    	console.log(dateStr);
    }
    var tpl = CQ.mcm.utils.Newsletter.FLIGHT_TEST_RESULT_TMPL;
    var heading = CQ.I18n.getMessage("Result");

	var formData = {
        mailListName:mailList,
        url:curPageUrl,
        action:"broadcast",
        siteUrl:prefixUrl,
        date:dateStr
    };

	$.ajax({
        url : formUrl,
        type: "POST",
        data : formData,
        success: function(data, textStatus, jqXHR)
        {
            alert("Newsletter sent successfully...");
        },
        error: function (jqXHR, textStatus, errorThrown)
        {
            alert("Error sending newsletter: " + textStatus);
     
        }
	});


};