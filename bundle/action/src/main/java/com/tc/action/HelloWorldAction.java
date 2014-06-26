/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.action;

import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFormatException;

import org.slf4j.Logger;

import com.tc.action.BaseAction;
import com.tc.framework.api.AppsConfigService;
import com.tc.framework.api.ManagerProvider;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.model.HelloWorldBean;

public class HelloWorldAction extends BaseAction {

    private Logger log = FrameworkLogger.getLogger();

    public HelloWorldBean helloWorld() {
        HelloWorldBean helloWorldBean = new HelloWorldBean();
        try {
            helloWorldBean.setMessage(getCurrentNode().getProperty("name").getString());
        } catch (ValueFormatException e) {
            log.error(e.getMessage(), e);
        } catch (PathNotFoundException e) {
            log.error(e.getMessage(), e);
        } catch (RepositoryException e) {
            log.error(e.getMessage(), e);
        }

        helloWorldBean.setAppConfigMessage(ManagerProvider.getManager(AppsConfigService.class).getProperty("launchpad.en.message"));

        return helloWorldBean;
    }

}
