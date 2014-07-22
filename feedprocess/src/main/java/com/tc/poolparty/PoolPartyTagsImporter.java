/**
 * 
 */
package com.tc.poolparty;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.tc.poolparty.impl.PoolPartyBean;
import com.tc.poolparty.impl.PoolPartyManagerImpl;

/**
 * The Class PoolPartyTagsImporter.
 * 
 * @author gdinakar
 */
public class PoolPartyTagsImporter {
	private static int indent = 2;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InputStream poolPartyPropsStream = PoolPartyTagsImporter.class
				.getClassLoader().getResourceAsStream("poolparty.properties");
		String locale = null;
		if (args != null) {
			locale = args[0];
		}
		Properties poolPartyProps = new Properties();
		try {
			poolPartyProps.load(poolPartyPropsStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PoolPartyManager poolPartyManager = new PoolPartyManagerImpl(
				poolPartyProps);
		String topConcepts = poolPartyProps.get("poolparty.topconcepts").toString();
		if (StringUtils.isEmpty(locale)) {
			locale = poolPartyProps.get("poolparty.language").toString();
		}
		List<PoolPartyBean> listOfTags = poolPartyManager.getTags(topConcepts, true, locale);
		
		//logTags(listOfTags);
		
		if (listOfTags != null && listOfTags.size() > 0 ) {
			poolPartyManager.createTags(listOfTags);	
		}
		
	}

	static Logger LOG = Logger.getLogger(PoolPartyTagsImporter.class);
	public static void logTags(List<PoolPartyBean> listOfTags) {
		for (int i = 0; i < listOfTags.size(); i++) {
			PoolPartyBean bean = listOfTags.get(i);
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < indent; j++) {
				sb.append(" ");
			}
			sb.append(bean.getKey());
			LOG.info(sb.toString());
			
			if (bean.getTags() != null && bean.getTags().size() > 0) {
				indent +=2;
				logTags(bean.getTags());
				indent -=2;
			}
			
		}
		
	}
}
