package Pages_ServiceNow;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class AlertEnrichersPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;


	public AlertEnrichersPage(RemoteWebDriver driver) {
		this.driver = driver;

		switchToMainFrame();

		// Check that we're on the right page.
		if (!isExistByXpath("NavBar_Xpath")) {
			Reporter_ServiceNow.reportStep("This is not the Alert Enrichers Page.", "FAILURE");
		}
	}

	public AlertEnrichersPage switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}

	public AlertEnrichersPage enterEnricherForm(String labelTm, String order, String decoratorType, String recurBehav, String operator , String modification){

		if(!enterById("ALERT_Label_Id", labelTm))
			Reporter_ServiceNow.reportStep("Label: "+ labelTm +" in Alert Enrichers could not be entered","FAILURE");

		if(!clickById("ALERT_Active_Id"))
			Reporter_ServiceNow.reportStep("Active in Alert Enrichers could not be clicked","FAILURE");

		if(!enterById("ALERT_Order_Id", order))
			Reporter_ServiceNow.reportStep("Order: "+ order +" in Alert Enrichers could not be entered","FAILURE");

		if(!selectByVisibleTextById("ALERT_DecoratorType_Id", decoratorType))
			Reporter_ServiceNow.reportStep("Decorator Type: "+ decoratorType +" in Alert Enrichers could not be selected","FAILURE");

		if(!selectByVisibleTextById("ALERT_RecurrenceBehaviour_Id", recurBehav ))
			Reporter_ServiceNow.reportStep("Recurrence Behaviour: "+ recurBehav +" in Alert Enrichers could not be selected","FAILURE");

		Wait(2000);

		if(!addNewFilterUsingInput1("Alert Attribute", "contains", "Node Status"))
			Reporter_ServiceNow.reportStep("Alert Attribute contains Node Status could not be selected","FAILURE");

		if(!selectByVisibleTextById("ALERT_Operator_Id", operator))
			Reporter_ServiceNow.reportStep("Operator: "+ operator +" in Alert Enrichers could not be selected","FAILURE");

		if(enterById("ALERT_Modification_Id", modification))
			Reporter_ServiceNow.reportStep("The Values Label: "+ labelTm +", Order: "+ order +", Decorator Type: "+ decoratorType +", Recurrence Behaviour: "+ recurBehav +", Operator: "+ operator +", Modification: "+ modification +" are entered in Alert Enrichers page successfully.","SUCCESS");
		else 
			Reporter_ServiceNow.reportStep("Modification: "+ modification +" in Alert Enrichers could not be entered","FAILURE");

		return this;
	}
	public AlertEnrichersPage enterEnricherFormWithCollection(String labelTm, String order,String decoratorType,String recurBehav, String colletionType , String dotwalk_Expre){

		if(!enterById("ALERT_Label_Id", labelTm))
			Reporter_ServiceNow.reportStep("Label in Alert Enrichers could not be entered","FAILURE");

		if(!clickById("ALERT_Active_Id"))
			Reporter_ServiceNow.reportStep("Active in Alert Enrichers could not be clicked","FAILURE");

		if(!enterById("ALERT_Order_Id", order))
			Reporter_ServiceNow.reportStep("Order in Alert Enrichers could not be entered","FAILURE");

		if(!selectByVisibleTextById("ALERT_DecoratorType_Id", decoratorType))
			Reporter_ServiceNow.reportStep("Decorator Type in Alert Enrichers could not be selected","FAILURE");

		if(!selectByVisibleTextById("ALERT_RecurrenceBehaviour_Id", recurBehav ))
			Reporter_ServiceNow.reportStep("Recurrence Behaviour in Alert Enrichers could not be selected","FAILURE");

		if(!selectByVisibleTextById("ALERT_CollectionType_Id", colletionType))
			Reporter_ServiceNow.reportStep("Colletion Type in Alert Enrichers could not be selected","FAILURE");

		if(enterById("ALERT_DotwalkExpression_Id", dotwalk_Expre))
			Reporter_ServiceNow.reportStep("All the fields in Alert Enrichers form is filled  successfully","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Alert Enrichers form could not be filled all the fields successfully","FAILURE");

		return this;
	}

	public AlertsEnrichersListPage clickSubmitButton() {
	
	if(clickById("Submit_Id"))
		Reporter_ServiceNow.reportStep("The Submit Button is clicked successfully","SUCCESS");
	else
		Reporter_ServiceNow.reportStep("The Submit Button is not clicked or not found. ","FAILURE");
	
	return new AlertsEnrichersListPage(driver);

}
	
	
}


