package TestPackage;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListener extends MainTestClass implements ITestListener{
	  public void onTestFailure(ITestResult result) {
		  screenshotP(driver, "screenshotName");
		  }
		 
	  public void onTestSkipped(ITestResult result) {
		    // not implemented
		  }

		
	  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		    // not implemented
		  }

	
	  public void onTestFailedWithTimeout(ITestResult result) {
		  teardown(result);
		  }

		
	  public void onStart(ITestContext context) {
		  setUp();
		  }

	
	  public void onFinish(ITestContext context) {
		    // not implemented
		  }
	
	
}
