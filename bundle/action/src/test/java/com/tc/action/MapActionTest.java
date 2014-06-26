/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.action;

import static org.junit.Assert.assertEquals;

import javax.jcr.Node;
import javax.jcr.Property;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;

import com.tc.action.MapAction;
import com.tc.action.NewsAction;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.model.MapBean;

/**
 * The Class MapActionTest.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ MapAction.class, FrameworkLogger.class })
public class MapActionTest extends BaseTest {

	/** The map action. */
	private MapAction mapAction = null;

	/** The height. */
	private String height = "300";

	/** The width. */
	private String width = "960";

	/** The map node. */
	private Node mapNode;

	/** The height prop. */
	private Property heightProp;

	/** The width prop. */
	private Property widthProp;

	/** The location prop. */
	private Property locationProp;

	private Logger logger;


	/**
	 * Setup method.
	 * 
	 * @throws Exception
	 *             - exception
	 */
	@Before
	public void setUp() throws Exception {
		mapNode = Mockito.mock(Node.class);
		heightProp = Mockito.mock(Property.class);
		widthProp = Mockito.mock(Property.class);
		locationProp = Mockito.mock(Property.class);
		logger = Mockito.mock(Logger.class);
		mapAction = Whitebox.newInstance(MapAction.class);
		PowerMockito.stub(
				PowerMockito.method(NewsAction.class, "getCurrentNode"))
				.toReturn(mapNode);
		PowerMockito.stub(
				PowerMockito.method(FrameworkLogger.class, "getLogger"))
				.toReturn(logger);
		Whitebox.setInternalState(mapAction, logger);
		

	}

	/**
	 * Tear down method.
	 * 
	 * @throws Exception
	 *             - exception
	 */
	@After
	public void tearDown() throws Exception {
		mapAction = null;
	}

	/**
	 * Test map action.
	 */
	@Test
	public void testMapAction() {

		try {

			String location = "";
			String locationTest = "virtusa";
			Mockito.when(mapNode.hasProperty("./height")).thenReturn(true);
			Mockito.when(mapNode.getProperty("./height"))
					.thenReturn(heightProp);
			Mockito.when(heightProp.getString()).thenReturn("0");
			Mockito.when(mapNode.hasProperty("./width")).thenReturn(true);
			Mockito.when(mapNode.getProperty("./width")).thenReturn(widthProp);
			Mockito.when(widthProp.getString()).thenReturn("0");
			Mockito.when(mapNode.hasProperty("./location")).thenReturn(true);
			Mockito.when(mapNode.getProperty("./location")).thenReturn(
					locationProp);
			Mockito.when(locationProp.getString()).thenReturn(location);
			PowerMockito.stub(PowerMockito.method(MapAction.class, "fromatLocation", String.class)).toReturn(
					locationTest);
			MapBean mapBean = mapAction.getMapInfo();
			assertEquals("height", Integer.parseInt(height),
					mapBean.getHeight());
			assertEquals("width", Integer.parseInt(width), mapBean.getWidth());
			assertEquals("location", locationTest, mapBean.getLocation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	@Test
	public void testMapAction1() {

		try {

			String location = "Virtusa Corporation,Hyderabad,Andhra Pradesh";
			String locationTest = "Virtusa+Corporation,+Hyderabad,+Andhra,+Pradesh";
			Mockito.when(mapNode.hasProperty("./height")).thenReturn(true);
			Mockito.when(mapNode.getProperty("./height"))
					.thenReturn(heightProp);
			Mockito.when(heightProp.getString()).thenReturn(height);
			Mockito.when(mapNode.hasProperty("./width")).thenReturn(true);
			Mockito.when(mapNode.getProperty("./width")).thenReturn(widthProp);
			Mockito.when(widthProp.getString()).thenReturn(width);
			Mockito.when(mapNode.hasProperty("./location")).thenReturn(true);
			Mockito.when(mapNode.getProperty("./location")).thenReturn(
					locationProp);
			Mockito.when(locationProp.getString()).thenReturn(location);
			PowerMockito.stub(PowerMockito.method(MapAction.class, "fromatLocation", String.class)).toReturn(
					locationTest);
			MapBean mapBean = mapAction.getMapInfo();
			assertEquals("height", Integer.parseInt(height),
					mapBean.getHeight());
			assertEquals("width", Integer.parseInt(width), mapBean.getWidth());
			assertEquals("location", locationTest, mapBean.getLocation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test map action1.
	 */
	@Test
	public void testMapActionFormat() {
		String location = "Virtusa Corporation, Hyderabad, Andhra Pradesh";
		String locationTest = "Virtusa+Corporation,+Hyderabad,+Andhra+Pradesh";

		
		
		
		String s = mapAction.fromatLocation(location);
		
		assertEquals("location", locationTest,s);

	}
	
	
	@Test
	public void testMapActionFormat1() {
		String location = "Virtusa Corporation";
		String locationTest = "Virtusa+Corporation";

		
		
		String s = mapAction.fromatLocation(location);
		
		assertEquals("location", locationTest,s);

	}
	
	@Test
	public void testMapActionFormat2() {
		String location = "Virtusa";
		String locationTest = "Virtusa";

		
		
		String s = mapAction.fromatLocation(location);
		
		assertEquals("location", locationTest,s);

	}
	
	
	@Test
	public void testMapActionIf() {

		try {

			//String location = "";
			String locationTest = "virtusa";
			Mockito.when(mapNode.hasProperty("./height")).thenReturn(false);
			/*Mockito.when(mapNode.getProperty("./height"))
					.thenReturn(heightProp);
			Mockito.when(heightProp.getString()).thenReturn("0");*/
			Mockito.when(mapNode.hasProperty("./width")).thenReturn(false);
			/*Mockito.when(mapNode.getProperty("./width")).thenReturn(widthProp);
			Mockito.when(widthProp.getString()).thenReturn("0");*/
			Mockito.when(mapNode.hasProperty("./location")).thenReturn(false);
			/*Mockito.when(mapNode.getProperty("./location")).thenReturn(
					locationProp);
			Mockito.when(locationProp.getString()).thenReturn(location);*/
			PowerMockito.stub(PowerMockito.method(MapAction.class, "fromatLocation", String.class)).toReturn(
					locationTest);
			MapBean mapBean = mapAction.getMapInfo();
			/*assertEquals("height", Integer.parseInt(height),
					mapBean.getHeight());
			assertEquals("width", Integer.parseInt(width), mapBean.getWidth());
			assertEquals("location", locationTest, mapBean.getLocation());*/
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
