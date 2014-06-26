/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.framework.constants;

public enum LogWriterConfiguration {

    LOG_FILE("org.apache.sling.commons.log.file", "logs/virtusaframework.log"),
    LOG_FILE_NUMBER("org.apache.sling.commons.log.file.number", "5"),
    LOG_FILE_SIZE("org.apache.sling.commons.log.file.size", "'.'yyyy-MM-dd");

    private String configurationKey;
    private String configurationValue;

    private LogWriterConfiguration(String configurationKey, String configurationValue) {
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
