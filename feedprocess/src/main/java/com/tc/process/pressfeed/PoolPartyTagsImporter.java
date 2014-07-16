/**
 * 
 */
package com.tc.process.pressfeed;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import com.tc.poolparty.PoolPartyManager;
import com.tc.poolparty.impl.PoolPartyManagerImpl;

/**
 * The Class PoolPartyTagsImporter.
 *
 * @author gdinakar
 */
public class PoolPartyTagsImporter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InputStream poolPartyPropsStream = PoolPartyTagsImporter.class.getClassLoader().getResourceAsStream("poolparty.properties");
		Properties poolPartyProps =new Properties();
		try {
			poolPartyProps.load(poolPartyPropsStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PoolPartyManager poolPartyManager = new PoolPartyManagerImpl(poolPartyProps);
		List<String> listOfTags = poolPartyManager.getTags();
		poolPartyManager.createTags(listOfTags);
	}

}
