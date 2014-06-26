(function() {        
    var imgDiv = document.getElementById("imgDiv");
    var divId = imgDiv.getAttribute("divId");
    var imageLink = imgDiv.getAttribute("imageLink");
    var imageAsset = imgDiv.getAttribute("imageAsset");
    var imageTitle = imgDiv.getAttribute("imageTitle");
    var componentPath = imgDiv.getAttribute("componentPath");

    var imageDiv = document.getElementById(divId);

    var imageEvars = '{ imageLink: "'+ imageLink +'", imageAsset: "'+ imageAsset + '", imageTitle: "'+ imageTitle +'" }';

    var tagNodes = imageDiv.getElementsByTagName('A');
    for (var i = 0; i < tagNodes.length; i++) {
        var link = tagNodes.item(i); 
        link.setAttribute('onclick', 'CQ_Analytics.record({event: "imageClick", values: ' + imageEvars + ', collect: false, options: { obj: this }, componentPath:'+ componentPath+'})');        
    }

    /*
        // Leave this out, because only the last image processed is taken into account.
        tagNodes = imageDiv.getElementsByTagName('IMG');
    for (var i = 0; i < tagNodes.length; i++) {
        var img = tagNodes.item(i);
        img.setAttribute("data-tracking", "{event:'imageView', values:" + imageEvars + ", componentPath:'+componentPath+'}");
    }
    */
})();