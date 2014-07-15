package com.tc.process.pressfeed;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class CanadianFeedEntityResolver implements EntityResolver {
	Map entityCache;

	public CanadianFeedEntityResolver() {
		entityCache = Collections.synchronizedMap(new HashMap());
	}

	public InputSource resolveEntity(String publicId, String systemId)
			throws SAXException, IOException {
		
		StringReader stringInput = new StringReader(" ");
		return new InputSource(stringInput);
	}

}
