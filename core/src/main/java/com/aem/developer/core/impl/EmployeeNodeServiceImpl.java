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

import com.aem.developer.core.service.EmployeeNodeService;

@Component(service = EmployeeNodeService.class, immediate = true)
public class EmployeeNodeServiceImpl implements EmployeeNodeService {

	private static Logger log = LoggerFactory.getLogger(EmployeeNodeServiceImpl.class);

	@Reference
	public ResourceResolverFactory resolverFactory;
	ResourceResolver resourceResolver;
	Resource resource;
	private Session session;
	@Override
	public boolean registerEmployeeDetails(String firstName, String lastName, String userName, String password) {

		boolean flag = false;
			

		try {

			log.info("***************registerEmployeeDetails method started******************");

			resourceResolver = resolverFactory.getResourceResolver(getSubService());

			log.info("rr " + resourceResolver);

			resource = resourceResolver.getResource("/content/aemdeveloper/en/demoNode"); 

			log.info("resource " + resource);

			session = resourceResolver.adaptTo(Session.class);

			log.info("session***" + session);

			// Create random numbers

			java.util.Random r = new java.util.Random();

			int low = 10;
			int high = 1000;
			int result = r.nextInt(high - low) + low;
			String randomValue = "employee" + result;

			log.info("Random value " + randomValue);

			Node node = resource.adaptTo(Node.class);
			log.info("node===="+node);

			if (node != null) {

				log.info("inside if condition");

				Node empNode = node.addNode(randomValue, "nt:unstructured");
				empNode.setProperty("FirstName", firstName);
				empNode.setProperty("LastName", lastName);
				empNode.setProperty("UserName", userName);
				empNode.setProperty("Password", password);

				session.save();

				flag = true;
			}

		} catch (Exception e) {
			log.error("Exception " + e.getMessage());

		} finally {
			if (session != null) {
				log.info("inside finally block");
				session.logout();
			}
		}
		log.info("register employee method end");
		return flag;
	}

	@Override
	public boolean loginEmployee(String userName, String password) {

		return false;
	}

	@Override
	public Map<String, Object>getSubService() {
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
