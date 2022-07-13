package com.aem.developer.core.impl;
import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aem.developer.core.constans.AppConstants.*;
import com.aem.developer.core.service.ResourceResolverService;
public class ResourceResolverServiceImpl implements ResourceResolverService 
{

    private static final String TAG = ResourceResolverServiceImpl.class.getSimpleName();
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceResolverServiceImpl.class);
	private static final Object SUB_SERVICE="tutorialSubService";

    @Reference
    ResourceResolverFactory resourceResolverFactory;

    private ResourceResolver resourceResolver;

    @Activate
    protected void activate() {
        try {
            // Service User map
            Map<String, Object> serviceUserMap = new HashMap<>();
            // Putting sub-service name in the map
            serviceUserMap.put(ResourceResolverFactory.SUBSERVICE,SUB_SERVICE);
            // Get the instance of Service Resource Resolver
            resourceResolver = resourceResolverFactory.getServiceResourceResolver(serviceUserMap);
        } catch (LoginException e) {
            LOGGER.error("{}: Exception occurred while getting resource resolver: {}", TAG, e.getMessage());
        }
    }

    @Override
    public ResourceResolver getResourceResolver() {
        return resourceResolver;
    }
}
