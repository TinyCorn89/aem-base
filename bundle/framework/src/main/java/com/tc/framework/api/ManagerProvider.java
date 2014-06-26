/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.framework.api;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public class ManagerProvider {

    @SuppressWarnings("unchecked")
    public static <ManagerType> ManagerType getManager(Class<ManagerType> managerType) {
        BundleContext bundleContext = FrameworkUtil.getBundle(managerType).getBundleContext();
        ServiceReference serviceReference = bundleContext.getServiceReference(managerType.getName());
        return (ManagerType) bundleContext.getService(serviceReference);
    }

    @SuppressWarnings("unchecked")
    public static <ServiceFactoryType> ServiceFactoryType getServiceFactoryType(Class<ServiceFactoryType> serviceFactoryType) {
        ServiceFactoryType servicetype = null;
        Object service = null;
        Bundle bundle = FrameworkUtil.getBundle(serviceFactoryType);
        ServiceReference[] serviceReferences = bundle.getRegisteredServices();
        BundleContext bundleContext = bundle.getBundleContext();
        for (ServiceReference serviceReference : serviceReferences) {
            service = bundleContext.getService(serviceReference);
            if (service instanceof ServiceFactory) {
                servicetype = (ServiceFactoryType) service;
            }
        }

        return servicetype;
    }

}
