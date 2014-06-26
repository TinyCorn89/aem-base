<%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>

<%@page session="false"%>
<%@ include file="/apps/tc/global/global.jsp" %>

<action:action actionClassName="com.tc.action.VideoAction" bean="videoBean" actionName="getVideoInfo" />
<div id="videoDiv" mediaName="${videoBean.mediaName}" mediaFile="${videoBean.mediaFile}" mediaPath="${videoBean.mediaPath}"  temp="${videoBean.ID}" res="${videoBean.reourceType}" />

<c:if test="${videoBean.asset != null}">
<cq:includeClientLib js="cq.video" />

<c:choose>
    <c:when test="${ (param.strobe != null )&& (param.strobe == 'true')}">

      <cq:include script="flash.jsp"/>

    </c:when>
    <c:otherwise>
        <video id="${videoBean.ID}" ${videoBean.widthHeight} ${videoBean.attributes}>
            <cq:include script="sources.jsp"/>
            <cq:include script="flash.jsp"/>
        </video>
    </c:otherwise>
</c:choose>
   <script type="text/javascript">
(function() {

    //get video file name,fileName and path
    var mediaName = '${videoBean.mediaName}';
    var mediaFile = '${videoBean.mediaFile}';
    var mediaPath = '${videoBean.mediaPath}';

    var video = document.getElementById("${videoBean.mediaPath}");
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

        CQ_Analytics.record({event: 'videoinitialize', values: CQ_data, componentPath: '${videoBean.reourceType}' });    

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
            CQ_Analytics.record({event: 'videoplay', values: CQ_data, componentPath: '${videoBean.reourceType}' }); 
        }
    }

    function pause() {
        CQ_data = new Object(); 
        CQ_data["playhead"] = pauseTime;
        CQ_data["source"] = mediaName;
        CQ_Analytics.record({event: 'videopause', values: CQ_data, componentPath: '${videoBean.reourceType}' }); 
    }

    function ended() {
        CQ_data = new Object(); 
        CQ_data["playhead"] = Math.floor(video.currentTime);
        CQ_data["source"] = mediaName;
        CQ_Analytics.record({event: 'videoend', values: CQ_data, componentPath: '${videoBean.reourceType}' }); 
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
</script>






</c:if>

<c:if test="${videoBean.asset == null}">
${videoBean.placeholder}
</c:if>


