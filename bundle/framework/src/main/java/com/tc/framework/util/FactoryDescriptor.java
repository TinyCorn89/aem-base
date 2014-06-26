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
public class FactoryDescriptor {

    private Object factory;

    private final String[] factoryClasses;

    private final ServiceReference reference;

    private final ComponentContext context;

    public FactoryDescriptor(
            final ComponentContext context,
            final ServiceReference reference,
            final String[] factoryClasses) {
        this.reference = reference;
        this.context = context;
        this.factoryClasses = factoryClasses;
    }

    public Object getFactory(String factoryName) {
        if (factory == null) {
            synchronized (this) {
                if (factory == null) {
                    factory = context.locateService(
                            factoryName, reference);
                }
            }
        }
        return factory;
    }

    public String[] getFactoryClasses() {
        return factoryClasses;
    }
}
