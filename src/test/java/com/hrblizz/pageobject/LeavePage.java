package com.hrblizz.pageobject;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hrblizz.base.Base;

public class  LeavePage extends pageFactory{
	int number;
	Base b=new Base();
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
	
	@FindBy(xpath="//*[contains(@class,'balance-row balance')]//div/span")
	WebElement LeaveBalance;
	
	@FindBy(xpath="//button[@aria-label='Request new leave']")
	WebElement clickonLeave;
	
	@FindBy(id = "leave-types")
	WebElement clickonLeaveType;
	

	@FindBy(xpath="//*[@id='dropdown-list_leave-types']//div[text()='Annual leave']")
	WebElement SelectLeaveType;

	@FindBy(xpath="//*[text()='Request leave']")
	WebElement requestLeave;
	
	
	@FindBy(xpath="//*[text()='Confirm']")
	WebElement confirmClick;
	
	@FindBy(xpath="//*[contains(text(),'Request has been auto approved')]")
	WebElement captureText;
	
	
	public void clickLeave() {
		leaveClick.click();
	}
	
	public void clickOnProfile() {
		profileLink.click();
	}
	
	//extract leave balance
	public void LeaveBalance() {
		
		String text=LeaveBalance.getText();
		String text2 = text.replaceAll("\\D+", "");
	number=Integer.parseInt(text2);
		System.out.println(number);
	}
	
	public void clickonLeave() {
		clickonLeave.click();
	}
	
	public void clickonLeaveType() {
		clickonLeaveType.click();
	}
	
	public void SelectLeaveType() {
		SelectLeaveType.click();
	}
	
	
	public void requestLeave() {
		requestLeave.click();
	}
	

		public void clickAndWaitForPageLoad() throws InterruptedException {

			confirmClick.click();

		Thread.sleep(7000);

		    System.out.println("Clicked element and page load completed.");
		}
		
	
	
	public void captureText() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		String xpath="//*[contains(text(),'Request has been auto approved')]";
		WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

		// Capture and print toast text
		String message = toastMessage.getText();
		System.out.println("Toast Message: " + message);
	}
	
	
	
	public void signOut() {
		//Explicit wait called from Base Method
		
		b.explicitWait(signOut, 30);
	}
	
	
	
	
	public void selectFutureDate(WebDriver driver, int n) {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    LocalDate date = LocalDate.now().plusDays(n);

	    // Skip weekends
	    while (date.getDayOfWeek() == DayOfWeek.SATURDAY ||
	           date.getDayOfWeek() == DayOfWeek.SUNDAY) {
	        date = date.plusDays(1);
	    }

	    String dateId = date.toString();  // yyyy-MM-dd format
	    System.out.println("Selecting date: " + dateId);

	    String xpath = "//div[@id='" + dateId + "' and contains(@class,'calendar__grid-cell')]";

	    WebElement dateElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dateElement);
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dateElement);

	    System.out.println("Selected Date = " + date);
	}
	
	
}
