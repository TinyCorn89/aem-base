package com.tc.servlet;

import java.io.IOException;
import java.io.StringWriter;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import org.apache.commons.lang.StringUtils;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.jackrabbit.commons.JcrUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tc.framework.util.Constants;


@SlingServlet(paths = { "/bin/services/ads-list" }, methods = { "GET",
		"POST" }, metatype = true)
@Properties({ 
	@Property(name = "service.pid", value = "com.tc.servlet.AdvertisersListServlet", propertyPrivate = false), 
	@Property(name = "service.description", value = "Ads List Servlet", propertyPrivate = false),
	@Property(name = "service.vendor", value = "tc", propertyPrivate = false)
})


public class AdvertisersListServlet extends BaseSlingServlet {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory
			.getLogger(AdvertisersListServlet.class);
	
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws IOException {
		doPost(request, response);
	}

	protected void doPost(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", Constants.NOCACHE_HEADER);
        response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("q");
		if (StringUtils.equals(action,"advertisers") || StringUtils.equals(action,"ads")) {
			action = action.toLowerCase();
			
			Session session = getSession(request);
			try {
                String allNodes = getAllNodes(action, session);
                if (allNodes != null) {
                	response.setContentType(Constants.APPLICATION_JSON);
                    response.getWriter().write(allNodes);
                }
            } catch (Exception e) {
                LOG.error("Exception in doPost method of AdvertisersListServlet", e);
            }
			
		}
	}
	

    /**
     * Gets the all properties.
     *
     * @return
     */
    protected String getAllNodes(String action, Session session) {
        JSONObject jsonObj = new JSONObject();
        JSONArray array = new JSONArray();
        StringWriter writer = new StringWriter();
        
		try {
			LOG.error("enter in : " + action);
			QueryManager queryManager = session.getWorkspace().getQueryManager();
			StringBuilder queryString = new StringBuilder("SELECT p.* FROM [nt:base] AS p WHERE ISDESCENDANTNODE(p,[/etc/tc/"+action+"])");
			if(StringUtils.equals(action,"advertisers") ){
				queryString.append(" AND p.[sling:resourceType] = 'tc/components/content/adfinder/advertiser'");
				queryString.append(" ORDER BY p.[name1] DESC");
			}else{
				queryString.append(" AND p.[sling:resourceType] = 'tc/components/content/adfinder/adcomponent'");
				queryString.append(" ORDER BY p.[ticketNumber] DESC");
			}
			LOG.error(queryString.toString());
			Query query = queryManager.createQuery(queryString.toString(), Query.JCR_SQL2);
			QueryResult result = query.execute();
			if (result != null) {
				for (Node node : JcrUtils.getNodes(result)) {
					 JSONObject curObj = new JSONObject();
		                try {
		                	if(StringUtils.equals(action,"advertisers") ){
		                		String name1 ="";
		                		if(node.getProperty("name1").getString()!=null){
		                			name1 = JSONObject.quote(node.getProperty("name1").getString());
		                		}
		                		String province ="";
		                		if(node.getProperty("province").getString()!=null){
		                			province = JSONObject.quote(node.getProperty("province").getString());
		                		}
		                		String address ="";
		                		if(node.getProperty("address").getString()!=null){
		                			address = JSONObject.quote(node.getProperty("address").getString());
		                		}
		                		String city ="";
		                		if(node.getProperty("city").getString()!=null){
		                			city = JSONObject.quote(node.getProperty("city").getString());
		                		}
		                		
			                    curObj.put("advertiserID",node.getName());
			                    curObj.put("path",node.getPath());
			                    curObj.put("name1",name1);
			                    curObj.put("province",province);
			                    curObj.put("address",address);
			                    curObj.put("city",city);
		                	}else{
			                    curObj.put("adId", node.getName() );
			                    curObj.put("path", node.getPath());
			                    curObj.put("ticketNumber",node.getProperty("ticketNumber").getString());
			                    curObj.put("advertiserId",node.getProperty("advertiserId").getString());
			                    curObj.put("publicationDate",node.getProperty("publicationDate").getString());
			                    curObj.put("expirationDate",node.getProperty("expirationDate").getString());
		                	}
		                    array.put(curObj);
		                } catch (JSONException e) {
		                    LOG.error("JSONException ", e);
		                }
				}
			}
			try {
	            jsonObj.put(Constants.RESULT, array);
	            jsonObj.write(writer);
	        } catch (JSONException je) {
	            LOG.error("exception in getAllNodes", je);
	        }
			
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
        
        
		return writer.getBuffer().toString();
    }

}