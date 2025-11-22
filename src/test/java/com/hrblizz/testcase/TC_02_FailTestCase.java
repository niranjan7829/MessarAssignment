package com.hrblizz.testcase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.hrblizz.base.Base;
import com.hrblizz.pageobject.LoginPage;

public class TC_02_FailTestCase extends Base{
	
	private static final Logger logger = LogManager.getLogger( TC_02_FailTestCase.class);
	//Login Fail
	
	@Test
	public void failLogin() {
		
		
logger.info("****** Starting TC_02 *****");
		
		try {
		logger.info("launched application");
		
		LoginPage login=new LoginPage(driver);
		
		login.setEmail(p.getProperty("MUn"));
		login.setPassword(p.getProperty("Mpasswor"));
		login.clickLogin();
		
		logger.info("Logged in succesfully");
		logger.info("Page loaded succesfully");
	}
		catch(Exception e)
		{
			
			Assert.fail();
			
		}
		
}
		
	}


