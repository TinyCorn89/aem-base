/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

(function (e) { e.fn.FeedEk = function (t) { 

var n = { 
FeedUrl: "", MaxCount: 1000000, ShowDesc: true, ShowPubDate: true, CharacterLimit: 0, TitleLinkTarget: "_blank" 
}; 
if (t) { e.extend(n, t) } var r = e(this).attr("id"); 

    var i; e("#" + r).empty().append('<div style="padding:3px;"><img src="loader.gif" /></div>');

    e.ajax({ url: "http://ajax.googleapis.com/ajax/services/feed/load?v=1.0&num=" + n.MaxCount + "&output=json&q=" + encodeURIComponent(n.FeedUrl) + "&hl=en&callback=?", dataType: "json",
		success: function (t) { 
            			e("#" + r).empty(); var s = "";
            			e.each(t.responseData.feed.entries,function (e, t) { s += '<li><div class="itemTitle"><a href="' + t.link + '" target="' + n.TitleLinkTarget + '" >' + t.title + "</a></div>"; 
if (n.ShowPubDate) {
i = new Date(t.publishedDate);
 s += '<div class="itemDate">' + i.toLocaleDateString() +  "</div>" 
 }else{
	i = new Date(t.publishedDate); 
    var d = new Date();



	var minutes = (d.getTime() - i.getTime())/(1000*60);
    var a = "test";                                                                                                                                                       

    var timeDuration = Math.round(minutes);
    var final =   timeDuration +" minutes ago";                                                                                                                                                      
    if((timeDuration/60) > 1)
    {
       var min = timeDuration%60;
       timeDuration = Math.round((timeDuration/60));
       final =  timeDuration +" hrs ago";

			 if((min > 0) && ((timeDuration/24)<1))
             {
				final =  timeDuration +" hrs "+ min +" minutes ago";
             }    
             if((timeDuration/24) > 1)
             {
                    var hr = timeDuration%24;
					timeDuration = Math.round((timeDuration/24));
       				final =  timeDuration +" days ago";
                 if(hr>0)
                 {
                    final =  timeDuration +" days "+ hr +" hrs ago";
                 }
             }
    }

 s += '<div class="itemDate">'+   "   " +final+ "</div>" 
} 
 if (n.ShowDesc) { 
     var str = t.content;
     var temp = str.split(" ");
    if (n.DescCharacterLimit > 0 && temp.length > n.DescCharacterLimit) {
	var desc = "";
    var limit = n.DescCharacterLimit;
	for(var index=0; index<limit; index++){
		desc = desc.concat(temp[index]).concat(" ");
	}
	 s += '<div class="itemContent">' + desc + "</div>" 
	 } else { s += '<div class="itemContent">' + t.content + "</div>" } 
 } }); 

 e("#" + r).append('<ul class="feedEkList">' + s + "</ul>") } }) 
 } })(jQuery)





