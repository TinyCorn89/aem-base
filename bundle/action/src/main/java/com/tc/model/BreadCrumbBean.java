/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.model;

import java.io.Serializable;
import java.util.List;

/**
 * Bean to hold the breadcrumb details
 *
 */
public class BreadCrumbBean implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 639566909506060300L;
	public long getEndLevel() {
		return endLevel;
	}
	public void setEndLevel(long endLevel) {
		this.endLevel = endLevel;
	}
	public String getDelimStr() {
		return delimStr;
	}
	public void setDelimStr(String delimStr) {
		this.delimStr = delimStr;
	}
	public String getTrailStr() {
		return trailStr;
	}
	public void setTrailStr(String trailStr) {
		this.trailStr = trailStr;
	}
	public int getCurrentLevel() {
		return currentLevel;
	}
	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}
	public String getDelim() {
		return delim;
	}
	public void setDelim(String delim) {
		this.delim = delim;
	}
	private List<BreadCrumbBean> levellist; 
		
	private long endLevel ;
		private long level ;
		private String delimStr;
		private String trailStr ;
		private int currentLevel;
	    public String delim = "";
	    public String title="";
	    public String path="";
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public List<BreadCrumbBean> getLevellist() {
			return levellist;
		}
		public void setLevellist(List<BreadCrumbBean> levellist) {
			this.levellist = levellist;
		}
		public long getLevel() {
			return level;
		}
		public void setLevel(long level) {
			this.level = level;
		}
		
	
}
