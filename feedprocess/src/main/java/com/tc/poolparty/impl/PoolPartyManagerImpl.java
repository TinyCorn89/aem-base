package com.tc.poolparty.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipOutputStream;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.tc.aem.importer.AEMPackageImporter;
import com.tc.poolparty.PoolPartyManager;
import com.tc.process.handler.TCTransformerHandler;

public class PoolPartyManagerImpl implements PoolPartyManager {

	private Properties poolPropertyProperties;
	static Logger LOG = Logger.getLogger(PoolPartyManagerImpl.class);

	public PoolPartyManagerImpl(Properties poolPropertyProperties) {
		this.poolPropertyProperties = poolPropertyProperties;
	}

	@Override
	public void createTags(List<String> tags) {
		String organizationName = poolPropertyProperties
				.getProperty("poolparty.organizationName");
		String destinationMetaInfoDir = poolPropertyProperties
				.getProperty("poolparty.jcrRootParentDir");
		File metaInfDir = new File(destinationMetaInfoDir + File.separator
				+ "META-INF");
		String jcrRootParentDir = poolPropertyProperties
				.getProperty("poolparty.jcrRootParentDir");
		File directory = new File(jcrRootParentDir);
		if (!directory.exists()) {
			LOG.info("Parent folder, " + directory.getName()
					+ " Folder does not exists!");
			directory.mkdirs();
			LOG.info("creatred parent folder, " + directory.getName());
		}
		String tagsDirectory = null;
		File jcrRootDir = new File(directory.getAbsolutePath() + File.separator
				+ "jcr_root");
		if (jcrRootDir.exists()) {
			LOG.info(jcrRootDir.getName() + " is already exists");
			try {
				FileUtils.deleteDirectory(jcrRootDir);
				LOG.info("So deleted " + jcrRootDir.getName());
			} catch (IOException e) {
				LOG.error(e);
			}
			tagsDirectory = createJCRRootDirStructure(jcrRootDir);
		} else {
			tagsDirectory = createJCRRootDirStructure(jcrRootDir);
		}

		String srcMetaInfoDir = poolPropertyProperties
				.getProperty("poolparty.metainffolder");
		createMetaInfoDir(srcMetaInfoDir, destinationMetaInfoDir);
		String destinationFolder = tagsDirectory + File.separator
				+ organizationName;
		File tagsFolder = new File(destinationFolder);
		if (!tagsFolder.exists()) {
			if (tagsFolder.mkdir()) {
				LOG.info(tagsFolder.getName() + " Folder is created!");
			} else {
				LOG.info("Failed to create " + tagsFolder.getName()
						+ " folder!");
			}
		}
		createParentTagContentXML(tags, destinationFolder, organizationName);
		for (String tag : tags) {
			String xmlContent = createXMLContent(tag);
			createContentXML(tag, xmlContent, destinationFolder);
		}
		File jcrRootParentFolder = new File(jcrRootParentDir);
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		String aemPackagePath = jcrRootParentFolder.getAbsolutePath()
				+ File.separator + jcrRootParentFolder.getName() + ".zip";
		try {
			fos = new FileOutputStream(aemPackagePath);
			zos = new ZipOutputStream(fos);
			TCTransformerHandler tcNewLetterTransformerHandler = new TCTransformerHandler();
			tcNewLetterTransformerHandler.addDirToZipArchive(zos, jcrRootDir,
					null, false);
			tcNewLetterTransformerHandler.addDirToZipArchive(zos, metaInfDir,
					null, false);
		} catch (FileNotFoundException fileNotFoundException) {
			LOG.error(fileNotFoundException);
		} catch (IOException ioException) {
			LOG.error(ioException);
		} catch (Exception exception) {
			LOG.error(exception);
		} finally {
			try {
				zos.close();
			} catch (IOException e) {
				LOG.error(e);
			}
		}
		LOG.info("AEM Zip File is Created");
		AEMPackageImporter aemPackageImporter = new AEMPackageImporter();
		InputStream aemInputStream = this.getClass().getClassLoader()
				.getResourceAsStream("aem.properties");
		Properties aemProperties = new Properties();
		try {
			aemProperties.load(aemInputStream);
		} catch (IOException e) {
			LOG.error(e);
		}
		String repoURL = aemProperties.getProperty("aem.url") + "/crx/server";
		String aemUserName = aemProperties.getProperty("aem.userid");
		String aemPassword = aemProperties.getProperty("aem.password");

		boolean uninstallationStatus = aemPackageImporter.uninstallPackage(
				repoURL, aemUserName, aemPassword, aemPackagePath);
		if (uninstallationStatus) {
			LOG.info("Package uninstalled successfully...");
		} else {
			LOG.info("Failed to uninstall the Package...");
		}
		boolean installationStatus = aemPackageImporter.importPackage(repoURL,
				aemUserName, aemPassword, aemPackagePath, false);
		if (installationStatus) {
			LOG.info("Package installed successfully...");
		} else {
			LOG.info("Failed to install the package...");
		}
	}

	/**
	 * Creates the jcr root dir structure with .content.xml files.
	 * 
	 * @param jcrRootDir
	 *            the jcr root dir
	 * @return the string
	 */
	private String createJCRRootDirStructure(File jcrRootDir) {
		String tagsDirectory = null;
		if (jcrRootDir.mkdir()) {
			LOG.info(jcrRootDir.getName() + " is created");
		}
		String jcrRootXMLContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<jcr:root xmlns:sling=\"http://sling.apache.org/jcr/sling/1.0\" xmlns:jcr=\"http://www.jcp.org/jcr/1.0\" xmlns:rep=\"internal\" jcr:mixinTypes=\"[rep:AccessControllable,rep:RepoAccessControllable]\" jcr:primaryType=\"rep:root\" sling:resourceType=\"sling:redirect\" sling:target=\"/index.html\"/>";
		createXMLFile(jcrRootXMLContent, jcrRootDir.getAbsolutePath()
				+ File.separator + ".content.xml");
		File etcDir = new File(jcrRootDir.getAbsolutePath() + File.separator
				+ "etc");
		if (etcDir.mkdir()) {
			LOG.info(etcDir.getName() + " is created");
		}
		String etcXMLContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<jcr:root xmlns:sling=\"http://sling.apache.org/jcr/sling/1.0\" xmlns:jcr=\"http://www.jcp.org/jcr/1.0\" xmlns:rep=\"internal\" jcr:mixinTypes=\"[rep:AccessControllable]\" jcr:primaryType=\"sling:Folder\"/>";
		createXMLFile(etcXMLContent, etcDir.getAbsolutePath() + File.separator
				+ ".content.xml");
		File tagsDir = new File(etcDir.getAbsolutePath() + File.separator
				+ "tags");
		if (tagsDir.mkdir()) {
			LOG.info(tagsDir.getName() + " is created");
		}
		String tagsXMLContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<jcr:root xmlns:sling=\"http://sling.apache.org/jcr/sling/1.0\" xmlns:jcr=\"http://www.jcp.org/jcr/1.0\" xmlns:rep=\"internal\" jcr:mixinTypes=\"[rep:AccessControllable,sling:Redirect]\" jcr:primaryType=\"sling:Folder\" jcr:title=\"Tags\" sling:resourceType=\"sling:redirect\" sling:target=\"/tagging\" hidden=\"{Boolean}true\" languages=\"[en,de,es,fr,it,pt_br,zh_cn,zh_tw,ja,ko_kr]\"/>";
		createXMLFile(tagsXMLContent, tagsDir.getAbsolutePath()
				+ File.separator + ".content.xml");
		tagsDirectory = tagsDir.getAbsolutePath();
		return tagsDirectory;
	}

	/**
	 * Creates the xml file with the given xml content under the given path.
	 * 
	 * @param xmlContent
	 *            the xml content
	 * @param path
	 *            the path
	 */
	private void createXMLFile(String xmlContent, String path) {
		FileWriter fw = null;
		try {
			File xmlFile = new File(path);
			if (!xmlFile.exists()) {
				fw = new java.io.FileWriter(path);
				fw.write(xmlContent);
			} else {
				LOG.info(xmlFile.getName() + " is already exists");
			}
		} catch (IOException e) {
			LOG.error(e);
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				LOG.error(e);
			}
		}

	}

	/**
	 * Creates the meta info dir.
	 * 
	 * @param srcMetaInfoDir
	 *            the src meta info dir
	 * @param destinationMetaInfoDir
	 *            the destination meta info dir
	 */
	private void createMetaInfoDir(String srcMetaInfoDir,
			String destinationMetaInfoDir) {
		File metaInfoDir = new File(srcMetaInfoDir);
		File destinationMetaInfoDirectory = new File(destinationMetaInfoDir
				+ File.separator + "META-INF");
		if (metaInfoDir.exists()) {
			LOG.info("Source " + metaInfoDir.getName() + " is exists");
			if (!destinationMetaInfoDirectory.exists()) {
				try {
					FileUtils.copyDirectoryToDirectory(metaInfoDir, new File(
							destinationMetaInfoDir));
				} catch (IOException e) {
					LOG.error(e);
				}
			} else {
				LOG.info(destinationMetaInfoDirectory.getName()
						+ " is already exists");
				try {
					FileUtils.deleteDirectory(destinationMetaInfoDirectory);
					LOG.info("So deleted "
							+ destinationMetaInfoDirectory.getName());
					FileUtils.copyDirectoryToDirectory(metaInfoDir, new File(
							destinationMetaInfoDir));
					LOG.info("Again copied "
							+ destinationMetaInfoDirectory.getName());
				} catch (IOException e) {
					LOG.error(e);
				}
			}

		} else {
			LOG.info("Source " + metaInfoDir.getName() + " does not exists");
		}

	}

	/**
	 * Creates the parent tag content xml.
	 * 
	 * @param tags
	 *            the tags
	 * @param destinationFolder
	 *            the destination folder
	 * @param organizationName
	 *            the organization name
	 */
	private void createParentTagContentXML(List<String> tags,
			String destinationFolder, String organizationName) {
		String listOfTags = "";
		for (String tag : tags) {
			listOfTags += "<" + tag + "/>\n";
		}
		String xmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<jcr:root xmlns:sling=\"http://sling.apache.org/jcr/sling/1.0\" xmlns:cq=\"http://www.day.com/jcr/cq/1.0\" xmlns:jcr=\"http://www.jcp.org/jcr/1.0\" jcr:description=\"\" jcr:primaryType=\"cq:Tag\" jcr:title=\""
				+ organizationName
				+ "\" sling:resourceType=\"cq/tagging/components/tag\">\n"
				+ listOfTags + "</jcr:root>";
		FileWriter fw = null;
		try {
			File tagFolder = new File(destinationFolder);
			if (tagFolder.exists()) {
				fw = new java.io.FileWriter(destinationFolder + File.separator
						+ ".content.xml");
				fw.write(xmlContent);
				LOG.info("Created .content.xml file for the organization"
						+ organizationName);
			} else {
				LOG.info(tagFolder.getName() + " folder is not availabe");
			}
		} catch (IOException e) {
			LOG.error(e);
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				LOG.error(e);
			}
		}

	}

	/**
	 * Creates the xml content for the given tag.
	 * 
	 * @param tag
	 *            the tag
	 * @return the string
	 */
	private String createXMLContent(String tag) {
		String xmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<jcr:root xmlns:sling=\"http://sling.apache.org/jcr/sling/1.0\" xmlns:cq=\"http://www.day.com/jcr/cq/1.0\" xmlns:jcr=\"http://www.jcp.org/jcr/1.0\" jcr:description=\"\" jcr:primaryType=\"cq:Tag\" jcr:title=\""
				+ tag + "\" sling:resourceType=\"cq/tagging/components/tag\"/>";
		return xmlContent;
	}

	/**
	 * Creates the content xml file for the given tag under the given folder
	 * with the given content.
	 * 
	 * @param tag
	 *            the tag
	 * @param xmlContent
	 *            the xml content
	 * @param destinationFolder
	 *            the destination folder
	 */
	private void createContentXML(String tag, String xmlContent,
			String destinationFolder) {
		createTagFolder(tag, destinationFolder);
		FileWriter fw = null;
		try {
			File tagFolder = new File(destinationFolder + File.separator + tag);
			if (tagFolder.exists()) {
				fw = new java.io.FileWriter(destinationFolder + File.separator
						+ tag + File.separator + ".content.xml");
				fw.write(xmlContent);
			} else {
				LOG.info(tagFolder.getName() + " folder is not availabe");
			}
		} catch (IOException e) {
			LOG.error(e);
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				LOG.error(e);
			}
		}
	}

	/**
	 * Creates the tag folder.
	 * 
	 * @param tag
	 *            the tag
	 * @param destinationFolder
	 *            the destination folder
	 */
	private void createTagFolder(String tag, String destinationFolder) {
		File tagFolder = new File(destinationFolder + File.separator + tag);
		if (!tagFolder.exists()) {
			if (tagFolder.mkdir()) {
				LOG.info(tagFolder.getName() + " Folder is created!");
			} else {
				LOG.info("Failed to create " + tagFolder.getName() + " folder!");
			}
		} else {
			LOG.info(tagFolder.getName() + " is already exists");
		}
	}

	@Override
	public List<String> getTags() {
		LOG.info("Entered getTags()");
		String masterJSON = getJSONFromPoolParty();
		List<String> tags = null;
		try {
			JSONObject masterJSONObject = new JSONObject(masterJSON);
			JSONObject results = masterJSONObject.getJSONObject("results");
			JSONArray bindings = results.getJSONArray("bindings");
			tags = new ArrayList<String>();
			for (int i = 0; i < bindings.length(); ++i) {
				JSONObject binding = bindings.getJSONObject(i);
				JSONObject prefLabelObject = (JSONObject) binding
						.get("prefLabel");
				tags.add(prefLabelObject.getString("value"));
			}
		} catch (JSONException e) {
			LOG.error(e);
		}
		return tags;
	}

	/**
	 * Connects to PoolParty. Gets the jSON from pool party.
	 * 
	 * @return the jSON from pool party
	 */
	private String getJSONFromPoolParty() {
		StringBuilder content = null;
		String serverAddress = poolPropertyProperties
				.getProperty("poolparty.serverAddress");
		String userId = poolPropertyProperties.getProperty("poolparty.userId");
		String password = poolPropertyProperties
				.getProperty("poolparty.password");
		boolean doOutput = Boolean.parseBoolean(poolPropertyProperties
				.getProperty("poolparty.doOutput"));
		String contentType = poolPropertyProperties
				.getProperty("poolparty.contentType");
		int connectTimeout = Integer.parseInt(poolPropertyProperties
				.getProperty("poolparty.connectTimeout"));
		int readTimeout = Integer.parseInt(poolPropertyProperties
				.getProperty("poolparty.readTimeout"));
		String encodedContentType = poolPropertyProperties
				.getProperty("poolparty.content-type");
		InputStream queryFileStream = this.getClass().getClassLoader()
				.getResourceAsStream("query.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				queryFileStream));
		StringBuilder query = new StringBuilder();
		String lineOfQuery;
		try {
			while ((lineOfQuery = reader.readLine()) != null) {
				query.append(lineOfQuery + "\n");
			}
		} catch (IOException e) {
			LOG.error(e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				LOG.error(e);
			}
		}
		BufferedReader in = null;
		try {
			URL url = new URL(serverAddress);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			String userpassword = userId + ":" + password;
			String encoded = "Basic "
					+ DatatypeConverter.printBase64Binary(userpassword
							.getBytes());
			connection.setRequestProperty("Authorization", encoded);
			connection.setDoOutput(doOutput);
			connection.setRequestProperty("Content-Type", contentType);
			connection.setConnectTimeout(connectTimeout);
			connection.setReadTimeout(readTimeout);
			String encodedQuery = URLEncoder.encode(query.toString(), "UTF-8");
			connection.setRequestProperty("content-type", encodedContentType);
			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream());
			out.write("query=" + encodedQuery);
			out.write("&format="
					+ URLEncoder.encode("application/json", "utf-8"));
			out.flush();
			out.close();
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			content = new StringBuilder();

			// read from the urlconnection via the bufferedreader
			while ((line = in.readLine()) != null) {
				content.append(line + "\n");
			}
			LOG.info("\nREST Service Invoked Successfully.."
					+ content.toString());
		} catch (MalformedURLException malformedURLException) {
			LOG.error(malformedURLException);
		} catch (UnsupportedEncodingException unsupportedEncodingException) {
			LOG.error(unsupportedEncodingException);
		} catch (IOException ioException) {
			LOG.error(ioException);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				LOG.error(e);
			}
		}
		return content.toString();

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
			query.append("&").append(
					String.format("projectId=%s",
							URLEncoder.encode(projectId, charSet)));

			query.append("&").append(
					String.format("language=%s",
							URLEncoder.encode(language, charSet)));
			URL url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			String userpassword = user + ":" + password;
			String encoded = "Basic "
					+ DatatypeConverter.printBase64Binary(userpassword
							.getBytes());
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization", encoded);
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			connection.setRequestProperty("content-type",
					"application/x-www-form-urlencoded");
			connection.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream());
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
		try {
			JSONArray jsonArray = root.getJSONArray("categories");
			if (jsonArray != null) {
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject obj = jsonArray.getJSONObject(i);

					String tag = "tc:" + obj.getString("prefLabel");

					tags.add(tag);
				}
			}
		} catch (JSONException e) {
			LOG.info("Did not get any categories [tags]");
		}
		

		return tags;
	}

}
