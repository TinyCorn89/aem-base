/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.framework.event.constants;

public enum EventMessageProperty {

    FACTORY_NAME("FactoryName");

    String propertyName;

    EventMessageProperty(String property) {
        this.propertyName = property;
    }

    public String getProperty() {
        return this.propertyName;
    }

}
