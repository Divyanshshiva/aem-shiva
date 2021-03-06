
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
import com.aem.developer.core.impl.EmployeeNodeServiceImpl;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=Employee NodeRegister Servlet",
													  "sling.servlet.methods=" + HttpConstants.METHOD_GET, 
													  "sling.servlet.paths=" + "/bin/noderegister" })
public class NodeRegisterServlet extends SlingAllMethodsServlet {

	
	private static final long serialVersionUID = 1L;
	

	private static Logger log = LoggerFactory.getLogger(NodeRegisterServlet.class);

	

	

@Reference
EmployeeNodeServiceImpl emp;

@Override
protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
		throws ServletException, IOException {

	String firstName = req.getParameter("firstName");
	String lastName = req.getParameter("lastName");
	String userName = req.getParameter("userName");
	String password = req.getParameter("pwd");
	


	//Registering Employee data into jcr 
boolean b=emp.registerEmployeeDetails(firstName, lastName, userName, password);
log.info("boolean check "+b);
	resp.setContentType("text/html");
	resp.getWriter().write("<h1>Employee Node Data Registration started Successfully</h1>" );
	// resource.adaptTo(ValueMap.class).get("jcr:title"));
	resp.getWriter().write(firstName+","+lastName+","+userName+","+password);
	}
}
