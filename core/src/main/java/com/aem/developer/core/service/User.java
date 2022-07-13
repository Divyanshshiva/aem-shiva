package com.aem.developer.core.service;

public interface User 
{
	String getId();
    /**
    * @return String to represent the name of the user
    */
    String getName();
    /**
    * @return String to represent the gender of the user
    */
    String getGender();

    /**
    * @return String to represent the address of the user
    */
    String getAddress();

}
