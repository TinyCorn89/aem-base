/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.service.impl;


import com.tc.framework.api.GatewayManager;
import com.tc.framework.api.ManagerProvider;
import com.tc.framework.api.ServiceFactory;
import com.tc.gateway.api.CustomerProfileGateway;
import com.tc.service.api.CustomerProfileService;
import com.tc.service.factory.LaunchpadServiceFactory;

public class CustomerProfileServiceImpl implements CustomerProfileService {

    public String getCustomerName() {
        GatewayManager gatewayManager = ManagerProvider.getManager(GatewayManager.class);
        ServiceFactory serviceFactory = ManagerProvider.getServiceFactoryType(LaunchpadServiceFactory.class);
        return gatewayManager.getGateway(serviceFactory, CustomerProfileGateway.class).getCustomerName();

    }
}
