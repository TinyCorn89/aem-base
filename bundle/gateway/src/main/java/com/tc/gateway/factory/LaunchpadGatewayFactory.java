/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.gateway.factory;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.osgi.framework.Constants;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tc.framework.api.GatewayFactory;
import com.tc.framework.api.ServiceFactory;
import com.tc.gateway.api.CustomerProfileGateway;
import com.tc.gateway.util.CustomerInfoGateways;

@Component(enabled = true, name = "org.virtusa.framework.gateway.factory.GatewayFactory", immediate = true)
@Service(serviceFactory = false)
@Properties({
    @Property(name = Constants.SERVICE_VENDOR, value = "The Virtusa Corporation"),
    @Property(name = Constants.SERVICE_DESCRIPTION, value = "Launchpad GatewayFactory"),
    @Property(name = "interface", value = "org.virtusa.framework.api.GatewayFactory")
})
public class LaunchpadGatewayFactory implements GatewayFactory {

    private static final Logger log = LoggerFactory.getLogger(LaunchpadGatewayFactory.class);
    @Property(name = "factoryname")
    public static final String FACTORY_NAME = LaunchpadGatewayFactory.class.getName();

    @Property(name = "gatewayclasses")
    public static final String[] GATEWAY_CLASSES = {CustomerProfileGateway.class.getName()};

    private String bundleVersion;

    public <GatewayType> GatewayType getGateway(Object source, Class<GatewayType> type) {
        log.info("Calling GatewayFactory:getGetway");
        System.out.println("Inside getGetway");
        if ((source instanceof ServiceFactory)) {

            return getGateway((ServiceFactory) source, type);

        }
        log.warn("Unable to handle adaptable {}", source.getClass().getName());
        return null;
    }

    private <GatewayType> GatewayType getGateway(ServiceFactory serviceFactory, Class<GatewayType> type) {
        log.info("Calling LaunchpadFacadeFactory:Adapters.getAdapterImpl(type)");
        System.out.println("Inside GetAdapater with Object");
        return CustomerInfoGateways.getGatewayImpl(type);

    }

    protected void activate(ComponentContext context) {
        this.bundleVersion = ((String) context.getBundleContext().getBundle().getHeaders().get("Bundle-Version"));
    }

}
