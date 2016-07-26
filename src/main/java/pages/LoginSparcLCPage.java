package pages;

import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class LoginSparcLCPage extends ServiceNowWrappers {

	private final RemoteWebDriver driver;

	public LoginSparcLCPage() {
		this.driver = getDriver();
//		switchToFrame("Frame_Main");
//		System.out.println(driver.getTitle());
	
		// Check that we're on the right page.
//		if (!"Test".equals(driver.getTitle())) {
//			Reporter.reportStep("This is not the login page", "FAILURE");
//		}
	}

	public LoginSparcLCPage switchToMainFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Main");
		return this;
	}

	// The login page allows the user to type their username into the username
	// field
	public LoginSparcLCPage enterUsername(String username) {
		
		if(!enterById("UserName_Id", username))
			Reporter.reportStep("The user name field could not entered with text: "+username, "FAILED");
		return this;
	}

	// The login page allows the user to type their password into the password
	// field
	public LoginSparcLCPage enterPassword(String password) {
		
		if(!enterById("Password_Id", password))
			Reporter.reportStep("The password field could not entered with text: "+password, "FAILED");

		return this;
	}

	// The login page allows the user to submit the login form
	public MenuPage clickLogin() {
		
		clickById("LoginButton_Id");
		Wait(10000);
		goOutOfFrame();
		
		if (isExistById("Welcome_Id"))
			Reporter.reportStep("The Login is successful", "SUCCESS");
		else
			Reporter.reportStep("The Login failed. Check snapshot", "SUCCESS");
		
			
			
		// Return a new page object representing the menu page / home page
		return new MenuPage(driver);
	}

	// The login page allows the user to submit the login form knowing that an
	// invalid username and / or password were entered
	private String getErrorMessage() {
		return getTextByXpath("ErrorMessage_Xpath");
	}

	// The login page allows the user to submit the login form knowing that an
	// invalid username and / or password were entered
	public LoginSparcLCPage verifyErrorMessage(String errorMsg) {
		if (getErrorMessage().equals(errorMsg))
			Reporter.reportStep("The error message:" + errorMsg
					+ " matches the expected error.", "SUCCESS");
		else
			Reporter.reportStep("The error message:" + getErrorMessage()
					+ " do not match with the expected error:" + errorMsg,
					"SUCCESS");

		return new LoginSparcLCPage();
	}

	// The login page allows the user to submit the login form knowing that an
	// invalid username and / or password were entered
	public LoginSparcLCPage clickLoginExpectingFailure() {
		clickById("Welcome_Id");

		if (!isExistById("Home_Welcome"))
			Reporter.reportStep("The Login failed as expected", "SUCCESS");
		else
			Reporter.reportStep(
					"The Login is sucessful but should have failed. Check snapshot",
					"SUCCESS");

		// Return a new page object representing the destination. Should the
		// user ever be navigated to the home page after submiting a login with
		// credentials
		// expected to fail login, the script will fail when it attempts to
		// instantiate the LoginPage PageObject.
		return this;
	}

	// Conceptually, the login page offers the user the service of being able to
	// "log into"
	// the application using a user name and password.
	public MenuPage loginAs(String username, String password) {
		return switchToMainFrame().
			   enterUsername(username).
			   enterPassword(password).
			   clickLogin();
	}

	// The login page allows the user to submit the login form
	public SPARCPortalPage clickLoginforSParcPortal() {
		
		clickById("LoginButton_Id");
		Wait(10000);
		goOutOfFrame();
		
		if (isExistById("welcome"))
			Reporter.reportStep("The Login is successful", "SUCCESS");
		else
			Reporter.reportStep("The Login failed. Check snapshot", "SUCCESS");
		
		// Return a new page object representing the menu page / home page
		return new SPARCPortalPage(driver);
	}

	
	
	
	public SPARCPortalPage loginAsSparcportal(String username, String password) {
		return switchToMainFrame().
			   enterUsername(username).
			   enterPassword(password).
			   clickLoginforSParcPortal();
	}

public SPARCPortalPage clickLoginforSParcPortal1() {
		
		clickById("LoginButton_Id");
		Wait(10000);
//		goOutOfFrame();
		
		if (isExistByXpath("SPARC_MyProfile_Xpath"))
			Reporter.reportStep("The Login is successful", "SUCCESS");
		else
			Reporter.reportStep("The Login failed. Check snapshot", "SUCCESS");
		
		// Return a new page object representing the menu page / home page
		return new SPARCPortalPage(driver);
	}
public SPARCPortalPage loginAsSparcportal1(String username, String password) {
		return switchToMainFrame().
		   enterUsername(username).
		   enterPassword(password).
		   clickLoginforSParcPortal1();
}
}
