package com.tc.servlet;

import java.io.IOException;
import java.net.URI;

import org.apache.commons.lang.StringUtils;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.mcm.api.newsletter.NewsLetter;
import com.day.cq.mcm.api.newsletter.NewsletterService;
import com.day.cq.retriever.RetrieverService;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.tc.service.api.NewsLetterService;
import com.tc.service.impl.ThindataEmailRetrieverStorage;

@SlingServlet(paths = { "/bin/services/thindataservlet" }, methods = { "GET",
		"POST" }, metatype = true)
@Properties({ @Property(name = "service.pid", value = "com.tc.servlet.ThindataNewsLetterSendServlet", propertyPrivate = false) })
public class ThindataNewsLetterServlet extends BaseSlingServlet {
	private static final Logger LOG = LoggerFactory
			.getLogger(ThindataNewsLetterServlet.class);

	@Reference(referenceInterface = NewsLetterService.class, bind = "setNewsLetter", unbind = "unSetNewsLetter", cardinality = ReferenceCardinality.MANDATORY_UNARY)
	private NewsLetterService newsLetter;

	@Reference(referenceInterface = NewsletterService.class, bind = "setAemNewsLetter", unbind = "unSetAemNewsLetter", cardinality = ReferenceCardinality.MANDATORY_UNARY)
	private NewsletterService aemNewsLetterService;

	@Reference(referenceInterface = RetrieverService.class, bind = "setRetrieverService", unbind = "unSetRetrieverService", cardinality = ReferenceCardinality.MANDATORY_UNARY)
	private RetrieverService retriever;

	protected void doPost(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws IOException {
		doGet(request, response);
	}

	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws IOException {

		
		String action = request.getParameter("action");
		
		LOG.info("action = " + action);
		if (StringUtils.equalsIgnoreCase(action, "broadcast")) {
			broadCastNewsLetter(request);
		}
		

	}

	private void broadCastNewsLetter(SlingHttpServletRequest request) {
		
		String newsLetterPageUrl = request.getParameter("url");
		String mailListName = request.getParameter("mailListName");
		String siteUrl = request.getParameter("siteUrl");
		String schedule = request.getParameter("date");
		
		if (StringUtils.isEmpty(newsLetterPageUrl)) {
			LOG.error("newsLetterPageUrl parameter is empty, cannot send news letter");
			return;
		}
		if (StringUtils.isEmpty(mailListName)) {
			LOG.error("mailListName parameter is empty, cannot send news letter");
			return;
		}
		if (StringUtils.isEmpty(siteUrl)) {
			LOG.error("siteUrl parameter is empty, cannot send news letter");
			return;
		}
		
		LOG.info("newsLetterPageUrl=" + newsLetterPageUrl);
		LOG.info("mailListName=" + mailListName);
		LOG.info("scheduleDate=" + schedule);

		Resource resource = request.getResourceResolver().resolve(
				newsLetterPageUrl);

		try {
			NewsLetter news = null;
			PageManager pmgr = (PageManager) request.getResourceResolver()
					.adaptTo(PageManager.class);
			Page page = pmgr.getContainingPage(resource);

			news = aemNewsLetterService.buildNewsletter(page);
			String subject = news.getSubject();
			LOG.info(subject);
			if (siteUrl.endsWith("/")) {
				siteUrl = siteUrl.substring(0, siteUrl.length()-1);
			}
			LOG.info("siteUrl=" + siteUrl);
			
			URI uri = new URI(siteUrl + newsLetterPageUrl);
			String baseUri = uri.resolve("/").toString();
			ThindataEmailRetrieverStorage thindataRetriever = new ThindataEmailRetrieverStorage();
			retriever.retrieve(uri.toString(), baseUri, thindataRetriever);
			String htmlContent = thindataRetriever.getContent();

			
			if (StringUtils.isEmpty(schedule)) {
				newsLetter.broadcast(subject, htmlContent, mailListName);
			} else {
				newsLetter.schedule(schedule, subject, htmlContent, mailListName);
			}
			

		} catch (Exception e) {
			LOG.error("Error sending newsletter", e);
		}
	}

	protected void setRetrieverService(RetrieverService retriever) {
		if (this.retriever == null) {
			this.retriever = retriever;
		}
	}

	protected void unSetRetrieverService(RetrieverService retriever) {
		if (this.retriever != null) {
			this.retriever = null;
		}
	}

	protected void setAemNewsLetter(NewsletterService newsLetter) {
		if (this.aemNewsLetterService == null) {
			this.aemNewsLetterService = newsLetter;
		}
	}

	protected void unSetAemNewsLetter(NewsletterService aemNewsLetterService) {
		if (this.aemNewsLetterService != null) {
			this.aemNewsLetterService = null;
		}
	}

	protected void setNewsLetter(NewsLetterService newsLetter) {
		if (this.newsLetter == null) {
			this.newsLetter = newsLetter;
		}
	}

	protected void unSetNewsLetter(NewsLetterService newsLetter) {
		if (this.newsLetter != null) {
			this.newsLetter = null;
		}
	}
}
