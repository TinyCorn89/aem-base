/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.framework.constants;

public enum LoggerConfiguration {

    LOG_LEVEL("org.apache.sling.commons.log.level", "INFO"),
    LOG_FILE("org.apache.sling.commons.log.file", "logs/virtusaframework.log"),
    LOG_PATTERN("org.apache.sling.commons.log.pattern", "{0,date,dd.MM.yyyy HH:mm:ss.SSS} *{4}* [{2}] {3} {5}"),
    LOG_LOGGERS("org.apache.sling.commons.log.names", "org.virtusa.framework");

    private String configurationKey;
    private String configurationValue;

    private LoggerConfiguration(String configurationKey, String configurationValue) {
        this.configurationKey = configurationKey;
        this.configurationValue = configurationValue;

    }

    public String getConfigurationKey() {
        return this.configurationKey;
    }

    public String getConfigurationValue() {
        return this.configurationValue;
    }

  //  public static final String LOG_PATTERN_DEFAULT = "{0,date,dd.MM.yyyy HH:mm:ss.SSS} *{4}* [{2}] {3} {5}";
}
