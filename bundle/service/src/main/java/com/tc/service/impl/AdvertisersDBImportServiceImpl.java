package com.tc.service.impl;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
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

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.commons.datasource.poolservice.DataSourcePool;
import com.day.cq.commons.jcr.JcrUtil;
import com.tc.service.api.AdvertisersDBImportService;
import com.tc.service.api.BaseService;

@Component(label = "TC Content Import Service", description = "TC Content Import Service either imports individual xml files or zip files containing multiple xml files", immediate = true, metatype = true)
@Properties({
	@Property(name = "service.pid", value = "com.tc.service.api.AdvertisersDBImportServiceImpl", propertyPrivate = false),
	@Property(name = "service.description", value = "Sync content from Advertisers DB to AEM", propertyPrivate = false) })
@Service({ AdvertisersDBImportService.class })

public class AdvertisersDBImportServiceImpl extends BaseService  implements AdvertisersDBImportService {

	@Reference
	private DataSourcePool source;


	private static final Logger LOG = LoggerFactory
			.getLogger(TCFileImportServiceImpl.class);

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
				LOG.info(nodeTree.getPath());
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
					"SELECT * FROM annonces "
					+ "GROUP BY noBillet, date, idPublication "
					+ "ORDER BY noBillet DESC, idPublication DESC"); 

			
			if(!session.nodeExists("/etc/tc")){
				tc = JcrUtil.createPath("/etc/tc","sling:OrderedFolder",session);
				session.save();
			}else{
				tc = session.getNode("/etc/tc");
			}
			if(!session.nodeExists("/etc/tc/ads")){
				parent = JcrUtil.createPath(tc.getPath()+"/ads","sling:OrderedFolder",session);
				session.save();
			}else{
				parent = session.getNode("/etc/tc/ads");
			}
			while(resultSet.next()){
				DateTime date = null;
				if(resultSet.getDate("derniereModif")!=null){
					date = new DateTime(resultSet.getDate("derniereModif"));
				}else{
					date = new DateTime();
				}

				Node nodeTree = createContentStructure(parent,date.getYear(), date.getMonthOfYear(), date.getDayOfMonth(), session);
				LOG.info(nodeTree.getPath());
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
						/*
						 * 
							adCategory
							keywords
							displayName
							advertisersKeywords
							photos
						 */
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

	private Connection getConnection(){
		DataSource dataSource = null;
		Connection con = null;
		try{
			dataSource = (DataSource) source.getDataSource("advertisers_ds");
			con = dataSource.getConnection();
			return con;

		}
		catch (Exception e){
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
