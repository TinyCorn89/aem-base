package com.tc.action;

import javax.jcr.Node;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tc.model.AdvertiseBean;
import com.tc.util.Constants;

public class AdvertiseAction extends BaseAction {

	
	private static final Logger LOG = LoggerFactory
			.getLogger(AdvertiseAction.class);

	public AdvertiseBean getAdvertiserInfo() {

		AdvertiseBean advertiseBean = new AdvertiseBean();
		try {
			Node adrvertiser = getCurrentNode();
			if (null != adrvertiser) {
				if (adrvertiser.hasProperty(Constants.ADVERTISER.NAME1)) {
					advertiseBean.setName1(adrvertiser.getProperty(
							Constants.ADVERTISER.NAME1).getString());

				}
				if (adrvertiser.hasProperty(Constants.ADVERTISER.NAME2)) {
					advertiseBean.setName2(adrvertiser.getProperty(
							Constants.ADVERTISER.NAME2).getString());
				}
				if (adrvertiser.hasProperty(Constants.ADVERTISER.ADDRESS)) {
					advertiseBean.setAddress(adrvertiser.getProperty(
							Constants.ADVERTISER.ADDRESS).getString());
				}
				if (adrvertiser.hasProperty(Constants.ADVERTISER.CITY)) {
					advertiseBean.setCity(adrvertiser.getProperty(
							Constants.ADVERTISER.CITY).getString());
				}
				if (adrvertiser.hasProperty(Constants.ADVERTISER.PROVINCE)) {
					advertiseBean.setProvince(adrvertiser.getProperty(
							Constants.ADVERTISER.PROVINCE).getString());
				}
				if (adrvertiser.hasProperty(Constants.ADVERTISER.COUNTRY)) {
					advertiseBean.setProvince(adrvertiser.getProperty(
							Constants.ADVERTISER.COUNTRY).getString());

				}
				if (adrvertiser.hasProperty(Constants.ADVERTISER.COUNTRY)) {
					advertiseBean.setCountry(adrvertiser.getProperty(
							Constants.ADVERTISER.COUNTRY).getString());
				}
				if (adrvertiser.hasProperty(Constants.ADVERTISER.ZIPCODE)) {
					advertiseBean.setZipcode(adrvertiser.getProperty(
							Constants.ADVERTISER.ZIPCODE).getString());
				}
				if (adrvertiser.hasProperty(Constants.ADVERTISER.TELEPHONE)) {
					advertiseBean.setTelephone(adrvertiser.getProperty(
							Constants.ADVERTISER.TELEPHONE).getString());

				}
				if (adrvertiser.hasProperty(Constants.ADVERTISER.CLIENT_NUMBER)) {
					advertiseBean.setClientNumber(adrvertiser.getProperty(Constants.ADVERTISER.CLIENT_NUMBER).getString());

				}
			}

		} catch (Exception e) {
			LOG.error("Error message", e);
		}

		return advertiseBean;
	}

}
