/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.Value;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
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

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.tc.action.BaseTest;
import com.tc.action.HeroBannerAction;
import com.tc.model.HeroBannerBean;
import com.tc.model.HeroBean;
import com.tc.util.Constants;
import com.tc.util.SchedulerUtil;

/**
 * The Class HeroBannerActionTest.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({HeroBannerAction.class,SchedulerUtil.class})
public class HeroBannerActionTest extends BaseTest  {	
	
	private Logger log;
	/** The hero banner action. */
	private HeroBannerAction heroBannerAction = null;
	
	/** The asset path. */
	String assetPath = "/content/launchpad/test";
	
	/** The desc string. */
	String descString = "description";
	
	/** The banner image string. */
	String bannerImageString = "/content/dam/launchpad/Images/gallery-2.jpg";
	
	/** The title string. */
	String titleString = "title";
	
	/** The is share string. */
	Boolean isShareString = true;
	
	/** The mode string. */
	String modeString = "author";
	
	/** The video flag string. */
	String videoFlagString = "on";
	
	/** The asset format string. */
	String assetFormat = "image/video";
	
	/** The video path string. */
	String videoPathString = "/content/dam/launchpad/Videos/Slap.mp4";	

	/** The thumb nail string. */
	String thumbNailString = "thumbnail.jpg";
	
	/** The button text. */
	String buttonText ="buttonText";
	
	/** The rotation rate. */
	int rotationRate  = 1000;
	
	/** The description. */
	final String description = "video banner description";
	
	/** The title. */
	final String title = "video banner title";	
	
	/** The renditionPath. */
	final String renditionPath = "/content/dam/launchpad/Videos/MVI_0216.mp4";
	
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		try{
			log = Mockito.mock(Logger.class);
			heroBannerAction = Whitebox.newInstance(HeroBannerAction.class);
			Whitebox.setInternalState(heroBannerAction, log);
		}catch(Exception exception){
			log.error(exception.getMessage(), exception);
		}
	}
	
	/**
	 * Tear down.
	 *
	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
		heroBannerAction = null;
	}
	
	/**
	 * Test hero banner.
	 */
	@Test
	public void testHeroBannerAdvanced(){
		log.info("entering into the testHeroBanner advanced method of the HeroBannerAction ");
		
		Node nod = Mockito.mock(Node.class);	
		Property selectedTabProperty = Mockito.mock(Property.class);		
		Value selectedValue = Mockito.mock(Value.class);		
		
		List<HeroBean> heroBeanList = new ArrayList<HeroBean>();	
		HeroBean heroBean = new HeroBean();		
		heroBean.setAssetPath(assetPath);
		heroBean.setBannerDescription(descString);
		heroBean.setBannerImage(bannerImageString);
		heroBean.setBannerTitle(titleString);	
		heroBean.setVideoFlag(videoFlagString);
		heroBean.setAssetFormat(assetFormat);
		heroBean.setVideoPath(videoPathString);		
		heroBeanList.add(heroBean);

		try{
			PowerMockito.stub(PowerMockito.method(HeroBannerAction.class, "getSlingRequest")).toReturn(getSlingHttpServletRequest());
			PowerMockito.stub(PowerMockito.method(HeroBannerAction.class, "getCurrentNode")).toReturn(nod);  
			Mockito.when(nod.hasProperty(Constants.HERO.HERO_SELECTED_TAB_RADIO)).thenReturn(true);
			Mockito.when(nod.getProperty(Constants.HERO.HERO_SELECTED_TAB_RADIO)).thenReturn(selectedTabProperty);
			Mockito.when(selectedTabProperty.getValue()).thenReturn(selectedValue);		
			
			Mockito.when(selectedValue.getString()).thenReturn("videos");			
			PowerMockito.stub(PowerMockito.method(HeroBannerAction.class,"getVideoDetails", Node.class)).toReturn(heroBeanList);
			PowerMockito.stub(PowerMockito.method(HeroBannerAction.class,"getSchedulerList", Node.class,ArrayList.class)).toReturn(heroBeanList);
			
			HeroBannerBean returningBannerBean = heroBannerAction.heroBanner();
			
			assertEquals("asset path is::",assetPath,returningBannerBean.getBannersList().get(0).getAssetPath());
			assertEquals("banner description is::",descString,returningBannerBean.getBannersList().get(0).getBannerDescription());
			assertEquals("bannaer image is::",bannerImageString,returningBannerBean.getBannersList().get(0).getBannerImage());
			assertEquals("bannaer title is::",titleString,returningBannerBean.getBannersList().get(0).getBannerTitle());
			assertEquals("video flag is::",videoFlagString,returningBannerBean.getBannersList().get(0).getVideoFlag());
			assertEquals("video path is::",videoPathString,returningBannerBean.getBannersList().get(0).getVideoPath());
			
			log.info("exiting from testHeroBanner advanced method of the HeroBannerAction ");
		}catch(Exception e){
			fail("exception occured in the testHeroBannerAdvanced method:");
		}
	}
	
	/**
	 * Test hero banner.
	 */
	@Test
	public void testHeroBannerAdvancedElse(){
		log.info("entering into the testHeroBanner advanced method of the HeroBannerAction ");		
		Node nod = Mockito.mock(Node.class);	
		Property selectedTabProperty = Mockito.mock(Property.class);			
		Value selectedValue = Mockito.mock(Value.class);
		
		List<HeroBean> heroBeanList = new ArrayList<HeroBean>();		
		HeroBean heroBean = new HeroBean();		
		heroBean.setVideoPath(videoPathString);		
		heroBeanList.add(heroBean);		
		HeroBean heroBean1 = new HeroBean();		
		heroBean1.setVideoPath(videoPathString);		
		heroBeanList.add(heroBean1);
		HeroBean heroBean2 = new HeroBean();		
		heroBean2.setVideoPath(videoPathString);		
		heroBeanList.add(heroBean2);
		List<HeroBean> newList = new ArrayList<HeroBean>();	
		HeroBean newBean = new HeroBean();		
		newBean.setVideoPath(videoPathString);		
		newList.add(newBean);		
		HeroBean newBean1 = new HeroBean();		
		newBean1.setVideoPath(videoPathString);		
		newList.add(newBean1);
		
		
		try{
			PowerMockito.stub(PowerMockito.method(HeroBannerAction.class, "getSlingRequest")).toReturn(getSlingHttpServletRequest());
			PowerMockito.stub(PowerMockito.method(HeroBannerAction.class, "getCurrentNode")).toReturn(nod);  
			Mockito.when(nod.hasProperty(Constants.HERO.HERO_SELECTED_TAB_RADIO)).thenReturn(true);
			Mockito.when(nod.getProperty(Constants.HERO.HERO_SELECTED_TAB_RADIO)).thenReturn(selectedTabProperty);
			Mockito.when(selectedTabProperty.getValue()).thenReturn(selectedValue);		
			Mockito.when(selectedValue.getString()).thenReturn("videos");
			PowerMockito.stub(PowerMockito.method(HeroBannerAction.class,"getVideoDetails", Node.class)).toReturn(heroBeanList);
			PowerMockito.stub(PowerMockito.method(HeroBannerAction.class,"getSchedulerList", Node.class,ArrayList.class)).toReturn(newList);
			
			HeroBannerBean returningBannerBean = heroBannerAction.heroBanner();				
			assertEquals("set Multiplee is::",true,returningBannerBean.isMultiple());
			
			log.info("exiting from testHeroBanner advanced method of the HeroBannerAction ");
		}catch(Exception e){
			fail("exception occured in the testHeroBannerAdvancedElse method:");
		}
	}
	
	/**
	 * Test hero banner.
	 */
	@Test
	public void testHeroBanner(){
		log.info("entering into the testHeroBanner advanced method of the HeroBannerAction ");
		
		Node nod = Mockito.mock(Node.class);
		
		List<HeroBean> heroBeanList = new ArrayList<HeroBean>();	
		HeroBean heroBean = new HeroBean();		
		heroBean.setAssetPath(assetPath);
		heroBean.setBannerDescription(descString);
		heroBean.setBannerImage(bannerImageString);
		heroBean.setBannerTitle(titleString);	
		heroBean.setVideoFlag(videoFlagString);
		heroBean.setAssetFormat(assetFormat);
		heroBean.setVideoPath(videoPathString);		
		heroBeanList.add(heroBean);
		try{
			PowerMockito.stub(PowerMockito.method(HeroBannerAction.class, "getSlingRequest")).toReturn(getSlingHttpServletRequest());
			PowerMockito.stub(PowerMockito.method(HeroBannerAction.class, "getCurrentNode")).toReturn(nod);  
			Mockito.when(nod.hasProperty(Constants.HERO.HERO_SELECTED_TAB_RADIO)).thenReturn(false);								
		}catch(Exception e){
			fail("exception occured in the testHeroBanner method:");
		}
	}	
	
	/**
	 * Test hero banner.
	 */
	@Test
	public void testHeroBannerGeneral(){
		log.info("entering into the testHeroBanner general method of the HeroBannerAction ");
		
		Node nod = Mockito.mock(Node.class);	
		Property selectedTabProperty = Mockito.mock(Property.class);	
		Value selectedValue = Mockito.mock(Value.class);		
		NodeIterator nodeit = Mockito.mock(NodeIterator.class);
		Node innerNode = Mockito.mock(Node.class);
		
		List<HeroBean> heroBeanList = new ArrayList<HeroBean>();	
		HeroBean heroBean = new HeroBean();
		heroBean.setAssetPath(assetPath);
		heroBean.setBannerImage(bannerImageString);		
		heroBean.setBannerTitle(titleString);		
		heroBean.setVideoFlag(videoFlagString);
		heroBean.setAssetFormat(assetFormat);
		heroBean.setVideoPath(videoPathString);		
		heroBeanList.add(heroBean);
		try{
			PowerMockito.stub(PowerMockito.method(HeroBannerAction.class, "getSlingRequest")).toReturn(getSlingHttpServletRequest());
			PowerMockito.stub(PowerMockito.method(HeroBannerAction.class, "getCurrentNode")).toReturn(nod);  
			Mockito.when(nod.hasProperty(Constants.HERO.HERO_SELECTED_TAB_RADIO)).thenReturn(true);
			Mockito.when(nod.getProperty(Constants.HERO.HERO_SELECTED_TAB_RADIO)).thenReturn(selectedTabProperty);
			Mockito.when(selectedTabProperty.getValue()).thenReturn(selectedValue);		
			Mockito.when(selectedValue.getString()).thenReturn("banners");
			Mockito.when(nod.getNodes()).thenReturn(nodeit);
			Mockito.when(nodeit.hasNext()).thenReturn(true,false);
			Mockito.when((Node) nodeit.next()).thenReturn(innerNode);
			Mockito.when(innerNode.getName()).thenReturn(Constants.HERO.HERO_BANNERS_NODE);
			PowerMockito.stub(PowerMockito.method(HeroBannerAction.class,"getNodeProperties", Node.class)).toReturn(heroBeanList);
		
			HeroBannerBean generalBean = heroBannerAction.heroBanner();			
			assertEquals("asset path is::",assetPath,generalBean.getBannersList().get(0).getAssetPath());		
			assertEquals("bannaer image is::",bannerImageString,generalBean.getBannersList().get(0).getBannerImage());
			assertEquals("bannaer title is::",titleString,generalBean.getBannersList().get(0).getBannerTitle());						
			assertEquals("video flag is::",videoFlagString,generalBean.getBannersList().get(0).getVideoFlag());
			assertEquals("video path is::",videoPathString,generalBean.getBannersList().get(0).getVideoPath());
			
			log.info("exiting from testHeroBanner general method of the HeroBannerAction ");
		}catch(Exception e){
			fail("exception occured in the testHeroBannerGeneral method:");
		}
	}
	

/**
	 * Test hero banner.
	 */
	@Test
	public void testHeroBannerGeneralElse(){
		log.info("entering into the testHeroBanner general method of the HeroBannerAction ");		
		Node nod = Mockito.mock(Node.class);	
		Property selectedTabProperty = Mockito.mock(Property.class);	
		Value selectedValue = Mockito.mock(Value.class);		
		NodeIterator nodeit = Mockito.mock(NodeIterator.class);
		Node innerNode = Mockito.mock(Node.class);		
		List<HeroBean> heroBeanList = new ArrayList<HeroBean>();	
		try{
			PowerMockito.stub(PowerMockito.method(HeroBannerAction.class, "getSlingRequest")).toReturn(getSlingHttpServletRequest());
			PowerMockito.stub(PowerMockito.method(HeroBannerAction.class, "getCurrentNode")).toReturn(nod);  
			Mockito.when(nod.hasProperty(Constants.HERO.HERO_SELECTED_TAB_RADIO)).thenReturn(true);
			Mockito.when(nod.getProperty(Constants.HERO.HERO_SELECTED_TAB_RADIO)).thenReturn(selectedTabProperty);
			Mockito.when(selectedTabProperty.getValue()).thenReturn(selectedValue);		
			Mockito.when(selectedValue.getString()).thenReturn("banners");
			Mockito.when(nod.getNodes()).thenReturn(nodeit);
			Mockito.when(nodeit.hasNext()).thenReturn(true,false);
			Mockito.when((Node) nodeit.next()).thenReturn(innerNode);
			Mockito.when(innerNode.getName()).thenReturn(Constants.HERO.HERO_BANNERS_NODE);
			PowerMockito.stub(PowerMockito.method(HeroBannerAction.class,"getNodeProperties", Node.class)).toReturn(heroBeanList);
			HeroBannerBean generalBean = heroBannerAction.heroBanner();	
		}catch(Exception e){
			fail("exception occured in the testHeroBannerGeneral method:");
		}
	}
	
	
	
	/**
	 * Test get video node details.
	 */
	@Test
	public void testGetVideoDetails(){
		
		final String videoPath = "/content/dam/launchpad/Videos/MVI_0216.mp4";
		String primaryType="dam:Asset";
		String imagePath = "content/dam/launchpad/videos/samplevideo/jcr:content/renditions/original";
		String description = "description";
		String title = "title";		
		
		Property videoPathProperty = Mockito.mock(Property.class);
		Property primaryTypeProperty= Mockito.mock(Property.class);
		Value videoPathValue = Mockito.mock(Value.class);
		Value primaryTypeValue = Mockito.mock(Value.class);
		ResourceResolver resourceResolver = Mockito.mock(ResourceResolver.class);
		Resource resource = Mockito.mock(Resource.class);		
		Node videoItemsNode = Mockito.mock(Node.class);
		Node resourceNode = Mockito.mock(Node.class);
		NodeIterator videoNodeIterator = Mockito.mock(NodeIterator.class);
		Node videoNode = Mockito.mock(Node.class);	
		try {
			PowerMockito.stub(PowerMockito.method(HeroBannerAction.class, "getSlingRequest")).toReturn(getSlingHttpServletRequest());
			Mockito.when(getSlingHttpServletRequest().getResourceResolver()).thenReturn(resourceResolver);
			
			Mockito.when(videoItemsNode.hasProperty(Constants.HERO.HERO_VIDEOS_NODE)).thenReturn(true);
			Mockito.when(videoItemsNode.getProperty(Constants.HERO.HERO_VIDEOS_NODE)).thenReturn(videoPathProperty);
			Mockito.when(videoPathProperty.getValue()).thenReturn(videoPathValue);		
			Mockito.when(videoPathValue.getString()).thenReturn(videoPath);
			Mockito.when(resourceResolver.getResource(videoPath)).thenReturn(resource);
			Mockito.when(resource.adaptTo(Node.class)).thenReturn(resourceNode);
			Mockito.when(resourceNode.hasNodes()).thenReturn(true,true,false);
			Mockito.when(resourceNode.getNodes()).thenReturn(videoNodeIterator);
			Mockito.when(videoNodeIterator.hasNext()).thenReturn(true,false);
			Mockito.when(videoNodeIterator.next()).thenReturn(videoNode);
			Mockito.when(videoNode.getName()).thenReturn("item_node_0");
			Mockito.when(videoNode.hasProperty(Constants.HERO.HERO_PRIMARY_TYPE_PROPERTY)).thenReturn(true);
			Mockito.when(videoNode.getProperty(Constants.HERO.HERO_PRIMARY_TYPE_PROPERTY)).thenReturn(primaryTypeProperty);
			Mockito.when(primaryTypeProperty.getValue()).thenReturn(primaryTypeValue);
			Mockito.when(primaryTypeValue.getString()).thenReturn(primaryType);
		
			HeroBean heroBean = new HeroBean();
			heroBean.setBannerDescription(description);
			heroBean.setBannerTitle(title);		
			heroBean.setBannerImage(imagePath);
			PowerMockito.stub(PowerMockito.method(HeroBannerAction.class,"getVideoHeroBean",HeroBean.class,String.class,String.class,ResourceResolver.class)).toReturn(heroBean);
			List<HeroBean> heroBannerList  = Whitebox.invokeMethod(heroBannerAction, "getVideoDetails",videoItemsNode);
			assertEquals("banners list size is::",1,heroBannerList.size());
		} catch (Exception e) {
			fail("fail occured in testGetVideoDetails method of heroBannerAction :");
		}
	}
	
	/**
	 * Test get video node details.
	 */
	@Test
	public void testGetVideoDetailsElse(){		
		ResourceResolver resourceResolver = Mockito.mock(ResourceResolver.class);		
		Node videoItemsNode = Mockito.mock(Node.class);	
		try {
			PowerMockito.stub(PowerMockito.method(HeroBannerAction.class, "getSlingRequest")).toReturn(getSlingHttpServletRequest());
			Mockito.when(getSlingHttpServletRequest().getResourceResolver()).thenReturn(resourceResolver);
			Mockito.when(videoItemsNode.hasProperty(Constants.HERO.HERO_VIDEOS_NODE)).thenReturn(false);
		} catch (Exception e) {
			fail("fail occured in testGetVideoDetailsElse method of heroBannerAction :");
		}
	}
	
	/**
	 * Test get video video hero bean.
	 */
	
	@Test
	public void testGetVideoHeroBean(){
		ResourceResolver resourceResolver = Mockito.mock(ResourceResolver.class);
		Resource renditionsResource = Mockito.mock(Resource.class);
		Resource metaDataResource = Mockito.mock(Resource.class);
		
		String videoPath ="/content/dam/";
		String nodeName ="item1";
		String renditoinPath ="/content/dam/videos";
		String metaDataPath = "/content/dam/launchpad/videos/samplevideo/jcr:content/metadata";
		String imagePath = "content/dam/launchpad/videos/samplevideo/jcr:content/renditions/original";		
		try {
		Mockito.when(resourceResolver.getResource(renditoinPath)).thenReturn(renditionsResource);
		Mockito.when(resourceResolver.getResource(metaDataPath)).thenReturn(metaDataResource);
		HeroBean heroBean = new HeroBean();
		heroBean.setBannerDescription(description);
		heroBean.setBannerTitle(title);		
		
		PowerMockito.stub(PowerMockito.method(HeroBannerAction.class, "getMetaData",HeroBean.class,Resource.class,String.class)).toReturn(heroBean);
		heroBean.setBannerImage(imagePath);
		PowerMockito.stub(PowerMockito.method(HeroBannerAction.class, "getRenditions",HeroBean.class,Resource.class,String.class)).toReturn(heroBean);
		
		HeroBean heroBeanVideo  = Whitebox.invokeMethod(heroBannerAction, "getVideoHeroBean",heroBean,videoPath,nodeName,resourceResolver);
		assertEquals("banenr title is::",title,heroBeanVideo.getBannerTitle());
		assertEquals("banner description is::",description,heroBeanVideo.getBannerDescription());
		assertEquals("banner image is::",imagePath,heroBeanVideo.getBannerImage());
		} catch (Exception e) {
			fail("fail occured in testGetVideoHeroBean method of heroBannerAction :");
		}
	}
		
	/**
	 * Test get time difference.
	 */
	
	@Test
	public void testGetTimeDifferenceElse(){		
		Node heronode = Mockito.mock(Node.class);	
		Property dateProperty = Mockito.mock(Property.class);			
		Long timeDifference=null;			
		Calendar date = Calendar.getInstance();
		try{
		Mockito.when(heronode.hasProperty(Constants.HERO.HERO_DATE_PROPERTY)).thenReturn(true);
		Mockito.when(heronode.hasProperty(Constants.HERO.HERO_MODIFY_DATE_PROPERTY)).thenReturn(false);
		Mockito.when(heronode.getProperty(Constants.HERO.HERO_DATE_PROPERTY)).thenReturn(dateProperty);
		Mockito.when(dateProperty.getDate()).thenReturn(date);
		timeDifference = date.getTimeInMillis() - date.getTimeInMillis();	
		Long timeDiff  = Whitebox.invokeMethod(heroBannerAction, "getTimeDifference",heronode);
		
		assertEquals("time difference is::",timeDiff,timeDiff);
		}catch (Exception e) {
			fail("fail occured in testGetTimeDifferenceElse method of heroBannerAction :");
		}
	}	
	
	@Test
	public void testGetTimeDifferenceIf(){		
		Node heronode = Mockito.mock(Node.class);	
		Property dateProperty = Mockito.mock(Property.class);		
		Long timeDifference=null;		
		Calendar date = Calendar.getInstance();
		try{
		Mockito.when(heronode.hasProperty(Constants.HERO.HERO_DATE_PROPERTY)).thenReturn(false);
		Mockito.when(heronode.hasProperty(Constants.HERO.HERO_MODIFY_DATE_PROPERTY)).thenReturn(true);
		Mockito.when(heronode.getProperty(Constants.HERO.HERO_DATE_PROPERTY)).thenReturn(dateProperty);
		Mockito.when(dateProperty.getDate()).thenReturn(date);
		timeDifference = date.getTimeInMillis() - date.getTimeInMillis();	
		Long timeDiff  = Whitebox.invokeMethod(heroBannerAction, "getTimeDifference",heronode);
		
		assertEquals("time difference is::",timeDiff,timeDiff);
		}catch (Exception e) {
			fail("fail occured in testGetTimeDifferenceIf method of heroBannerAction :");
		}
	}	
	
	@Test
	public void testGetSchedulerList(){
		List<HeroBean> newList = new ArrayList<HeroBean>();		
		Integer index = 1;		
		Node heronode = Mockito.mock(Node.class);	
		Property sizeProperty = Mockito.mock(Property.class);		
		HeroBean heroBean = new HeroBean();
		heroBean.setBannerDescription(description);
		heroBean.setBannerTitle(title);		
		newList.add(heroBean);
		HeroBean heroBean1 = new HeroBean();
		heroBean1.setBannerDescription(description);
		heroBean1.setBannerTitle(title);		
		newList.add(heroBean1);
		Long timeDifference=200L;	
		try{
		Mockito.when(heronode.hasProperty(Constants.HERO.HERO_LIST_SIZE)).thenReturn(true);	
		Mockito.when(heronode.getProperty(Constants.HERO.HERO_LIST_SIZE)).thenReturn(sizeProperty);
		Mockito.when(sizeProperty.getString()).thenReturn("1");		
		
		PowerMockito.stub(PowerMockito.method(HeroBannerAction.class, "getTimeDifference",Node.class)).toReturn(timeDifference);
		PowerMockito.stub(PowerMockito.method(HeroBannerAction.class, "getIndex",Node.class,Long.class,Integer.class,Integer.class,ArrayList.class)).toReturn(index);
		
		List<HeroBean> newList1 = Whitebox.invokeMethod(heroBannerAction, "getSchedulerList",heronode,newList);	
		assertEquals("banner description is::","video banner description",newList1.get(0).getBannerDescription());
		assertEquals("banner title is::","video banner title",newList1.get(0).getBannerTitle());		
		}catch (Exception e) {
			fail("fail occured in testGetSchedulerList method of heroBannerAction :");
		}
	}	
	
	@Test
	public void testGetSchedulerListElse(){
		List<HeroBean> newList = new ArrayList<HeroBean>();			
		Integer index = 1;		
		Node heronode = Mockito.mock(Node.class);	
		Property sizeProperty = Mockito.mock(Property.class);		
		HeroBean heroBean = new HeroBean();
		heroBean.setBannerDescription(description);
		heroBean.setBannerTitle(title);		
		newList.add(heroBean);
		HeroBean heroBean1 = new HeroBean();
		heroBean1.setBannerDescription(description);
		heroBean1.setBannerTitle(title);		
		newList.add(heroBean1);
		HeroBean heroBean2 = new HeroBean();
		heroBean2.setBannerDescription(description);
		heroBean2.setBannerTitle(title);		
		newList.add(heroBean2);		
		Long timeDifference=200L;		
		try{
		Mockito.when(heronode.hasProperty(Constants.HERO.HERO_LIST_SIZE)).thenReturn(true);	
		Mockito.when(heronode.getProperty(Constants.HERO.HERO_LIST_SIZE)).thenReturn(sizeProperty);
		Mockito.when(sizeProperty.getString()).thenReturn("2");		
		
		PowerMockito.stub(PowerMockito.method(HeroBannerAction.class, "getTimeDifference",Node.class)).toReturn(timeDifference);
		PowerMockito.stub(PowerMockito.method(HeroBannerAction.class, "getIndex",Node.class,Long.class,Integer.class,Integer.class,ArrayList.class)).toReturn(index);
		
		List<HeroBean> newList1 = Whitebox.invokeMethod(heroBannerAction, "getSchedulerList",heronode,newList);	
		assertEquals("banner description is::","video banner description",newList1.get(0).getBannerDescription());
		assertEquals("banner title is::","video banner title",newList1.get(0).getBannerTitle());		
		}catch (Exception e) {
			fail("fail occured in testGetSchedulerListElse method of heroBannerAction :");
		}
	}
	
	/**
	 * Test get index.
	 */
	
	@Test
	public void testGetIndex(){
		Node heronode = Mockito.mock(Node.class);	
		Property rotationRateProperty = Mockito.mock(Property.class);	
		Property rotationUnitProperty = Mockito.mock(Property.class);	
		
		List<HeroBean> heroList = new ArrayList<HeroBean>();
		HeroBean heroBean = new HeroBean();
		heroBean.setBannerDescription(description);
		heroBean.setBannerTitle(title);					
		heroList.add(heroBean);	
		
		Long timeDifference=2l;
		Integer listSize=5;
		Integer index=0;			
		try{
		Mockito.when(heronode.hasProperty(Constants.HERO.HERO_ROTATION_RATE_PROPERTY)).thenReturn(true);
		Mockito.when(heronode.getProperty(Constants.HERO.HERO_ROTATION_RATE_PROPERTY)).thenReturn(rotationRateProperty);
		Mockito.when(rotationRateProperty.getString()).thenReturn("2");
		Mockito.when(heronode.hasProperty(Constants.HERO.HERO_ROTATION_UNITS_PROPERTY)).thenReturn(true);
		Mockito.when(heronode.getProperty(Constants.HERO.HERO_ROTATION_UNITS_PROPERTY)).thenReturn(rotationUnitProperty);
		Mockito.when(rotationUnitProperty.getString()).thenReturn("minutes");			
		PowerMockito.mockStatic(SchedulerUtil.class);
		Mockito.when(SchedulerUtil.getIndex((long)10,(long)20,"",1,1)).thenReturn(index);		
		Integer index1=Whitebox.invokeMethod(heroBannerAction, "getIndex",heronode,timeDifference,listSize,index,heroList);
		assertEquals("index is::",index,index1);
		}catch (Exception e) {
			fail("fail occured in testGetIndex method of heroBannerAction :");
		}
	}	
	
	/**
	 * Test get meta data.
	 */
	@Test
	public void testGetMetaData(){		
		Resource metaDataResource = Mockito.mock(Resource.class);
		ValueMap meataDataMap = Mockito.mock(ValueMap.class);
		Mockito.when(metaDataResource.adaptTo(ValueMap.class)).thenReturn(meataDataMap);
		Mockito.when(meataDataMap.get(Constants.HERO.HERO_META_DESCRIPTION,"")).thenReturn(description);
		Mockito.when(meataDataMap.get(Constants.HERO.HERO_META_TITLE,"")).thenReturn(title);
		Mockito.when(meataDataMap.get(Constants.HERO.HERO_META_FORMAT,"")).thenReturn(assetFormat);
		try {
			HeroBean bean = Whitebox.invokeMethod(heroBannerAction, "getMetaData",new HeroBean(),metaDataResource,renditionPath);
			
			assertEquals("video banner description is::",description,bean.getBannerDescription());
			assertEquals("video banner title is::",title,bean.getBannerTitle());
			assertEquals("associated banner video is::",renditionPath,bean.getVideoPath());
			assertEquals("is video flag on?::","on",bean.getVideoFlag());
			assertEquals("format of asset is::",assetFormat,bean.getAssetFormat());		
		} catch (Exception e) {
			fail("fail occured in testGetMetaData method of heroBannerAction :");
		}
	}
		
	
	/**
	 * Test get renditions.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetRenditions(){
		String imagePath = "/content/dam/launchpad/Videos/MVI_0216.mp4/jcr:content/renditions/";	
		HeroBean hero=new HeroBean();
		hero.setAssetFormat("image");
		Resource renditionsResource = Mockito.mock(Resource.class);
		Asset videoAsset = Mockito.mock(Asset.class);
		Rendition rendition = Mockito.mock(Rendition.class);
		List<Rendition> renditions = Mockito.mock(ArrayList.class);
		Iterator<Rendition> iterator = Mockito.mock(Iterator.class);
		
		Mockito.when(renditionsResource.adaptTo(Asset.class)).thenReturn(videoAsset);
		Mockito.when(videoAsset.getRenditions()).thenReturn(renditions);
		Mockito.when(renditions.iterator()).thenReturn(iterator);
		Mockito.when(iterator.hasNext()).thenReturn(true,false);
		Mockito.when(iterator.next()).thenReturn(rendition);				
		Mockito.when(rendition.getName()).thenReturn(Constants.HERO.HERO_ORIGINAL_RENDITION);		
		try {
			HeroBean bean = Whitebox.invokeMethod(heroBannerAction, "getRenditions",hero,renditionsResource,imagePath);
			assertEquals("banner image is::",imagePath+"original",bean.getBannerImage());
		} catch (Exception e) {
			fail("fail occured in testGetRenditions method of heroBannerAction :");
		}
	}

	
	/**
	 * Test get renditions.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetRenditionsElse(){
		String imagePath = "/content/dam/launchpad/Videos/MVI_0216.mp4/jcr:content/renditions/";	
		HeroBean hero=new HeroBean();
		hero.setAssetFormat("video");
		Resource renditionsResource = Mockito.mock(Resource.class);
		Asset videoAsset = Mockito.mock(Asset.class);
		Rendition rendition = Mockito.mock(Rendition.class);
		List<Rendition> renditions = Mockito.mock(ArrayList.class);
		Iterator<Rendition> iterator = Mockito.mock(Iterator.class);
		
		Mockito.when(renditionsResource.adaptTo(Asset.class)).thenReturn(videoAsset);
		Mockito.when(videoAsset.getRenditions()).thenReturn(renditions);
		Mockito.when(renditions.iterator()).thenReturn(iterator);
		Mockito.when(iterator.hasNext()).thenReturn(true,false);
		Mockito.when(iterator.next()).thenReturn(rendition);		
		try {
			HeroBean bean = Whitebox.invokeMethod(heroBannerAction, "getRenditions",hero,renditionsResource,imagePath);
			assertEquals("banner image is::",imagePath+"cq5dam.thumbnail.319.319.png",bean.getBannerImage());
		} catch (Exception e) {
			fail("fail occured in testGetRenditionsElse method of heroBannerAction :");
		}
	}

	
	/**
	 * Test get node properties.
	 */
	@Test
	public void tesGetNodeProperties(){
		log.info("entering into the tesGetNodeProperties method of the HeroBannerAction ");
		Node outerNode = Mockito.mock(Node.class);
		NodeIterator nodeit = Mockito.mock(NodeIterator.class);
		Node innerNode = Mockito.mock(Node.class);
		ResourceResolver resourceResolver = Mockito.mock(ResourceResolver.class);
		Resource resource = Mockito.mock(Resource.class);
		ValueMap nodePropertiesMap = Mockito.mock(ValueMap.class);	
		try {
			Mockito.when(outerNode.getNodes()).thenReturn(nodeit);
			Mockito.when(nodeit.hasNext()).thenReturn(true,false);
			Mockito.when((Node) nodeit.next()).thenReturn(innerNode);
			PowerMockito.stub(PowerMockito.method(HeroBannerAction.class, "getSlingRequest")).toReturn(getSlingHttpServletRequest());
			Mockito.when(getSlingHttpServletRequest().getResourceResolver()).thenReturn(resourceResolver);
			Mockito.when(innerNode.getPath()).thenReturn("nodePath");
			Mockito.when(resourceResolver.getResource(Mockito.any(String.class))).thenReturn(resource);
			Mockito.when(resource.adaptTo(ValueMap.class)).thenReturn(nodePropertiesMap);
			Mockito.when(nodePropertiesMap.get(Constants.HERO.HERO_SELECTED_VIDEO,"")).thenReturn(videoPathString);
			Mockito.when(nodePropertiesMap.get(Constants.HERO.HERO_BANNER_IMAGEE,"")).thenReturn(bannerImageString);
			Mockito.when(nodePropertiesMap.get(Constants.HERO.HERO_BANNER_TITLE,"")).thenReturn(titleString);
			Mockito.when(nodePropertiesMap.get(Constants.HERO.HERO_CTA_BUTTON_TITLE,"")).thenReturn(buttonText);
			Mockito.when(nodePropertiesMap.get(Constants.HERO.HERO_BANNER_DESCRIPTION,"")).thenReturn(descString);
			Mockito.when(nodePropertiesMap.get(Constants.HERO.HERO_ASSET_PATH,"#")).thenReturn(assetPath);
			Mockito.when(nodePropertiesMap.get(Constants.HERO.HERO_IS_VIDEO,"")).thenReturn(videoFlagString);
			assetFormat=Constants.HERO.HERO_IMAGE_VIDEO;
			List<HeroBean> listOfHeroBeans = Whitebox.invokeMethod(heroBannerAction, "getNodeProperties",outerNode);
			assertEquals("banner description is::",descString,listOfHeroBeans.get(0).getBannerDescription());
			assertEquals("bannaer image is::",bannerImageString,listOfHeroBeans.get(0).getBannerImage());
			assertEquals("bannaer title is::",titleString,listOfHeroBeans.get(0).getBannerTitle());
			assertEquals("button tesxt is::",buttonText,listOfHeroBeans.get(0).getButtonText());
			assertEquals("video flag is::",videoFlagString,listOfHeroBeans.get(0).getVideoFlag());
			assertEquals("video path is::",videoPathString,listOfHeroBeans.get(0).getVideoPath());
			assertEquals("asset format is::",assetFormat,listOfHeroBeans.get(0).getAssetFormat());
		} catch (Exception e) {
			fail("fail occured in tesGetNodeProperties method:");
		}
	}
	
	/**
	 * Test get node properties.
	 */
	@Test
	public void tesGetNodePropertiesElse(){
		log.info("entering into the tesGetNodeProperties method of the HeroBannerAction ");
		String assetPathext = "google.com";
		Node outerNode = Mockito.mock(Node.class);
		NodeIterator nodeit = Mockito.mock(NodeIterator.class);
		Node innerNode = Mockito.mock(Node.class);
		ResourceResolver resourceResolver = Mockito.mock(ResourceResolver.class);
		Resource resource = Mockito.mock(Resource.class);
		ValueMap nodePropertiesMap = Mockito.mock(ValueMap.class);	
		try {
			Mockito.when(outerNode.getNodes()).thenReturn(nodeit);
			Mockito.when(nodeit.hasNext()).thenReturn(true,false);
			Mockito.when((Node) nodeit.next()).thenReturn(innerNode);
			PowerMockito.stub(PowerMockito.method(HeroBannerAction.class, "getSlingRequest")).toReturn(getSlingHttpServletRequest());
			Mockito.when(getSlingHttpServletRequest().getResourceResolver()).thenReturn(resourceResolver);
			Mockito.when(innerNode.getPath()).thenReturn("nodePath");
			Mockito.when(resourceResolver.getResource(Mockito.any(String.class))).thenReturn(resource);
			Mockito.when(resource.adaptTo(ValueMap.class)).thenReturn(nodePropertiesMap);
			Mockito.when(nodePropertiesMap.get(Constants.HERO.HERO_ASSET_PATH,"#")).thenReturn(assetPathext);		
			List<HeroBean> listOfHeroBeans = Whitebox.invokeMethod(heroBannerAction, "getNodeProperties",outerNode);
			assertEquals("asset path is::",assetPathext,listOfHeroBeans.get(0).getAssetPath());
		} catch (Exception e) {
			fail("fail occured in tesGetNodeProperties method:");
		}
	}
	
	/**
	 * Test get node properties.
	 */
	@Test
	public void tesGetNodePropertiesAssetElse(){
		log.info("entering into the tesGetNodeProperties method of the HeroBannerAction ");
		Node outerNode = Mockito.mock(Node.class);
		NodeIterator nodeit = Mockito.mock(NodeIterator.class);
		Node innerNode = Mockito.mock(Node.class);
		ResourceResolver resourceResolver = Mockito.mock(ResourceResolver.class);
		Resource resource = Mockito.mock(Resource.class);
		ValueMap nodePropertiesMap = Mockito.mock(ValueMap.class);	
		try {
			Mockito.when(outerNode.getNodes()).thenReturn(nodeit);
			Mockito.when(nodeit.hasNext()).thenReturn(true,false);
			Mockito.when((Node) nodeit.next()).thenReturn(innerNode);
			PowerMockito.stub(PowerMockito.method(HeroBannerAction.class, "getSlingRequest")).toReturn(getSlingHttpServletRequest());
			Mockito.when(getSlingHttpServletRequest().getResourceResolver()).thenReturn(resourceResolver);
			Mockito.when(innerNode.getPath()).thenReturn("nodePath");
			Mockito.when(resourceResolver.getResource(Mockito.any(String.class))).thenReturn(resource);
			Mockito.when(resource.adaptTo(ValueMap.class)).thenReturn(nodePropertiesMap);
			Mockito.when(nodePropertiesMap.get(Constants.HERO.HERO_ASSET_PATH,"#")).thenReturn("#");		
			List<HeroBean> listOfHeroBeans = Whitebox.invokeMethod(heroBannerAction, "getNodeProperties",outerNode);
			assertEquals("asset path is::","#",listOfHeroBeans.get(0).getAssetPath());
		} catch (Exception e) {
			fail("fail occured in tesGetNodeProperties method:");
		}
	}
	
	
}
