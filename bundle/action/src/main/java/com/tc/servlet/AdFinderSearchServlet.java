/**
 * 
 */
package com.tc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tc.util.CommonUtils;

/**
 * The Class AdFinderSearchServlet.
 * 
 * @author gdinakar
 */

@SlingServlet(paths = { "/bin/adFinderSearchResultsServlet" })
@Properties({
		@Property(name = "service.pid", value = "com.tc.servlet.AdFinderSearchServlet", propertyPrivate = false),
		@Property(name = "service.description", value = "AdFinder SearchServlet Servlet", propertyPrivate = false),
		@Property(name = "service.vendor", value = "tc", propertyPrivate = false) })
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

		request.setAttribute("advertiserId",
				request.getParameter("advertiserId"));
		LOG.info("advertiserId from request = "
				+ request.getParameter("advertiserId"));
		try {
			CommonUtils.getRequestDispatcher(request, response,
					request.getParameter("resultPath") + ".html");
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

	}
}
