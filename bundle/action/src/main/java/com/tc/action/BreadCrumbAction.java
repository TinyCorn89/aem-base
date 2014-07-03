/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;

import org.slf4j.Logger;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.designer.Style;
import com.tc.action.BaseAction;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.model.BreadCrumbBean;


/**
 * @author piyushs
 *
 */
public class BreadCrumbAction extends BaseAction{
	
	 private Logger log = FrameworkLogger.getLogger();
	// private List<BreadCrumbBean> levellist;
	    public BreadCrumbBean getbreadCrumb() {
	        

	    	BreadCrumbBean breadCrumbBean = new BreadCrumbBean();
	    	
	       List<BreadCrumbBean> listOfBread = new ArrayList<BreadCrumbBean>();
	        log.info("started here+");
	        try {
	        	
	        	Style currentStyle = getCurrentStyle();
	        	Page currentpage=getCurrentPage();
	        	long level = currentStyle.get("absParent", 2L);
	            long endLevel = currentStyle.get("relParent", 1L);
	            String delimStr = currentStyle.get("delim", ">");
	            String trailStr = currentStyle.get("trail", "");
	            int currentLevel = currentpage.getDepth();
	            String delim = "";
	            String path="";
	       	 
	            while (level < currentLevel - endLevel) {
	        		Page trail = currentpage.getAbsoluteParent((int) level);
	        		BreadCrumbBean breadCrumblevelBean = new BreadCrumbBean();
	        		if (trail == null) {
	                    break;
	                }
	                String title = trail.getNavigationTitle();
	                if (title == null || title.equals("")) {
	                    title = trail.getNavigationTitle();
	                }
	                if (title == null || title.equals("")) {
	                    title = trail.getTitle();
	                }
	                if (title == null || title.equals("")) {
	                    title = trail.getName();
	                }
	                if (trail != null) {
	                	breadCrumblevelBean.setTitle(title);
	                }
	                delim = delimStr;
	                path=trail.getPath();
	                if(path!=null)
	                {path=path.concat(".html");}
	                breadCrumblevelBean.setPath(path);
	                breadCrumblevelBean.setLevel(level);
	                listOfBread.add(breadCrumblevelBean);
	                level++;
	            }
	            
	            breadCrumbBean.setLevellist(listOfBread);
	            breadCrumbBean.setDelim(delim);
	            breadCrumbBean.setDelimStr(delimStr);
	            breadCrumbBean.setTrailStr(trailStr);
	            breadCrumbBean.setCurrentLevel(currentLevel);
	            breadCrumbBean.setEndLevel(endLevel);
	       
	        
	        breadCrumbBean.setCurrentLevel(currentLevel);
	        } catch (Exception e) {
	            log.debug("Exception" + e.getMessage());
	        }
	        return breadCrumbBean;

	    }

	}