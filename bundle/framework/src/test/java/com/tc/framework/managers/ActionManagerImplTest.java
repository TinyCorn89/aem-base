/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.framework.managers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.PageContext;

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

import com.tc.framework.api.ActionFactory;
import com.tc.framework.api.ActionManager;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.framework.managers.impl.ActionManagerImpl;
import com.tc.framework.util.FactoryDescriptorMap;


@RunWith(PowerMockRunner.class)
@PrepareForTest({ActionManagerImpl.class, FrameworkLogger.class})
public class ActionManagerImplTest {

	private ActionManager actionManager;
	private Logger logger;
	Map<String, FactoryDescriptorMap> factories = null;
	
	@Before
	public void setUp() throws Exception {
		try{
			logger = Mockito.mock(Logger.class);
			actionManager = Mockito.mock(ActionManagerImpl.class);
			PowerMockito.stub(
					PowerMockito.method(FrameworkLogger.class, "getLogger"))
					.toReturn(logger);
			factories = Mockito.mock(Map.class);
			//ActionManager ac1 = Whitebox.newInstance(ActionManagerImpl.class);
			//Whitebox.setInternalState(ac1, factories);
			PowerMockito.stub(
					PowerMockito.method(ActionManagerImpl.class, "getFactories"))
					.toReturn(factories);
		}catch(Exception e){

			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testInvokeAction() {
		try{
			ActionFactory factory = Mockito.mock(ActionFactory.class);
			ActionManagerImpl impl = Mockito.mock(ActionManagerImpl.class);
			Object sourceTag = new Object();
			String actionName = "actionName";
			PageContext pageContext = Mockito.mock(PageContext.class);
			String actionClassName = "actionClassName";
			ActionManagerImpl ac = new ActionManagerImpl();
			//Map<String, ActionFactory> map = Mockito.mock(Map.class);
			FactoryDescriptorMap actionFactoryDescriptorMap = Mockito.mock(FactoryDescriptorMap.class);
			Mockito.when(factories.get(actionClassName)).thenReturn(actionFactoryDescriptorMap);
			
			//ActionFactory entry = Whitebox.invokeMethod(ac, "getActionFactoryFromMap", actionClassName);
			ActionFactory entry = Mockito.mock(ActionFactory.class);
			ActionFactory factory1 = Mockito.mock(ActionFactory.class);
			PowerMockito.stub(PowerMockito.method(ActionManagerImpl.class, "getActionFactoryFromMap")).toReturn(entry);
			PowerMockito.stub(PowerMockito.method(ActionManagerImpl.class, "getActionFactory")).toReturn(factory);
			PowerMockito.stub(PowerMockito.method(ActionManagerImpl.class, "getActionFactoryFromCache")).toReturn(factory1);
			ac.invokeAction(sourceTag, actionClassName, actionName, pageContext);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRegisterActionFactory() {
		try{
			ComponentContext context = Mockito.mock(ComponentContext.class);
			ServiceReference reference = Mockito.mock(ServiceReference.class);
			String[] actionClasses = new String[5];
			actionClasses[0] = "first";
			actionClasses[1] = "second";
			Mockito.when(reference.getProperty("actionclasses")).thenReturn(actionClasses);
			Bundle bundle = Mockito.mock(Bundle.class);
			Mockito.when(reference.getBundle()).thenReturn(bundle);
			Mockito.when(bundle.getBundleId()).thenReturn((long)1);
			ActionManagerImpl ac = new ActionManagerImpl();
			Whitebox.invokeMethod(ac, "registerActionFactory",context,reference);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testUnregisterActionFactory() {
		try{
			ServiceReference reference = Mockito.mock(ServiceReference.class);
			String[] actionClasses = new String[5];
			actionClasses[0] = "first";
			actionClasses[1] = "second";
			Mockito.when(reference.getProperty("actionclasses")).thenReturn(actionClasses);
			Bundle bundle = Mockito.mock(Bundle.class);
			Mockito.when(reference.getBundle()).thenReturn(bundle);
			Mockito.when(bundle.getBundleId()).thenReturn((long)1);
			ActionManagerImpl ac = new ActionManagerImpl();
			Whitebox.invokeMethod(ac, "unregisterActionFactory",reference);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testActionFactory() {
		try{
			
			String actionClassName = "actionClassName";
			ActionManagerImpl ac = new ActionManagerImpl();
			Whitebox.invokeMethod(ac, "getActionFactory",actionClassName);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testGetActionFactoryFromMap() {
		try{
			String actionClassName = "actionClassName";
			ActionManagerImpl ac = new ActionManagerImpl();
			Whitebox.invokeMethod(ac, "getActionFactoryFromMap",actionClassName);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testActivate() {
		try{
			ComponentContext context = Mockito.mock(ComponentContext.class);
			ActionManagerImpl ac = new ActionManagerImpl();
			Whitebox.invokeMethod(ac, "activate",context);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeActivate() {
		try{
			ComponentContext context = Mockito.mock(ComponentContext.class);
			ActionManagerImpl ac = new ActionManagerImpl();
			Whitebox.invokeMethod(ac, "deactivate",context);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBindActionFactory() {
		try{
			ServiceReference reference = Mockito.mock(ServiceReference.class);
			ActionManagerImpl ac = new ActionManagerImpl();
			Whitebox.invokeMethod(ac, "bindActionFactory",reference);
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
			ActionManagerImpl ac = new ActionManagerImpl();
			Whitebox.invokeMethod(ac, "log",level,message,t);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
