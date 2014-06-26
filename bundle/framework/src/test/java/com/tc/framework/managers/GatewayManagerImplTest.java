/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.framework.managers;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;

import com.tc.framework.api.GatewayFactory;
import com.tc.framework.api.GatewayManager;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.framework.managers.impl.GatewayManagerImpl;
import com.tc.framework.util.FactoryDescriptorMap;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GatewayManagerImpl.class, FrameworkLogger.class})
public class GatewayManagerImplTest {
	
	private GatewayManager gatewayManager;
	private Logger logger;
	Map<String, FactoryDescriptorMap> factories = null;
	
	@Before
	public void setUp() throws Exception {
		try{
			logger = Mockito.mock(Logger.class);
			gatewayManager = Mockito.mock(GatewayManagerImpl.class);
			PowerMockito.stub(PowerMockito.method(FrameworkLogger.class, "getLogger")).toReturn(logger);
			factories = Mockito.mock(Map.class);		
			PowerMockito.stub(PowerMockito.method(GatewayManagerImpl.class, "getFactories")).toReturn(factories);
		}catch(Exception e){

			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetGateway() {
		try{			
			GatewayFactory factory = Mockito.mock(GatewayFactory.class);			
			Class gateway=GatewayManagerImpl.class;
			Object source = new Object();	
			
			GatewayFactory entry = Mockito.mock(GatewayFactory.class);
			GatewayFactory factory1 = Mockito.mock(GatewayFactory.class);
			PowerMockito.stub(PowerMockito.method(GatewayManagerImpl.class, "getGatewayFactoryFromMap")).toReturn(entry);
			PowerMockito.stub(PowerMockito.method(GatewayManagerImpl.class, "getGatewayFactory")).toReturn(factory);
			PowerMockito.stub(PowerMockito.method(GatewayManagerImpl.class, "getGatewayFactoryFromCache")).toReturn(factory1);
			//impl.getGateway(source, gateway);
			GatewayManagerImpl gatewayImpl = new GatewayManagerImpl();
			Whitebox.invokeMethod(gatewayImpl,"getGateway",source,gateway);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRegisterGatewayFactory() {
		try{
			ComponentContext context = Mockito.mock(ComponentContext.class);
			ServiceReference reference = Mockito.mock(ServiceReference.class);
			String[] gatewayClasses = new String[5];
			gatewayClasses[0] = "first";
			gatewayClasses[1] = "second";
			Mockito.when(reference.getProperty("gatewayclasses")).thenReturn(gatewayClasses);
			Bundle bundle = Mockito.mock(Bundle.class);
			Mockito.when(reference.getBundle()).thenReturn(bundle);
			Mockito.when(bundle.getBundleId()).thenReturn((long)1);
			GatewayManagerImpl ac = new GatewayManagerImpl();
			Whitebox.invokeMethod(ac, "registerGatewayFactory",context,reference);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testUnregisterGatewayFactory() {
		try{
			ServiceReference reference = Mockito.mock(ServiceReference.class);
			String[] gatewayclasses = new String[5];
			gatewayclasses[0] = "first";
			gatewayclasses[1] = "second";
			Mockito.when(reference.getProperty("gatewayclasses")).thenReturn(gatewayclasses);
			Bundle bundle = Mockito.mock(Bundle.class);
			Mockito.when(reference.getBundle()).thenReturn(bundle);
			Mockito.when(bundle.getBundleId()).thenReturn((long)1);
			GatewayManagerImpl ac = new GatewayManagerImpl();
			Whitebox.invokeMethod(ac, "unregisterGatewayFactory",reference);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetGatewayFactory() {
		try{	
			GatewayManagerImpl ac = new GatewayManagerImpl();
			Class gateway=GatewayManagerImpl.class;
			Whitebox.invokeMethod(ac, "getGatewayFactory",gateway);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testGetGatewayFactoryFromMap() {
		try{
			String gatewayClassName = "gatewayClassName";
			GatewayManagerImpl ac = new GatewayManagerImpl();
			Whitebox.invokeMethod(ac, "getGatewayFactoryFromMap",gatewayClassName);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testActivate() {
		try{
			ComponentContext context = Mockito.mock(ComponentContext.class);
			GatewayManagerImpl ac = new GatewayManagerImpl();
			Whitebox.invokeMethod(ac, "activate",context);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeActivate() {
		try{
			ComponentContext context = Mockito.mock(ComponentContext.class);
			GatewayManagerImpl ac = new GatewayManagerImpl();
			Whitebox.invokeMethod(ac, "deactivate",context);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBindGatewayFactory() {
		try{
			ServiceReference reference = Mockito.mock(ServiceReference.class);
			GatewayManagerImpl ac = new GatewayManagerImpl();
			Whitebox.invokeMethod(ac, "bindGatewayFactory",reference);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLog() {
		try{
			int level =1;
			String message = "message";
			Throwable t = Mockito.mock(Throwable.class);
			GatewayManagerImpl ac = new GatewayManagerImpl();
			Whitebox.invokeMethod(ac, "log",level,message,t);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
