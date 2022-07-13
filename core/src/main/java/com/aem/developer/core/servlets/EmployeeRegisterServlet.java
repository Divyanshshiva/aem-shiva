/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.aem.developer.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.aem.developer.core.service.EmployeeI;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=Employee Register Servlet",
													  "sling.servlet.methods=" + HttpConstants.METHOD_GET, 
													  "sling.servlet.paths=" + "/bin/registerservlet" })
public class EmployeeRegisterServlet extends SlingAllMethodsServlet {

	
	private static final long serialVersionUID = 1L;

	@Reference
	EmployeeI objEmp;

	@Override
	protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
			throws ServletException, IOException {

		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String email = req.getParameter("email");
		String weight = req.getParameter("weight");
		
		//Registering Employee data into Database 
		boolean reg = objEmp.registerEmployeeDetails(name, age, email, weight);

		resp.setContentType("text/html");
		resp.getWriter().write("<h1>Emplo Data Registered Successfully</h1>" );
		// resource.adaptTo(ValueMap.class).get("jcr:title"));
	}
}
