package com.hrblizz.testcase;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
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
	//
	Base b = new Base();
	private static final Logger logger = LogManager.getLogger(TC_01_Manager_ApplyLeave.class);

	private static int offsetValue;

	@BeforeSuite
	public void setOffset() throws Exception {
		offsetValue = Base.getOffset();
	}

	@Test
	public void ManagerApplyLeave() throws IOException, InterruptedException {

		logger.info("****** Starting TC_01 *****");

		try {
			logger.info("launched application");

			LoginPage login = new LoginPage(driver);

			login.setEmail(p.getProperty("MUn"));
			login.setPassword(p.getProperty("Mpassword"));
			login.clickLogin();

			logger.info("Logged in succesfully");
			logger.info("Page loaded succesfully");
		} catch (Exception e) {
			Assert.fail();
		}
		
		LeavePage leave = new LeavePage(driver);

		leave.clickLeave();
		logger.info("Succesfully clicked on Leaves Link");

		leave.LeaveBalance();
		logger.info("Leave Balance before Applying leave has saved");

		leave.clickonLeave();
		logger.info("Succesfully clicked on Request Leave");

		leave.clickonLeaveType();
		logger.info("Clicked on leave type");

		leave.SelectLeaveType();
		logger.info("Selected Annual Leave in Dropdown");

		leave.selectFutureDate(driver, offsetValue);
		logger.info("Succesfully selected Leave start");

		leave.selectFutureDate(driver, offsetValue);
		logger.info("Succesfully selected  end Date");

		offsetValue++; // Increase offset

		Base.updateOffset(offsetValue); // Save new value

		scrollToEnd(driver);
		logger.info("Succesfully scrolled till end of page");

		leave.requestLeave();
		logger.info("Succesfully clicked on request Leave");

		leave.clickAndWaitForPageLoad();
		logger.info("Succesfully clicked on Confirm");

		logger.info("Page Loaded completly");

		leave.captureText();
		logger.info("Succesfully Leave is applied by manager");

		leave.clickOnProfile();
		logger.info("Succesfully clicked on Profile Link");

		leave.signOut();
		logger.info("UserLogged Out succesfully");

	}

	
}