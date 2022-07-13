
package com.aem.developer.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.LoggerFactory;

import com.aem.developer.core.service.EmployI;

@Component(service=Servlet.class,
           property={
        		   Constants.SERVICE_DESCRIPTION + "=Demo Servlet",
                   "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                   "sling.servlet.paths=" + "/bin/myservlet"
           })
public class MyOsgiService extends SlingSafeMethodsServlet
{
    	private static final org.slf4j.Logger log = LoggerFactory.getLogger(MyOsgiService.class);
	   @Reference
	  private EmployI obj ;  //emlopyi  obj=new emloyimpl();
	   protected void doGet(final SlingHttpServletRequest req,
	            final SlingHttpServletResponse resp) throws ServletException, IOException
	   {
		   	try
		   	{
		   		log.info("----------starts--------");
		   		
			}
		   	catch (Exception e)
		   	{
				// TODO: handle exception
		   		log.error(e.getMessage(),e);
			}
		   	resp.getWriter().write(obj.getEmployDetails());
      
    
	   }
}

