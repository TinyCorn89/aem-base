/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.framework.util;

import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;

import com.tc.framework.api.ActionFactory;

/**
 * The <code>FactoryDescriptor</code> is an entry in the
 * {@link FactoryDescriptorMap} conveying the list of ActionClass (target) types
 * and the respective {@link ActionFactory}.
 */
public class ServiceFactoryDescriptor {

    private ActionFactory factory;

    private final String[] actionClasses;

    private final ServiceReference reference;

    private final ComponentContext context;

    public ServiceFactoryDescriptor(
            final ComponentContext context,
            final ServiceReference reference,
            final String[] actionClasses) {
        this.reference = reference;
        this.context = context;
        this.actionClasses = actionClasses;
    }

    public ActionFactory getFactory() {
        if (factory == null) {
            synchronized (this) {
                if (factory == null) {
                    factory = (ActionFactory) this.context.locateService(
                            "ActionFactory", this.reference);
                }
            }
        }
        return factory;
    }

    public String[] getActionClasses() {
        return actionClasses;
    }
}
