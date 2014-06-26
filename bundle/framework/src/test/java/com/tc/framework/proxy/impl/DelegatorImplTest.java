/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.framework.proxy.impl;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.osgi.framework.BundleContext;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.powermock.reflect.Whitebox;

import com.tc.framework.api.Delegator;
import com.tc.framework.api.ManagerProvider;
import com.tc.framework.api.ServiceManager;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.framework.managers.impl.ActionManagerImpl;
import com.tc.framework.proxy.impl.DelegatorImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DelegatorImpl.class, FrameworkLogger.class,ManagerProvider.class})
public class DelegatorImplTest {
	
	private Delegator delegator;
	private Logger logger;
	Object action;
	Class type;
	ServiceManager serviceManager;
		@Before
	public void setUp() throws Exception {
		try{
			logger = Mockito.mock(Logger.class);
			delegator = Mockito.mock(DelegatorImpl.class);
			action = Mockito.mock(Object.class);
			serviceManager = Mockito.mock(ServiceManager.class);
			PowerMockito.stub(
					PowerMockito.method(FrameworkLogger.class, "getLogger"))
					.toReturn(logger);
			
		}catch(Exception e){

			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testAdaptToAction() {
		try{
			DelegatorImpl delegatorImpl = Mockito.mock(DelegatorImpl.class);
			PowerMockito.mockStatic(ManagerProvider.class);
			Mockito.when(ManagerProvider.getManager(ServiceManager.class)).thenReturn(serviceManager);
			type = DelegatorImpl.class;
			//Mockito.when(serviceManager.getService(delegatorImpl, type)).thenReturn(type);
			delegatorImpl.adaptTo(action,type);		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testActivate() {
		try{
			BundleContext  bundleContext = Mockito.mock(BundleContext.class);
			Map  map = Mockito.mock(Map.class);
			DelegatorImpl delegatorImpl = Whitebox.newInstance(DelegatorImpl.class);
			Whitebox.invokeMethod(delegatorImpl,"activate",bundleContext,map);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeActivate() {
		try{
			DelegatorImpl delegatorImpl = new DelegatorImpl();
			Whitebox.invokeMethod(delegatorImpl,"deactivate");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	

}
