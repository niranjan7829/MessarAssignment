package com.hrblizz.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class Base {

	public static WebDriver driver;
	public org.apache.logging.log4j.Logger logger;
	public Properties p;

	@BeforeClass
	public void setUp() throws IOException {

		FileInputStream File = new FileInputStream(
				System.getProperty("user.dir") + "./src//test//resources//config.properties");
		p = new Properties();
		p.load(File);

		logger = LogManager.getLogger(Base.class);

		if (p.getProperty("browser").equalsIgnoreCase("chrome")) {

			driver = new ChromeDriver();
			logger.info("ChromeDriver Instance is created.");

		}

		else if (p.getProperty("browser").equalsIgnoreCase("firefox")) {

			driver = new FirefoxDriver();
			logger.info("FirefoxDriver Instance is created.");
		}

		else if (p.getProperty("browser").equalsIgnoreCase("edge")) {

			driver = new EdgeDriver();
			logger.info("EdgeDriver Instance is created.");
		} else {
			throw new IllegalArgumentException("Browser Not Supported:" + p.getProperty("browser"));
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
		driver.manage().window().maximize();
		driver.navigate().to(p.getProperty("Appurl"));
	}

	@AfterClass
	public void tearDown() {

		driver.quit();
	}

	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		FileUtils.copyFile(sourceFile, new File(targetFilePath));

		return targetFilePath;

	}

	// Alternative click method
	public void explicitWait(WebElement signOut, int seconds) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));

		WebElement signOutButton = wait.until(ExpectedConditions.elementToBeClickable((signOut)));

		signOutButton.click();
	}

	// scroll till end of page
	public void scrollToEnd(WebDriver driver) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		Thread.sleep(3000);
	}

	// to update in property files
	private static int offsetValue;

	@BeforeSuite
	@Parameters("offsetDays")
	public void setOffset(@Optional("1")int offsetDays) {
		offsetValue = offsetDays;
	}

	// to get value
	public static int getOffset() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "./src//test//resources//config.properties");
		prop.load(fis);
		return Integer.parseInt(prop.getProperty("offsetDays"));
	}

	// to set value
	public static void updateOffset(int newValue) throws IOException {

		String path = System.getProperty("user.dir") + "/src/test/resources/config.properties";
		Properties prop = new Properties();

		// Load existing properties
		FileInputStream fis = new FileInputStream(path);
		prop.load(fis);
		fis.close();

		if (newValue > 8) {
			newValue = 1; // restart cycle
		}

		// Update only offsetDays
		prop.setProperty("offsetDays", String.valueOf(newValue));

		// Write back to file without deleting others
		FileOutputStream fos = new FileOutputStream(path);
		prop.store(fos, "Offset value is updated ");
		fos.close();
	}
}
