package demo;


import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class LoginPage_ServiceNowDemo extends ServiceNowWrappers {

	private final RemoteWebDriver driver;

	public LoginPage_ServiceNowDemo() {
		this.driver = getDriver();

		//		  System.out.println(driver.getTitle());
		//		  // Check that we're on the right page.
		//		  if (!(driver.getTitle()).contains("Test")) {
		//		   Reporter.reportStep("This is not the login page", "FAILURE");
		//		  }
	}

	public LoginPage_ServiceNowDemo switchToMainFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Main");

		return this;
	}

	// The login page allows the user to type their username into the username
	// field
	public LoginPage_ServiceNowDemo enterUsername(String username) {
		if(!enterById("UserName_Id", username))
			Reporter.reportStep("The user name field could not entered with text: "+username, "FAILED");
		return this;
	}

	// The login page allows the user to type their password into the password
	// field
	public LoginPage_ServiceNowDemo enterPassword(String password) {
		if(!enterById("Password_Id", password))
			Reporter.reportStep("The password field could not entered with text: "+password, "FAILED");

		return this;
	}

	// The login page allows the user to submit the login form
	public MenuPage_ServiceNowDemo clickLogin() {
		clickById("LoginButton_Id");
		Wait(10000);

		goOutOfFrame();

		if (isExistById("Welcome_Id"))
			Reporter.reportStep("The Login is successful", "SUCCESS");
		else
			Reporter.reportStep("The Login failed. Check snapshot", "SUCCESS");

		// Return a new page object representing the menu page / home page
		return new MenuPage_ServiceNowDemo(driver);
	}

	public MenuPage_ServiceNowDemo loginAs(String username, String password) {
		return switchToMainFrame().
				enterUsername(username).
				enterPassword(password).
				clickLogin();
	}




}
