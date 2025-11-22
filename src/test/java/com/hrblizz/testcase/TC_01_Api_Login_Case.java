package com.hrblizz.testcase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints2;
import io.restassured.response.Response;

public class TC_01_Api_Login_Case {
	private static final Logger logger = LogManager.getLogger( TC_01_Api_Login_Case .class);
	//post_url
	
	@Test(priority=1)
	public void testPostUser()
	{
		//create user from api.endpoints package
		logger.info("********** Creating user  ***************");
		Response response=UserEndpoints2.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("**********User is creatged  ***************");
		
			
	}

}
