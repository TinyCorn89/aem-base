$(document).ready(function(){
  
    var mediaName = videoDiv.getAttribute("mediaName");
	var mediaFile = videoDiv.getAttribute("mediaFile");
	var mediaPath = videoDiv.getAttribute("mediaPath");
	var temp = videoDiv.getAttribute("temp");
	var resourceType = videoDiv.getAttribute("res");

	(function() {


    //get video file name,fileName and path
  //  var mediaName = '<%= asset.getMetadataValue("dc:title")!= "" ? asset.getMetadataValue("dc:title") : asset.getName() %>';
   // var mediaFile = '<%= asset.getName() %>';
    //var mediaPath = '<%= asset.getPath() %>';

    var video = document.getElementById(temp);
       
    var videoOpen = false;
    // delay (in ms) due to buggy player implementation
    // when seeking, video.currentTime is not updated correctly so we need to delay
    // retreiving currentTime by an offset
    var delay = 250;
    //mouse up flag
    var isMouseUp = true;
    //store currentTime for 1 second
    var pauseTime = 0;
    // clickstream cloud data to be send based on context mapping
     var CQ_data = new Object();

    if (video && video.addEventListener) {
        video.addEventListener("playing", play, false);
    }

    function open() {
        video.addEventListener("pause", pause, false);
        video.addEventListener("ended", ended, false);
        video.addEventListener("seeking", pause, false);
        video.addEventListener("seeked", play, false);
         
        //store flag for mouse events in order to play only if the mouse is up
        video.addEventListener("mousedown", mouseDown, false);
        video.addEventListener("mouseup", mouseUp, false);
        function mouseDown(){ 
            isMouseUp=false;
        } 
        function mouseUp(){ 
            isMouseUp = true;
        }

        CQ_data = new Object();          
        CQ_data["length"] = Math.floor(video.duration);
        CQ_data["playerType"] = "HTML5 video";
        CQ_data["source"] = mediaName;
        CQ_data["playhead"] = Math.floor(video.currentTime);
        
        CQ_data["videoName"] = mediaName;
        CQ_data["videoFileName"] = mediaFile;
        CQ_data["videoFilePath"] = mediaPath;

        CQ_Analytics.record({event: 'videoinitialize', values: CQ_data, componentPath: 'resourceType' });    

        storeVideoCurrentTime();
    }

    function play() {
        if (CQ_Analytics.Sitecatalyst) {
            // open video call
            if (!videoOpen) {
                open();
                videoOpen = true; 
            } else {
                //send pause event before play for scrub events
                pause();
                // register play
                setTimeout(playDelayed, delay);
            }
        }
    }

    function playDelayed() {
        if (isMouseUp){
            CQ_data = new Object(); 
            CQ_data["playhead"] = Math.floor(video.currentTime-delay/1000);
            CQ_data["source"] = mediaName;
            CQ_Analytics.record({event: 'videoplay', values: CQ_data, componentPath: 'resourceType' }); 
        }
    }

    function pause() {
        CQ_data = new Object(); 
        CQ_data["playhead"] = pauseTime;
        CQ_data["source"] = mediaName;
        CQ_Analytics.record({event: 'videopause', values: CQ_data, componentPath: 'resourceType' }); 
    }

    function ended() {
        CQ_data = new Object(); 
        CQ_data["playhead"] = Math.floor(video.currentTime);
        CQ_data["source"] = mediaName;
        CQ_Analytics.record({event: 'videoend', values: CQ_data, componentPath: 'resourceType' }); 
        //reset temp variables
        videoOpen = false;
        pauseTime = 0;
    }
    
    //store current time for one second that will be use for pause
    function storeVideoCurrentTime() {
        timer = window.setInterval(function() {
            if (video.ended != true) {
                pauseTime = Math.floor(video.currentTime); 
            } else { 
                window.clearInterval(timer);
            }
        },1000);
    }
    

})();
	
});