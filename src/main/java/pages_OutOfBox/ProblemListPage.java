package pages_OutOfBox;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.ColorUtils;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class ProblemListPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;


	public ProblemListPage(RemoteWebDriver driver) {
		this.driver = driver;

		switchToMainFrame();
		// Check that we're on the right page.
		if (!isExistByXpath("NavBar_Xpath")) {
//			Reporter_ServiceNow.reportStep("This is not the Incident List page", "FAILURE");
		}
	}
	public ProblemListPage switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}	
	public ProblemPage clickCreatedProblem(String incNum){

		if(!selectByVisibleTextByXpath("GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if(enterByXpath("GotoSearch_Xpath",incNum)){
			pressKey(Keys.ENTER);
			Wait(3000);}
		else
			Reporter_ServiceNow.reportStep("The Problem: "+incNum+" could not be entered", "FAILURE");

		// click the first Incident Link
		if(clickByXpath("FirstLink_Xpath"))
			Reporter_ServiceNow.reportStep("The Problem: "+incNum+" is searched and opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Problem is not clicked or not found", "FAILURE");

		return new ProblemPage(driver);
	}	
	public ProblemPage clickFirstProblem(){

		verifyData();

		if(clickByXpath("FirstLink_Xpath"))
			Reporter_ServiceNow.reportStep("The First Problem is clicked Successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The First Problem could not be clicked", "FAILURE");

		return new ProblemPage(driver);
	}

	public ProblemListPage verifyData() {

		resetImplicitWait(5);
		if(isExistByXpath("NoRecords_Xpath"))
			Reporter_ServiceNow.reportStep("Insufficient Data, hence failure.", "FAILURE");
		resetImplicitWait(30);
		return this;	
	}
	
	public ProblemListPage verifyNewButton(){

		switchToMainFrame();
		// Click create new
		if(isExistById("New_Button"))
			Reporter_ServiceNow.reportStep("The New Button is present at the top as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The  New Button could not be found at top", "FAILURE");

		return this;
	}
	
	public ProblemPage clickCreatedChangeRequest(String incNum){

		if(!selectByVisibleTextByXpath("GotoSelect_Xpath", "Number"))
			Reporter_ServiceNow.reportStep("The Number could not be selected", "FAILURE");

		if(enterByXpath("GotoSearch_Xpath",incNum)){
			pressKey(Keys.ENTER);
			Wait(3000);}
		else
			Reporter_ServiceNow.reportStep("The Incident: "+incNum+" could not be entered", "FAILURE");

		// click the first Incident Link
		if(clickByXpath("FirstLink_Xpath"))
			Reporter_ServiceNow.reportStep("The Incident: "+incNum+" is searched and opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Incident is not clicked or not found", "FAILURE");

		return new ProblemPage(driver);
	}	

	
	
}
