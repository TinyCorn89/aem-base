/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.service.api;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.sling.jcr.api.SlingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public abstract class BaseService {

    private static final Logger LOG = LoggerFactory
            .getLogger(BaseService.class);

    /**
     * The admin session.
     */
    private Session adminSession = null;

    @Reference
    /**
     * The repository.
     */
    protected SlingRepository repository;

    protected final Session getSession() throws RepositoryException {
        LOG.info("Retrieving JCR session");
        if (adminSession == null) {
            adminSession = repository.loginAdministrative(null);
        }

        if (adminSession == null) {
            LOG.error("Admin session is null");
        }

        LOG.info("Returing JCR session ");
        return adminSession;
    }

}
