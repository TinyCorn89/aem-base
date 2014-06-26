/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.gateway.util;

import com.tc.gateway.api.CustomerProfileGateway;

public enum CustomerInfoGateways {

    CUSTOMER_PROFILE_GATEWAY(CustomerProfileGateway.class,
            com.tc.gateway.impl.CustomerProfileGatewayImpl.class);

    Class<?> gatewayClass;
    Class<?> gatewayClassImpl;

    CustomerInfoGateways(Class<?> gatewayType, Class<?> gatewayTypeImpl) {
        this.gatewayClass = gatewayType;
        this.gatewayClassImpl = gatewayTypeImpl;
    }

    @SuppressWarnings("unchecked")
    public static <GatewayType> GatewayType getGatewayImpl(
            Class<GatewayType> gatewayType) {

        CustomerInfoGateways[] customerInforReleatedGateways = CustomerInfoGateways.values();
        for (CustomerInfoGateways customerGateway : customerInforReleatedGateways) {
            if (customerGateway.gatewayClass == gatewayType) {
				// if(adapter.adapterClass.getName().equals(adapterType.getName()))
                // {
                try {
                    return (GatewayType) customerGateway.gatewayClassImpl.newInstance();
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
