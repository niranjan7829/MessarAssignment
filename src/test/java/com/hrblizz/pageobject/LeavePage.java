package com.hrblizz.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.hrblizz.base.Base;

public class  LeavePage extends pageFactory{

	public LeavePage(WebDriver driver) {
		super(driver);
	
	}
	


	//Click on leave
	@FindBy(xpath="//a[@class='lp-dashboard-card']//*[text()='Leaves']")
	WebElement leaveClick;
	
	@FindBy(css=".profile-menu")
	WebElement profileLink;
	
	@FindBy(css=".profile-dropdown__sign-out")
	WebElement signOut;
	
	
	
	public void clickLeave() {
		leaveClick.click();
	}
	
	public void clickOnProfile() {
		profileLink.click();
	}
	
	public void signOut() {
		//Explicit wait called from Base Method
		Base b=new Base();
		b.explicitWait(signOut, 30);
	}
}
