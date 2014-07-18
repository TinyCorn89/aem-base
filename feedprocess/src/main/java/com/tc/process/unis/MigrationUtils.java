package com.tc.process.unis;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class MigrationUtils {
	static Logger LOG = Logger.getLogger(MigrationUtils.class);
	static Properties props = null; 
	
	public static void clearDirectory(File dir) {
		for (File file : dir.listFiles()) {
			if (file.isDirectory())
				clearDirectory(file);
			file.delete();
		}
	}

	public static Properties loadProperties(String propertiesFileName) {
		if (props != null) {
			return props;
		}
		
		props = new Properties();
		InputStream propsStream = null;
		try {
			propsStream = MigrationUtils.class.getClassLoader()
					.getResourceAsStream(propertiesFileName);
			props.load(propsStream);
			
			return props;
			
		} catch (Exception e) {
			LOG.error("error while loading properties propertiesFileName", e);
			return null;
		} finally {
			if (propsStream != null) {
				try {
					propsStream.close();
				} catch (IOException e) {
					LOG.error(e);
				}
			}
		}
	}
	
	
	public static Properties getProperties(String unisType) {
		if (props == null) {
			LOG.error("No properties loaded");
			return null;
		}
		Properties subProperties = new Properties();
		Iterator<Object> propsIterator = props.keySet().iterator();
		if (propsIterator != null) {
			while(propsIterator.hasNext()) {
				String key = (String) propsIterator.next();
				if (StringUtils.startsWithIgnoreCase(key, unisType + ".")) {
					subProperties.put(key, props.get(key));
				}
			}
		}
		
		return subProperties;
	}
}
