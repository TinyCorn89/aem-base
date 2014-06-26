/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.action;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.Value;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
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

import com.tc.action.CarouselAction;
import com.tc.action.HeroBannerAction;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.model.BannerBean;
import com.tc.model.CarouselBean;
import com.tc.util.Constants;

/**
 * The Class RotatingBannerActionTest.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ CarouselAction.class, FrameworkLogger.class })
public class CarouselActionTest extends BaseTest {

	private Logger logger;

	/** The rotating banner action. */
	private CarouselAction carouselAction = null;

	/** The banner node. */
	private Node bannerNode;

	/** The node1. */
	private Node node1;

	/** The node iterator. */
	NodeIterator nodeIterator;

	/** The node iterator1. */
	NodeIterator nodeIterator1;

	/** The property iterator. */
	PropertyIterator propertyIterator;

	/** The slider image. */
	String sliderImage = "sliderImage";

	/** The overlay text1. */
	String description = "overlayText1";

	/** The overlay text2. */
	String category = "overlayText2";

	String imageUrl = "overlayText2";

	/** The title. */
	String title = "title";

	/** The image path. */
	String imagePath = "/etc/designs/launchpad/clientlibs-carousel/images/camera-next.gif";

	String imagesPath = "/etc/designs/launchpad/clientlibs-carousel/images";

	/** The text1. */
	String text1 = "hello";

	/** The text2. */
	String text2 = "everyone";

	/** The title1. */
	String title1 = "headtitle";

	/** The img prop. */
	Property imgProp = null;

	/** The text1 prop. */
	Property text1Prop = null;

	/** The text2 prop. */
	Property text2Prop = null;

	/** The title prop. */
	Property titleProp = null;

	/** The img value. */
	Value imgValue = null;

	/** The text1 value. */
	Value text1Value = null;

	/** The text2 value. */
	Value text2Value = null;

	/** The title value. */
	Value titleValue = null;

	Property property = null;

	Property property1 = null;

	Property property2 = null;

	Property property3 = null;

	Property property4 = null;

	ResourceResolver resourceResolver = null;

	Resource resource = null;

	Value value = null;

	/**
	 * Setup method.
	 * 
	 * @throws Exception
	 *             - exception
	 */
	@Before
	public void setUp() throws Exception {
		bannerNode = Mockito.mock(Node.class);
		node1 = Mockito.mock(Node.class);
		nodeIterator = Mockito.mock(NodeIterator.class);
		nodeIterator1 = Mockito.mock(NodeIterator.class);

		propertyIterator = Mockito.mock(PropertyIterator.class);
		imgProp = Mockito.mock(Property.class);
		text1Prop = Mockito.mock(Property.class);
		text2Prop = Mockito.mock(Property.class);
		titleProp = Mockito.mock(Property.class);
		imgValue = Mockito.mock(Value.class);
		text1Value = Mockito.mock(Value.class);
		text2Value = Mockito.mock(Value.class);
		titleValue = Mockito.mock(Value.class);
		property = Mockito.mock(Property.class);
		property1 = Mockito.mock(Property.class);
		property2 = Mockito.mock(Property.class);
		property3 = Mockito.mock(Property.class);
		property4 = Mockito.mock(Property.class);
		logger = Mockito.mock(Logger.class);
		resourceResolver = Mockito.mock(ResourceResolver.class);
		resource = Mockito.mock(Resource.class);
		value = Mockito.mock(Value.class);
		carouselAction = Whitebox.newInstance(CarouselAction.class);
		PowerMockito.stub(PowerMockito.method(CarouselAction.class, "getCurrentNode")).toReturn(bannerNode);
		PowerMockito.stub(PowerMockito.method(FrameworkLogger.class, "getLogger")).toReturn(logger);
		Whitebox.setInternalState(carouselAction, logger);

	}

	/**
	 * Tear down method.
	 * 
	 * @throws Exception
	 *             - exception
	 */
	@After
	public void tearDown() throws Exception {
		carouselAction = null;
	}

	/**
	 * Test news action.
	 */
	@Test
	public void testRotatingBannerActionSimple() {

		CarouselBean carouselBean = new CarouselBean();
		List<BannerBean> listOfimages = new ArrayList<BannerBean>();

		BannerBean bannerBean = new BannerBean();
		bannerBean.setCategory("science");
		bannerBean.setDescription("description");
		bannerBean.setImageUrl(imagePath);
		bannerBean.setSliderImage(imagePath);
		bannerBean.setTitle("title");

		BannerBean bannerBean1 = new BannerBean();
		bannerBean1.setCategory("science");
		bannerBean1.setDescription("description");
		bannerBean1.setImageUrl(imagesPath);
		bannerBean1.setSliderImage(imagePath);
		bannerBean1.setTitle("title");

		listOfimages.add(bannerBean);
		listOfimages.add(bannerBean1);
		carouselBean.setBannersList(listOfimages);
		carouselBean.setMultiple(true);
		carouselBean.setTimeInterval(2);
		carouselBean.setTimeUnits("seconds");

		try {
			Mockito.when(bannerNode.hasProperty(Constants.CAROUSEL.SELECTED_TAB)).thenReturn(true);
			Mockito.when(bannerNode.getProperty(Constants.CAROUSEL.SELECTED_TAB)).thenReturn(property);
			Mockito.when(property.getString()).thenReturn(Constants.CAROUSEL.CAROUSEL_NODE);

			PowerMockito.stub(PowerMockito.method(CarouselAction.class, "fetchDetailsSimple", Node.class)).toReturn(
					carouselBean);

			carouselBean = carouselAction.getCarouselInfo();
			assertEquals("imagepath", imagePath, carouselBean.getBannersList().get(0).getSliderImage());
			assertEquals("imageUrl", imagePath, carouselBean.getBannersList().get(0).getImageUrl());
			assertEquals("category", "science", carouselBean.getBannersList().get(0).getCategory());
			assertEquals("title", "title", carouselBean.getBannersList().get(0).getTitle());
			assertEquals("description", "description", carouselBean.getBannersList().get(0).getDescription());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test news action1.
	 */
	@Test
	public void testRotatingBannerActionAdvanced() {
		CarouselBean carouselBean1 = new CarouselBean();
		List<BannerBean> listOfimages1 = new ArrayList<BannerBean>();

		// listOfimages.add

		BannerBean bannerBean2 = new BannerBean();
		bannerBean2.setSliderImage(imagePath);

		BannerBean bannerBean3 = new BannerBean();
		bannerBean3.setSliderImage(imagePath);

		listOfimages1.add(bannerBean2);
		listOfimages1.add(bannerBean3);
		carouselBean1.setBannersList(listOfimages1);
		carouselBean1.setMultiple(false);
		carouselBean1.setTimeInterval(2);
		carouselBean1.setTimeUnits("minutes");

		try {

			Mockito.when(bannerNode.hasProperty(Constants.CAROUSEL.SELECTED_TAB)).thenReturn(true);
			Mockito.when(bannerNode.getProperty(Constants.CAROUSEL.SELECTED_TAB)).thenReturn(property);
			Mockito.when(property.getString()).thenReturn(Constants.CAROUSEL.IMAGES);

			PowerMockito.stub(PowerMockito.method(CarouselAction.class, "fetchDetailsAdvanced", Node.class)).toReturn(
					carouselBean1);

			carouselBean1 = carouselAction.getCarouselInfo();
			assertEquals("imagepath", imagePath, carouselBean1.getBannersList().get(0).getSliderImage());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test news action2.
	 */

	@Test
	public void testFetchDetailsSimple() {
		// CarouselBean carouselBean = new CarouselBean();

		// listOfimages.add

		BannerBean bannerBean = new BannerBean();
		bannerBean.setCategory("science");
		bannerBean.setDescription("description");
		bannerBean.setImageUrl(imagePath);
		bannerBean.setSliderImage(imagePath);
		bannerBean.setTitle("title");

		// listOfimages.add(bannerBean);

		// carouselBean.setBannersList(listOfimages);

		try {

			Mockito.when(bannerNode.getNodes()).thenReturn(nodeIterator);
			Mockito.when(nodeIterator.hasNext()).thenReturn(true, true, false);
			Mockito.when(nodeIterator.next()).thenReturn(node);
			Mockito.when(node.getNodes()).thenReturn(nodeIterator1);
			Mockito.when(nodeIterator1.hasNext()).thenReturn(true, false);
			Mockito.when(nodeIterator1.next()).thenReturn(node1);
			Mockito.when(node1.getProperties()).thenReturn(propertyIterator);

			PowerMockito.stub(PowerMockito.method(CarouselAction.class, "setPropertiesSimple", PropertyIterator.class))
					.toReturn(bannerBean);

			CarouselBean carouselBean = Whitebox.invokeMethod(carouselAction, "fetchDetailsSimple", bannerNode);
			assertEquals("imagepath", imagePath, carouselBean.getBannersList().get(0).getSliderImage());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFetchDetailsAdvanced() {
		// CarouselBean carouselBean = new CarouselBean();

		// listOfimages.add

		BannerBean bannerBean = new BannerBean();
		bannerBean.setCategory("science");
		bannerBean.setDescription("description");
		bannerBean.setImageUrl(imagePath);
		bannerBean.setSliderImage(imagePath);
		bannerBean.setTitle("title");

		// listOfimages.add(bannerBean);

		// carouselBean.setBannersList(listOfimages);

		try {
			// Mockito.when(bannerNode.getProperty("selectedTabRadio"))
			// .thenReturn(property);

			// Mockito.when(bannerNode.getNodes()).thenReturn(nodeIterator);
			// Mockito.when(nodeIterator.hasNext()).thenReturn(true);

			Mockito.when(property.getString()).thenReturn("banners");
			Mockito.when(bannerNode.getProperty("timeInterval")).thenReturn(property1);
			Mockito.when(property1.getString()).thenReturn("10");
			Mockito.when(bannerNode.getProperty("timeUnit")).thenReturn(property2);
			Mockito.when(property2.getString()).thenReturn("seconds");

			Calendar date = Calendar.getInstance();
			PowerMockito.stub(PowerMockito.method(HeroBannerAction.class, "getSlingRequest")).toReturn(
					getSlingHttpServletRequest());
			Mockito.when(bannerNode.hasProperty(Constants.CAROUSEL.IMAGES)).thenReturn(true);
			Mockito.when(bannerNode.getProperty(Constants.CAROUSEL.IMAGES)).thenReturn(property);
			Mockito.when(property.getString()).thenReturn(imagesPath);
			Mockito.when(getSlingHttpServletRequest().getResourceResolver()).thenReturn(resourceResolver);
			Mockito.when(resourceResolver.getResource(imagesPath)).thenReturn(resource);
			Mockito.when(resource.adaptTo(Node.class)).thenReturn(node1);
			Mockito.when(node1.getNodes()).thenReturn(nodeIterator1);
			Mockito.when(nodeIterator1.hasNext()).thenReturn(true, false);
			Mockito.when(nodeIterator1.next()).thenReturn(node);
			Mockito.when(node.getName()).thenReturn("image1.jpg");
			Mockito.when(bannerNode.hasProperty(Constants.CAROUSEL.DATE)).thenReturn(true);
			Mockito.when(bannerNode.getProperty(Constants.CAROUSEL.DATE)).thenReturn(property1);
			Mockito.when(property1.getDate()).thenReturn(date);
			Mockito.when(bannerNode.hasProperty(Constants.CAROUSEL.TIME_INTERVAL)).thenReturn(true);
			Mockito.when(bannerNode.getProperty(Constants.CAROUSEL.TIME_INTERVAL)).thenReturn(property2);
			Mockito.when(property2.getString()).thenReturn("2");
			Mockito.when(bannerNode.hasProperty(Constants.CAROUSEL.TIME_UNIT)).thenReturn(true);
			Mockito.when(bannerNode.getProperty(Constants.CAROUSEL.TIME_UNIT)).thenReturn(property3);
			Mockito.when(property3.getString()).thenReturn("days");
			Mockito.when(bannerNode.hasProperty(Constants.CAROUSEL.LOT)).thenReturn(true);
			Mockito.when(bannerNode.getProperty(Constants.CAROUSEL.LOT)).thenReturn(property4);
			Mockito.when(property4.getString()).thenReturn("3");

			CarouselBean carouselBean = Whitebox.invokeMethod(carouselAction, "fetchDetailsAdvanced", bannerNode);
			assertEquals("imagespath", imagesPath + "/" + "image1.jpg", carouselBean.getBannersList().get(0)
					.getSliderImage());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void setPropertiesSimpleImg() {
		// CarouselBean carouselBean = new CarouselBean();

		// listOfimages.add

		BannerBean bannerBean = new BannerBean();
		bannerBean.setCategory("science");
		bannerBean.setDescription("description");
		bannerBean.setImageUrl(imagePath);
		bannerBean.setSliderImage(imagePath);
		bannerBean.setTitle("title");

		// listOfimages.add(bannerBean);

		// carouselBean.setBannersList(listOfimages);

		try {
			// Mockito.when(bannerNode.getProperty("selectedTabRadio"))
			// .thenReturn(property);

			// Mockito.when(bannerNode.getNodes()).thenReturn(nodeIterator);
			// Mockito.when(nodeIterator.hasNext()).thenReturn(true);

			Mockito.when(propertyIterator.hasNext()).thenReturn(true, false);
			Mockito.when(propertyIterator.next()).thenReturn(property);
			Mockito.when(property.getName()).thenReturn(Constants.CAROUSEL.SLIDER_IMAGE);
			Mockito.when(property.getValue()).thenReturn(value);
			Mockito.when(value.getString()).thenReturn(imagePath);
			bannerBean = Whitebox.invokeMethod(carouselAction, "setPropertiesSimple", propertyIterator);

			assertEquals("imagepath", imagePath, bannerBean.getSliderImage());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void setPropertiesSimpleTitle() {
		// CarouselBean carouselBean = new CarouselBean();

		// listOfimages.add

		BannerBean bannerBean = new BannerBean();
		bannerBean.setCategory("science");
		bannerBean.setDescription("description");
		bannerBean.setImageUrl(imagePath);
		bannerBean.setSliderImage(imagePath);
		bannerBean.setTitle("title");

		// listOfimages.add(bannerBean);

		// carouselBean.setBannersList(listOfimages);

		try {
			// Mockito.when(bannerNode.getProperty("selectedTabRadio"))
			// .thenReturn(property);

			// Mockito.when(bannerNode.getNodes()).thenReturn(nodeIterator);
			// Mockito.when(nodeIterator.hasNext()).thenReturn(true);

			Mockito.when(propertyIterator.hasNext()).thenReturn(true, false);
			Mockito.when(propertyIterator.next()).thenReturn(property);
			Mockito.when(property.getName()).thenReturn(Constants.CAROUSEL.TITLE);
			Mockito.when(property.getValue()).thenReturn(value);
			Mockito.when(value.getString()).thenReturn(title);
			bannerBean = Whitebox.invokeMethod(carouselAction, "setPropertiesSimple", propertyIterator);

			assertEquals("title", title, bannerBean.getTitle());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void setPropertiesSimpleDescription() {
		// CarouselBean carouselBean = new CarouselBean();

		// listOfimages.add

		BannerBean bannerBean = new BannerBean();
		bannerBean.setCategory("science");
		bannerBean.setDescription("description");
		bannerBean.setImageUrl(imagePath);
		bannerBean.setSliderImage(imagePath);
		bannerBean.setTitle("title");

		// listOfimages.add(bannerBean);

		// carouselBean.setBannersList(listOfimages);

		try {
			// Mockito.when(bannerNode.getProperty("selectedTabRadio"))
			// .thenReturn(property);

			// Mockito.when(bannerNode.getNodes()).thenReturn(nodeIterator);
			// Mockito.when(nodeIterator.hasNext()).thenReturn(true);

			Mockito.when(propertyIterator.hasNext()).thenReturn(true, false);
			Mockito.when(propertyIterator.next()).thenReturn(property);
			Mockito.when(property.getName()).thenReturn(Constants.CAROUSEL.DESCRIPTION);
			Mockito.when(property.getValue()).thenReturn(value);
			Mockito.when(value.getString()).thenReturn(description);
			bannerBean = Whitebox.invokeMethod(carouselAction, "setPropertiesSimple", propertyIterator);

			assertEquals("description", description, bannerBean.getDescription());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void setPropertiesSimpleCategory() {
		// CarouselBean carouselBean = new CarouselBean();

		// listOfimages.add

		BannerBean bannerBean = new BannerBean();
		bannerBean.setCategory("science");
		bannerBean.setDescription("description");
		bannerBean.setImageUrl(imagePath);
		bannerBean.setSliderImage(imagePath);
		bannerBean.setTitle("title");

		// listOfimages.add(bannerBean);

		// carouselBean.setBannersList(listOfimages);

		try {
			// Mockito.when(bannerNode.getProperty("selectedTabRadio"))
			// .thenReturn(property);

			// Mockito.when(bannerNode.getNodes()).thenReturn(nodeIterator);
			// Mockito.when(nodeIterator.hasNext()).thenReturn(true);

			Mockito.when(propertyIterator.hasNext()).thenReturn(true, false);
			Mockito.when(propertyIterator.next()).thenReturn(property);
			Mockito.when(property.getName()).thenReturn(Constants.CAROUSEL.CATEGORY);
			Mockito.when(property.getValue()).thenReturn(value);
			Mockito.when(value.getString()).thenReturn(category);
			bannerBean = Whitebox.invokeMethod(carouselAction, "setPropertiesSimple", propertyIterator);

			assertEquals("category", category, bannerBean.getCategory());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void setPropertiesSimpleImageUrl() {
		// CarouselBean carouselBean = new CarouselBean();

		// listOfimages.add

		BannerBean bannerBean = new BannerBean();
		bannerBean.setCategory("science");
		bannerBean.setDescription("description");
		bannerBean.setImageUrl(imagePath);
		bannerBean.setSliderImage(imagePath);
		bannerBean.setTitle("title");

		// listOfimages.add(bannerBean);

		// carouselBean.setBannersList(listOfimages);

		try {
			// Mockito.when(bannerNode.getProperty("selectedTabRadio"))
			// .thenReturn(property);

			// Mockito.when(bannerNode.getNodes()).thenReturn(nodeIterator);
			// Mockito.when(nodeIterator.hasNext()).thenReturn(true);

			Mockito.when(propertyIterator.hasNext()).thenReturn(true, false);
			Mockito.when(propertyIterator.next()).thenReturn(property);
			Mockito.when(property.getName()).thenReturn(Constants.CAROUSEL.IMAGE_URL);
			Mockito.when(property.getValue()).thenReturn(value);
			Mockito.when(value.getString()).thenReturn(imageUrl);
			bannerBean = Whitebox.invokeMethod(carouselAction, "setPropertiesSimple", propertyIterator);

			assertEquals("imageUrl", imageUrl+".html", bannerBean.getImageUrl());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	@Test
	public void getCarouselInfoIf() {
		// CarouselBean carouselBean = new CarouselBean();

		// listOfimages.add

		BannerBean bannerBean = new BannerBean();
		bannerBean.setCategory("science");
		bannerBean.setDescription("description");
		bannerBean.setImageUrl(imagePath);
		bannerBean.setSliderImage(imagePath);
		bannerBean.setTitle("title");

		// listOfimages.add(bannerBean);

		// carouselBean.setBannersList(listOfimages);

		try {
			// Mockito.when(bannerNode.getProperty("selectedTabRadio"))
			// .thenReturn(property);

			// Mockito.when(bannerNode.getNodes()).thenReturn(nodeIterator);
			// Mockito.when(nodeIterator.hasNext()).thenReturn(true);

			Mockito.when(bannerNode.hasProperty(Constants.CAROUSEL.SELECTED_TAB)).thenReturn(false);
			Mockito.when(bannerNode.getProperty(Constants.CAROUSEL.SELECTED_TAB)).thenReturn(null);
			
			CarouselBean carouselBean = carouselAction.getCarouselInfo();

			//assertEquals("imageUrl", imageUrl, bannerBean.getImageUrl());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
