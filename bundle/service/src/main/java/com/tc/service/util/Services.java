/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.service.util;


import com.tc.service.api.CustomerProfileService;
import com.tc.service.impl.CustomerProfileServiceImpl;

public enum Services {

    LAUNCHPAD_CUSTOMER_PROFILE_SERVICE(CustomerProfileService.class, CustomerProfileServiceImpl.class);

    Class<?> serviceClass;
    Class<?> serviceClassImpl;

    Services(Class<?> serviceClassType, Class<?> serviceClassTypeImpl) {
        this.serviceClass = serviceClassType;
        this.serviceClassImpl = serviceClassTypeImpl;
    }

    @SuppressWarnings("unchecked")
    public static <ServiceType> ServiceType getServiceClassImpl(Class<ServiceType> serviceType) {

        Services[] services = Services.values();
        for (Services service : services) {
            if (service.serviceClass == serviceType) {
                try {
                    return (ServiceType) service.serviceClassImpl.newInstance();
                } catch (InstantiationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        }
        return null;
    }

}
