package com.aem.developer.core.service;

import java.util.Map;

public interface EmployeeNodeService {

	
	public boolean registerEmployeeDetails(String firstName, String lastName, String userName, String password);
	
	public boolean loginEmployee(String userName, String password);
	
	public Map<String, Object> getSubService();
	
	
}
