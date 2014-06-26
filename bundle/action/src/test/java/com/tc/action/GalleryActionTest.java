/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */
package com.tc.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Value;

import org.apache.sling.api.SlingHttpServletRequest;
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

import com.tc.action.GalleryAction;
import com.tc.model.GalleryBean;

@RunWith(PowerMockRunner.class)
@PrepareForTest(GalleryAction.class)
public class GalleryActionTest extends BaseTest{
	private GalleryAction galleryAction = null;
	private Node galleryNode;
	String albumpath = "album path";
	Property albumProperty;
	Property selectorProperty;
	String advanced;
	Property headingProperty;
	String headingString;
	Property radioProperty;
	String videos;
	private transient SlingHttpServletRequest slingRequest;
	Resource resource; 
	Node albumNode;
	ResourceResolver resourceResolver;
	GalleryBean galBean;
	GalleryBean galBean1;
	GalleryBean galBean2;
	Calendar  date;
	Property datePropety;
	Calendar  date1;
	Property timeIntervalProperty;
	Property timeUnitProperty;
	Property displayItemsProperty;
	NodeIterator albumIterator;
	NodeIterator albumIterator1;
	Node childAlbumNode;
	Node childItemNode;
	Node childNode;
	String childItemPath="itempath";
	String damTitle="damTitle";
	Node itemNode;
	Property dcTitleProperty;
	Property cqTagProperty;
	String currentNodePath="currentNodePath";
	NodeIterator nodeIterator;
	NodeIterator itemNodeIterator;
	Property itemPathProperty;
	Property saveRestrict;
	
	List<String> getTags = new ArrayList<String>();
	Property	titleProperty;
	Property	descriptionProperty;
	Property captionProperty;
	/*private GalleryAction videoGalleryAction = null;
	private Node videoGalleryNode;
	String path = "test path";
	Resource videoGalleryResource;
	NodeIterator nodeIterator;
	PropertyIterator propertyIterator;
	private transient SlingHttpServletRequest slingRequest1;
	Property prop1;
	Property prop2;
	ResourceResolver resourceResolver;*/
	@Before
	public void setUp() throws Exception {
		
		galleryNode = Mockito.mock(Node.class);
		
		albumProperty = Mockito.mock(Property.class);
		selectorProperty = Mockito.mock(Property.class);
		headingProperty = Mockito.mock(Property.class);
		radioProperty = Mockito.mock(Property.class);
		slingRequest = Mockito.mock(SlingHttpServletRequest.class);
		resourceResolver = Mockito.mock(ResourceResolver.class);
		resource = Mockito.mock(Resource.class);
		albumNode= Mockito.mock(Node.class);
		galBean=Mockito.mock(GalleryBean.class);
		galBean1=Mockito.mock(GalleryBean.class);
		galBean2=Mockito.mock(GalleryBean.class);
		galleryAction = Whitebox.newInstance(GalleryAction.class);
		timeIntervalProperty= Mockito.mock(Property.class);
		timeUnitProperty=Mockito.mock(Property.class);
		displayItemsProperty=Mockito.mock(Property.class);
		childAlbumNode=Mockito.mock(Node.class);
		childItemNode=Mockito.mock(Node.class);
		itemNode=Mockito.mock(Node.class);
		//date=Mockito.mock(Calendar.class);
		date1=Mockito.mock(Calendar.class);
		PowerMockito.stub(PowerMockito.method(GalleryAction.class, "getCurrentNode")) .toReturn(galleryNode);
		datePropety = Mockito.mock(Property.class);
		titleProperty = Mockito.mock(Property.class);
		descriptionProperty = Mockito.mock(Property.class);
		captionProperty = Mockito.mock(Property.class);
		saveRestrict = Mockito.mock(Property.class);
		PowerMockito.stub(
				PowerMockito.method(GalleryAction.class, "getCurrentResource"))
				.toReturn(resource);
		PowerMockito.stub(
				PowerMockito.method(GalleryAction.class, "getSlingRequest"))
				.toReturn(slingRequest);
		albumIterator = Mockito.mock(NodeIterator.class);
		albumIterator1 = Mockito.mock(NodeIterator.class);
		nodeIterator= Mockito.mock(NodeIterator.class);
		itemNodeIterator=Mockito.mock(NodeIterator.class);
		dcTitleProperty= Mockito.mock(Property.class);
		cqTagProperty=Mockito.mock(Property.class);
		childNode=Mockito.mock(Node.class);
		itemPathProperty=Mockito.mock(Property.class);
		/*
		nodeIterator = Mockito.mock(NodeIterator.class);
		propertyIterator = Mockito.mock(PropertyIterator.class);
		videoGalleryAction = Mockito.mock(GalleryAction.class);
		videoGalleryNode = Mockito.mock(Node.class);
		videoGalleryResource = Mockito.mock(Resource.class);
		slingRequest = Mockito.mock(SlingHttpServletRequest.class);
		prop1 = Mockito.mock(Property.class);
		prop2 = Mockito.mock(Property.class);
		PowerMockito.stub(
				PowerMockito.method(GalleryAction.class, "getCurrentNode")) 
				.toReturn(videoGalleryNode);
		PowerMockito.stub(
				PowerMockito.method(GalleryAction.class, "getCurrentResource"))
				.toReturn(videoGalleryResource);
		PowerMockito.stub(
				PowerMockito.method(GalleryAction.class, "getSlingRequest"))
				.toReturn(slingRequest);
*/
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGalleryAction() {
		try {
	
			
			galBean= new GalleryBean();
			galBean.setGalleryType("galleryType");
			galBean.setHeading("heading");
			galBean.setSelector("selector");
			
			
		Mockito.when(galleryNode.hasProperty("albumpath")).thenReturn(true);
		Mockito.when(galleryNode.getProperty("albumpath")).thenReturn(albumProperty);
		Mockito.when(albumProperty.getString()).thenReturn(albumpath);
		

		
		PowerMockito.stub(PowerMockito.method(GalleryAction.class, "getSimpleGallery"))
		.toReturn(galBean2);
		Node albumNode = Mockito.mock(Node.class);
		Mockito.when(galleryNode.hasProperty("selector")).thenReturn(true);
		Mockito.when(galleryNode.getProperty("selector")).thenReturn(selectorProperty);
		Mockito.when(selectorProperty.getString()).thenReturn("advanced");
		
		
	
		PowerMockito.stub(PowerMockito.method(GalleryAction.class, "getAdvancedGallery", String.class)).toReturn(
				galBean);


    	
    	
		Mockito.when(galleryNode.hasProperty("saveRestrict")).thenReturn(true);
		Mockito.when(galleryNode.getProperty("saveRestrict")).thenReturn(saveRestrict);
		Mockito.when(saveRestrict.getString()).thenReturn("true");
    	
		
		
		Mockito.when(galleryNode.hasProperty("heading")).thenReturn(true);
		Mockito.when(galleryNode.getProperty("heading")).thenReturn(headingProperty);
		Mockito.when(headingProperty.getString()).thenReturn("headingString");
		
		Mockito.when(galleryNode.hasProperty("radio")).thenReturn(true);
		Mockito.when(galleryNode.getProperty("radio")).thenReturn(radioProperty);
		Mockito.when(radioProperty.getString()).thenReturn("video");
		

		
     
        GalleryBean galBean=galleryAction.getGallery();
        
        
        
		
        
        assertEquals("galleryType", "video",galBean.getGalleryType());
        assertEquals("heading", "headingString",galBean.getHeading());
        assertEquals("selector", "advanced",galBean.getSelector());
        
        
		
		/*	GalleryAction vc = new GalleryAction();
			Node firstNode = Mockito.mock(Node.class);
			Node secondNode = Mockito.mock(Node.class);
			ResourceResolver resourceResolver = Mockito
					.mock(ResourceResolver.class);
			Resource resource = Mockito.mock(Resource.class);
			
			Mockito.when(videoGalleryResource.adaptTo(Node.class)).thenReturn(
					videoGalleryNode);
			Mockito.when(videoGalleryNode.getProperty("selector")).thenReturn(
					prop2);
			Mockito.when(prop2.getString()).thenReturn("simple");
			Mockito.when(videoGalleryNode.hasNode("videos")).thenReturn(true);
			Mockito.when(videoGalleryNode.getPath()).thenReturn(path);
			Mockito.when(slingRequest.getResourceResolver()).thenReturn(
					resourceResolver);
			Mockito.when(resourceResolver.getResource((String) Mockito.any()))
					.thenReturn(resource);
			Mockito.when(resource.adaptTo(Node.class)).thenReturn(firstNode);

			Mockito.when(firstNode.getNodes()).thenReturn(nodeIterator);
			Mockito.when(nodeIterator.hasNext()).thenReturn(true, false);
			Mockito.when(nodeIterator.nextNode()).thenReturn(secondNode);
			Mockito.when(secondNode.getProperty("videos")).thenReturn(prop1);
			Mockito.when(prop1.getString()).thenReturn("test");
			List<String> list = new ArrayList<String>();
			list.add("path");
			list.add("path1");
			Mockito.when(videoGalleryAction.getTags("path")).thenReturn(list);

			Value[] values = new Value[3];
			Value value = Mockito.mock(Value.class);

			values[0] = value;
			Mockito.when(firstNode.getProperty("cq:tags")).thenReturn(prop1);
			Mockito.when(prop1.getValues()).thenReturn(values);
			Mockito.when(values[0].getString()).thenReturn("test:test:test");

			Mockito.when(videoGalleryAction.getProperty("radio")).thenReturn(
					"video");

			NodeIterator nodeiterator = Mockito.mock(NodeIterator.class);
			GalleryBean videoGalleryBean = vc.getGallery();*/
        
        

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	
	

	@Test
	public void testGalleryNegativeAction(){
		try {
	

			
		
		Mockito.when(galleryNode.hasProperty("albumpath")).thenReturn(false);
		Mockito.when(galleryNode.getProperty("albumpath")).thenReturn(albumProperty);
		Mockito.when(albumProperty.getString()).thenReturn("");
		
		Mockito.when(galleryNode.hasProperty("selector")).thenReturn(false);
		Mockito.when(galleryNode.getProperty("selector")).thenReturn(selectorProperty);
		Mockito.when(selectorProperty.getString()).thenReturn("");
		
		Mockito.when(galleryNode.hasProperty("saveRestrict")).thenReturn(false);
		Mockito.when(galleryNode.getProperty("saveRestrict")).thenReturn(saveRestrict);
		Mockito.when(saveRestrict.getString()).thenReturn("");
		
		Mockito.when(galleryNode.hasProperty("heading")).thenReturn(false);
		Mockito.when(galleryNode.getProperty("heading")).thenReturn(headingProperty);
		Mockito.when(headingProperty.getString()).thenReturn("");
		
		Mockito.when(galleryNode.hasProperty("radio")).thenReturn(false);
		Mockito.when(galleryNode.getProperty("radio")).thenReturn(radioProperty);
		Mockito.when(radioProperty.getString()).thenReturn("");
		
        GalleryBean galBean=galleryAction.getGallery();


		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	
	public GalleryActionTest() {
		super();
		
	}
	
	
	@Test
	public void testGalleryActionSimple(){

		
		
		try {
		Mockito.when(resource.adaptTo(Node.class)).thenReturn(galleryNode);
	
			Mockito.when(galleryNode.hasNode("items")).thenReturn(true);
			Mockito.when(galleryNode.getPath()).thenReturn(currentNodePath);
			

			Mockito.when(slingRequest.getResourceResolver()).thenReturn(resourceResolver);
			Resource resource2 = Mockito.mock(Resource.class);
			Mockito.when(resourceResolver.getResource(currentNodePath+"/items")).thenReturn(resource2);
			Mockito.when(resource2.adaptTo(Node.class)).thenReturn(itemNode);
			
			Mockito.when(itemNode.getNodes()).thenReturn(itemNodeIterator);
			Mockito.when(itemNodeIterator.hasNext()).thenReturn(true,false);
			Mockito.when(itemNodeIterator.nextNode()).thenReturn(childItemNode);
			
			Mockito.when(childItemNode.hasProperty("itemPath")).thenReturn(true);
			Mockito.when(childItemNode.getProperty("itemPath")).thenReturn(itemPathProperty);
			Mockito.when(itemPathProperty.getString()).thenReturn("itemPathPropertystring");
			getTags.add("Entertaiment");
			getTags.add("Business");
			getTags.add("Wildlife");
			PowerMockito.stub(PowerMockito.method(GalleryAction.class, "getTags", String.class)).toReturn(
					getTags);
		   Mockito.when(childItemNode.hasProperty("title")).thenReturn(true);
		   Mockito.when(childItemNode.getProperty("title")).thenReturn(titleProperty);
		   Mockito.when(titleProperty.getString()).thenReturn("title");
		   Mockito.when(childItemNode.hasProperty("description")).thenReturn(true);
		   Mockito.when(childItemNode.getProperty("description")).thenReturn(descriptionProperty);
		   Mockito.when(descriptionProperty.getString()).thenReturn("description");
		   Mockito.when(childItemNode.hasProperty("caption")).thenReturn(true);
		   Mockito.when(childItemNode.getProperty("caption")).thenReturn(captionProperty);
		   Mockito.when(captionProperty.getString()).thenReturn("caption");
			
	
			
			
			 GalleryBean galBean1=galleryAction.getSimpleGallery();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testGalleryActionNegativeSimple(){

		
		
		try {
		Mockito.when(resource.adaptTo(Node.class)).thenReturn(galleryNode);
	
			Mockito.when(galleryNode.hasNode("items")).thenReturn(true);
			Mockito.when(galleryNode.getPath()).thenReturn(currentNodePath);
			

			Mockito.when(slingRequest.getResourceResolver()).thenReturn(resourceResolver);
			Resource resource2 = Mockito.mock(Resource.class);
			Mockito.when(resourceResolver.getResource(currentNodePath+"/items")).thenReturn(resource2);
			Mockito.when(resource2.adaptTo(Node.class)).thenReturn(itemNode);
			
			Mockito.when(itemNode.getNodes()).thenReturn(itemNodeIterator);
			Mockito.when(itemNodeIterator.hasNext()).thenReturn(true,false);
			Mockito.when(itemNodeIterator.nextNode()).thenReturn(childItemNode);
			
			Mockito.when(childItemNode.hasProperty("itemPath")).thenReturn(false);
			Mockito.when(childItemNode.hasProperty("title")).thenReturn(false);
			Mockito.when(childItemNode.hasProperty("description")).thenReturn(false);
			Mockito.when(childItemNode.hasProperty("caption")).thenReturn(false);
			
			
			
			 GalleryBean galBean1=galleryAction.getSimpleGallery();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testGalleryActionAdvanced() {
		try {
	
			
			Mockito.when(slingRequest.getResourceResolver()).thenReturn(resourceResolver);
			Resource resource1 = Mockito.mock(Resource.class);
			Mockito.when(resourceResolver.getResource(albumpath)).thenReturn(resource1);
			Mockito.when(resource1.adaptTo(Node.class)).thenReturn(albumNode);
			Mockito.when(galleryNode.hasProperty("date")).thenReturn(true);
	    	Mockito.when(galleryNode.getProperty("date")).thenReturn(datePropety);
	    	Mockito.when(galleryNode.hasProperty("modifiedDate")).thenReturn(true);
	   	 	Mockito.when(datePropety.getDate()).thenReturn(date1);
	   	 	
	   	    Mockito.when(galleryNode.hasProperty("timeInterval")).thenReturn(true);
	   	    Mockito.when(galleryNode.getProperty("timeInterval")).thenReturn(timeIntervalProperty);
	   	    Mockito.when(timeIntervalProperty.getString()).thenReturn("10");
	   	 	
	   	 	
	     Mockito.when(galleryNode.hasProperty("timeUnit")).thenReturn(true);
	   	 Mockito.when(galleryNode.getProperty("timeUnit")).thenReturn(timeUnitProperty);
	   	 Mockito.when(timeUnitProperty.getString()).thenReturn("seconds");
	   	 
	   	Mockito.when(galleryNode.getProperty("displayitems")).thenReturn(displayItemsProperty);
	  	 Mockito.when(displayItemsProperty.getString()).thenReturn("3");
	 	
			Mockito.when(albumNode.hasNodes()).thenReturn(true);
	  	 Mockito.when(albumNode.getNodes()).thenReturn(albumIterator);
	  	 Mockito.when(albumIterator.hasNext()).thenReturn(true,false);
	  	 Mockito.when(albumIterator.nextNode()).thenReturn(childAlbumNode);
	  	 Mockito.when(childAlbumNode.getName()).thenReturn("business");
	  	 Mockito.when(childAlbumNode.hasNodes()).thenReturn(true);
	  	 Mockito.when(childAlbumNode.getNodes()).thenReturn(albumIterator1);
		 Mockito.when(albumIterator1.hasNext()).thenReturn(true,false);
		 Mockito.when(albumIterator1.nextNode()).thenReturn(childItemNode);
		 Mockito.when(childItemNode.getPath()).thenReturn(childItemPath);
		 
	PowerMockito.stub(PowerMockito.method(GalleryAction.class, "getDamTitle", String.class)).toReturn(
					damTitle);
			/*
			Mockito.when(slingRequest.getResourceResolver()).thenReturn(resourceResolver);
			Mockito.when(resourceResolver.getResource((String) Mockito.any())).thenReturn(resource);
	        Mockito.when(resource.adaptTo(Node.class)).thenReturn(albumNode);
	      
	      //Calendar  date=Calendar.getInstance();
			Calendar  date1=Calendar.getInstance();
	   //     Mockito.when(Calendar.getInstance()).thenReturn(date);
	    	Mockito.when(galleryNode.hasProperty("date")).thenReturn(true);
	    	Mockito.when(galleryNode.hasProperty("modifiedDate")).thenReturn(true);
	   
			 Mockito.when(galleryNode.getProperty("date")).thenReturn(datePropety);
	    	 Mockito.when(datePropety.getDate()).thenReturn(date1);
			*/
	       GalleryBean galBean1=galleryAction.getAdvancedGallery(albumpath);
			
        
        

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void getDamTitle() {
		
		try {
			
		Mockito.when(slingRequest.getResourceResolver()).thenReturn(resourceResolver);
		Resource resource1 = Mockito.mock(Resource.class);
		Mockito.when(resourceResolver.getResource(childItemPath+"/jcr:content/metadata")).thenReturn(resource1);
		Mockito.when(resource1.adaptTo(Node.class)).thenReturn(itemNode);
		
		Mockito.when(itemNode.hasProperty("dc:title")).thenReturn(true);
		Mockito.when(itemNode.getProperty("dc:title")).thenReturn(dcTitleProperty);
		Mockito.when(dcTitleProperty.getString()).thenReturn("title");
		
		
		
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	String damTitle = galleryAction.getDamTitle(childItemPath);
		
	}
	
	@Test
	public void getDamIfTitleTest() {
		
		try {
			
		Mockito.when(slingRequest.getResourceResolver()).thenReturn(resourceResolver);
		Resource resource1 = Mockito.mock(Resource.class);
		Mockito.when(resourceResolver.getResource(childItemPath+"/jcr:content/metadata")).thenReturn(resource1);
		Mockito.when(resource1.adaptTo(Node.class)).thenReturn(itemNode);
		
		Mockito.when(itemNode.hasProperty("dc:title")).thenReturn(false);
	
		
		String damTitle = galleryAction.getDamTitle(childItemPath);
		
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

		
	}
	
	
/*	
	@Test
	public void getTags(){
		try {
			
			Value[] values = new Value[3];
			Value value = Mockito.mock(Value.class);

			values[0] = value;
			
		Mockito.when(slingRequest.getResourceResolver()).thenReturn(resourceResolver);
		Resource resource1 = Mockito.mock(Resource.class);
		String damPath = childItemPath + "/jcr:content/metadata";
		Mockito.when(resourceResolver.getResource(damPath)).thenReturn(resource1);
		Mockito.when(resource1.adaptTo(Node.class)).thenReturn(itemNode);
		Mockito.when(itemNode.getProperty("cq:tags")).thenReturn(cqTagProperty);
		
		
		Mockito.when(cqTagProperty.getValues()).thenReturn(values);
		Mockito.when(values[0].getString()).thenReturn("test:test:test");
	
			
		List<String> tag = galleryAction.getTags(childItemPath);
		
		} catch (PathNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
		
	}
	*/
	

	/*@Test
	public void testGalleryException() throws RepositoryException {
		GalleryAction vc = new GalleryAction();
		Node firstNode = Mockito.mock(Node.class);
		Node secondNode = Mockito.mock(Node.class);
		ResourceResolver resourceResolver = Mockito
				.mock(ResourceResolver.class);
	
		Resource resource = Mockito.mock(Resource.class);
		Mockito.when(videoGalleryResource.adaptTo(Node.class)).thenReturn(
				videoGalleryNode);
		Mockito.when(videoGalleryNode.getProperty("selector"))
				.thenReturn(prop2);
		Mockito.when(prop2.getString()).thenReturn("simple");
		Mockito.when(videoGalleryNode.hasNode("videos")).thenThrow(
				new RepositoryException());
		vc.getGallery();

	}*/

}
