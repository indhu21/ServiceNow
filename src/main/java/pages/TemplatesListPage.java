package pages;


import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import testng.SuiteMethods;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class TemplatesListPage extends SuiteMethods{

	private final RemoteWebDriver driver;

	public TemplatesListPage(RemoteWebDriver driver) {

		this.driver = driver;
		switchToMainFrame();
		resetImplicitWait(5);
		// Check that we're on the right page.
		if(!(isExistByXpath("HomeTitle_Xpath")||isExistByXpath("NavBar2_Xpath"))) {
			Reporter.reportStep("This is not the Alerts List page", "FAILURE");
		}
		resetImplicitWait(30);
	}
	public TemplatesListPage switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}

	public TemplatesPage clickNewButtonforSA(){	
		switchToMainFrame();

		if(clickById("New_Button"))
			Reporter.reportStep("The New button is clicked successfully", "SUCCESS");
		else
			Reporter.reportStep("The New button could not been found or clicked", "FAILURE");

		return new TemplatesPage(driver);
	}
	public TemplatesPage searchandClickFirstname(String name) {
		if(!enterByXpathAndClick("CIS_SearchReferenceData_Xpath",name))
			Reporter.reportStep("The Created Template: "+name+" could not be entered in searchbox", "FAILURE");

		verifyData();

		// click the first Incident Link
		if(clickByXpath("ALERTPROFILE_FirstAlert_Xpath"))
			Reporter.reportStep("The Created Template:"+name+" is searched and opened as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Created Template link could not be clicked", "FAILURE");

		return new TemplatesPage(driver);
	}

	public TemplatesListPage verifyData(){
		if(isExistByXpath("NoRecords_xpath")){
			//if(isExistByXpath("//*[text()='No records to display']", false)){		
			status="Insuffient Data";
			Reporter.reportStep("Insufficient Data, hence failure.", "FAILURE");}

		return this;
	}	
}

