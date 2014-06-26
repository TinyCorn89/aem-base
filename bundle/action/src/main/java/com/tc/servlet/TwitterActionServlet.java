/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import com.adobe.granite.auth.oauth.impl.helper.OAuthToken;
import com.adobe.granite.auth.oauth.impl.helper.OauthTokenManager;
import com.adobe.granite.crypto.CryptoSupport;
import com.tc.framework.api.AppsConfigService;
import com.tc.servlet.BaseSlingServlet;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;

@SlingServlet(paths = {"/bin/twitterActionPost"})
@Properties({
    @Property(name = "service.pid", value = "org.virtusa.launchpad.servlets.TwitterActionServlet", propertyPrivate = false),
    @Property(name = "service.description", value = "Twitter Action Servlet", propertyPrivate = false),
    @Property(name = "service.vendor", value = "vCommet Twitter", propertyPrivate = false)})
public class TwitterActionServlet extends BaseSlingServlet {

    @Reference
    private CryptoSupport cryptoSupport;

    @Override
    protected void doPost(SlingHttpServletRequest request,
            SlingHttpServletResponse response) throws ServletException,
            IOException {

        Twitter twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer("gvRrLUzen2eOhBlokvsJZA",
                "68Vr8BIM38dX3VA91d402kyDtlwbI9TYqBFlw9V3Bw");
        OauthTokenManager oauthTokenManager = new OauthTokenManager(
                cryptoSupport, 1000, 0);
		// twitter.setoa

		// AccessToken acc = new AccessToken("",
        // "{9100cc9e897ed64547cb3b6cfa3cc9c4c4de354c332d037570766c5739075f975abdbc50beb9b63d930fa923f8447c6e0e93ec5e2c023a56650664bf58e779d0b873497d9a0785690d437e30e0f1dbc3cbd59501d3812abe1364350b9914bf725470c4a706c6f35615a8d017999ca5de6a03acd4af5c5b559d3d22c197de57996dfd91d6dbf8ff31c6f8a01ab7f2b58edc0a3ba35e7e09d006a7ba24e476107084fb0c005e26e9064775e9a01c0c02ee}");
        OAuthToken oAuthToken = oauthTokenManager.getToken(
                "gvRrLUzen2eOhBlokvsJZA", request);

        try {
            Status status = twitter.updateStatus("hi vinay you are a sucker");

        } catch (TwitterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        response.setContentType("application/json");
        response.getWriter().write("{status:success}");

    }

}
