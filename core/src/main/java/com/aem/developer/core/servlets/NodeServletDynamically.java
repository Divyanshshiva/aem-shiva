
package com.aem.developer.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.developer.core.impl.EmployeeNodeDynamicallyServiceImpl;
import com.aem.developer.core.service.CreateNodeDynamically;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=Employee NodeRegister Servlet",
													  "sling.servlet.methods=" + HttpConstants.METHOD_GET, 
													  "sling.servlet.paths=" + "/bin/nodeservlt" 
											  })
public class NodeServletDynamically extends SlingAllMethodsServlet {

	
	private static final long serialVersionUID = 1L;
	

	private static Logger log = LoggerFactory.getLogger(NodeServletDynamically.class);

	@Reference
	CreateNodeDynamically emd;
	
	@Override
	protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
			throws ServletException, IOException {

		emd.createNode();
		resp.getWriter().write("node resgister servlet responded");
		log.info("hello");
	}
}
