package Pages_ServiceNow;


//import org.apache.bcel.generic.RETURN;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;

import testng.SuiteMethods;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class MenuPage_knowlwge16 extends SuiteMethods{

	private final RemoteWebDriver driver;

	public MenuPage_knowlwge16(RemoteWebDriver driver) {
		this.driver = driver;
//		goOutOfFrame();

		// Check that we're on the right page.
//		if (!isExistById("Welcome_Id")) {
//			Reporter_ServiceNow.reportStep("This is not the home page", "FAILURE");
//		}
	}

	public MenuPage_knowlwge16 switchToMenuFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Nav");

		return this;
	}

	public ListPage_knowlwge16 switchToMainFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Main",5);

		return new ListPage_knowlwge16(driver);
	}
	public MenuPage_knowlwge16 enterFilter(String value){

		// Switch to the menu frame
//		switchToMenuFrame();

		if(!enterById("filter_Id", value))
			Reporter_ServiceNow.reportStep("The "+value+" could not be entered in filter box","FAILURE");
		return this;
	}

	public MenuPage_knowlwge16 clearFilter(){

		// Switch to the menu frame
//		switchToMenuFrame();

		if(!clickById("filter_Clear_Id"))
			Reporter_ServiceNow.reportStep("Filter Box could not be cleared","FAILURE");

		return this;
	}

	public IncidentPage_knowlwge16 clickCreateNew(){

		// Switch to the menu frame
		switchToMenuFrame();
		enterFilter("Incident");
		// Click create new
		if(clickLink("INCMENU_NEW"))
			Reporter_ServiceNow.reportStep("The create new link is clicked and New Incident window opened as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The create new link is not found or clicked.", "FAILURE");
		clearFilter();
		return new IncidentPage_knowlwge16(driver);
	}
	public ListPage_knowlwge16 clickApplication(){

		// Switch to the menu frame
//		switchToMenuFrame();
		enterFilter("Configuration");
		// Click create new
		if(clickLink("Demo_Applications_Link")){
//			verifyData();
			Reporter_ServiceNow.reportStep("The Application link is clicked successfully.", "SUCCESS");}
		else
			Reporter_ServiceNow.reportStep("The Application link is not found or clicked, hence failure.", "FAILURE");
		
		clearFilter();
		
		return new ListPage_knowlwge16(driver);
	}
	public MenuPage_knowlwge16 verifyData(){
		switchToMainFrame();
		if(isExistByXpath("NoRecords_xpath")){
			//if(isExistByXpath("//*[text()='No records to display']", false)){		
			status = "INSUFFICIENT DATA";
			Reporter_ServiceNow.reportStep("Insufficient Data, hence failure.", "FAILURE");}

		return this;
	}
	public LoginPage_knowlwge16 clickLogoutdemo(){
		// go out of the frame
		switchToDefault();
		if (!clickByXpath("Demo_Logout_xpath"))
			Reporter_ServiceNow.reportStep("The Log out link is not clicked, hence failure.","FAILURE");
		

		// Step 15: Log out
		if (clickLink("Demo_LogoutLink_xpath"))
			Reporter_ServiceNow.reportStep("The Log out is successful.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Log out link could not be clicked.", "FAILURE");

		return new LoginPage_knowlwge16();
	}	
}
