/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.gateway.impl;

import com.tc.gateway.api.CustomerProfileGateway;

public class CustomerProfileGatewayImpl implements CustomerProfileGateway {

    public String getCustomerName() {
        return "John Smith";
    }

}
