package com.aem.developer.core.impl;

import org.osgi.service.component.annotations.Component;

import com.aem.developer.core.service.EmployI;

 @Component(service=EmployI.class,immediate=true)
public class EmployImpl implements EmployI{

	@Override
  public String getEmployDetails() {
	
		try {
				} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace(); 
		}
		 		
		return "welcome srinu";
		
	}
}
