/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.framework.api;

import javax.servlet.jsp.PageContext;

public interface ActionFactory {

    /**
     * The service name to use when registering implementations of this
     * interface as services (value is
     * "org.apache.sling.osgi.commons.AdapterFactory").
     */
    String SERVICE_NAME = "factoryname";

    /**
     * The service registration property listing the fully qualified names of
     * classes which can be adapted by this adapter factory (value is
     * "adaptables"). The "adaptable" parameters of the
     * {@link #getAdapter(Object, Class)} method must be an instance of any of
     * these classes for this factory to be able to adapt the object.
     */
    public String ACTION_CLASSES = "actionclasses";

    public Object invokeAction(Object sourceTag, String actionClassName, String actionName, PageContext pageContext);

}
