package com.tc.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.Value;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tc.model.AdBean;
import com.tc.util.Constants;

public class AdAction extends BaseAction {

	private static final Logger LOG = LoggerFactory.getLogger(AdAction.class);

	public AdBean getAdInfo() {
		AdBean adBean = new AdBean();
		try {
			Node adNode = getCurrentNode();
			if (null != adNode) {
				if (adNode.hasProperty(Constants.ADCOMPONENT.PUBLICATION_DATE)) {
					Date pubDate = parseDate(convert(adNode.getProperty(
							Constants.ADCOMPONENT.PUBLICATION_DATE).getString()));
					adBean.setPublicationDate(getDateAsString(pubDate));
				}
				if (adNode.hasProperty(Constants.ADCOMPONENT.EXPIRATION_DATE)) {
					Date expDate = parseDate(convert(adNode.getProperty(
							Constants.ADCOMPONENT.EXPIRATION_DATE).getString()));
					adBean.setExpirationDate(getDateAsString(expDate));
				}
				if (adNode.hasProperty(Constants.ADCOMPONENT.ADVERTISER_ID)) {
					adBean.setAdvertiserId(adNode.getProperty(
							Constants.ADCOMPONENT.ADVERTISER_ID).getString());
				}
				if(adNode.hasProperty(Constants.ADCOMPONENT.AD_CATEGORY)){
					adBean.setAdCategory(adNode.getProperty(Constants.ADCOMPONENT.AD_CATEGORY).getString());
				}
				
				if(adNode.hasProperty(Constants.ADCOMPONENT.AD_FINDER)){
					if (StringUtils.isNotBlank(adNode.getProperty(Constants.ADCOMPONENT.AD_FINDER)
	                        .getString())) {
						adBean.setAdFinder(adNode.getProperty(Constants.ADCOMPONENT.AD_FINDER).getString());
	                }
				}
				if (adNode.hasProperty(Constants.ADCOMPONENT.START_DATE)) {
					Date startDate = parseDate(convert(adNode.getProperty(
							Constants.ADCOMPONENT.START_DATE).getString()));
					adBean.setStartDate(getDateAsString(startDate));
				}
				if (adNode.hasProperty(Constants.ADCOMPONENT.END_DATE)) {
					Date endDate = parseDate(convert(adNode.getProperty(
							Constants.ADCOMPONENT.END_DATE).getString()));
					adBean.setEndDate(getDateAsString(endDate));
				}
				if(adNode.hasProperty(Constants.ADCOMPONENT.DISPLAY_NAME)){
					adBean.setDisplayName(adNode.getProperty(Constants.ADCOMPONENT.DISPLAY_NAME).getString());
				}
				if(adNode.hasProperty(Constants.ADCOMPONENT.SITE)){
					adBean.setSite(adNode.getProperty(Constants.ADCOMPONENT.SITE).getString());
				}
				
				if(adNode.hasProperty(Constants.ADCOMPONENT.KEYWORDS)){
					adBean.setKeywords(getTags(adNode.getProperty(Constants.ADCOMPONENT.KEYWORDS)));
				}
				if(adNode.hasProperty(Constants.ADCOMPONENT.SHARED_SITES)){
					adBean.setSharedSites(getTags(adNode.getProperty(Constants.ADCOMPONENT.SHARED_SITES)));
				}
				
				
			}

		} catch (Exception e) {
			LOG.error("Error is",e);

		}

		return adBean;

	}

	private Collection<String> getTags(Property property) {

		Node node = getCurrentNode();
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

	private String convert(String dateAndTime) {
		String date = dateAndTime.substring(0, dateAndTime.indexOf("T"));
		String time = dateAndTime.substring(dateAndTime.indexOf("T") + 1,
				dateAndTime.length());
		String fromDate = date.concat(" ").concat(time);
		return fromDate;
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

	private String getDateAsString(Date date) {

		if (date == null) {
			return "";
		}
		DateFormat df = DateFormat.getDateInstance(DateFormat.LONG,
				Locale.CANADA);
		return df.format(date);
	}

}
