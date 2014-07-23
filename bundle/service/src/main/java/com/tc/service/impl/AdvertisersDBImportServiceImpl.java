package com.tc.service.impl;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.GregorianCalendar;

import javax.jcr.ItemExistsException;
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.nodetype.NoSuchNodeTypeException;
import javax.jcr.nodetype.NodeType;
import javax.jcr.version.VersionException;
import javax.sql.DataSource;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.osgi.service.component.ComponentContext;

import com.day.commons.datasource.poolservice.DataSourcePool;
import com.day.cq.commons.jcr.JcrUtil;
import com.tc.process.handler.TCFTPAdsProcessHandler;
import com.tc.service.api.AdvertisersDBImportService;
import com.tc.service.api.BaseService;

@Component(label = "TC Advertisers Import Service", description = "Sync content from Advertisers DB to AEM", immediate = true, metatype = true)
@Properties({
	@Property(name = "service.pid", value = "com.tc.service.api.AdvertisersDBImportServiceImpl", propertyPrivate = false),
	@Property(name = "service.description", value = "Sync content from Advertisers DB to AEM", propertyPrivate = false) })
@Service({ AdvertisersDBImportService.class })

public class AdvertisersDBImportServiceImpl extends BaseService  implements AdvertisersDBImportService {
	
	@Property(label = "FTP Server Adress", description = "URL of the FTP")
	private static final String FTP_SERVER_ADDRESS = "ftp.serverAddress";
	
	@Property(label = "FTP USER", description = "User name")
	private static final String FTP_USER_ID = "ftp.userId";
	
	@Property(label = "FTP Password", description = "Password")
	private static final String FTP_PASSWORD = "ftp.password";
	
	@Property(label = "FTP remote dir", description = "Remote directory")
	private static final String FTP_REMOTE_DIR = "ftp.remoteDirectory";
	
	@Property(label = "DAM local dir", description = "DAM local dir for images")
	private static final String DAM_LOCAL_DIR = "ftp.localDirectory";
 
	@Property(label = "FTP Timeout", description = "Timeout")
	private static final String FTP_TIMEOUT = "ftp.timeout";
	
	@Reference
	private DataSourcePool source;

	java.util.Properties props;
	
	private static final Logger LOG = LoggerFactory
			.getLogger(TCFileImportServiceImpl.class);

	@Activate
	private synchronized void activate(ComponentContext context) {
		
		if (context != null) {
			Dictionary<?, ?> properties = context.getProperties();
			if (properties != null) {
				props = new java.util.Properties();
				props.setProperty(FTP_SERVER_ADDRESS, PropertiesUtil.toString(properties.get(FTP_SERVER_ADDRESS), null));
				props.setProperty(FTP_USER_ID, PropertiesUtil.toString(properties.get(FTP_USER_ID), null));
				props.setProperty(FTP_PASSWORD, PropertiesUtil.toString(properties.get(FTP_PASSWORD), null));
				props.setProperty(FTP_REMOTE_DIR, PropertiesUtil.toString(properties.get(FTP_REMOTE_DIR), null));
				props.setProperty(DAM_LOCAL_DIR, PropertiesUtil.toString(properties.get(DAM_LOCAL_DIR), null));
				props.setProperty(FTP_TIMEOUT, PropertiesUtil.toString(properties.get(FTP_TIMEOUT), null));
			}
		}
	}

	
	@Override
	public void importAdvertisers(){
		Connection  connection = null;
		Session session;
		Node tc = null;
		Node parent = null;

		Calendar myCal = new GregorianCalendar();


		try{
			connection = getConnection();
			session = getSession();
			final Statement statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery(
					"SELECT * FROM annonceurs "
							+ "INNER JOIN liens_vendeurs ON liens_vendeurs.noClient = annonceurs.noClient "
							+ "LEFT JOIN codesPostaux ON annonceurs.codePostal = codesPostaux.codePostal "
							+ "WHERE annonceurs.nom != '' "
							+ "ORDER BY dateAjout DESC"); 

			
			if(!session.nodeExists("/etc/tc")){
				tc = JcrUtil.createPath("/etc/tc","sling:OrderedFolder",session);
				session.save();
			}else{
				tc = session.getNode("/etc/tc");
			}
			if(!session.nodeExists("/etc/tc/advertisers")){
				parent = JcrUtil.createPath(tc.getPath()+"/advertisers","sling:OrderedFolder",session);
				session.save();
			}else{
				parent = session.getNode("/etc/tc/advertisers");
			}


			while(resultSet.next()){
				DateTime date = null;
				if(resultSet.getDate("derniereModif")!=null){
					date = new DateTime(resultSet.getDate("derniereModif"));
				}else{
					date = new DateTime();
				}

				Node nodeTree = createContentStructure(parent,date.getYear(), date.getMonthOfYear(), date.getDayOfMonth(), session);
				
				Node advertiser = null;

				if(nodeTree!=null){
					String noClient = resultSet.getString("noClient");
					
					//we need to search for this node in all /etc/tc/advertisers to avoid duplicates
					if(!session.nodeExists(nodeTree.getPath()+"/"+noClient)){
						advertiser = JcrUtil.createPath(nodeTree.getPath()+"/"+noClient,NodeType.NT_UNSTRUCTURED,session);
						if(isNotBlank(resultSet.getString("nom"))){
							advertiser.setProperty("name1", resultSet.getString("nom"));
						}
						if(isNotBlank(resultSet.getString("nom2"))){
							advertiser.setProperty("name2", resultSet.getString("nom2"));
						}
						if(isNotBlank(resultSet.getString("adresse"))){
							advertiser.setProperty("address", resultSet.getString("adresse"));
						}
						if(isNotBlank(resultSet.getString("adresse2"))){
							advertiser.setProperty("city", resultSet.getString("adresse2"));
						}
						if(isNotBlank(resultSet.getString("idProvince"))){
							advertiser.setProperty("province", "provinces:"+resultSet.getString("idProvince").toUpperCase());
						}
						if(isNotBlank(resultSet.getString("idPays"))){
							advertiser.setProperty("country", "countries:"+resultSet.getString("idPays").toUpperCase());
						}
						if(isNotBlank(resultSet.getString("codePostal"))){
							advertiser.setProperty("zipCode", resultSet.getString("codePostal").toUpperCase());
						}
						if(isNotBlank(resultSet.getString("tel"))){
							advertiser.setProperty("telephone", resultSet.getString("tel"));
						}
						if(isNotBlank(resultSet.getString("codeVendeur"))){
							advertiser.setProperty("salesRepId", resultSet.getString("codeVendeur"));
						}
						if(isNotBlank(resultSet.getString("derniereModif"))){
							myCal.setTime(resultSet.getDate("derniereModif"));
							advertiser.setProperty("modifiedAt",myCal);
						}
						
						advertiser.setProperty("sling:resourceType", "tc/components/content/adfinder/advertiser");
						session.save();
						
					}
				}

			} 
			resultSet.close();


		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try{
				connection.close();
				LOG.info("importAdvertisers : Connection Closed");
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void importAds(){
		TCFTPAdsProcessHandler process = new TCFTPAdsProcessHandler(props);
		Connection  connection = null;
		Session session;
		Node tc = null;
		Node parent = null;
		Node tcDam = null;
		Node parentDam = null;
		Calendar myCal = new GregorianCalendar();
		
		try{
			connection = getConnection();

            if (connection == null) throw new Exception("Could not retrieve MySQL connection; Check your config");
			session = getSession();
			final Statement statement = connection.createStatement();
			final ResultSet resultSet = statement.executeQuery(
					"SELECT * FROM annonces "
					+ "GROUP BY noBillet, date, idPublication "
					+ "ORDER BY noBillet DESC, idPublication DESC"); 

			
			if(!session.nodeExists("/etc/tc")){
				tc = JcrUtil.createPath("/etc/tc","sling:OrderedFolder",session);
				tcDam = JcrUtil.createPath("/content/dam/tc","sling:OrderedFolder",session);
				session.save();
			}else{
				tc = session.getNode("/etc/tc");
				tcDam = session.getNode("/content/dam/tc");
			}
			if(!session.nodeExists("/etc/tc/ads")){
				parent = JcrUtil.createPath(tc.getPath()+"/ads","sling:OrderedFolder",session);
				parentDam = JcrUtil.createPath(tcDam.getPath()+"/ads","sling:OrderedFolder",session);
				session.save();
			}else{
				parent = session.getNode("/etc/tc/ads");
				parentDam = session.getNode("/content/dam/tc/ads");
			}
			while(resultSet.next()){
				DateTime date = null;
				if(resultSet.getDate("derniereModif")!=null){
					date = new DateTime(resultSet.getDate("derniereModif"));
				}else{
					date = new DateTime();
				}

				Node nodeTree = createContentStructure(parent,date.getYear(), date.getMonthOfYear(), date.getDayOfMonth(), session);
				//Node nodeTreeDam = createContentStructure(parentDam,date.getYear(), date.getMonthOfYear(), date.getDayOfMonth(), session);
				Node ad = null;

				if(nodeTree!=null){
					String noAnnonce = resultSet.getString("noAnnonce");
					//we need to search for this node in all /etc/tc/ads to avoid duplicates
					if(!session.nodeExists(nodeTree.getPath()+"/"+noAnnonce)){
						ad = JcrUtil.createPath(nodeTree.getPath()+"/"+noAnnonce,NodeType.NT_UNSTRUCTURED,session);
						ad.setProperty("sling:resourceType", "tc/components/content/adfinder/adcomponent");
						if(isNotBlank(resultSet.getString("date"))){
							myCal.setTime(resultSet.getDate("date"));
							ad.setProperty("publicationDate", myCal);
							ad.setProperty("startDate", myCal);
						}
						
						//Adds 30 days to expiration date
						myCal.setTime(resultSet.getDate("date"));
						myCal.add(Calendar.DAY_OF_MONTH, 30);
						ad.setProperty("expirationDate", myCal);
						ad.setProperty("endDate", myCal);
						ad.setProperty("adFinder", true);
						
						if(isNotBlank(resultSet.getString("noClient"))){
							ad.setProperty("advertiserId", resultSet.getString("noClient"));
						}
						if(isNotBlank(resultSet.getString("idPublication"))){
							ad.setProperty("sharedSites", "share:"+resultSet.getString("idPublication"));
						}
						if(isNotBlank(resultSet.getString("noBillet"))){
							ad.setProperty("ticketNumber", resultSet.getString("noBillet"));
						} 
						session.save(); 
						if (process.singleProcess(session,parentDam.getPath(),"AW",resultSet.getString("noBillet")+".jpg")){
						
							LOG.info("File imported:"+parentDam.getPath()+"/"+resultSet.getString("noBillet")+".jpg");
							Node image = JcrUtil.createPath(ad.getPath()+"/image",NodeType.NT_UNSTRUCTURED,session);
							image.setProperty("sling:resourceType", "foundation/components/image");
							image.setProperty("fileReference", parentDam.getPath()+"/"+resultSet.getString("noBillet")+".jpg");
							session.save();
							
						}
					}
				}
			}
			resultSet.close();
			process.disconnect();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try{
				connection.close();
				LOG.info("importAdvertisers : Connection Closed");
			}catch (SQLException e) { 
				e.printStackTrace();
			}
		}

	}

	private Connection getConnection(){
		DataSource dataSource = null;
		Connection con = null;
		try{
			dataSource = (DataSource) source.getDataSource("advertisers_ds");
            LOG.info("----- dataSource is " + dataSource);
			con = dataSource.getConnection();
            LOG.info("----- con is " + con);
			return con;

		}
		catch (Exception e){
            LOG.error("------ Connection error; check your settings in Day Commons JDBC Connections Pool");
			e.printStackTrace(); 
		}
		return null; 
	}


	private Node createContentStructure(Node parent, int year, int month, int day, Session session ){

		Node yearNode = null; 
		Node monthNode = null;
		Node dayNode = null;
		try {
			if(!session.nodeExists(parent.getPath()+"/"+year)){
				yearNode = JcrUtil.createPath(parent.getPath()+"/"+year,"sling:OrderedFolder",session);
				session.save();
			}else{
				yearNode = session.getNode(parent.getPath()+"/"+year);
			}
			if(!session.nodeExists(yearNode.getPath()+"/"+month)){
				monthNode = JcrUtil.createPath(yearNode.getPath()+"/"+month,"sling:OrderedFolder",session);
				session.save();
			}else{
				monthNode = session.getNode(yearNode.getPath()+"/"+month);
			}
			if(!session.nodeExists(monthNode.getPath()+"/"+day)){
				dayNode = JcrUtil.createPath(monthNode.getPath()+"/"+day,"sling:OrderedFolder",session);
				session.save();
			}else{
				dayNode = session.getNode(monthNode.getPath()+"/"+day);
			}
			return session.getNode(dayNode.getPath());

		} catch (ItemExistsException e) {
			e.printStackTrace();
		} catch (PathNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchNodeTypeException e) {
			e.printStackTrace();
		} catch (LockException e) {
			e.printStackTrace();
		} catch (VersionException e) {
			e.printStackTrace();
		} catch (ConstraintViolationException e) {
			e.printStackTrace();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}

		return null;
	}


}
