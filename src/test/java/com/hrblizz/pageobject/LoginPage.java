package com.hrblizz.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hrblizz.base.Base;

public class LoginPage extends pageFactory {



	
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@name='email']")
	WebElement txtEmail;
	
	@FindBy(xpath = "//input[@name='password']")
	WebElement txtPassWord;
	
	@FindBy(xpath="//button[contains(@class,'button-main label')]")
	WebElement btnLogin;
	
	
	
	//Methods to access this xpaths
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}

	public void setPassword(String pwd) {
		txtPassWord.sendKeys(pwd);
	}

	public void clickLogin() {
		btnLogin.click();
	}
	
}

	
	



