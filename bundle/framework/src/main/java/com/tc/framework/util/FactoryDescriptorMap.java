/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.framework.util;

import java.util.TreeMap;

/**
 * The <code>AdapterFactoryDescriptorMap</code> is a sorted map of
 * {@link FactoryDescriptor} instances indexed (and ordered) by their
 * {@link FactoryDescriptorKey}. This map is used to organize the registered
 * {@link org.apache.sling.api.adapter.AdapterFactory} services for a given
 * adaptable type.
 * <p>
 * Each entry in the map is a {@link FactoryDescriptor} thus enabling the
 * registration of multiple factories for the same (adaptable, adapter) type
 * tuple. Of course only the first entry (this is the reason for having a sorted
 * map) for such a given tuple is actually being used. If that first instance is
 * removed the eventual second instance may actually be used instead.
 */
public class FactoryDescriptorMap extends
        TreeMap<FactoryDescriptorKey, FactoryDescriptor> {

    private static final long serialVersionUID = 1L;

}
