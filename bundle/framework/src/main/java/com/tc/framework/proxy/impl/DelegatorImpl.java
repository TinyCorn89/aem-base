/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.framework.proxy.impl;

import java.util.Map;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;

import com.tc.action.BaseAction;
import com.tc.framework.api.Delegator;
import com.tc.framework.api.ManagerProvider;
import com.tc.framework.api.ServiceManager;

@Component(name = "org.virtusa.framework.proxy.Delegator", immediate = true)
@Service(serviceFactory = false)
@Properties({
    @Property(name = Constants.SERVICE_VENDOR, value = "The Virtusa Corporation"),
    @Property(name = Constants.SERVICE_DESCRIPTION, value = "Delegator to facade layer"),
    @Property(name = "interface", value = "org.virtusa.framework.proxy.api.Delegator")
})

public class DelegatorImpl implements Delegator {

    public <Type> Type adaptTo(Object action, Class<Type> type) {

        ServiceManager serviceManager = ManagerProvider.getManager(ServiceManager.class);
        if (serviceManager != null && action instanceof BaseAction) {
            return serviceManager.getService(this, type);
        }
        return null;
    }

    @Activate
    protected void activate(final BundleContext bundleContext,
            final Map<String, Object> componentConfig) {

        /*
         * Dictionary props = new Properties(); // add specific service
         * properties here...
         * 
         * 
         * // Register our example service implementation in the OSGi service
         * registry bundleContext.registerService( Delegator.class.getName(),
         * new Delegator(), props );
         */    }

    @Deactivate
    protected void deactivate() {

    }

}
