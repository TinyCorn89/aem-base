/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.framework.logger;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tc.framework.constants.LogWriterConfiguration;
import com.tc.framework.constants.LoggerConfiguration;

public class FrameworkLogger {

    private static Logger logger;

    private static void registerLogger() throws InvalidSyntaxException, ConfigurationException {
        BundleContext bundleContext = FrameworkUtil.getBundle(FrameworkLogger.class).getBundleContext();
        ServiceReference[] services = bundleContext.getServiceReferences("org.osgi.service.cm.ManagedServiceFactory", null);
        ManagedServiceFactory managedServiceFactory = null;
        for (ServiceReference serviceReference : services) {
            if ("org.apache.sling.commons.log.LogManager.factory.config".equalsIgnoreCase((String) serviceReference.getProperty("service.pid"))) {
                managedServiceFactory = (ManagedServiceFactory) bundleContext.getService(serviceReference);
                managedServiceFactory.updated("org.virtusa.framework", getFrameworkLoggerConfiguration());
            }
            if ("org.apache.sling.commons.log.LogManager.factory.writer".equalsIgnoreCase((String) serviceReference.getProperty("service.pid"))) {
                managedServiceFactory = (ManagedServiceFactory) bundleContext.getService(serviceReference);
                managedServiceFactory.updated("org.virtusa.framework", getFrameworkLogWriterConfiguration());
            }
        }

        logger = LoggerFactory.getLogger("org.virtusa.framework");

    }

    public static Logger getLogger() {
        if (logger == null) {
            try {
                registerLogger();
            } catch (InvalidSyntaxException e) {
                logger.error(e.getMessage(), e);
            } catch (ConfigurationException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return logger;

    }

    public static Dictionary<String, ?> getFrameworkLoggerConfiguration() {

        Dictionary<String, String> config = new Hashtable<String, String>();
        LoggerConfiguration[] loggerConfigurations = LoggerConfiguration.values();

        for (LoggerConfiguration configuration : loggerConfigurations) {

            config.put(configuration.getConfigurationKey(), configuration.getConfigurationValue());

        }
        return config;
    }

    public static Dictionary<String, ?> getFrameworkLogWriterConfiguration() {

        Dictionary<String, String> config = new Hashtable<String, String>();
        LogWriterConfiguration[] logWriterConfigurations = LogWriterConfiguration.values();

        for (LogWriterConfiguration configuration : logWriterConfigurations) {

            config.put(configuration.getConfigurationKey(), configuration.getConfigurationValue());

        }
        return config;
    }

}
