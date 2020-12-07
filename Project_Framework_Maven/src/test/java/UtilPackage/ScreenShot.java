package UtilPackage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenShot {
	static String screenshot_path=null;
	public  static void screenshotP(WebDriver driver, String screenshotName){

		try {

			String timeStamp_1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			screenshot_path = "G:\\Person\\Framework2020\\Project_Framework_Maven\\src\\test\\resources\\Screenshots"+ timeStamp_1 +"_"+screenshotName+ ".jpg";
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(source, new File(screenshot_path));
			//Extest.log(LogStatus.FAIL, Extest.addScreenCapture(screenshot_path));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}

	}


}
