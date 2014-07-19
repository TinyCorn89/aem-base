/**
 * 
 */
package com.tc.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;
import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;
import com.tc.framework.api.ManagerProvider;
import com.tc.model.AdFinderSearchResultBean;
import com.tc.util.CommonUtils;

/**
 * The Class AdFinderSearchServlet.
 * 
 * @author gdinakar
 */

@SlingServlet(paths = { "/bin/adFinderSearchResultsServlet" })
@Properties({
		@org.apache.felix.scr.annotations.Property(name = "service.pid", value = "com.tc.servlet.AdFinderSearchServlet", propertyPrivate = false),
		@org.apache.felix.scr.annotations.Property(name = "service.description", value = "AdFinder SearchServlet Servlet", propertyPrivate = false),
		@org.apache.felix.scr.annotations.Property(name = "service.vendor", value = "tc", propertyPrivate = false) })
public class AdFinderSearchServlet extends BaseSlingServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory
			.getLogger(AdFinderSearchServlet.class);

	protected void doGet(SlingHttpServletRequest req,
			SlingHttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet");
		doPost(req, resp);
	}

	protected void doPost(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {
		System.out.println("doPost");
		LOG.info("Enetered in to AdFinderSearchServlet class");

		request.setAttribute("advertiserId",
				request.getParameter("advertiserId"));
		LOG.info("advertiserId from request = "
				+ request.getParameter("advertiserId"));
		
		List<AdFinderSearchResultBean> listOfAdNodes = new ArrayList<AdFinderSearchResultBean>();
		listOfAdNodes = getAds(request);
		System.out.println("listOfAdNodes::"+listOfAdNodes.size());
		System.out.println("listOfAdNodes::"+listOfAdNodes);
		request.setAttribute("adFinderSearchResults", listOfAdNodes);
		try {
			CommonUtils.getRequestDispatcher(request, response,
					request.getParameter("resultPath") + ".html");
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

	}

	/**
	 * Gets the ads.
	 *
	 * @param advertiserId the advertiser id
	 * @return the ads
	 */
	private List<AdFinderSearchResultBean> getAds(SlingHttpServletRequest request) {
		//Map<String, Object> responseMap = new HashMap<String, Object>();
		System.out.println(request.getParameter("advertiserId"));
		QueryBuilder builder = ManagerProvider.getManager(QueryBuilder.class);
		 Map<String, Object> predicateMap = new LinkedHashMap<String, Object>();
		 predicateMap.put("type", "nt:unstructured");
		 predicateMap.put("property", "advertiserId");
		 predicateMap.put("property.value", request.getParameter("advertiserId"));
		 predicateMap.put("path", "/content/tc/ads");
		 
		 ResourceResolver resolver = request.getResourceResolver();
	     Session session = resolver.adaptTo(Session.class);
	
	     Query query = builder.createQuery(PredicateGroup.create(predicateMap), session);
	     SearchResult searchResult = query.getResult();
	     Iterator<Node> iterator = searchResult.getNodes();
	    
	 	AdFinderSearchResultBean adFinderSearchResultBean = null;
	 	Node imageNode = null;
	 	List<AdFinderSearchResultBean> listOfAds = new ArrayList<AdFinderSearchResultBean>();
	    
	 	while (iterator.hasNext()) {
	        Node adNode = (Node) iterator.next();
	        adFinderSearchResultBean = new AdFinderSearchResultBean();
	        try {
	        	System.out.println(adNode.getName());
				if(adNode.hasProperty("displayName")) {
					adFinderSearchResultBean.setTitle(adNode.getProperty("displayName").getString());
				}
				if(adNode.hasProperty("publicationDate")) {
					adFinderSearchResultBean.setPublicationDate(getDateAsString(parseDate(convert(adNode.getProperty("publicationDate").getString()))));
				}
				if(adNode.hasProperty("sharedSites")) {
					adFinderSearchResultBean.setJournal(getTags(adNode.getProperty("sharedSites")));
				}
				if(adNode.hasProperty("keywords")) {
					adFinderSearchResultBean.setKeywords(getTags(adNode.getProperty("keywords")));
				}
				if(adNode.getParent().hasNode("image")) {
					imageNode = adNode.getParent().getNode("image");
					if(imageNode.hasProperty("fileReference")) {
						adFinderSearchResultBean.setImagePath(imageNode.getProperty("fileReference").getString());
					}
				}
				adFinderSearchResultBean.setAdvertiserId(Long.parseLong(request.getParameter("advertiserId")));
				
				listOfAds.add(adFinderSearchResultBean);
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
	     }
	 	//request.setAttribute("adFinderSearchResults", listOfAds);
	 	for(AdFinderSearchResultBean temp: listOfAds){
	 		System.out.println(temp.getJournal());
	 	}
		return listOfAds;
	}
	
	private Date parseDate(String date) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			return format.parse(date);
		} catch (java.text.ParseException e) {
			return new Date(0);
		}
	}

	private String convert(String dateAndTime) {
		String date = dateAndTime.substring(0, dateAndTime.indexOf("T"));
		String time = dateAndTime.substring(dateAndTime.indexOf("T") + 1,
				dateAndTime.length());
		String fromDate = date.concat(" ").concat(time);
		return fromDate;
	}

	private Collection<String> getTags(javax.jcr.Property property) {

		Collection<String> tags = new ArrayList<String>();
		Value[] values = null;
		try {
			values = property.getValues();

			for (Value tagValue : values) {
				tags.add(tagValue.getString());
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return tags;
	}
	
	private String getDateAsString(Date date) {

		if (date == null) {
			return "";
		}
		DateFormat df = DateFormat.getDateInstance(DateFormat.LONG,
				Locale.CANADA);
		return df.format(date);
	}
}
