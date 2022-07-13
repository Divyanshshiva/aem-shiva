package com.aem.developer.core.impl;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.developer.core.service.CreateNodeDynamically;

@Component(service = CreateNodeDynamically.class, immediate = true)
public class EmployeeNodeDynamicallyServiceImpl implements CreateNodeDynamically {

	private static Logger log = LoggerFactory.getLogger(EmployeeNodeDynamicallyServiceImpl.class);

	@Reference
	public ResourceResolverFactory resolverFactory;
	ResourceResolver resourceResolver;
	Resource resource;
	private Session session;
	@Override
	public void createNode()
	{
	
			try {
			

			log.info("***************registerEmployeeDetails method started******************");

			resourceResolver = resolverFactory.getResourceResolver(getSubService());	

			log.info("rr " + resourceResolver);

			Resource resource = resourceResolver.getResource("http://localhost:4502/crx/de/index.jsp#/content/aemdeveloper/en");

			log.info("Resource is at path {}", resource.getPath());

			/**
			 * Adapt the resource to javax.jcr.Node type
			 */
			Node node = resource.adaptTo(Node.class);

			/**
			 * Create a new node with name and primary type and add it below the path specified by the resource
			 */
			Node newNode = node.addNode("demoNode1", "nt:unstructured");
			
			/**
			 * Setting a name property for this node
			 */
			newNode.setProperty("name", "Demo Node");
			
			newNode.setProperty("name1", "Demo Node1");
			
			/**
			 * Commit the changes to JCR
			 */
			resourceResolver.commit();

				

		} catch (Exception e) {
			log.error("Exception " + e.getMessage());

		} finally {
			if (session != null) {
				log.info("inside finally block");
				session.logout();
			}
		}
		
	}
	private Map<String, Object> getSubService()
	{
		log.info("inside getSubService method");
		Map<String, Object> serviceMap = null;
		try {

			serviceMap = new HashMap<String, Object>();

			serviceMap.put(ResourceResolverFactory.SUBSERVICE, "aemdeveloper");

		} catch (Exception e) {

			log.error("Exception " + e.getMessage());

		}
		log.info("getSubService method ended");

		log.info(serviceMap.toString());

		return serviceMap;
	}

	
	
}
