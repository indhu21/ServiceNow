package pages;


import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Reporter1;
import wrapper.ServiceNowWrappers;

public class MenuPage_DEMO extends ServiceNowWrappers{

	private final RemoteWebDriver driver;

	public MenuPage_DEMO(RemoteWebDriver driver) {
		this.driver = driver;
		goOutOfFrame();

		// Check that we're on the right page.
		if (!isExistById("Welcome_Id")) {
			Reporter1.reportStep("This is not the home page", "FAILURE");
		}
		switchToMenuFrame();
	}

	public MenuPage_DEMO switchToMenuFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Nav");

		return this;
	}

	public IncidentPage_DEMO selectCreateNew() {

		expandMainHeader("Incidents");
		if(selectMenu("Incidents", "Inc_CreateNew"))
			Reporter1.reportStep("The Create New link is clicked successfully and New Incident window opened as expected.","SUCCESS");
		else
			Reporter1.reportStep("The Create New under Incidents - menu is not clicked or not found.","FAILURE");

		return new IncidentPage_DEMO(driver);
	}
	public LoginPage_DEMO clickLogout() {
		// go out of the frame
		switchToDefault();

		// Step 15: Log out
		if (clickByXpath("Logout_Xpath"))
			Reporter1.reportStep("The Log out is successful.","SUCCESS");
		else
			Reporter1.reportStep("The Log out link could not be clicked.", "FAILURE");

		return new LoginPage_DEMO();

	}

	public MenuPage_DEMO createIncident(	String caller, String category, String subCategory, 
			String ciValue, String contactType, String state, String assGroup,
			String assignTo, String impact, String urgency, String shortDes,
			String regUser, String priority) {

		createIncident(	caller, category, subCategory, ciValue, 
				contactType, state, assGroup, assignTo, impact, 
				urgency, shortDes, regUser, priority);
		return this;
	}
	
	public IncidentsListPage_DEMO clickopenUnassigned(){

		expandMainHeader("Incidents");
		
		if(selectMenu("Incidents", "Inc_Unassign"))
			Reporter1.reportStep("The Open-Unassigned link is clicked and a list of all the unassigned incidents are listed as expected ", "SUCCESS");
		else
			Reporter1.reportStep("The Open-Unassigned link is not found or clicked.", "FAILURE");

		return new IncidentsListPage_DEMO(driver);
	}

	public IncidentsListPage_DEMO clickOpen(){

		expandMainHeader("Incidents");
		
		if(selectMenu("Incidents", "Inc_Open"))
			Reporter1.reportStep("The Open link is clicked and a list of all the  Open incidents are listed as expected", "SUCCESS");
		else
			Reporter1.reportStep("The Open link is not found or clicked.", "FAILURE");

		return new IncidentsListPage_DEMO(driver);
	}

	public IncidentsListPage_DEMO clickClosed(){

		expandMainHeader("Incidents");
		
		if(selectMenu("Incidents", "Inc_Closed"))
			Reporter1.reportStep("The Closed link is clicked and a list of all the Closed incidents are listed as expected ", "SUCCESS");
		else
			Reporter1.reportStep("The Closed link is not found or clicked.", "FAILURE");

		return new IncidentsListPage_DEMO(driver);
	}

	public IncidentsListPage_DEMO clickAssignedToMe(){

		expandMainHeader("Incidents");
		
		if(selectMenu("Incidents", "Inc_Assign"))
			Reporter1.reportStep("The Assigned to me link is clicked and a list of all the Assigned to me incidents are listed as expected ", "SUCCESS");
		else
			Reporter1.reportStep("The Assigned to me link is not found or clicked.", "FAILURE");

		return new IncidentsListPage_DEMO(driver);
	}

	public IncidentsListPage_DEMO clickAll(){

		expandMainHeader("Incidents");
		
		if(selectMenu("Incidents", "Inc_All"))
			Reporter1.reportStep("The All link is clicked and a list of all the incidents are listed as expected ", "SUCCESS");
		else
			Reporter1.reportStep("The All link is not found or clicked.", "FAILURE");

		return new IncidentsListPage_DEMO(driver);
	}

}
