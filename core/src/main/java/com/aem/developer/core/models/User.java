package com.aem.developer.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class)
public class User 
{
	
    private String name;

    @Inject
    private String address;

    @Inject
    private String contact;

    private String details;

    
    
}
