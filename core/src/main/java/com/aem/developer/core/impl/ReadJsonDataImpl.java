package com.aem.developer.core.impl;

import org.osgi.service.component.annotations.Component;
import org.slf4j.LoggerFactory;

import com.aem.developer.core.service.ReadJsonService;
@Component(immediate = true, service = ReadJsonService.class)
public class ReadJsonDataImpl implements ReadJsonService {


	/**
	 * Overridden method which will read the JSON data via an HTTP GET call
	 */
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(ReadJsonDataImpl.class);
	@Override
	public String getData() {
		
		String response = Network.readJson(AppConstants.URL);
		log.info("response ----from ---impl--"+response);
		
		return response;
	}

}
