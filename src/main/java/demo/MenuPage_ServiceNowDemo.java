package demo;


import org.openqa.selenium.remote.RemoteWebDriver;

import pages.IncidentsListPage;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class MenuPage_ServiceNowDemo extends ServiceNowWrappers{

	private final RemoteWebDriver driver;

	public MenuPage_ServiceNowDemo(RemoteWebDriver driver) {
		this.driver = driver;
		goOutOfFrame();

		// Check that we're on the right page.
//		if (!isExistById("Welcome_Id")) {
//			Reporter.reportStep("This is not the home page", "FAILURE");
//		}
	}

	public MenuPage_ServiceNowDemo switchToMenuFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Nav");

		return this;
	}
	public MenuPage_ServiceNowDemo enterFilter(String value){

		// Switch to the menu frame
		switchToMenuFrame();

		if(!enterById("filter_Id", value))
			Reporter.reportStep("The "+value+" could not be entered in filter box","FAILURE");
		return this;
	}

	public MenuPage_ServiceNowDemo clearFilter(){

		// Switch to the menu frame
		switchToMenuFrame();

		if(!clickById("filter_Clear_Id"))
			Reporter.reportStep("Filter Box could not be cleared","FAILURE");

		return this;
	}
	
	public IncidentPage_ServiceNowDemo clickCreateNew(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("Incident");
		// Click create new
		if(clickLink("INCMENU_NEW"))
			Reporter.reportStep("The create new link is clicked and New Incident window opened as expected.", "SUCCESS");
		else
			Reporter.reportStep("The create new link is not found or clicked.", "FAILURE");
		clearFilter();
		return new IncidentPage_ServiceNowDemo(driver);
	}
	public LoginPage_ServiceNowDemo clickLogout(){
		// go out of the frame
		switchToDefault();

		
		// Step 15: Log out
		if (clickByXpath("Logout_Xpath"))
			Reporter.reportStep("The Log out is successful.","SUCCESS");
		else
			Reporter.reportStep("The Log out link could not be clicked.", "FAILURE");
		
		changeUrlForIM();
		return new LoginPage_ServiceNowDemo();
	}
	public IncidentsListPage_ServiceNowDemo clickOpenUnAssigned(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("Incident");
		// Click create new
		if(clickLink("INCMENU_UNASSIGN"))
			Reporter.reportStep("The Open - Unassigned link is clicked and all the respective incidents displayed as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Open - Unassigned is not found or clicked.", "FAILURE");
		clearFilter();

		return new IncidentsListPage_ServiceNowDemo(driver);
	}
	public IncidentsListPage_ServiceNowDemo clickAssignedToMe(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("Incident");
		// Click Assigned to me - menu
		if(clickLink("INCMENU_ASSIGN")){
			Reporter.reportStep("The Assigned to me - menu is clicked and All the respective Incidents displayed as expected.", "SUCCESS");}
		else
			Reporter.reportStep("The Assigned to me - menu is not found or clicked.", "FAILURE");
		
		clearFilter();
		
		return new IncidentsListPage_ServiceNowDemo(driver);
		
	}
	public IncidentsListPage_ServiceNowDemo clickOpen(){

		// Switch to the menu frame

		switchToMenuFrame();
		Wait(2000);
		enterFilter("Incident");
		// Click create new
		if(clickLink("INCMENU_OPEN")){
			Reporter.reportStep("The Open link is clicked and All the respective Incidents displayed as expected.", "SUCCESS");}
		else
			Reporter.reportStep("The Open is not found or clicked.", "FAILURE");
		
		clearFilter(); 
		
		return new IncidentsListPage_ServiceNowDemo(driver);
	}
	public IncidentsListPage_ServiceNowDemo clickResolved(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("Incident");
		// Click create new
		if(clickLink("INCMENU_RESOLVED")){
			Reporter.reportStep("The Resolved link is clicked and All the respective Incidents displayed as expected.", "SUCCESS");}
		else
			Reporter.reportStep("The Work In Progress link is not found or clicked.", "FAILURE");
		clearFilter();
		return new IncidentsListPage_ServiceNowDemo(driver);
	}
	public IncidentsListPage_ServiceNowDemo clickAll(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("Incident");
		// Click Assigned to me - menu
		if(clickLink("INCMENU_ALL")){
			Reporter.reportStep("The ALL - menu is clicked and all the respective incident displayed as expected.", "SUCCESS");}
		else
			Reporter.reportStep("The ALL - menu is not found or clicked.", "FAILURE");
		clearFilter();
		return new IncidentsListPage_ServiceNowDemo(driver);
	}
}
