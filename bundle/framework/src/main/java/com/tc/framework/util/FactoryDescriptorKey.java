/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.framework.util;

import org.apache.sling.commons.osgi.PropertiesUtil;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;

/**
 * The <code>ActionFactoryDescriptorKey</code> provides the indexing
 * functionality for the {@link FactoryDescriptorMap}. The key consists of the
 * OSGi <code>service.id</code> of the
 * {@link org.apache.sling.api.adapter.AdapterFactory} service and the ID of the
 * the bundle providing the service.
 * <p>
 * Sort order among the keys is defined primarily by the bundle id and
 * secondarily by the service id.
 */
public class FactoryDescriptorKey implements
        Comparable<FactoryDescriptorKey> {

    private long bundleId;

    private long serviceId;

    public FactoryDescriptorKey(ServiceReference ref) {
        bundleId = ref.getBundle().getBundleId();
        serviceId = PropertiesUtil.toLong(ref.getProperty(Constants.SERVICE_ID), -1);
    }

    public int compareTo(FactoryDescriptorKey o) {
        if (o.equals(this)) {
            return 0;
        }

        // result for differing bundleId
        if (bundleId < o.bundleId) {
            return -1;
        } else if (bundleId > o.bundleId) {
            return 1;
        }

        // result for differing serviceId, we do not expect the two
        // serviceId values to be equal because otherwise the equals
        // test above would have yielded true
        if (serviceId < o.serviceId) {
            return -1;
        }

        // serviceId is larger than the other object's, we do not expect
        // the two serviceId values to be equal because otherwise the equals
        // test above would have yielded true
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof FactoryDescriptorKey) {
            FactoryDescriptorKey oKey = (FactoryDescriptorKey) o;
            return bundleId == oKey.bundleId && serviceId == oKey.serviceId;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return (int) (bundleId * 33 + serviceId);
    }
}
