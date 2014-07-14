package com.tc.process.handler;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.Properties;

import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;



public class DamAssetUploadHandler {

	static Logger LOG = Logger.getLogger(  DamAssetUploadHandler.class.getName());

	public  boolean uploadToAEM (String fileName,Properties props) throws Exception{
       try {
		File binaryFile = new File(fileName);
		String boundary = Long.toHexString(System.currentTimeMillis()); // Just generate some unique random value.
		String CRLF = "\r\n"; // Line separator required by multipart/form-data.
		String url=props.getProperty("aem.url");

		URLConnection connection = new URL(url).openConnection();
		connection.setDoOutput(true);
		String userpassword = props.getProperty("aem.userid") + ":" + props.getProperty("aem.password");

		String encoded = "Basic " +DatatypeConverter.printBase64Binary(userpassword.getBytes());


		connection.setRequestProperty ("Authorization", encoded);
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setUseCaches(false);
		connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

		OutputStream output = connection.getOutputStream() ;
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, "UTF-8"), true) ;


		// Send binary file.
		writer.append("--" + boundary).append(CRLF);
		writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + binaryFile.getName() + "\"").append(CRLF);
		writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(binaryFile.getName())).append(CRLF);
		writer.append("Content-Transfer-Encoding: binary").append(CRLF);
		writer.append(CRLF).flush();
		Files.copy(binaryFile.toPath(), output);
		output.flush(); // Important before continuing with writer!
		writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.

		// End of multipart/form-data.
		writer.append("--" + boundary + "--").append(CRLF);

             writer.close();output.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));

		String line;
		StringBuilder content=new StringBuilder();

		// read from the urlconnection via the bufferedreader
		while ((line = in.readLine()) != null){
			content.append(line + "\n");
		}

        
		LOG.info("Zip deployed sucessfully on AEM.. respose from AEM.."+content.toString());
        }catch (Exception e){
        e.printStackTrace();
        LOG.error ("error in AEM asset upload"+e);
           throw e;
        }
		return true;

	}

}
