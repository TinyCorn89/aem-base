package com.tc.process.pressfeed;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public class TCCanadianPressFeedProcessor {
    static Logger LOG = Logger.getLogger(  TCCanadianPressFeedProcessor.class.getName());

    public static void main (String ar[]) throws Exception{

        LOG.info("Starting the sync with PoolParty process");

        InputStream poolpartyInputSt = tc.getClass().getClassLoader().getResourceAsStream("syncpoolparty.properties");
        Properties ppt2aemProps =new Properties();
        ppt2aemProps.load(poolpartyInputSt);

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(ppt2aemProps.getProperty("poolparty.serverAddress"));
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
        String line = '';

        ClientResponse response = resource
                .accept( MediaType.APPLICATION_JSON )
                .type( MediaType.APPLICATION_JSON )
                .entity( payload )
                .post( ClientResponse.class );

        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }


    }

}
