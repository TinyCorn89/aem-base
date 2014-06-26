/**
 * Copyright (C) 2014 Virtusa Corporation.
 * This file is proprietary and part of Virtusa LaunchPad.
 * LaunchPad code can not be copied and/or distributed without the express permission of Virtusa Corporation
 */

package com.tc.framework.managers.impl;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.tc.framework.api.ActionFactory.SERVICE_NAME;
import static com.tc.framework.api.ServiceFactory.SERVICE_CLASSES;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.ReferenceCardinality;
import org.apache.felix.scr.annotations.ReferencePolicy;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.log.LogService;

import com.tc.framework.api.ServiceFactory;
import com.tc.framework.api.ServiceManager;
import com.tc.framework.event.constants.EventMessageProperty;
import com.tc.framework.event.constants.EventTopics;
import com.tc.framework.logger.FrameworkLogger;
import com.tc.framework.util.FactoryDescriptor;
import com.tc.framework.util.FactoryDescriptorKey;
import com.tc.framework.util.FactoryDescriptorMap;

/**
 * The <code>ServiceManagerImpl</code> class implements the
 * {@link ServiceManager} interface and is registered as a service for that
 * interface to be used by any clients.
 *
 */
@Component(metatype = false, immediate = true)
@Service(serviceFactory = false)
@Properties({
    @Property(name = "service.description", value = "Virtusa Action Class Manager"),
    @Property(name = "service.vendor", value = "Virtusa Corporation")
})
@Reference(name = "ServiceFactory",
        referenceInterface = com.tc.framework.api.ServiceFactory.class,
        cardinality = ReferenceCardinality.OPTIONAL_MULTIPLE,
        bind = "bindServiceFactory", unbind = "unbindServiceFactory", policy = ReferencePolicy.DYNAMIC)

public class ServiceManagerImpl implements ServiceManager {

    /**
     * @scr.reference cardinality="0..1" policy="dynamic"
     */
    private LogService log;

    /**
     * Whether to debug this class or not
     */
    private boolean debug = false;

    /**
     * The OSGi <code>ComponentContext</code> to retrieve {@link ServiceFactory}
     * service instances.
     */
    private volatile ComponentContext context;

    /**
     * A list of {@link AdapterFactory} services bound to this manager before
     * the manager has been activated. These bound services will be accessed as
     * soon as the manager is being activated.
     */
    private List<ServiceReference> boundActionFactories = new LinkedList<ServiceReference>();

    /**
     * A map of {@link FactoryDescriptorMap} instances. The map is indexed by
     * the fully qualified class names listed in the
     * {@link AdapterFactory#ADAPTABLE_CLASSES} property of the
     * {@link AdapterFactory} services.
     *
     * @see FactoryDescriptorMap
     */
    private Map<String, FactoryDescriptorMap> factories = new HashMap<String, FactoryDescriptorMap>();

    /**
     * Matrix of {@link AdapterFactory} instances primarily indexed by the fully
     * qualified name of the class to be adapted and secondarily indexed by the
     * fully qualified name of the class to adapt to (the target class).
     * <p>
     * This cache is built on demand by calling the
     * {@link #getAdapterFactories(Class)} class. It is removed altogether
     * whenever an adapter factory is registered on unregistered.
     */
    private Map<String, ServiceFactory> factoryCache;

    /**
     * The service tracker for the event admin
     *
     * @scr.reference cardinality="0..1" policy="dynamic"
     */
    @Reference(cardinality = ReferenceCardinality.OPTIONAL_UNARY, policy = ReferencePolicy.DYNAMIC)
    private EventAdmin eventAdmin;

    // ---------- AdapterManager interface -------------------------------------
    /**
     * Returns the adapted <code>adaptable</code> or <code>null</code> if the
     * object cannot be adapted.
     */
    public <ServiceType> ServiceType getService(Object source, Class<ServiceType> type) {

        FrameworkLogger.getLogger().info("ServiceManager:invokeAction:Starts");
        // get the adapter factories for the type of adaptable object
        ServiceFactory factory = getServiceFactory(type);

        // have the factory adapt the adaptable if the factory exists
        if (factory != null) {
            if (debug) {
                log(LogService.LOG_DEBUG, "Using Service factory " + factory + " to map " + type.getName(), null);
            }
            // FrameworkLogger.getLogger().info("ServiceManager:ServiceFound");
            return factory.getService(source, type);
        }

        // no factory has been found, so we cannot adapt
        if (debug) {
            log(LogService.LOG_DEBUG, "No Service factory found to map " + type.getName(), null);
        }
        FrameworkLogger.getLogger().info("ServiceManager:invokeAction:ends");
        return null;
    }

    // ----------- SCR integration ---------------------------------------------
    protected void activate(ComponentContext context) {
        this.context = context;

        // register all adapter factories bound before activation
        final List<ServiceReference> refs;
        synchronized (this.boundActionFactories) {
            refs = new ArrayList<ServiceReference>(this.boundActionFactories);
            boundActionFactories.clear();
        }
        for (ServiceReference reference : refs) {
            registerServiceFactory(context, reference);
        }

    }

    /**
     * @param context Not used
     */
    protected void deactivate(ComponentContext context) {
        this.context = null;
    }

    protected void bindServiceFactory(ServiceReference reference) {
        boolean create = true;
        if (context == null) {
            synchronized (this.boundActionFactories) {
                if (context == null) {
                    boundActionFactories.add(reference);
                    create = false;
                }
            }
        }
        if (create) {
            registerServiceFactory(context, reference);
        }
    }

    protected void unbindServiceFactory(ServiceReference reference) {
        unregisterServiceFactory(reference);
    }

    // ---------- unit testing stuff only --------------------------------------
    /**
     * Returns the active adapter factories of this manager.
     * <p>
     * <strong><em>THIS METHOD IS FOR UNIT TESTING ONLY. IT MAY BE REMOVED OR
     * MODIFIED WITHOUT NOTICE.</em></strong>
     */
    Map<String, FactoryDescriptorMap> getFactories() {
        return factories;
    }

    /**
     * Returns the current adapter factory cache.
     * <p>
     * <strong><em>THIS METHOD IS FOR UNIT TESTING ONLY. IT MAY BE REMOVED OR
     * MODIFIED WITHOUT NOTICE.</em></strong>
     */
    Map<String, ServiceFactory> getFactoryCache() {
        return factoryCache;
    }

    // ---------- internal -----------------------------------------------------
    private void log(int level, String message, Throwable t) {
        LogService logger = this.log;
        if (logger != null) {
            logger.log(level, message, t);
        } else {
            if (t != null) {
                t.printStackTrace(System.out);
            }
        }
    }

    /**
     * Unregisters the {@link AdapterFactory} referred to by the service
     * <code>reference</code> from the registry.
     */
    private void registerServiceFactory(ComponentContext context,
            ServiceReference reference) {
        final String[] serviceClasses = PropertiesUtil.toStringArray(reference.getProperty(SERVICE_CLASSES));

        if (serviceClasses == null || serviceClasses.length == 0) {
            return;
        }

        final FactoryDescriptorKey factoryKey = new FactoryDescriptorKey(
                reference);
        final FactoryDescriptor factoryDesc = new FactoryDescriptor(context,
                reference, serviceClasses);

        synchronized (factories) {
            for (final String serviceClass : serviceClasses) {
                FactoryDescriptorMap adfMap = factories.get(serviceClass);
                if (adfMap == null) {
                    adfMap = new FactoryDescriptorMap();
                    factories.put(serviceClass, adfMap);
                }
                adfMap.put(factoryKey, factoryDesc);
            }
        }

        // clear the factory cache to force rebuild on next access
        factoryCache = null;

        // send event
        final EventAdmin localEA = this.eventAdmin;
        if (localEA != null) {
            final Dictionary<String, Object> props = new Hashtable<String, Object>();
            props.put(EventMessageProperty.FACTORY_NAME.getProperty(), PropertiesUtil.toStringArray(reference.getProperty(SERVICE_NAME)));
            localEA.sendEvent(new Event(EventTopics.FACTORY_REGISTERED,
                    props));
        }
    }

    /**
     * Unregisters the {@link AdapterFactory} referred to by the service
     * <code>reference</code> from the registry.
     */
    private void unregisterServiceFactory(ServiceReference reference) {
        synchronized (this.boundActionFactories) {
            boundActionFactories.remove(reference);
        }
        final String[] serviceClassess = PropertiesUtil.toStringArray(reference.getProperty(SERVICE_CLASSES));

        if (serviceClassess == null || serviceClassess.length == 0) {
            return;
        }

        FactoryDescriptorKey factoryKey = new FactoryDescriptorKey(
                reference);

        boolean factoriesModified = false;
        synchronized (factories) {
            for (String actionClass : serviceClassess) {
                FactoryDescriptorMap adfMap = factories.get(actionClass);
                if (adfMap != null) {
                    factoriesModified |= (adfMap.remove(factoryKey) != null);
                    if (adfMap.isEmpty()) {
                        factories.remove(actionClass);
                    }
                }
            }
        }

        // only remove cache if some adapter factories have actually been
        // removed
        if (factoriesModified) {
            factoryCache = null;
        }

        // send event
        final EventAdmin localEA = this.eventAdmin;
        if (localEA != null) {
            final Dictionary<String, Object> props = new Hashtable<String, Object>();
            props.put(EventMessageProperty.FACTORY_NAME.getProperty(), PropertiesUtil.toStringArray(reference.getProperty(SERVICE_NAME)));
            localEA.sendEvent(new Event(EventTopics.FACTORY_REMOVED,
                    props));
        }
    }

    /**
     * Returns a map of {@link AdapterFactory} instances for the given class to
     * be adapted. The returned map is indexed by the fully qualified name of
     * the target classes (to adapt to) registered.
     *
     * @param clazz The type of the object for which the registered adapter
     * factories are requested
     * @return The map of adapter factories. If there is no adapter factory
     * registered for this type, the returned map is empty.
     */
    private ServiceFactory getServiceFactory(Class<?> type) {
        Map<String, ServiceFactory> cache = factoryCache;
        if (cache == null) {
            cache = new HashMap<String, ServiceFactory>();
            factoryCache = cache;
        }

        synchronized (cache) {
            return getServiceFactoryFromCache(type, cache);
        }
    }

    /**
     * Returns the map of adapter factories index by adapter (target) class name
     * for the given adaptable <code>clazz</code>. If no adapter exists for the
     * <code>clazz</code> and empty map is returned.
     *
     * @param clazz The adaptable <code>Class</code> for which to return the
     * adapter factory map by target class name.
     * @param cache The cache of already defined adapter factory mappings
     * @return The map of adapter factories by target class name. The map may be
     * empty if there is no adapter factory for the adaptable
     * <code>clazz</code>.
     */
    private ServiceFactory getServiceFactoryFromCache(Class<?> type, Map<String, ServiceFactory> cache) {

        String serviceName = type.getName();
        ServiceFactory entry = cache.get(serviceName);
        if (entry == null) {
            // create entry
            entry = getServiceFactoryFromMap(serviceName);
            cache.put(serviceName, entry);
        }

        return entry;
    }

    /**
     * Creates a new target adapter factory map for the given
     * <code>clazz</code>. First all factories defined to support the adaptable
     * class by registration are taken. Next all factories for the implemented
     * interfaces and finally all base class factories are copied. Later adapter
     * factory entries do NOT overwrite earlier entries.
     *
     * @param clazz The adaptable <code>Class</code> for which to build the
     * adapter factory map by target class name.
     * @param cache The cache of already defined adapter factory mappings
     * @return The map of adapter factories by target class name. The map may be
     * empty if there is no adapter factory for the adaptable
     * <code>clazz</code>.
     */
    private ServiceFactory getServiceFactoryFromMap(String serviceName) {

        ServiceFactory serviceFactory = null;
        // AdapterFactories for this class
        FactoryDescriptorMap serviceFactoryDescriptorMap;
        synchronized (factories) {
            serviceFactoryDescriptorMap = factories.get(serviceName);
        }
        if (serviceFactoryDescriptorMap != null) {
            for (FactoryDescriptor afd : serviceFactoryDescriptorMap.values()) {
                String[] factoryServiceClasses = afd.getFactoryClasses();
                for (String serviceClass : factoryServiceClasses) {
                    if (serviceName.equalsIgnoreCase(serviceClass)) {
                        serviceFactory = (ServiceFactory) afd.getFactory("ServiceFactory");
                    }
                }

            }
        }

        return serviceFactory;
    }

}
