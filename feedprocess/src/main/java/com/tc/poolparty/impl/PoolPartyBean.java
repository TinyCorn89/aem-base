package com.tc.poolparty.impl;

import java.util.ArrayList;
import java.util.List;

public class PoolPartyBean {
	
	private String key;
	private List<String> narrowers;
	public List<String> getNarrowers() {
		return narrowers;
	}

	public void setNarrowers(List<String> narrowers) {
		this.narrowers = narrowers;
	}
	private List<PoolPartyBean> tags;
	
	public PoolPartyBean(String key) {
		this.key = key;
		this.tags = new ArrayList<PoolPartyBean>();
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<PoolPartyBean> getTags() {
		return tags;
	}
	public void setTags(List<PoolPartyBean> tags) {
		this.tags = tags;
	}
	public void addTag(String tag) {
		PoolPartyBean ppBean = new PoolPartyBean(tag);
		tags.add(ppBean);
	}
	
	

}
