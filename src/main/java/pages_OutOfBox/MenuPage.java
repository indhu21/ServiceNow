package pages_OutOfBox;


import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;

import pages_OutOfBox.ChangeRequestListPage;
import pages_OutOfBox.ChangeRequestPage;
import pages_OutOfBox.CreateNewPage;
import pages_OutOfBox.IncidentPage;
import pages_OutOfBox.IncidentsListPage;
import pages_OutOfBox.LoginPage;
import pages_OutOfBox.ProblemCreateNewPage;
import pages_OutOfBox.ProblemListPage;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class MenuPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;

	public MenuPage(RemoteWebDriver driver) {
		this.driver = driver;
		goOutOfFrame();

		// Check that we're on the right page.
//		if (!isExistById("Welcome_Id")) {
//			Reporter_ServiceNow.reportStep("This is not the home page", "FAILURE");
//		}
	}

	public MenuPage switchToMenuFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Nav");

		return this;
	}

	public IncidentPage selectCreateNew() {

		enterFilter("Incident");
		
		if(clickLink("Inc_CreateNew"))
			Reporter_ServiceNow.reportStep("The Create New link is clicked successfully and New Create window opened as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Create New under Incidents - menu is not clicked or not found.","FAILURE");

		clearFilter();
		
		return new IncidentPage(driver);
	}

	public IncidentPage searchIncident(String data) {

		// Switch to the out frame
		switchToDefault();

		// enter search    
		if(!enterByXpath("INCMENU_TopSearch_Xpath", data))
			Reporter_ServiceNow.reportStep("Incident: "+data+" could not been entered in search box of the home page", "FAILURE");

		if(pressKey(Keys.ENTER))
			Wait(2000);
		Reporter_ServiceNow.reportStep("Incident: "+data+" is entered successfully in search box of the home page ", "SUCCESS");

		return new IncidentPage(driver);
	}

	public IncidentsListPage clickOpen(){

		// Switch to the menu frame
//		switchToMenuFrame();
//		Wait(2000);
		// Click create new
		
		enterFilter("Incident");
		
		if(clickLink("Inc_Open"))
			Reporter_ServiceNow.reportStep("The Open link is clicked", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Open is not found or clicked.", "FAILURE");

		clearFilter();
		
		return new IncidentsListPage(driver);
	}



	public CreateNewPage clickCreateNew(){

		// Switch to the menu frame
		switchToMenuFrame();
		Wait(2000);
		// Click create new
		if(clickLink("Inc_CreateNew"))
			Reporter_ServiceNow.reportStep("The Create New link is clicked", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Create New is not found or clicked.", "FAILURE");

		return new CreateNewPage(driver);
	}

	public IncidentsListPage clickopenUnassigned(){

		// Switch to the menu frame
//		switchToMenuFrame();
//		Wait(2000);
		
		enterFilter("Incident");
		// Click create new
		if(clickLink("Inc_Unassign"))
			Reporter_ServiceNow.reportStep("The Open-Unassigned link is clicked and a list of all the unassigned Change request are listed as expected ", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Open-Unassigned link is not found or clicked.", "FAILURE");

		clearFilter();
		
		return new IncidentsListPage(driver);
	}

	public IncidentsListPage clickopen(){

		// Switch to the menu frame
		switchToMenuFrame();
		Wait(2000);
		// Click create new
		if(clickLink("Inc_Open"))
			Reporter_ServiceNow.reportStep("The Open link is clicked and a list of all the Open Change request are listed as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Open link is not found or clicked.", "FAILURE");

		return new IncidentsListPage(driver);
	}

	public IncidentsListPage clickClosed(){

		// Switch to the menu frame
//		switchToMenuFrame();
		Wait(2000);
		// Click create new
	
		enterFilter("Incident");
		
		if(clickLink("Inc_Closed"))
			Reporter_ServiceNow.reportStep("The Closed link is clicked and a list of all the Closed Change request are listed as expected ", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Closed link is not found or clicked.", "FAILURE");

		clearFilter();
		
		return new IncidentsListPage(driver);
	}

	public IncidentsListPage clickAssignedToMe(){

		// Switch to the menu frame
//		switchToMenuFrame();
		Wait(2000);
		// Click create new
		enterFilter("Incident");
		
		if(clickLink("Inc_Assign"))
			Reporter_ServiceNow.reportStep("The Assigned to me link is clicked and a list of all the Assigned to me Change request are listed as expected ", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Assigned to me link is not found or clicked.", "FAILURE");

		clearFilter();
		
		return new IncidentsListPage(driver);
	}

	public IncidentsListPage clickAll(){

		// Switch to the menu frame
//		switchToMenuFrame();
		Wait(2000);
		// Click create new
		enterFilter("Incident");
		
		if(clickLink("Inc_All"))
			Reporter_ServiceNow.reportStep("The All link is clicked and a list of all the Change request are listed as expected ", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The All link is not found or clicked.", "FAILURE");

		clearFilter();
		
		return new IncidentsListPage(driver);
	}

	public ChangeRequestListPage selectOpenForChange() {

		enterFilter("Change");

		if(selectMenu("Change", "CHN_Open"))
			Reporter_ServiceNow.reportStep("The Open link is clicked successfully and All the respective Requests are listed as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Open link is not clicked or not found.","FAILURE");

		clearFilter();

		return new ChangeRequestListPage(driver);
	}
	public MenuPage enterFilter(String value){

		// Switch to the menu frame
//		switchToMenuFrame();

		if(!enterById("filter_Id", value))
			Reporter_ServiceNow.reportStep("The "+value+" could not be entered in filter box","FAILURE");
		return this;
	}

	public MenuPage clearFilter(){

		// Switch to the menu frame
//		switchToMenuFrame();

		if(!clickById("filter_Clear_Id"))
			Reporter_ServiceNow.reportStep("'Filter Box' could not be cleared","FAILURE");

		return this;
	}
	public ChangeRequestListPage selectClosedForChange() {

		enterFilter("Change");

		if(selectMenu("Change", "CHN_Closed"))
			Reporter_ServiceNow.reportStep("The Closed link is clicked successfully and All the respective Requests are listed as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Closed link is not clicked or not found.","FAILURE");

		clearFilter();

		return new ChangeRequestListPage(driver);
	}

	public LoginPage clickLogout() {
		// go out of the frame
		switchToDefault();

		// Step 15: Log out
		if (clickByXpath("Logout_Xpath"))
			Reporter_ServiceNow.reportStep("The Log out is successful.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Log out link could not be clicked.", "FAILURE");

		return new LoginPage();

	}

	public MenuPage createIncident(	String caller, String category, String subCategory, 
			String ciValue, String contactType, String state, String assGroup,
			String assignTo, String impact, String urgency, String shortDes,
			String regUser, String priority) {

		createincident_DEMO(	caller, category, subCategory, ciValue, 
				contactType, state, assGroup, assignTo, impact, 
				urgency, shortDes, regUser, priority);
		return this;
	}


	public ChangeRequestPage selectCreateNewForChange() {


		enterFilter("Change");

		if(clickLink("CHN_CreateNew"))
			Reporter_ServiceNow.reportStep("The Create New link is clicked successfully and New Request window opened as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Create New under Incidents - menu is not clicked or not found.","FAILURE");

		clearFilter();

		return new ChangeRequestPage(driver);
	}

	public MenuPage verifyExistanceOfProblemOptions(){

		// Switch to the menu frame
		switchToMenuFrame();

		// you need to change the menu names when the application changes
		// also maintain the order of the links as in screen
		String[] expectedMenus = {"PRTC_CreateNew",
				"PRTC_Assignedtome",
				"PRTC_KnownErrors",
				"PRTC_Open", 
				"PRTC_Pending",
				"PRTC_All",
				"PRTC_Overview",
		};

		String[] expectedMenusDesc = {"Create New",
				"Assigned to me",
				"Known Errors",
				"Open",
				"Pending",
				"All",
				"Overview"
		};

		// Verify the menus of the CMDB
		verifyMenuItems("PRTC_Problem_AllMenu",expectedMenus, expectedMenusDesc);

		return this;
	}
	
	public ProblemCreateNewPage clickProblemCreateNew(){

		// Switch to the menu frame
		switchToMenuFrame();
		Wait(2000);
		// Click create new
		if(clickLink("Inc_CreateNew"))
			Reporter_ServiceNow.reportStep("The Create New link is clicked", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Create New is not found or clicked.", "FAILURE");

		return new ProblemCreateNewPage(driver);
	}
	
	public ProblemListPage clickProblemOpen(){

		// Switch to the menu frame
		switchToMenuFrame();
		Wait(2000);
		// Click create new
		if(clickLink("INCMENU_OPEN"))
			Reporter_ServiceNow.reportStep("The Open link is clicked", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Open link is not found or clicked.", "FAILURE");

		return new ProblemListPage(driver);
	}
	

	public ProblemListPage clickProblemAll(){

		// Switch to the menu frame
		switchToMenuFrame();
		Wait(2000);
		// Click create new
		if(clickLink("Inc_All"))
			Reporter_ServiceNow.reportStep("The All link is clicked and a list of all the Change request are listed as expected ", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The All link is not found or clicked.", "FAILURE");

		return new ProblemListPage(driver);
	}

	public MenuPage rightclickandClickChangeCreate(){

		if (!rightClickByXpath("CHN_Header_Xpath"))
			Reporter_ServiceNow.reportStep("Right click could not be done on the header",
					"FAILURE");

		if (clickByXpath("PRTC_CreateChange")){
			Reporter_ServiceNow.reportStep("Right click is done and Create change is clicked successfully",
					"SUCCESS");
			
		}
		else
			Reporter_ServiceNow.reportStep("The Create change button could not been clicked or found", "FAILURE");
		return this;

	}
	
	public boolean createincident_DEMO(String caller, String category, String subCategory, String ciValue, String contactType,
			String state, String assGroup, String assignTo, String impact, String urgency, String shortDes,
			String regUser, String priority) {
		Boolean breturn=false;
		new MenuPage(driver)
		.selectCreateNew();

		IncidentPage inc = new IncidentPage(driver);

		String incNum = 
				inc.getIncident();
		inc.verifyCategoryIsAlreadySelected()
		.enterCaller(caller)
		.verifyLocationIsAlreadySelected()
		.enterAllFields(category, subCategory, ciValue, contactType, state, assGroup, assignTo)
		.selectImpactAndUrgency(impact, urgency)
		//.verifyPriorityIsReadonly()
		.enterSortDes(shortDes)
		.clickSubmit(regUser)
		.clickCreatedIncident(incNum);
		//.verifyPriority(priority);
		breturn=true;

		return breturn;

	}
}
