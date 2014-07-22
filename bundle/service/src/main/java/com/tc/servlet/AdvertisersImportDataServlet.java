package com.tc.servlet;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.jcr.contentloader.ContentImporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tc.service.api.AdvertisersDBImportService;
import com.tc.service.api.NewsLetterService;

@SlingServlet(paths = { "/bin/services/ads-import" }, methods = { "GET",
		"POST" }, metatype = true)
@Properties({ 
	@Property(name = "service.pid", value = "com.tc.servlet.AdvertisersImportDataServlet", propertyPrivate = false), 
	@Property(name = "service.description", value = "AdFinder Import Servlet", propertyPrivate = false),
	@Property(name = "service.vendor", value = "tc", propertyPrivate = false)
})


public class AdvertisersImportDataServlet extends BaseSlingServlet {
	
	
	@Reference(referenceInterface = AdvertisersDBImportService.class, bind = "setAdvertiserImport", unbind = "unSetAdvertiserImpor", cardinality = ReferenceCardinality.MANDATORY_UNARY)
	private AdvertisersDBImportService importer;
	
	private static final Logger LOG = LoggerFactory
			.getLogger(ThindataNewsLetterServlet.class);
	
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws IOException {
		doPost(request, response);
	}

	protected void doPost(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws IOException {
		
		String action = request.getParameter("action");
		
		if (StringUtils.equalsIgnoreCase(action, "advertisers")) {
			importer.importAdvertisers();
			LOG.info("Into the action = " + action);
			
		}else if (StringUtils.equalsIgnoreCase(action, "ads")) {
			importer.importAds();
			LOG.info("Into the action = " + action);
		}

	}
	
	protected void setAdvertiserImport(AdvertisersDBImportService importer) {
		if (this.importer == null) {
			this.importer = importer;
		}
	}

	protected void unSetAdvertiserImpor(AdvertisersDBImportService importer) {
		if (this.importer != null) {
			this.importer = null;
		}
	}

}