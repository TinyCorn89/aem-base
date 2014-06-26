/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.service.factory;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.osgi.framework.Constants;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tc.framework.api.AppsConfigService;
import com.tc.framework.api.Delegator;
import com.tc.framework.api.ServiceFactory;
import com.tc.service.api.CustomerProfileService;
import com.tc.service.util.Services;

@Component(name = "org.virtusa.framework.service.factory.ServiceFactory", immediate = true)
@Service(serviceFactory = false)
@Properties({
    @Property(name = Constants.SERVICE_VENDOR, value = "The Virtusa Corporation"),
    @Property(name = Constants.SERVICE_DESCRIPTION, value = "Launchpad ServiceFactory"),
    @Property(name = "interface", value = "org.virtusa.framework.api.ServiceFactory")
})
public class LaunchpadServiceFactory implements ServiceFactory {

    private static final Logger log = LoggerFactory.getLogger(LaunchpadServiceFactory.class);

    @Property(name = "factoryname")
    public static final String FACTORY_NAME = LaunchpadServiceFactory.class.getName();
    @Property(name = "serviceclasses")
    public static final String[] SERVICE_CLASSES = {CustomerProfileService.class.getName()};

    private String bundleVersion;

    public <ServiceType> ServiceType getService(Object source, Class<ServiceType> type) {
        log.info("Calling ServiceFactory:getService");
        if (source instanceof Delegator) {
            return getService((Delegator) source, type);

        }
        log.warn("Unable to handle adaptable {}", type.getClass().getName());
        return null;
    }

    private <ServiceType> ServiceType getService(Delegator delegator, Class<ServiceType> type) {
        log.info("Calling LaunchpadserviceFactory:services.getServiceImpl(type)");
        return Services.getServiceClassImpl(type);

    }

    protected void activate(ComponentContext context) {
        this.bundleVersion = ((String) context.getBundleContext().getBundle().getHeaders().get("Bundle-Version"));
    }

}
