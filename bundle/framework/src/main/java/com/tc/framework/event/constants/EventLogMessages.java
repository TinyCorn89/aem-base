/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.framework.event.constants;

public enum EventLogMessages {

    FACTORY_REGISTERED_MESSAGE(" Factory has been registered"),
    FACTORY_REMOVED_MESSAGE(" Factory has been removed");

    String message;

    EventLogMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
