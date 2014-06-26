/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.framework.api;

public interface GatewayManager {

    public <Type> Type getGateway(Object sourceType, Class<Type> type);

}
