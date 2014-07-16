package com.tc.poolparty.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.tc.poolparty.PoolPartyManager;

public class PoolPartyManagerImpl implements PoolPartyManager {

	private Properties poolPropertyProperties;
	static Logger LOG = Logger.getLogger(PoolPartyManagerImpl.class);

	public PoolPartyManagerImpl(Properties poolPropertyProperties) {
		this.poolPropertyProperties = poolPropertyProperties;
	}

	@Override
	public void createTags(List<String> tags) {

	}

	@Override
	public List<String> getTags() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> crawlText(String text) {
		String charSet = "UTF-8";
		String urlStr = poolPropertyProperties
				.getProperty("poolparty.crawlerurl");
		String user = poolPropertyProperties.getProperty("poolparty.userId");
		String password = poolPropertyProperties
				.getProperty("poolparty.password");
		String projectId = poolPropertyProperties
				.getProperty("poolparty.projectId");
		String language = poolPropertyProperties
				.getProperty("poolparty.language");

		LOG.info(urlStr + "--" + user + "--" + projectId + "--" + language);
		try {
			
			StringBuffer query = new StringBuffer(String.format("text=%s", 
	                   URLEncoder.encode(text, charSet)));
			query.append("&").append(String.format("projectId=%s", 
	                   URLEncoder.encode(projectId, charSet)));
			
			query.append("&").append(String.format("language=%s", 
	                   URLEncoder.encode( language, charSet)));
			URL url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			String userpassword = user + ":" + password;
			String encoded = "Basic "
					+ DatatypeConverter.printBase64Binary(userpassword.getBytes());
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization", encoded);
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			connection.setRequestProperty("content-type",
					"application/x-www-form-urlencoded");
			connection.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(query.toString());
			wr.flush();
			wr.close();
			

			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			String line;
			StringBuilder content = new StringBuilder();

			// read from the urlconnection via the bufferedreader
			while ((line = in.readLine()) != null) {
				content.append(line + "\n");
			}
			LOG.debug(content.toString());
			
			in.close();	
			
			List<String> tags = getTagsFromJson(content.toString());
			return tags;
			
		} catch (Exception e) {
			LOG.error("Error while crawling", e);
		}
		

		return null;
	}
	
	
	private List<String> getTagsFromJson(String content) {
		List<String> tags = new ArrayList<String>();
		JSONObject root = new JSONObject(content);
		JSONArray jsonArray = root.getJSONArray("categories");
		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject obj = jsonArray.getJSONObject(i);
				
				String tag = obj.getString("prefLabel");
				
				tags.add(tag);
			}
		}
		
		return tags;
	}

}
