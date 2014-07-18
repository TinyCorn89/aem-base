package com.tc.model;

import java.io.Serializable;
import java.util.Collection;

public class AdBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String publicationDate;
	private String expirationDate;
	private String advertiserId;
	private String adCategory;
	Collection<String> keywords ;
	private String adFinder;
	private String startDate;
	private String endDate;
	private String displayName;
	Collection<String>sharedSites;
	private String site;
	public String getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getAdvertiserId() {
		return advertiserId;
	}
	public void setAdvertiserId(String advertiserId) {
		this.advertiserId = advertiserId;
	}
	public String getAdCategory() {
		return adCategory;
	}
	public void setAdCategory(String adCategory) {
		this.adCategory = adCategory;
	}
	public Collection<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(Collection<String> keywords) {
		this.keywords = keywords;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public Collection<String> getSharedSites() {
		return sharedSites;
	}
	public void setSharedSites(Collection<String> sharedSites) {
		this.sharedSites = sharedSites;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getAdFinder() {
		return adFinder;
	}
	public void setAdFinder(String adFinder) {
		this.adFinder = adFinder;
	}
	
	

}
