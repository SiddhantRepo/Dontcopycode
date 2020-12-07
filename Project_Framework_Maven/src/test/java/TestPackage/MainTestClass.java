package TestPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import UtilPackage.ScreenShot;

public class MainTestClass {

	static WebDriver driver;
	static ExtentTest test;
	static String screenshot_path=null;
	static String timestamp1=new SimpleDateFormat("YYYY:MM:dd:HH:mm:ss").format(new Date());
	static ExtentReports extent =new ExtentReports( "G:\\Person\\Framework2020\\Project_Framework_Maven\\src\\test\\resources\\Reports\\RegReport_.html");
	 static Properties config=new Properties();
	 static Properties OR=new Properties();
	

@BeforeMethod
	public static void setUp() {
		
		test=	extent.startTest("setUp");
		System.setProperty("webdriver.chrome.driver", "G:\\Person\\ChromeDriver\\chromedriver.exe");
		driver=new ChromeDriver();
		config=new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("G:\\Person\\Framework2020\\Project_Framework_Maven\\Config");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			config.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		OR=new Properties();
		FileInputStream fisor = null;
		try {
			fisor = new FileInputStream("G:\\Person\\Framework2020\\Project_Framework_Maven\\OR");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			OR.load(fisor);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.get("http://demo.automationtesting.in/Register.html");
		test.log(LogStatus.PASS, test.addScreenCapture("screenshot_path"), "Browser launched");
		
	}
		
	

	@Test(dataProvider="getTestData")

	public static void registrationData(String firstname, String lastname, String Address, String Emailaddress, String Phone) {
		test=	extent.startTest("registrationData");
		driver.findElement(By.xpath(OR.getProperty("firstname"))).sendKeys(firstname);
		driver.findElement(By.xpath(OR.getProperty("lastname"))).sendKeys(lastname);
		driver.findElement(By.xpath(OR.getProperty("address"))).sendKeys(Address);
		driver.findElement(By.xpath(OR.getProperty("Emailaddress"))).sendKeys(Emailaddress);
		driver.findElement(By.xpath(OR.getProperty("Phone"))).sendKeys(Phone);
		test.log(LogStatus.PASS, test.addScreenCapture(screenshot_path), "Registration Completed successfully.");
	}

	@DataProvider
	public Iterator<Object[]> getTestData(){
		ArrayList<Object[] > testdata=TestUtil.getDataFromExcel();
		return testdata.iterator();

	}

  @AfterMethod
	public void teardown(ITestResult result)
	{
	if(ITestResult.FAILURE==result.getStatus())
		{
			ScreenShot.screenshotP(driver, result.getName());
			test.log(LogStatus.FAIL, test.addScreenCapture("screenshot_path"), "Failed screenshot added successfully.");
		}
		extent.endTest(test);
		extent.flush();
		driver.quit();
	}
	public  static void screenshotP(WebDriver driver, String screenshotName){

		try {

			String timeStamp_1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			screenshot_path = "G:\\Person\\Framework2020\\Project_Framework_Maven\\Screenshots"+ timeStamp_1 +"_"+screenshotName+ ".jpg";
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(source, new File(screenshot_path));
			//Extest.log(LogStatus.FAIL, Extest.addScreenCapture(screenshot_path));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}

	}


}
