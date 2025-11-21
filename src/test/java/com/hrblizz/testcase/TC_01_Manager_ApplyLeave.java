package com.hrblizz.testcase;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.hrblizz.base.Base;
import com.hrblizz.pageobject.LeavePage;
import com.hrblizz.pageobject.LoginPage;



public class TC_01_Manager_ApplyLeave extends Base {

	Base b=new Base();
	private static final Logger logger = LogManager.getLogger(TC_01_Manager_ApplyLeave.class);
	@Test
	public void ManagerApplyLeave() throws IOException {
		
	
		logger.info("****** Starting TC_01 *****");
		
		try {
		logger.info("launched application");
		
		LoginPage login=new LoginPage(driver);
		
		login.setEmail(p.getProperty("MUn"));
		login.setPassword(p.getProperty("Mpassword"));
		login.clickLogin();
		logger.info("Logged in succesfully");
		logger.info("Page loaded succesfully");
	}
		catch(Exception e)
		{
			Assert.fail();
		}
		
}
	@Test
	public void leave() {
		
		LeavePage leave=new LeavePage(driver);
		leave.clickLeave();
		logger.info("Succesfully clicked on Leaves Link");
		leave.clickOnProfile();
		logger.info("Succesfully clicked on Profile Link");
		
		leave.signOut();
		logger.info("UserLogged Out succesfully");
	}
	
}