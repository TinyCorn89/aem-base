/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.servlet;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Session;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.commons.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import com.day.cq.commons.SymmetricCrypt;
import com.tc.framework.managers.impl.AppsConfigServiceImpl;
import com.tc.framework.model.AppsConfigBean;
import com.tc.framework.util.Constants;
import com.tc.framework.util.Util;
import com.tc.servlet.AppsConfigServlet;



@RunWith(PowerMockRunner.class)
@PrepareForTest({AppsConfigServlet.class})
public class AppsConfigServletTest
{
	private AppsConfigServlet appsConfigServlet;
	private SlingHttpServletRequest request;
	private SlingHttpServletResponse response;
	private Map<String, String> map;
	private Session session;
	private ResourceResolver resourceResolver; 	
	private PrintWriter printWriter;
	private AppsConfigServiceImpl appsConfigService;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		try
		{
			appsConfigServlet = Whitebox.newInstance(AppsConfigServlet.class);
			request = Mockito.mock(SlingHttpServletRequest.class);
			response = Mockito.mock(SlingHttpServletResponse.class);
			session = Mockito.mock(Session.class);
			map = Mockito.mock(HashMap.class);		
			resourceResolver = Mockito.mock(ResourceResolver.class);
			printWriter = Mockito.mock(PrintWriter.class);	
			appsConfigService=Mockito.mock(AppsConfigServiceImpl.class);
			Whitebox.setInternalState(appsConfigServlet, appsConfigService);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
		appsConfigServlet = null;
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testDoPostActionWithValidate() 
	{
		String path="appsconfig.html";
		String action="validate";	
		try 
		{
			Mockito.when(request.getParameter(Constants.ACTION)).thenReturn(action);
			Mockito.when(request.getHeader(Constants.REFERER)).thenReturn(path);			
			PowerMockito.stub(PowerMockito.method(Util.class, "getUrlParts",String.class)).toReturn(map);
			Mockito.when(map.get(Constants.PATH)).thenReturn(path);			
			Mockito.when(request.getResourceResolver()).thenReturn(resourceResolver);
			Mockito.when(resourceResolver.adaptTo(Session.class)).thenReturn(session);					
			Mockito.when(request.getParameter(Constants.PROPERTY_NAME)).thenReturn("loginURL");			
			PowerMockito.whenNew(AppsConfigServiceImpl.class).withNoArguments().thenReturn(appsConfigService);
			Mockito.when(appsConfigService.isDuplicate(Mockito.anyString(), Mockito.anyString())).thenReturn(false);
			Mockito.when(response.getWriter()).thenReturn(printWriter);
			Whitebox.invokeMethod(appsConfigServlet, "doPost", request, response);

		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed :: Exception came during execution");
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testDoPostActionWithGetall() 
	{
		try 
		{
			String path="siteconfig.html";
			String action="getall";
			String allNodes = "all nodes";
			PrintWriter writer = Mockito.mock(PrintWriter.class);
			Mockito.when(request.getParameter(Constants.ACTION)).thenReturn(action);
			Mockito.when(request.getHeader(Constants.REFERER)).thenReturn(path);			
			PowerMockito.stub(PowerMockito.method(Util.class, "getUrlParts",String.class)).toReturn(map);
			Mockito.when(map.get(Constants.PATH)).thenReturn(path);			
			Mockito.when(request.getResourceResolver()).thenReturn(resourceResolver);
			Mockito.when(resourceResolver.adaptTo(Session.class)).thenReturn(session);		
			
			PowerMockito.stub(PowerMockito.method(AppsConfigServlet.class, "getAllProperties",String.class,Session.class)).toReturn(allNodes);
			assertNotNull(allNodes);
			Mockito.when(response.getWriter()).thenReturn(writer);
			Whitebox.invokeMethod(appsConfigServlet, "doPost", request, response);

		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed !! Exception came during execution");
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testDoPostActionWithSave() 
	{
		try 
		{
			String path="siteconfig.html";
			String action="save";
			String responseStr = "all nodes";
			PrintWriter writer = Mockito.mock(PrintWriter.class);
			Mockito.when(request.getParameter(Constants.ACTION)).thenReturn(action);
			Mockito.when(request.getHeader(Constants.REFERER)).thenReturn(path);			
			PowerMockito.stub(PowerMockito.method(Util.class, "getUrlParts",String.class)).toReturn(map);
			Mockito.when(map.get(Constants.PATH)).thenReturn(path);			
			Mockito.when(request.getResourceResolver()).thenReturn(resourceResolver);
			Mockito.when(resourceResolver.adaptTo(Session.class)).thenReturn(session);
			
			PowerMockito.stub(PowerMockito.method(AppsConfigServlet.class, "saveProperty",SlingHttpServletRequest.class,String.class)).toReturn(responseStr);
			assertNotNull(responseStr);
			Mockito.when(response.getWriter()).thenReturn(writer);
			Whitebox.invokeMethod(appsConfigServlet, "doPost", request, response);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	@SuppressWarnings("deprecation")
	@Test
	public void testDoPostActionWithPublishNodes() 
	{
		try
		{
			String publishList = "publish";
			JSONObject jsonObj =  Mockito.mock(JSONObject.class);
			PrintWriter writer = Mockito.mock(PrintWriter.class);
			String path="siteconfig.html";
			String action="publishNodes";
			
			Mockito.when(request.getParameter(Constants.ACTION)).thenReturn(action);
			Mockito.when(request.getHeader(Constants.REFERER)).thenReturn(path);			
			PowerMockito.stub(PowerMockito.method(Util.class, "getUrlParts",String.class)).toReturn(map);
			Mockito.when(map.get(Constants.PATH)).thenReturn(path);			
			Mockito.when(request.getResourceResolver()).thenReturn(resourceResolver);
			Mockito.when(resourceResolver.adaptTo(Session.class)).thenReturn(session);
			Mockito.when(request.getParameter(Constants.PUBLISH_ID)).thenReturn(publishList);
			assertNotNull(publishList);
			PowerMockito.whenNew(AppsConfigServiceImpl.class).withNoArguments().thenReturn(appsConfigService);
			Mockito.when(appsConfigService.publishNodes(session, publishList)).thenReturn(true);
			PowerMockito.whenNew(JSONObject.class).withNoArguments().thenReturn(jsonObj);
			Mockito.when(response.getWriter()).thenReturn(writer);
			Whitebox.invokeMethod(appsConfigServlet, "doPost", request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testDoPostActionWithPublishAllNodes() 
	{
		try
		{
			String publishList = "publish";
			JSONObject jsonObj =  Mockito.mock(JSONObject.class);
			PrintWriter writer = Mockito.mock(PrintWriter.class);
			String path="siteconfig.html";
			String action="publishAllNodes";		
			
			Mockito.when(request.getParameter(Constants.ACTION)).thenReturn(action);
			Mockito.when(request.getHeader(Constants.REFERER)).thenReturn(path);			
			PowerMockito.stub(PowerMockito.method(Util.class, "getUrlParts",String.class)).toReturn(map);
			Mockito.when(map.get(Constants.PATH)).thenReturn(path);			
			Mockito.when(request.getResourceResolver()).thenReturn(resourceResolver);
			Mockito.when(resourceResolver.adaptTo(Session.class)).thenReturn(session);		
			Mockito.when(request.getParameter(Constants.PUBLISH_ID)).thenReturn(publishList);
			assertNotNull(publishList);
			PowerMockito.whenNew(AppsConfigServiceImpl.class).withNoArguments().thenReturn(appsConfigService);
			Mockito.when(appsConfigService.getAppsConfigCollectionPath(session, path)).thenReturn(path);
			Mockito.when(appsConfigService.publishAllNodes(session, path)).thenReturn(true);
			PowerMockito.whenNew(JSONObject.class).withNoArguments().thenReturn(jsonObj);
			Mockito.when(response.getWriter()).thenReturn(writer);
			Whitebox.invokeMethod(appsConfigServlet, "doPost", request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	@Test
	public void testSavePropertyWithIf(){
		String path = "";
		String propertyName  = "test";
		String authorValue = "testauthor";
		String publishValue = "testpublish";
		String encrypt = "on";
		String nodeName = "siteconfig";
		AppsConfigBean appsconfigBean = new AppsConfigBean();  
		try 
		{	
			Mockito.when(request.getParameter(Constants.SLASH_PROPERTY_NAME)).thenReturn(propertyName);
			Mockito.when(request.getParameter(Constants.SLASH_AUTHOR_VALUE)).thenReturn(authorValue);
			Mockito.when(request.getParameter(Constants.SLASH_PUBLISH_VALUE)).thenReturn(publishValue);
			Mockito.when(request.getParameter(Constants.SLASH_ENCRYPT)).thenReturn(encrypt);
			Mockito.when(request.getParameter(Constants.NODENAME)).thenReturn(nodeName);
			assertNotNull(encrypt);
			PowerMockito.stub(PowerMockito.method(SymmetricCrypt.class,"decrypt", String.class)).toReturn("on");
			PowerMockito.stub(PowerMockito.method(SymmetricCrypt.class,"decrypt", String.class)).toReturn("on");
			PowerMockito.whenNew(AppsConfigServiceImpl.class).withNoArguments().thenReturn(appsConfigService);
			PowerMockito.doNothing().when(appsConfigService).saveProperty(appsconfigBean, path);
			String response = Whitebox.invokeMethod(appsConfigServlet, "saveProperty", request,path);
			assertEquals(response,"true");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testSavePropertyWithElse(){
		String path = "";
		String propertyName  = "test";
		String authorValue = "testauthor";
		String publishValue = "testpublish";
		String encrypt = "off";
		String nodeName = "siteconfig";
		AppsConfigBean appsconfigBean = new AppsConfigBean();  
		try 
		{	
			Mockito.when(request.getParameter(Constants.SLASH_PROPERTY_NAME)).thenReturn(propertyName);
			Mockito.when(request.getParameter(Constants.SLASH_AUTHOR_VALUE)).thenReturn(authorValue);
			Mockito.when(request.getParameter(Constants.SLASH_PUBLISH_VALUE)).thenReturn(publishValue);
			Mockito.when(request.getParameter(Constants.SLASH_ENCRYPT)).thenReturn(encrypt);
			Mockito.when(request.getParameter(Constants.NODENAME)).thenReturn(nodeName);
			assertNotNull(encrypt);
			PowerMockito.whenNew(AppsConfigServiceImpl.class).withNoArguments().thenReturn(appsConfigService);
			PowerMockito.doNothing().when(appsConfigService).saveProperty(appsconfigBean, path);
			String response = Whitebox.invokeMethod(appsConfigServlet, "saveProperty", request,path);
			assertEquals(response,"true");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAllProperties(){
		@SuppressWarnings("unchecked")
		List<AppsConfigBean> appsconfigList = Mockito.mock(ArrayList.class);
		JSONObject jsonObj = Mockito.mock(JSONObject.class);
		StringWriter writer = Mockito.mock(StringWriter.class);
		StringBuffer buffer = new StringBuffer();
		String path = "";
		long size = 1;
		AppsConfigBean siteconfigBean = Mockito.mock(AppsConfigBean.class);		
		try
		{
			PowerMockito.whenNew(AppsConfigServiceImpl.class).withNoArguments().thenReturn(appsConfigService);
			Mockito.when(appsConfigService.getAllProperties(path, session)).thenReturn(appsconfigList);
			Mockito.when(appsconfigList.size()).thenReturn((int)size);
			assertNotNull(size);
			Mockito.when(appsconfigList.get(0)).thenReturn(siteconfigBean);
			PowerMockito.whenNew(JSONObject.class).withNoArguments().thenReturn(jsonObj);
			Mockito.when(siteconfigBean.getPath()).thenReturn("/en/siteconfig.html");
			Mockito.when(siteconfigBean.getPropertyName()).thenReturn("test");
			Mockito.when(siteconfigBean.getAuthorValue()).thenReturn("testauthor");
			Mockito.when(siteconfigBean.getPublishValue()).thenReturn("testpublish");
			Mockito.when(writer.getBuffer()).thenReturn(buffer);
			String response =  Whitebox.invokeMethod(appsConfigServlet, "getAllProperties",path,session);
			assertEquals(response,"");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
