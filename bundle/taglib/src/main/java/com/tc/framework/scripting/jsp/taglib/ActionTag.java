/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.framework.scripting.jsp.taglib;


import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tc.framework.api.ActionManager;
import com.tc.framework.api.ManagerProvider;


/**
 * ActionTag class that extends TagSupport.
 *
 */

public class ActionTag extends TagSupport {

    
	/**
	 * 
	 */
	private static final long serialVersionUID = 5149668215451508385L;

	/**
     * bean.
     */
    private String bean;

    /**
     * interfaceName.
     */
    private String actionClassName;
    
    /**
     * actionName.
     */
    private String actionName;

    public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	/**
     * logger object to set the LOG messages.
     */
    private static final Logger LOG = LoggerFactory
            .getLogger(ActionTag.class);


    /**
     * This method will be executed at the end of the tag. it will call the base
     * action class execute method.
     *
     * @return true/false
     */
    public final int doEndTag() {
        LOG.info("INFO: in ActionTag class name is===" + this.actionClassName);
        ActionManager actionManager = ManagerProvider.getManager(ActionManager.class);
        Object returnObject = actionManager.invokeAction(this, actionClassName, actionName, pageContext);
        pageContext.setAttribute(this.bean, returnObject);
        return 0;
    }

    /**
     * to set the bean.
     *
     * @param beanArg
     *            - bean.
     */
    public final void setBean(final String beanArg) {
        this.bean = beanArg;
    }

    /**
     * to get the bean.
     *
     * @return bean.
     */
    public final String getBean() {
        return bean;
    }

    /**
     * To set the class name.
     *
     * @param classNameArg
     *            - className.
     */
    public final void setActionClassName(final String classNameArg) {
        this.actionClassName = classNameArg;
    }

    /**
     * To get the className.
     *
     * @return className.
     */
    public final String getActionClassName() {
        return actionClassName;
    }
        
}