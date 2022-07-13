package com.aem.developer.core.impl;
import java.util.ArrayList;
import java.util.List;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.developer.core.service.TestService;

@Component(immediate = true, service = TestService.class)
public class TaskServiceImpl implements TestService 
{
	public static final Logger lOG = LoggerFactory.getLogger(TaskServiceImpl.class);

	@Override
	public List<String> getData()
	{

		List<String> al = new ArrayList<String>();

		al.add("JAVA");
		al.add("AEM");
		al.add("SOFTWARE");
		al.add("DEVELOPER");

		return al;

	}
}
