package TestPackage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import UtilPackage.ScreenShot;



public class TestBase {
	static WebDriver driver;
	static ExtentTest Extest;
	static String screenshot_path=null;
	
	static String timestamp1=new SimpleDateFormat("YYYY:MM:dd:HH:mm:ss").format(new Date());
	static ExtentReports ExtRe =new ExtentReports( "G:\\Person\\Framework2020\\Project_Framework_Maven\\src\\test\\resources\\Reports\\Report_.html");
	@BeforeMethod
	public static void setUp() {
		Extest=	ExtRe.startTest("setUp");
		System.setProperty("webdriver.chrome.driver", "G:\\Person\\ChromeDriver\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.google.com");
		Extest.log(LogStatus.PASS, Extest.addScreenCapture("screenshot_path"), "Browser launched");
		//Extest.log(LogStatus.PASS, "Google has launched.");
	}

	@Test
	public void login() {
		Extest=	ExtRe.startTest("login");
		driver.findElement(By.xpath("//*[@name='a']")).sendKeys("Testing");
		Extest.log(LogStatus.FAIL, Extest.addScreenCapture("screenshot_path"), "Screenshot taken");
		}

	@Test
	public void login2() {
		Extest=	ExtRe.startTest("login2");
		driver.findElement(By.xpath("//*[@name='q']")).sendKeys("Testing");
		Extest.log(LogStatus.PASS, Extest.addScreenCapture("screenshot_path"), "Testing login 2 Screenshot taken");
		driver.findElement(By.xpath("//*[@name='q']")).sendKeys(Keys.RETURN);
		Extest.log(LogStatus.PASS, Extest.addScreenCapture("screenshot_path"), "Serach COmplete Screenshot taken");
		}
	
	@AfterMethod
	public void teardown(ITestResult result)
	{
		if(ITestResult.FAILURE==result.getStatus())
		{
			ScreenShot.screenshotP(driver, result.getName());
			Extest.log(LogStatus.FAIL, Extest.addScreenCapture("screenshot_path"), "Added screenshot successfully.");
					}
		ExtRe.endTest(Extest);
		ExtRe.flush();
		driver.quit();
	}
	public  static void screenshotP(WebDriver driver, String screenshotName){

		try {

			String timeStamp_1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			screenshot_path = "G:\\Person\\Framework2020\\Project_Framework_Maven\\src\\test\\resources\\Screenshots"+ timeStamp_1 +"_"+screenshotName+ ".jpg";
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(source, new File(screenshot_path));
			Extest.log(LogStatus.PASS, Extest.addScreenCapture(screenshot_path));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			Extest.log(LogStatus.FAIL, Extest.addScreenCapture(screenshot_path));
			
		}

	}

}