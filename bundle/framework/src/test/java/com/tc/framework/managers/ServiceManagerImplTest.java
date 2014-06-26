/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.framework.managers;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;

import com.tc.framework.api.ServiceFactory;
import com.tc.framework.api.ServiceManager;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.framework.managers.impl.ServiceManagerImpl;
import com.tc.framework.util.FactoryDescriptorMap;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ServiceManagerImpl.class, FrameworkLogger.class})
public class ServiceManagerImplTest {

	private ServiceManager serviceManager;
	private Logger logger;
	Map<String, FactoryDescriptorMap> factories = null;
	@Before
	public void setUp() throws Exception {
		try{
			logger = Mockito.mock(Logger.class);
			serviceManager = Mockito.mock(ServiceManagerImpl.class);
			PowerMockito.stub(
					PowerMockito.method(FrameworkLogger.class, "getLogger"))
					.toReturn(logger);
			PowerMockito.stub(
					PowerMockito.method(ServiceManagerImpl.class, "getFactories"))
					.toReturn(factories);
			//ActionManager ac1 = Whitebox.newInstance(ActionManagerImpl.class);
			//Whitebox.setInternalState(ac1, factories);
			
		}catch(Exception e){

			e.printStackTrace();
		}
	}
	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testGetService() {
		try{
			Object source = new Object();
			Class type = null;
			ServiceFactory sf = Mockito.mock(ServiceFactory.class);
			ServiceManagerImpl smImpl = new ServiceManagerImpl();
			PowerMockito.stub(PowerMockito.method(ServiceManagerImpl.class, "getServiceFactory")).toReturn(sf);
			smImpl.getService(source, type);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testActivate() {
		try{
			ComponentContext context = Mockito.mock(ComponentContext.class);
			ServiceManagerImpl smImpl = new ServiceManagerImpl();
			Whitebox.invokeMethod(smImpl, "activate",context);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testDeActivate() {
		try{
			ComponentContext context = Mockito.mock(ComponentContext.class);
			ServiceManagerImpl smImpl = new ServiceManagerImpl();
			Whitebox.invokeMethod(smImpl, "activate",context);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testBindServiceFactory() {
		try{
			ComponentContext context = Mockito.mock(ComponentContext.class);
			ServiceReference reference = Mockito.mock(ServiceReference.class);
			ServiceManagerImpl smImpl = new ServiceManagerImpl();
			Whitebox.invokeMethod(smImpl, "bindServiceFactory",reference);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testUnBindServiceFactory() {
		try{
			ServiceReference reference = Mockito.mock(ServiceReference.class);
			ServiceManagerImpl smImpl = new ServiceManagerImpl();
			Whitebox.invokeMethod(smImpl, "unBindServiceFactory",reference);
		}catch(Exception e){
			
		}
	}
	@Test
	public void testgetFactories() {
		try{
			ServiceManagerImpl smImpl = new ServiceManagerImpl();
			Whitebox.invokeMethod(smImpl, "getFactories");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testgetFactoryCache() {
		try{
			ServiceManagerImpl smImpl = new ServiceManagerImpl();
			Whitebox.invokeMethod(smImpl, "getFactoryCache");
		}catch(Exception e){
			
		}
	}
	@Test
	public void testlog() {
		try{
			int level =2;
			String message = "message";
			Throwable t = Mockito.mock(Throwable.class);
			ServiceManagerImpl smImpl = new ServiceManagerImpl();
			Whitebox.invokeMethod(smImpl, "log", level, message, t);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testregisterServiceFactory() {
		try{
			ComponentContext context = Mockito.mock(ComponentContext.class);
			ServiceReference reference = Mockito.mock(ServiceReference.class);
			ServiceManagerImpl smImpl = new ServiceManagerImpl();
			Whitebox.invokeMethod(smImpl, "registerServiceFactory",context,reference);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testunregisterServiceFactory() {
		try{
			ServiceReference reference = Mockito.mock(ServiceReference.class);
			ServiceManagerImpl smImpl = new ServiceManagerImpl();
			Whitebox.invokeMethod(smImpl, "unregisterServiceFactory",reference);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testgetServiceFactory() {
		try{
			Class type = ServiceManagerImpl.class;
			ServiceManagerImpl smImpl = new ServiceManagerImpl();
			Whitebox.invokeMethod(smImpl, "getServiceFactory",type);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testgetServiceFactoryFromCache() {
		try{
			Map<String, ServiceFactory> cache = Mockito.mock(Map.class);
			ServiceFactory sf = Mockito.mock(ServiceFactory.class);
			Mockito.when(cache.get("org.virtusa.launchpad.framework.managers.impl.ServiceManagerImpl")).thenReturn(sf);
			Class<?> type = ServiceManagerImpl.class;
			ServiceManagerImpl smImpl = new ServiceManagerImpl();
			Whitebox.invokeMethod(smImpl, "getServiceFactoryFromCache",type, cache);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testgetServiceFactoryFromMap() {
		try{
			String test = "stringtest";
			ServiceManagerImpl smImpl = new ServiceManagerImpl();
			Whitebox.invokeMethod(smImpl, "getServiceFactoryFromMap",test);

		}catch(Exception e){
			e.printStackTrace();
			
		}
	}
}
