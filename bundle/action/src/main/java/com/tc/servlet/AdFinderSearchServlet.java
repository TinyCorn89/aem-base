/**
 * 
 */
package com.tc.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.query.InvalidQueryException;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
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
import com.day.cq.search.eval.JcrPropertyPredicateEvaluator;
import com.day.cq.search.result.SearchResult;
import com.tc.framework.api.ManagerProvider;
import com.tc.model.AdFinderSearchResultBean;
import com.tc.model.AdFinderSearchResultsBean;
import com.tc.util.CommonUtils;

/**
 * The Class AdFinderSearchServlet.
 * 
 *
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
		doPost(req, resp);
	}

	protected void doPost(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {

		LOG.info("Enetered in to AdFinderSearchServlet class");

		request.setAttribute("advertisersKeywords",
				request.getParameter("advertisersKeywords"));
		request.setAttribute("daterange", request.getParameter("daterange"));
		request.setAttribute("serchPath", request.getParameter("serchPath"));
		LOG.info("advertisersKeywords from request = "
				+ request.getParameter("advertisersKeywords"));

		

		try {
			List<AdFinderSearchResultsBean> listOfadvertiesrs = getResultpage(request);

			request.setAttribute("adFinderSearchResults", listOfadvertiesrs);
			CommonUtils.getRequestDispatcher(request, response,
					request.getParameter("resultPath") + ".html");
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

	}

	private List<AdFinderSearchResultsBean> getResultpage(
			SlingHttpServletRequest request) throws InvalidQueryException, RepositoryException {
         String path = request.getParameter("serchPath");
		List<AdFinderSearchResultBean> tempListOfads = getAds1(request);
		String keyword = request.getParameter("advertisersKeywords");
		//QueryBuilder builder = ManagerProvider.getManager(QueryBuilder.class);
		//Map<String, Object> predicateMap1 = new LinkedHashMap<String, Object>();
		
		ResourceResolver resolver = request.getResourceResolver();
		Session session = resolver.adaptTo(Session.class);
		StringBuffer xpath = new StringBuffer();
		///etc/tc/advertisers//element(*, nt:unstructured)[jcr:contains(., 'KENT')]
		if(keyword.isEmpty()){
			xpath.append("/jcr:root/"+ path+" /advertisers//element(*, nt:unstructured)");
		}else{
			xpath.append("/jcr:root/"+ path+"/advertisers//element(*, nt:unstructured)[jcr:like(name1, '%"+keyword+"%')]");
			
		}
		String xpathStatement = xpath.toString();
		QueryManager queryManager = session.getWorkspace().getQueryManager();
		javax.jcr.query.Query query =  queryManager.createQuery(xpathStatement, "xpath");
		QueryResult queryResult = query.execute();
		AdFinderSearchResultsBean adFinderSearchResultsBean = null;
		
		List<AdFinderSearchResultsBean> listOfadvertiesrs = new ArrayList<AdFinderSearchResultsBean>();
		Iterator<Node> advertisers;
		try {
			advertisers = queryResult.getNodes();
		
		List<AdFinderSearchResultBean> adslist = null;
		while (advertisers.hasNext()) {
			adslist = new ArrayList<AdFinderSearchResultBean>();
			adFinderSearchResultsBean = new AdFinderSearchResultsBean();
			Node advertiser = advertisers.next();
			try {
				String advertiserID = advertiser.getName();
				if (advertiser.hasProperty("name1")) {
					adFinderSearchResultsBean.setName(advertiser.getProperty(
							"name1").getString());
				}
				if (advertiser.hasProperty("address")) {
					adFinderSearchResultsBean.setAdress(advertiser.getProperty(
							"address").getString());
				}
				if (advertiser.hasProperty("city")) {
					adFinderSearchResultsBean.setCity(advertiser.getProperty(
							"city").getString());
				}
				if (advertiser.hasProperty("zipCode")) {
					adFinderSearchResultsBean.setZipcode(advertiser
							.getProperty("zipCode").getString());
				}

				for (int i = 0; i < tempListOfads.size(); i++) {
					AdFinderSearchResultBean adFinderSearchResultBean = tempListOfads
							.get(i);
					if (advertiserID.equalsIgnoreCase(adFinderSearchResultBean
							.getAdvertiserID())) {
						adslist.add(adFinderSearchResultBean);
					}
				}
				adFinderSearchResultsBean.setListOfAds(adslist);
				if (adslist.size() > 0) {
					listOfadvertiesrs.add(adFinderSearchResultsBean);
				}

			} catch (Exception e) {
				LOG.error("Error is ", e);
			}

		}} catch (RepositoryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return listOfadvertiesrs;
	}

	private List<AdFinderSearchResultBean> getAds1(
			SlingHttpServletRequest request) {
		String path = request.getParameter("serchPath");;
		int dateRange = Integer.parseInt(request.getParameter("daterange"));
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -dateRange);
		String publicationDate = dateFormat.format(cal.getTime());
		QueryBuilder builder = ManagerProvider.getManager(QueryBuilder.class);
		Map<String, Object> predicateMap = new LinkedHashMap<String, Object>();
		predicateMap.put("path",path);
		predicateMap.put("type", "nt:unstructured");
		predicateMap.put("daterange.property", "publicationDate");
		predicateMap.put("daterange.lowerBound", publicationDate);
		predicateMap.put("p.offset", "0"); // same as query.setStart(0) below
		predicateMap.put("p.limit", "200");
		// predicateMap.put("orderby.sor", "desc");
		ResourceResolver resolver = request.getResourceResolver();
		Session session = resolver.adaptTo(Session.class);
		Query query = builder.createQuery(PredicateGroup.create(predicateMap),
				session);
		SearchResult searchResult = query.getResult();
		Iterator<Node> ads = searchResult.getNodes();
		AdFinderSearchResultBean adFinderSearchResultBean = null;
		List<AdFinderSearchResultBean> listOfAds = new ArrayList<AdFinderSearchResultBean>();
		while (ads.hasNext()) {
			adFinderSearchResultBean = new AdFinderSearchResultBean();
			Node adNode = ads.next();
			try {

				adFinderSearchResultBean.setAdID(adNode.getName());
				System.out.println("adNode.getName()" + adNode.getName());
				if (adNode.hasProperty("publicationDate")) {
					adFinderSearchResultBean
							.setPublicationDate(getDateAsString(parseDate(convert(adNode
									.getProperty("publicationDate").getString()))));
				}
				if (adNode.hasProperty("advertiserId")) {
					adFinderSearchResultBean.setAdvertiserID(adNode
							.getProperty("advertiserId").getString());

				}
				if (adNode.hasNode("image")) {
					Node imageNode = adNode.getNode("image");
					if (imageNode.hasProperty("fileReference")) {
						adFinderSearchResultBean.setImagePath(imageNode
								.getProperty("fileReference").getString());
					}

				}

			} catch (Exception e) {
				LOG.error("Error is " + e);
			}
			listOfAds.add(adFinderSearchResultBean);
		}
		predicateMap = null;

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

	private String getDateAsString(Date date) {

		if (date == null) {
			return "";
		}
		DateFormat df = DateFormat.getDateInstance(DateFormat.LONG,
				Locale.CANADA);
		return df.format(date);
	}

}
