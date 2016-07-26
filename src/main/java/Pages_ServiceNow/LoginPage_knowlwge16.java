package Pages_ServiceNow;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class LoginPage_knowlwge16 extends ServiceNowWrappers {

	private final RemoteWebDriver driver;

	public LoginPage_knowlwge16() {
		  this.driver = getDriver();

//		  System.out.println(driver.getTitle());
//		  // Check that we're on the right page.
//		  if (!(driver.getTitle()).contains("Test")) {
//		   Reporter_ServiceNow.reportStep("This is not the login page", "FAILURE");
//		  }
		 }

	public LoginPage_knowlwge16 switchToMainFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Main");

		return this;
	}

	public MenuPage_knowlwge16 clickLoginForDemo() {
		clickById("LoginButton_Id");
//		Wait(10000);
		
//		goOutOfFrame();
		
		if (isExistByXpath("NavBar2_Xpath"))
			Reporter_ServiceNow.reportStep("The Login is successful", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Login failed. Check snapshot", "SUCCESS");

		// Return a new page object representing the menu page / home page
		return new MenuPage_knowlwge16(driver);
	}	
	public LoginPage_knowlwge16 enterUsername(String username) {
		if(!enterById("UserName_Id", username))
			Reporter_ServiceNow.reportStep("The user name field could not entered with text: "+username, "FAILED");
		return this;
	}
	
	public LoginPage_knowlwge16 enterPassword(String password) {
		if(!enterById("Password_Id", password))
			Reporter_ServiceNow.reportStep("The password field could not entered with text: "+password, "FAILED");

		return this;
	}
	
	public MenuPage_knowlwge16 loginAsForDemo(String username, String password) {
		return switchToMainFrame().
			   enterUsername(username).
			   enterPassword(password).
			   clickLoginForDemo();
	}
}
