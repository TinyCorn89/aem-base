/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;

import org.apache.sling.api.resource.Resource;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.day.cq.dam.video.VideoProfile;
import com.day.cq.wcm.api.WCMMode;
import com.day.cq.wcm.api.components.Component;
import com.day.cq.wcm.api.components.DropTarget;
import com.day.cq.wcm.api.designer.Style;
import com.day.cq.wcm.foundation.Placeholder;
import com.tc.action.BaseAction;
import com.tc.model.VideoBean;
import com.tc.model.VideoFlashBean;
import com.tc.model.VideoSourceBean;

public class VideoAction  extends BaseAction{
	private String width ;
	private String height;
	private String mediaName = "";
	private String mediaFile = "";
	private String mediaPath = "";
	private String reourceType = "";
	public VideoBean getVideoInfo(){
		Asset asset = null;
		//String width ;
		//String height;
		String wh = "";
		StringBuilder attributes  = null ;
		String id = "";
		//String mediaName = "";
		//String mediaFile = "";
		//String mediaPath = "";
		 String placeholder = "";
		 String ddClassName = DropTarget.CSS_CLASS_PREFIX + "video";
		 String classicPlaceholder;
		 VideoFlashBean videoFlashBean = null;
		 
		 List<VideoSourceBean>videoSourceBeanList = null;
		VideoBean  videoBean = new VideoBean();
		try{
			Node currentNode = getCurrentNode();
			Style currentStyle = getCurrentStyle();
			if(currentNode.hasProperty("asset")){
				asset= getAsset(currentNode.getProperty("asset").getString());
				if(null != asset){
				 getSlingRequest().setAttribute("video_asset", asset);
				
				if(currentNode.hasProperty("width")){
					width = currentNode.getProperty("width").getString();
					}else if(currentStyle.get("width") != null){
						 width = currentStyle.get("width", String.class); 
					 }else{
						 width = "480";
					 }
					if(currentNode.hasProperty("height")){
						height = currentNode.getProperty("height").getString();
					}else if(currentStyle.get("height") != null){
						height = currentStyle.get("height", String.class); 
					 }else{
						 height = "320";
					 }
					 wh = (width != null ? "width=\"" + width + "\"" : "") + " " + (height != null ? "height=\"" + height + "\"" : "");	
					
					 attributes = getAttributes(currentStyle);
					 mediaFile = asset.getName();
					 mediaPath = asset.getPath();
					 if(asset.getMetadataValue("dc:title") != ""){
						 mediaName =  asset.getMetadataValue("dc:title");
					 }else{
						 mediaName = asset.getName();
					 }
					 
					 id = "cq-video-html5-" + System.currentTimeMillis();
					Resource res = getCurrentResource();
					if(null != res){
						reourceType = res.getResourceType();
						
					}
					
					videoFlashBean = flashInfo(currentStyle,asset);
					videoSourceBeanList = sourceInfo(currentStyle,asset);
				}
			
			}else{
					Component component = getComponent();
					classicPlaceholder =
				                    "<div class=\"" + ddClassName +
				                            (WCMMode.fromRequest(getSlingRequest()) == WCMMode.EDIT ? " cq-video-placeholder" : "")  +
				                            "\"></div>";
				       placeholder = Placeholder.getDefaultPlaceholder(getSlingRequest(),component,classicPlaceholder, ddClassName);
				}
			videoBean.setAsset(asset);
			videoBean.setAttributes(attributes);
			videoBean.setID(id);
			videoBean.setMediaFile(mediaFile);
			videoBean.setMediaPath(mediaPath);
			videoBean.setWidthHeight(wh);
			videoBean.setMediaName(mediaName);
			videoBean.setPlaceholder(placeholder);
			videoBean.setReourceType(reourceType);
			videoBean.setVideoSourceBeanList(videoSourceBeanList);
			videoBean.setVideoFlashBean(videoFlashBean);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return videoBean;
	}
	
	public Asset getAsset(String assertPath){
		Resource assetRes = getResource(assertPath);
		Asset asset = assetRes == null ? null : assetRes.adaptTo(Asset.class);
		return asset;
		
	}
	
	  public Resource getResource(String path) {
	        Resource resource = getSlingRequest().getResourceResolver().getResource(path);
	        return resource;

	    }
	  
	  public StringBuilder getAttributes(Style style){
		  StringBuilder attributes = new StringBuilder();
		  String videoClass ="" ;
		  if(style.get("videoClass") != null){
			  videoClass = style.get("videoClass", "");
		  }
		  if (videoClass.length() > 0) {
	            attributes.append(" class=\"").append(videoClass).append("\"");
	        }
	        if (!style.get("noControls", false)) {
	            attributes.append(" controls=\"controls\"");
	        }
	        if (style.get("autoplay", false)) {
	            attributes.append(" autoplay=\"autoplay\"");
	        }
	        if (style.get("loop", false)) {
	            attributes.append(" loop=\"loop\"");
	        }
	        String preload = style.get("preload", "");
	        if (preload.length() > 0) {
	            attributes.append(" preload=\"").append(preload).append("\"");
	        }
		  
		  return attributes;
		  
	  }
	  
	  
	  
	  
	  public List<VideoSourceBean> sourceInfo(Style currentStyle,Asset asset){
		  VideoSourceBean videoSourceBean ;
		  List<VideoSourceBean>videoSourceBeanList = new ArrayList<VideoSourceBean>(); 
		  if(currentStyle.get("profiles") != null){
			  String [] prop =currentStyle.get("profiles",String[].class);
			  for(String profile : prop){
				  VideoProfile videoProfile = VideoProfile.get(getSlingRequest().getResourceResolver(), profile);
				  if (videoProfile != null) {
			            if( null != videoProfile.getRendition(asset)){
			            	 videoSourceBean = new VideoSourceBean();
			             videoSourceBean.setSrcValue(videoProfile.getHtmlSource(videoProfile.getRendition(asset)));
			             videoSourceBean.setType(videoProfile.getHtmlType());
			             videoSourceBeanList.add(videoSourceBean);
			            }
			         
			     }	  
		  
	              }
		  }
		  return videoSourceBeanList;
     }
	  

	  public VideoFlashBean flashInfo(Style currentStyle,Asset asset){
		  String DEFAULT_H264_PROFILE = "hq";
		  String DEFAULT_FLV_PROFILE = "flv";
		  String flashClass = "";
		  String wmode = "opaque";
		  String flashPlayer = "flvfallback"; 
		  String flashProfileId  = DEFAULT_H264_PROFILE;
		  Rendition rendition = null;
		  Rendition flvRendition = null;
		  String strobeSWF = "/etc/clientlibs/foundation/video/swf/StrobeMediaPlayback.swf";
		  String flvSWF = "/etc/clientlibs/foundation/video/swf/player_flv_maxi.swf";
		  String flvVideo = "";
		  String strobeVideo = "";
		  String strobeFlashvars = "";
		  String flvFlashvars = "";
		  String customFlvFlashvars = "margin=0&showvolume=1&showtime=1&showfullscreen=1";
		  boolean noControls = false;
		  boolean autoplay = false;
		  boolean loop = false;
		  String clsObj = "";
	      String flashProfileFLVId = DEFAULT_FLV_PROFILE;
	      String currentTime = "";
	      String flashProfileCustomId = "hq";
	      String customSWF = null;
	      String customVideo = "";
	      String customFlashvars = "";
	      String customMovieFlashvar = null;
	      String customWidthFlashvar = null;
	      String customHeightFlashvar = null;
	      Long time = null;
	      String tempID = null;
	      String fallbackMessage = "No video available.";
	    
	      
		  if(currentStyle.get("flashClass") != null){
			  flashClass =currentStyle.get("flashClass",String.class);
		  }
		  if(currentStyle.get("wmode") != null){
			  wmode =currentStyle.get("wmode",String.class);
		  }
		  if(currentStyle.get("flashPlayer") != null){
			  flashPlayer =currentStyle.get("flashPlayer",String.class);
		  }
		  if(currentStyle.get("flashProfile") != null){
			  flashProfileId =currentStyle.get("flashProfile",String.class);
		  }
		  if(currentStyle.get("noControls") != null){
			  noControls =currentStyle.get("noControls",Boolean.class);
		  }
		  if(currentStyle.get("autoplay") != null){
			  autoplay =currentStyle.get("autoplay",Boolean.class);
		  }
		  if(currentStyle.get("loop") != null){
			  loop =currentStyle.get("loop",Boolean.class);
		  }
		 // if("flvfallback".equals(flashPlayer) || "h264only".equals(flashPlayer)){
		  VideoProfile flashProfile = VideoProfile.get(getSlingRequest().getResourceResolver(), flashProfileId);
	         rendition = flashProfile.getRendition(asset);
	         strobeVideo = flashProfile.getStrobeVideoSource(rendition);
		 
	         if(currentStyle.get("strobeFlashvars") != null){
	        	 strobeFlashvars =currentStyle.get("strobeFlashvars",String.class);
			  }
	         strobeFlashvars = "src=" + strobeVideo + (strobeFlashvars.length() > 0 ? "&" : "") + strobeFlashvars;
	         strobeFlashvars += "&autoPlay=" + autoplay + "&loop=" + loop;
	         if (noControls) {
	             strobeFlashvars += "&controlBarMode=none";
	         }
	         if(currentStyle.get("flashProfileFLV") != null){
	        	 flashProfileFLVId =currentStyle.get("flashProfileFLV",String.class);
			  }
	         VideoProfile flashProfileFLV = VideoProfile.get(getSlingRequest().getResourceResolver(), flashProfileFLVId);
	        flvRendition = flashProfileFLV.getRendition(asset);
	        flvVideo = flashProfileFLV.getFlvVideoSource(flvRendition);
	        if(currentStyle.get("flvFlashvars") != null){
	        	customFlvFlashvars =currentStyle.get("flvFlashvars",String.class);
			  }
	        flvFlashvars = "flv=" + flvVideo + "&width=" + width + "&height=" + height;
	        if (customFlvFlashvars.length() > 0) {
                flvFlashvars += "&" + customFlvFlashvars;
            }
            flvFlashvars += "&autoplay=" + (autoplay ? "1" : "0") + "&loop=" + (loop ? "1" : "0");
            if (noControls) {
                flvFlashvars += "&showplayer=never";
            }
            clsObj = flashClass.length() > 0 ? ", {\"class\": \"" + flashClass + "\"}" : "";
            time =  System.currentTimeMillis();
            tempID = "cq-video-flash-alternate-" + time;
		 // } 
          
		  //else{
            if(currentStyle.get("flashProfileCustom") != null){
            	flashProfileCustomId = currentStyle.get("flashProfileCustom",String.class);
			  }
            
            VideoProfile flashProfileCustom = VideoProfile.get(getSlingRequest().getResourceResolver(), flashProfileCustomId);
            Rendition customRendition = flashProfileCustom.getRendition(asset);
            
            if(currentStyle.get("customSWF") != null){
            	customSWF = currentStyle.get("customSWF",String.class);
			  }
            customVideo = flashProfileCustom.getCustomVideoSource(customRendition);
            if(currentStyle.get("customFlashvars") != null){
            	customFlashvars = currentStyle.get("customFlashvars",String.class);
			  }
            if(currentStyle.get("customMovieFlashvar") != null){
            	customMovieFlashvar = currentStyle.get("customMovieFlashvar",String.class);
			  }
            if (customMovieFlashvar != null) {
                customFlashvars = customFlashvars + (customFlashvars.length() > 0 ? "&":"") + customMovieFlashvar + "=" + customVideo;
            }
            
            if(currentStyle.get("customWidthFlashvar") != null){
            	customWidthFlashvar = currentStyle.get("customWidthFlashvar",String.class);
			  }
            
            if (customWidthFlashvar != null) {
                customFlashvars = customFlashvars + (customFlashvars.length() > 0 ? "&":"") + customWidthFlashvar + "=" + width;
            }
            if(currentStyle.get("customHeightFlashvar") != null){
            	customHeightFlashvar = currentStyle.get("customHeightFlashvar",String.class);
			  }
            
            if (customHeightFlashvar != null) {
                customFlashvars = customFlashvars + (customFlashvars.length() > 0 ? "&":"") + customHeightFlashvar + "=" + height;
            }
         // } 
		  if(currentStyle.get("fallbackMessage") != null){
			  fallbackMessage = currentStyle.get("fallbackMessage",String.class);
			  }
            
            VideoFlashBean videoFlashBean = new VideoFlashBean();
            
            videoFlashBean.setWmode(wmode);
            videoFlashBean.setFlashPlayer(flashPlayer);
            videoFlashBean.setFlashClass(flashClass);
            videoFlashBean.setStrobeSWF(strobeSWF);
            videoFlashBean.setStrobeFlashvars(strobeFlashvars);
            videoFlashBean.setFlvSWF(flvSWF);
            videoFlashBean.setFlvFlashvars(flvFlashvars);
            videoFlashBean.setCustomSWF(customSWF);
            videoFlashBean.setCustomFlashvars(customFlashvars);
            videoFlashBean.setRendition(rendition);
            videoFlashBean.setClsObj(clsObj);
            videoFlashBean.setCurrentTime(time);
            videoFlashBean.setFlvRendition(flvRendition);
            videoFlashBean.setFlashjspId(tempID);
            videoFlashBean.setHeight(height);
            videoFlashBean.setWidth(width);
            videoFlashBean.setFallbackMessage(fallbackMessage);
        return  videoFlashBean;  
	  }
	  
	  
	  
}
