/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.framework.api;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.jcr.AccessDeniedException;
import javax.jcr.Session;


import com.tc.framework.exception.GenericFrameworkException;
import com.tc.framework.model.AppsConfigBean;

/**
 * The Interface AppsConfigService.
 */
public interface AppsConfigService {

    /**
     * Gets the property.
     *
     * @param key the key
     * @return the property
     */
    public String getProperty(final String key);

    /**
     * Transform content.
     *
     * @param htmlContent the html content
     * @return the string
     */
    public String transformContent(final String htmlContent);

    /**
     * Gets the property.
     *
     * @param locale the locale
     * @param key the key
     * @return the property
     */
    public String getProperty(Locale locale, final String key);

    /**
     * Gets the property.
     *
     * @param locale the locale
     * @param key the key
     * @param mockValues - The mock values to be used.
     * @return the property
     */
    public String getProperty(Locale locale, String key,
            Map<String, String> mockValues);

    /**
     * Transform content.
     *
     * @param locale the locale
     * @param htmlContent the html content
     * @return the string
     */
    public String transformContent(Locale locale, final String htmlContent);

    /**
     * Transform content.
     *
     * @param locale the locale
     * @param htmlContent the html content
     * @param mockValues - The mock values to be used.
     * @param isEditMode - true if edit mode, else false.
     * @return the string
     */
    public String transformContent(Locale locale, final String htmlContent,
            Map<String, String> mockValues, boolean isEditMode);

    /**
     * Transform content.
     *
     * @param locale the locale
     * @param htmlContent the html content
     * @param mockValues - The mock values to be used.
     * @return the string
     */
    public String transformContent(Locale locale, final String htmlContent,
            Map<String, String> mockValues);

    /**
     * For creating node in appsconfig
     *
     * @param appsConfigBean
     * @param path
     * @throws GenericFrameworkException
     */
    public void saveProperty(AppsConfigBean appsConfigBean, String path)
            throws GenericFrameworkException;

    /**
     * Gets the all appsconfig nodes.
     *
     * @param path
     * @param session
     * @return
     * @throws GenericFrameworkException
     */
    public List<AppsConfigBean> getAllProperties(String path, Session session)
            throws GenericFrameworkException;

    /**
     * Checks if it is duplicate.
     *
     * @param propertyName
     * @param path
     * @return
     */
    public boolean isDuplicate(String propertyName, String path);

    /**
     * For publishing appsConfig Nodes.
     *
     * @param session
     * @param publishList
     * @return status
     */
    public boolean publishNodes(Session session, String publishList) throws
            AccessDeniedException;

    /**
     * For publishing All appsConfig Nodes.
     *
     * @param session
     * @param nodePath
     * @return status
     */
    public boolean publishAllNodes(Session session, String nodePath)
            throws AccessDeniedException;

    /**
     * Provides the path of the publishing node
     *
     * @param session
     * @param path
     * @return nodePath
     */
    public String getAppsConfigCollectionPath(Session session, String path);

}
