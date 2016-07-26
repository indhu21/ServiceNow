package pages_OutOfBox;


import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class ProblemPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;

	public ProblemPage(RemoteWebDriver driver) {
		this.driver = driver;

		switchToMainFrame();

		// Check that we're on the right page.
		if (!isExistByXpath("Back_Xpath")) {
//			Reporter_ServiceNow.reportStep("This is not the Create New page", "FAILURE");
		}
	}

	public ProblemPage switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}	

	public ProblemPage verifyAllMandatoryFields(){

		// you need to change the mandatory fields when the application changes
		String[] mandatoryFields = {"CHN_ManRequestedToCT_Xpath","CHN_ManConfigItemCT_Xpath",
				"CHN_ManAssignmentGroupCT_Xpath",
		"CHN_ManAssignedToCT_Xpath"};

		String[] mandatoryFieldsDesc = {"Requested by","Configuration Item",
				"Assignment Group","Assigned To"};

		verifyMandatoryFields(mandatoryFields, mandatoryFieldsDesc);
		return this;

	}

	public ProblemPage changeState(String state){
		if(getAttributeByXpath("PRTC_AssignmentGroup_Xpath", "value").equals("")){
			enterAndChoose("PRTC_AssignmentGroup_Xpath", "Service Desk");
		}

		if(getAttributeByXpath("PRTC_ConfigurationItem_Xpath", "value").equals(""))
		{
			enterAndChoose("PRTC_ConfigurationItem_Xpath", "*ANNIE-IBM");
		}

//		if(getAttributeByXpath("PRTC_AssignedTo_Xpath", "value").equals(""))
		{
			enterAndChoose("PRTC_AssignedTo_Xpath", "ITIL User");
		}

		if (selectByVisibleTextByXpath("PRTC_State_Xpath", state)) 
			Reporter_ServiceNow.reportStep("The State: "+state+" is selected successfully", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The State: "+state+" could not be selected", "FAILURE");


		return this;

	}


	public ProblemPage rightclickandSave(){

		if (!rightClickByXpath("CHN_Header_Xpath"))
			Reporter_ServiceNow.reportStep("Right click could not be done on the header",
					"FAILURE");

		if (clickByXpath("CHN_Save_Xpath"))
			Reporter_ServiceNow.reportStep("Right clicked is done and Ticket is saved successfully",
					"SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The save button could not been clicked", "FAILURE");
		return this;

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
		return new MenuPage(driver);

	}

	public MenuPage rightclickandVerifyChangeCreateExists(){

		if (!rightClickByXpath("CHN_Header_Xpath"))
			Reporter_ServiceNow.reportStep("Right click could not be done on the header",
					"FAILURE");

		if (IsElementNotPresentByXpath("PRTC_CreateChange"))
			Reporter_ServiceNow.reportStep("Right click is done and Create change is Not exists as expected",
					"SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Right click is done and Create change exists", "FAILURE");
		return new MenuPage(driver);

	}
	

	public ProblemPage clickNewProblemTask(){

		if (!clickByXpath("PRTC_ProblemTasksNew_Xpath"))
			Reporter_ServiceNow.reportStep("New Problem Task could not be created",
					"FAILURE");
		
		enterAndChoose("PRTC_ConfigurationItem_Xpath", "*ANNIE-IBM");
		enterAndChoose("PRTC_AssignmentGroup_Xpath", "Service Desk");
		enterAndChoose("PRTC_AssignedTo_Xpath", "ITIL User");
		
		if (!rightClickByXpath("CHN_Header_Xpath"))
			Reporter_ServiceNow.reportStep("Right click could not be done on the header",
					"FAILURE");

		if (clickByXpath("CHN_Save_Xpath"))
			Reporter_ServiceNow.reportStep("Right clicked is done and Ticket is saved successfully",
					"SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The save button could not been clicked", "FAILURE");
		return this;

	}

}



