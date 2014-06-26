/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.framework.managers;

	import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.Session;
import javax.jcr.Value;
import javax.jcr.Workspace;
import javax.jcr.nodetype.NodeType;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import javax.jcr.query.RowIterator;

import org.apache.sling.commons.scheduler.Scheduler;
import org.apache.sling.jcr.api.SlingRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.osgi.service.component.ComponentContext;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.Replicator;
import com.tc.framework.managers.impl.AppsConfigServiceImpl;
import com.tc.framework.model.AppsConfigBean;
import com.tc.framework.util.Constants;
	


	/**
	 * The Class SiteConfigServiceImplTestCase.
	 */
	@RunWith(PowerMockRunner.class)
	@PrepareForTest(AppsConfigServiceImpl.class)
	@PowerMockIgnore("org.apache.http.conn.*")
	public class AppsConfigServiceImplTest {

		//** The site config service. *//*
		private AppsConfigServiceImpl appsConfigService;

		//** The config node. *//*
		private Node configNode;

		//** The property. *//*
		private Property property;

		//** The value. *//*
		private Value value;

		//** The siteconfig bean. *//*
		private AppsConfigBean siteconfigBean;

		//** The session. *//*
		@Mock
		private Session session;

		//** The site config properties. *//*
		@Mock
		private Hashtable<String, String> appConfigProperties;;

		//** The scheduler. *//*
		private Scheduler scheduler;

		//** The repository. *//*
		private SlingRepository repository;

		//** The replicator. *//*
		private Replicator replicator;

		/**
		 * Sets the up.
		 *
		 * @throws Exception the exception
		 */
		@SuppressWarnings("unchecked")
		@Before
		public void setUp() throws Exception {
			appsConfigService = Whitebox.newInstance(AppsConfigServiceImpl.class);
			session = Mockito.mock(Session.class);
			configNode = Mockito.mock(Node.class);
			property = Mockito.mock(Property.class);
			value = Mockito.mock(Value.class);
			scheduler = Mockito.mock(Scheduler.class);
			repository = Mockito.mock(SlingRepository.class);
			replicator = Mockito.mock(Replicator.class);
			appConfigProperties = Mockito.mock(Hashtable.class);
			
			Whitebox.setInternalState(appsConfigService, scheduler);
			Whitebox.setInternalState(appsConfigService, repository);
			Whitebox.setInternalState(appsConfigService, replicator);
			Whitebox.setInternalState(appsConfigService, appConfigProperties);
			Whitebox.setInternalState(appsConfigService, session);

		}

		/**
		 * Tear down.
		 *
		 * @throws Exception the exception
		 */
		@After
		public void tearDown() throws Exception {
			appsConfigService = null;
		}

		/**
		 * Test get property with if.
		 */
		@Test
		public void testGetPropertyWithIf() {
			String solrCoreReturnVal = "test url";
			String key  = "doec.en.solrHost";
			try
			{
				Mockito.when(appConfigProperties.contains(key)).thenReturn(true);
				PowerMockito.stub(PowerMockito.method(AppsConfigServiceImpl.class,"getSession")).toReturn(session);
				PowerMockito.stub(PowerMockito.method(AppsConfigServiceImpl.class,"isValidLocale",String.class)).toReturn(true);
				PowerMockito.stub(PowerMockito.method(System.class,"getProperty",String.class)).toReturn("author");
				PowerMockito.when(session, "getNode",(String)Mockito.any()).thenReturn(configNode);
				PowerMockito.when(configNode, "hasNode",(String)Mockito.any()).thenReturn(true);
				PowerMockito.when(configNode, "getNode",(String)Mockito.any()).thenReturn(configNode);
				PowerMockito.when(configNode, "getProperty",(String)Mockito.any()).thenReturn(property);
				PowerMockito.when(property, "getValue").thenReturn(value);
				PowerMockito.when(value, "getString").thenReturn(solrCoreReturnVal);
				String solrhost = appsConfigService.getProperty(key);
				assertEquals("Expected Output and actual Output did not match",solrhost,solrCoreReturnVal);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		/**
		 * Test get property with else.
		 */
		@Test
		public void testGetPropertyWithElse() {
			String solrCoreReturnVal = "test url";
			String key  = "doec.en.solrHost";
			try
			{
				Node configNode1 = Mockito.mock(Node.class);
				PowerMockito.stub(PowerMockito.method(AppsConfigServiceImpl.class,"getSession")).toReturn(session);
				PowerMockito.stub(PowerMockito.method(AppsConfigServiceImpl.class,"isValidLocale")).toReturn(false);
				PowerMockito.stub(PowerMockito.method(System.class,"getProperty",String.class)).toReturn("author");
				
				PowerMockito.when(session, "getNode",(String)Mockito.any()).thenReturn(configNode);
				
				Mockito.when(configNode.hasNode((String)Mockito.any())).thenReturn(true);
				Mockito.when(configNode.getNode((String)Mockito.any())).thenReturn(configNode1);
				Mockito.when(configNode1.getProperty("author")).thenReturn(property);
				//PowerMockito.when(configNode, "hasNode",(String)Mockito.any()).thenReturn(true);
				//PowerMockito.when(configNode, "getNode",(String)Mockito.any()).thenReturn(configNode);
				//PowerMockito.when(configNode, "getProperty",(String)Mockito.any()).thenReturn(property);
				Mockito.when(property.getValue()).thenReturn(value);
				Mockito.when(value.getString()).thenReturn(solrCoreReturnVal);
				//PowerMockito.when(property, "getValue").thenReturn(value);
				//PowerMockito.when(value, "getString").thenReturn(solrCoreReturnVal);
				String solrhost = appsConfigService.getProperty(key);
				//assertEquals("Expected Output and actual Output did not match",solrhost,solrCoreReturnVal);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		/**
		 * Test save property action.
		 */
		@Test
		public void testSavePropertyAction() 
		{
			Node siteConfigSaveNode = Mockito.mock(Node.class);
			siteconfigBean = new AppsConfigBean();
			try 
			{
				PowerMockito.when(session, "getNode",(String)Mockito.any()).thenReturn(configNode);
				siteconfigBean.setPropertyName("property1");
				siteconfigBean.setNodeName("node1");
				PowerMockito.when(configNode.addNode((String)Mockito.any(),(String)Mockito.any())).thenReturn(siteConfigSaveNode);
				appsConfigService.saveProperty(siteconfigBean,"");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}

		/**
		 * Test get all propertie action.
		 */
		@Test
		public void testGetAllPropertieAction() 
		{
			Node node = Mockito.mock(Node.class);
			NodeIterator nodeIterator = Mockito.mock(NodeIterator.class);
			Node appConfigNode = Mockito.mock(Node.class);
			String path = "en/newsiteconfig.html";
			siteconfigBean = new AppsConfigBean();
			Property authorProperty = Mockito.mock(Property.class);
			Property publishProperty = Mockito.mock(Property.class);
			try 
			{
				Mockito.when(appConfigNode.getPath()).thenReturn("content/en/siteconfig.html");
				Mockito.when(appConfigNode.getName()).thenReturn("siteconfig.html");
				Mockito.when(session.getNode(Mockito.anyString())).thenReturn(node);
				Mockito.when(node.getNodes()).thenReturn(nodeIterator);
				Mockito.when(nodeIterator.hasNext()).thenReturn(true,false);
				Mockito.when(nodeIterator.next()).thenReturn(appConfigNode);
				Mockito.when(appConfigNode.hasProperty("author")).thenReturn(true);
				Mockito.when(appConfigNode.getProperty("author")).thenReturn(authorProperty);
				Mockito.when(authorProperty.getString()).thenReturn("author");
				Mockito.when(appConfigNode.hasProperty("publish")).thenReturn(true);
				Mockito.when(appConfigNode.getProperty("publish")).thenReturn(publishProperty);
				Mockito.when(publishProperty.getString()).thenReturn("publish");

				List<AppsConfigBean> list = appsConfigService.getAllProperties(path, session);
				assertEquals(((AppsConfigBean)list.get(0)).getAuthorValue(), "author");

			}catch (Exception e) {
				e.printStackTrace();
			}

		}

		/**
		 * Test is duplicate.
		 */
		@Test
		public void testIsDuplicate() 
		{
			String propertyName = "node1";
			String path = "en/newsiteconfig.html";
			siteconfigBean = new AppsConfigBean();
			Workspace workspace = Mockito.mock(Workspace.class);
			QueryManager queryManager = Mockito.mock(QueryManager.class);
			Query query =  Mockito.mock(Query.class);
			QueryResult queryResult = Mockito.mock(QueryResult.class);
			RowIterator rowIterator = Mockito.mock(RowIterator.class);
			NodeIterator nodeIterator = Mockito.mock(NodeIterator.class);
			Node node = Mockito.mock(Node.class);
			try
			{
				Mockito.when(session.getWorkspace()).thenReturn(workspace);
				Mockito.when(workspace.getQueryManager()).thenReturn(queryManager);
				Mockito.when(queryManager.createQuery(Mockito.anyString(), Mockito.anyString())).thenReturn(query);
				Mockito.when(query.execute()).thenReturn(queryResult);
				Mockito.when(queryResult.getRows()).thenReturn(rowIterator);
				Mockito.when(rowIterator.getSize()).thenReturn((long)1);
				Mockito.when(queryResult.getNodes()).thenReturn(nodeIterator);
				Mockito.when(nodeIterator.hasNext()).thenReturn(true,false);
				Mockito.when(nodeIterator.nextNode()).thenReturn(node);
				Mockito.when(node.getName()).thenReturn("node1");
				boolean isDuplicate = appsConfigService.isDuplicate(propertyName,path);
				assertEquals(isDuplicate, true);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

		}

		/**
		 * Test publish nodes with if.
		 */
		@Test
		public void testPublishNodesWithIf()
		{
			String publishList = "author,publish";
			String path = "en/siteconfig.html";
			Node node = Mockito.mock(Node.class);
			StringTokenizer stringTokenizer = Mockito.mock(StringTokenizer.class);
			Session nodeSession = Mockito.mock(Session.class);
			try
			{
				Mockito.when(stringTokenizer.hasMoreElements()).thenReturn(true,false);
				Mockito.when(stringTokenizer.nextToken()).thenReturn("author");
				Mockito.when(session.getNode(Mockito.anyString())).thenReturn(node);
				Mockito.when(node.getSession()).thenReturn(nodeSession);
				Mockito.when(node.getPath()).thenReturn(path);
				Mockito.doNothing().when(replicator).replicate(nodeSession, ReplicationActionType.ACTIVATE, path);
				boolean isPublished = appsConfigService.publishNodes(session, publishList);
				assertEquals(isPublished, true);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}


		/**
		 * Test publish nodes with else.
		 */
		@Test
		public void testPublishNodesWithElse()
		{
			String publishList = "author&publish";
			String path = "en/siteconfig.html";
			Node node = Mockito.mock(Node.class);
			Session nodeSession = Mockito.mock(Session.class);
			try
			{
				Mockito.when(session.getNode(Mockito.anyString())).thenReturn(node);
				Mockito.when(node.getSession()).thenReturn(nodeSession);
				Mockito.when(node.getPath()).thenReturn(path);
				Mockito.doNothing().when(replicator).replicate(nodeSession, ReplicationActionType.ACTIVATE, path);
				boolean isPublished = appsConfigService.publishNodes(session, publishList);
				assertEquals(isPublished, true);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		/**
		 * Test publish all nodes.
		 */
		@Test
		public void testPublishAllNodes()
		{
			String path = "en/siteconfig.html";
			Node node = Mockito.mock(Node.class);
			NodeIterator nodeIterator  = Mockito.mock(NodeIterator.class);
			Session nodeSession = Mockito.mock(Session.class);
			Node childNode = Mockito.mock(Node.class);
			try
			{
				Mockito.when(session.getNode(Mockito.anyString())).thenReturn(node);
				Mockito.when(node.hasNodes()).thenReturn(true,false);
				Mockito.when(node.getNodes()).thenReturn(nodeIterator);
				assertNotNull(nodeIterator);
				Mockito.when(nodeIterator.hasNext()).thenReturn(true,false);
				Mockito.when(nodeIterator.nextNode()).thenReturn(childNode);
				Mockito.when(childNode.getPath()).thenReturn(path);
				//Mockito.when(siteConfigService.publishAllNodes(session, childNode.getPath())).thenReturn(true);
				Mockito.doNothing().when(replicator).replicate(nodeSession, ReplicationActionType.ACTIVATE, path);
				boolean isPublishAllNodes = appsConfigService.publishAllNodes(session, path);
				assertEquals(isPublishAllNodes, true);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		/**
		 * Test get site config collection path.
		 */
		@Test
		public void testGetSiteConfigCollectionPath()
		{
			String path = "en/siteconfig.html";
			String propNode = "/apps/template/templates/appsconfigcollection";
			String nodepath = "appsconfig/jcr:content";
			Node node = Mockito.mock(Node.class);
			NodeType nodeType = Mockito.mock(NodeType.class);
			NodeIterator nodeIterator  = Mockito.mock(NodeIterator.class);
			Session nodeSession = Mockito.mock(Session.class);
			Node childNode = Mockito.mock(Node.class);
			Property property = Mockito.mock(Property.class);
			Value value = Mockito.mock(Value.class);
			try
			{
				Mockito.when(session.getNode(Mockito.anyString())).thenReturn(node);
				Mockito.when(node.getPrimaryNodeType()).thenReturn(nodeType);
				Mockito.when(nodeType.getName()).thenReturn("cq:Page");
				//Mockito.when(node.hasNodes()).thenReturn(true,false);
				Mockito.when(node.getNodes()).thenReturn(nodeIterator);
				Mockito.when(nodeIterator.hasNext()).thenReturn(true,false);
				Mockito.when(nodeIterator.nextNode()).thenReturn(childNode);
				Mockito.when(childNode.getName()).thenReturn("jcr:content");
				Mockito.when(childNode.hasProperty("cq:template")).thenReturn(true);
				Mockito.when(childNode.getProperty("cq:template")).thenReturn(property);
				Mockito.when(property.getValue()).thenReturn(value);
				Mockito.when(value.getString()).thenReturn(propNode);
				Mockito.when(node.getPath()).thenReturn(nodepath);
				//Mockito.doNothing().when(replicator).replicate(nodeSession, ReplicationActionType.ACTIVATE, path);
				String expectedNodePath = appsConfigService.getAppsConfigCollectionPath(session, path);
				assertEquals(expectedNodePath, nodepath);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		@Test
		public void testGetSession()
		{
			SlingRepository repository = Mockito.mock(SlingRepository.class);
			try 
			{
				PowerMockito.stub(PowerMockito.method(AppsConfigServiceImpl.class, "getRepository")).toReturn(repository);
				Mockito.when(repository.loginAdministrative(null)).thenReturn(session);
				Session CQSession = Whitebox.invokeMethod(appsConfigService, "getSession");
				assertNotNull(CQSession);

			} catch (Exception re) {
				re.printStackTrace();
			}
		}

		@Test
		public void testActivate() {
			try{
				ComponentContext context = Mockito.mock(ComponentContext.class);
				Dictionary<String, String> properties = Mockito.mock(Dictionary.class);
				Mockito.when(context.getProperties()).thenReturn(properties);
				String period = "123";
				Mockito.when(properties.get(Constants.PERIOD)).thenReturn(period);
				Whitebox.invokeMethod(appsConfigService, "activate",context);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		@Test
		public void testSyncProperties() {
			try{
				Set<String> set = Mockito.mock(Set.class);
				Iterator<String> iterator = Mockito.mock(Iterator.class);
				Mockito.when(appConfigProperties.keySet()).thenReturn(set);
				Mockito.when(set.iterator()).thenReturn(iterator);
				Mockito.when(iterator.hasNext()).thenReturn(true,false);
				Mockito.when(iterator.next()).thenReturn("string test");
				Whitebox.invokeMethod(appsConfigService, "syncProperties");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		@Test
		public void testDeactivate() {
			try{
				ComponentContext context = Mockito.mock(ComponentContext.class);
				Whitebox.invokeMethod(appsConfigService, "deactivate",context);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		@Test
		public void testTransformContent() {
			try{
				/*PowerMockito.stub(PowerMockito.method(AppsConfigServiceImpl.class,"getSession")).toReturn(session);
				Locale locale = Mockito.mock(Locale.class);*/
				Locale locale = new Locale("US");
				String htmlContent = "htmlContent";
				 Map<String, String> mockValues = Mockito.mock(Map.class);
				 boolean isEditMode = true;
				 Pattern pattern = Pattern.compile("\\{(.+?)\\}");
				 Matcher matcher = pattern.matcher(htmlContent);
				 Whitebox.invokeMethod(appsConfigService, "transformContent",locale,htmlContent,mockValues,isEditMode);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		@Test
		public void testGetPropertyMultipleValues() {
			try{
				Locale locale = new Locale("US");
				String key = "key.key.key.key";
				Map<String, String> mockValues = new HashMap<String, String>();
				mockValues.put(key,"key1");
				Whitebox.invokeMethod(appsConfigService, "getProperty",locale,key,mockValues);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		@Test
		public void testGetPropertyMultipleValues1() {
			try{
				Locale locale = new Locale("US");
				String key = "key.key.key.key";
				Map<String, String> mockValues = new HashMap<String, String>();
				mockValues.put(key,"key1");
				Whitebox.invokeMethod(appsConfigService, "getProperty",locale,key,mockValues);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		@Test
		public void testtransformContent() {
			try{
				appsConfigService.transformContent("htmlcontent");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		@Test
		public void testGetRepository() {
			try{
				appsConfigService.getRepository();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
}
