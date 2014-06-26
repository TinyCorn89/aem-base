/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.managers;

import java.util.Map;

import org.apache.sling.api.SlingHttpServletRequest;


public interface SearchService {

	
	public Map<String,Object> simpleSearch(SlingHttpServletRequest request) throws Exception;
	
	public Map<String,Object> advancedsearch(SlingHttpServletRequest request) throws Exception;
}
