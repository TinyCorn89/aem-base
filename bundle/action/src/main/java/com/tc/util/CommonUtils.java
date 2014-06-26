/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.util;

import javax.servlet.RequestDispatcher;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.wrappers.SlingHttpServletRequestWrapper;

public class CommonUtils {

    public static void getRequestDispatcher(final SlingHttpServletRequest request, final SlingHttpServletResponse response, final String redirectPath) throws Exception {
        SlingHttpServletRequest slingRequest = new SlingHttpServletRequestWrapper(((SlingHttpServletRequest) request)) {
            public String getMethod() {
                return "GET";
            }
        };
        RequestDispatcher requestDispatcher = null;
        requestDispatcher = slingRequest.getRequestDispatcher(redirectPath);
        requestDispatcher.forward(slingRequest, response);
    }

}
