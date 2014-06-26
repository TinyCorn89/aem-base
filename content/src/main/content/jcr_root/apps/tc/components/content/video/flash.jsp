 <%--

  Copyright (C) 2014 Virtusa Corporation
  This file is proprietary and part of Virtusa LaunchPad.
  LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation

--%>

<%@page session="false"%>
<%@ include file="/apps/tc/global/global.jsp" %>
<%@ page import="com.day.cq.analytics.sitecatalyst.Framework" %>

<action:action actionClassName="com.tc.action.VideoAction" bean="videoBean" actionName="getVideoInfo" />

<c:choose>

    <c:when test="${ (videoBean.videoFlashBean.flashPlayer == 'flvfallback') || (videoBean.videoFlashBean.flashPlayer == 'h264only')}">

        <c:if test="${videoBean.videoFlashBean.rendition == null}">
            <cq:include script="fallback.jsp"/>
       </c:if>
           <c:if test="${videoBean.videoFlashBean.rendition != null}">


               <c:choose>
                  <c:when test="${(videoBean.videoFlashBean.flashPlayer == 'flvfallback') && (videoBean.videoFlashBean.flvRendition != null)  }">
                       <cq:includeClientLib js="cq.swfobject" />


                    
                     <script> 
                         var e = document.getElementById("${videoBean.videoFlashBean.flashjspId}");
                            if (e) e.style.display = "block"; 
                            
                            var flashVersion = window.CQ_swfobject ? CQ_swfobject.getFlashPlayerVersion() : -1;
                            if (flashVersion.major >= 10){
                               CQ_swfobject.embedSWF("${videoBean.videoFlashBean.strobeSWF}", "${videoBean.videoFlashBean.flashjspId}", "${videoBean.videoFlashBean.width}", "${videoBean.videoFlashBean.height}", "10", "", false,
                                                      {flashvars: "${videoBean.videoFlashBean.strobeFlashvars}&javascriptCallbackFunction=strobeTrackingCallback${videoBean.videoFlashBean.currentTime}", 
                                                          allowFullScreen: "true", wmode: "${videoBean.videoFlashBean.wmode}"}${videoBean.videoFlashBean.clsObj});
                            } else if (flashVersion.major >= 7) {
                                CQ_swfobject.embedSWF("${videoBean.videoFlashBean.flvSWF}", "${videoBean.videoFlashBean.flashjspId}", "${videoBean.videoFlashBean.width}", "${videoBean.videoFlashBean.height}", "7", "", false,
                                                      {flashvars: "${videoBean.videoFlashBean.flvFlashvars}", allowFullScreen: "true"}${videoBean.videoFlashBean.clsObj});
                            }
                    
                            if (!trackingClipData || typeof trackingClipData != "object") {
                                var trackingClipData = new Object();
                            }
                            trackingClipData["${videoBean.videoFlashBean.flashjspId}"] = new Object();
                            trackingClipData["${videoBean.videoFlashBean.flashjspId}"].duration   = -1;
                            trackingClipData["${videoBean.videoFlashBean.flashjspId}"].playhead   = 0;
                            trackingClipData["${videoBean.videoFlashBean.flashjspId}"].source     = "${videoBean.mediaName}";  
                            trackingClipData["${videoBean.videoFlashBean.flashjspId}"].sourceFile = "${videoBean.mediaFile}";
                            trackingClipData["${videoBean.videoFlashBean.flashjspId}"].sourcePath = "${videoBean.mediaPath}";
                            
                            //video open flag
                            var videoOpen = false;
                    
                            //send play event later to update the playhead on scrub events
                            var delay = 1000;
                        
                            //clickstream cloud data to be send based on context mapping
                            var CQ_data = new Object();
                    
                            function open${videoBean.videoFlashBean.currentTime}(){
                                videoOpen = true; 
                                
                                CQ_data = new Object(); 
                                CQ_data["length"]       = trackingClipData["${videoBean.videoFlashBean.flashjspId}"].duration;
                                CQ_data["playerType"]   = "Strobe Media Playback";
                                CQ_data["source"]       = trackingClipData["${videoBean.videoFlashBean.flashjspId}"].source;
                                CQ_data["playhead"]     = trackingClipData["${videoBean.videoFlashBean.flashjspId}"].playhead;
                                
                                CQ_data["videoName"]     = trackingClipData["${videoBean.videoFlashBean.flashjspId}"].source;
                                CQ_data["videoFileName"] = trackingClipData["${videoBean.videoFlashBean.flashjspId}"].sourceFile;
                                CQ_data["videoFilePath"] = trackingClipData["${videoBean.videoFlashBean.flashjspId}"].sourcePath;
                     
                                CQ_Analytics.record({event: 'videoinitialize', values: CQ_data, componentPath: '${videoBean.reourceType}' }); 
                            }
                            
                            function play${videoBean.videoFlashBean.currentTime}(){
                                if (trackingClipData["${videoBean.videoFlashBean.flashjspId}"].duration > 0) {
                                    if (!videoOpen) { 
                                        open${videoBean.videoFlashBean.currentTime}();
                                    } else {
                    
                                        CQ_data = new Object();  
                                        CQ_data["playhead"]     = trackingClipData["${videoBean.videoFlashBean.flashjspId}"].playhead - delay/1000; 
                                        CQ_data["source"]       = trackingClipData["${videoBean.videoFlashBean.flashjspId}"].source;
                    
                                        CQ_Analytics.record({event: 'videoplay', values: CQ_data, componentPath: '${videoBean.reourceType}' });
                                    }
                                }
                            }
                            
                            function pause${videoBean.videoFlashBean.currentTime}(){
                                CQ_data = new Object();  
                                CQ_data["playhead"]     = trackingClipData["${videoBean.videoFlashBean.flashjspId}"].playhead; 
                                CQ_data["source"]       = trackingClipData["${videoBean.videoFlashBean.flashjspId}"].source;
                                
                                CQ_Analytics.record({event: 'videopause', values: CQ_data, componentPath: '${videoBean.reourceType}' });
                            }
                            
                    
                            function strobeTrackingCallback${videoBean.videoFlashBean.currentTime}(playerId, state, obj) { 
                                    switch (state) {
                                        case "durationchange": 
                                            trackingClipData["${videoBean.videoFlashBean.flashjspId}"].duration = parseInt(obj.duration);
                                            if (!videoOpen) {
                                                open${videoBean.videoFlashBean.currentTime}();
                                            }
                                            break;
                                        case "play": 
                                            //send pause event befor delay play to fix scrub events for tracking
                                            pause${videoBean.videoFlashBean.currentTime}();
                                            setTimeout(play${videoBean.videoFlashBean.currentTime}, delay);
                                            break;
                                        case "timeupdate":
                                            trackingClipData["${videoBean.videoFlashBean.flashjspId}"].playhead = parseInt(obj.currentTime);  
                                            break;
                                        case "pause":
                                            pause${videoBean.videoFlashBean.flashjspId}();
                                            break;
                                        case "complete":
                                            CQ_data = new Object();  
                                            CQ_data["playhead"]       = trackingClipData["${videoBean.videoFlashBean.flashjspId}"].playhead; 
                                            CQ_data["source"]       = trackingClipData["${videoBean.videoFlashBean.flashjspId}"].source;
                                            
                                            CQ_Analytics.record({event: 'videoend', values: CQ_data, componentPath: '${videoBean.reourceType}' });
                    
                                            videoOpen = false; 
                                            break;
                                        default:
                                            // do nothing if other events are triggered
                                            break;
                                    }
                            }
                        </script>




                      <div id="${videoBean.videoFlashBean.flashjspId}" class="cq-video-flash-alternate" style="display:none;">

                          <cq:include script="fallback.jsp"/>
                      </div>
                      <noscript>
                         <object class="${videoBean.videoFlashBean.flashClass}" type="application/x-shockwave-flash" data="${videoBean.videoFlashBean.flvSWF}" width="${videoBean.videoFlashBean.width}" height="${videoBean.videoFlashBean.height}">
                            <param name="movie" value="${videoBean.videoFlashBean.flvSWF}" />
                            <param name="allowFullScreen" value="true" />
                            <param name="wmode" value="${videoBean.videoFlashBean.wmode}" />
                            <param name="flashvars" value="${videoBean.videoFlashBean.flvFlashvars}" />

                             <cq:include script="fallback.jsp"/>
                         </object>
                      </noscript>
                    </c:when>

                    <c:otherwise> 


                        <object class="${videoBean.videoFlashBean.flashClass}" type="application/x-shockwave-flash" data="${videoBean.videoFlashBean.strobeSWF}" width="${videoBean.videoFlashBean.width}" height="${videoBean.videoFlashBean.height}">
                          <param name="movie" value="${videoBean.videoFlashBean.strobeSWF}"/>
                          <param name="allowFullScreen" value="true" />
                          <param name="wmode" value="${videoBean.videoFlashBean.wmode}" />
                          <param name="flashvars" value="${videoBean.videoFlashBean.strobeFlashvars}" />
                          <cq:include script="fallback.jsp"/>
                       </object>
                   </c:otherwise>
               </c:choose>

        </c:if>
    </c:when>

    <c:otherwise>
        <object class="${videoBean.videoFlashBea.flashClass}" type="application/x-shockwave-flash" data="${videoBean.videoFlashBean.customSWF}" width="${videoBean.videoFlashBean.width}" height="${videoBean.videoFlashBean.height}">
            <param name="movie" value="${videoBean.videoFlashBean.customSWF}" />
            <param name="allowFullScreen" value="true" />
            <param name="flashvars" value="${videoBean.videoFlashBean.customFlashvars}" />
            <cq:include script="fallback.jsp"/>
        </object>


    </c:otherwise>

</c:choose>





