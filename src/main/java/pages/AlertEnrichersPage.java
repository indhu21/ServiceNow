package pages;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class AlertEnrichersPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;


	public AlertEnrichersPage(RemoteWebDriver driver) {
		this.driver = driver;

		switchToMainFrame();

		// Check that we're on the right page.
		if (!isExistByXpath("NavBar_Xpath")) {
			Reporter.reportStep("This is not the Alert Enrichers Page.", "FAILURE");
		}
	}

	public AlertEnrichersPage switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}

	public AlertEnrichersPage enterEnricherForm(String labelTm, String order, String decoratorType, String recurBehav, String operator , String modification){

		if(!enterById("ALERT_Label_Id", labelTm))
			Reporter.reportStep("Label: "+ labelTm +" in Alert Enrichers could not be entered","FAILURE");

		if(!clickById("ALERT_Active_Id"))
			Reporter.reportStep("Active in Alert Enrichers could not be clicked","FAILURE");

		if(!enterById("ALERT_Order_Id", order))
			Reporter.reportStep("Order: "+ order +" in Alert Enrichers could not be entered","FAILURE");

		if(!selectByVisibleTextById("ALERT_DecoratorType_Id", decoratorType))
			Reporter.reportStep("Decorator Type: "+ decoratorType +" in Alert Enrichers could not be selected","FAILURE");

		if(!selectByVisibleTextById("ALERT_RecurrenceBehaviour_Id", recurBehav ))
			Reporter.reportStep("Recurrence Behaviour: "+ recurBehav +" in Alert Enrichers could not be selected","FAILURE");

		Wait(2000);

		if(!addNewFilterUsingInput1("Alert Attribute", "contains", "Node Status"))
			Reporter.reportStep("Alert Attribute contains Node Status could not be selected","FAILURE");

		if(!selectByVisibleTextById("ALERT_Operator_Id", operator))
			Reporter.reportStep("Operator: "+ operator +" in Alert Enrichers could not be selected","FAILURE");

		if(enterById("ALERT_Modification_Id", modification))
			Reporter.reportStep("The Values Label: "+ labelTm +", Order: "+ order +", Decorator Type: "+ decoratorType +", Recurrence Behaviour: "+ recurBehav +", Operator: "+ operator +", Modification: "+ modification +" are entered in Alert Enrichers page successfully.","SUCCESS");
		else 
			Reporter.reportStep("Modification: "+ modification +" in Alert Enrichers could not be entered","FAILURE");

		return this;
	}
	public AlertEnrichersPage enterEnricherFormWithCollection(String labelTm, String order,String decoratorType,String recurBehav, String colletionType , String dotwalk_Expre){

		if(!enterById("ALERT_Label_Id", labelTm))
			Reporter.reportStep("Label in Alert Enrichers could not be entered","FAILURE");

		if(!clickById("ALERT_Active_Id"))
			Reporter.reportStep("Active in Alert Enrichers could not be clicked","FAILURE");

		if(!enterById("ALERT_Order_Id", order))
			Reporter.reportStep("Order in Alert Enrichers could not be entered","FAILURE");

		if(!selectByVisibleTextById("ALERT_DecoratorType_Id", decoratorType))
			Reporter.reportStep("Decorator Type in Alert Enrichers could not be selected","FAILURE");

		if(!selectByVisibleTextById("ALERT_RecurrenceBehaviour_Id", recurBehav ))
			Reporter.reportStep("Recurrence Behaviour in Alert Enrichers could not be selected","FAILURE");

		if(!selectByVisibleTextById("ALERT_CollectionType_Id", colletionType))
			Reporter.reportStep("Colletion Type in Alert Enrichers could not be selected","FAILURE");

		if(enterById("ALERT_DotwalkExpression_Id", dotwalk_Expre))
			Reporter.reportStep("All the fields in Alert Enrichers form is filled  successfully","SUCCESS");
		else
			Reporter.reportStep("Alert Enrichers form could not be filled all the fields successfully","FAILURE");

		return this;
	}

	public AlertsEnrichersListPage clickSubmitButton() {
	
	if(clickById("Submit_Id"))
		Reporter.reportStep("The Submit Button is clicked successfully","SUCCESS");
	else
		Reporter.reportStep("The Submit Button is not clicked or not found. ","FAILURE");
	
	return new AlertsEnrichersListPage(driver);

}
	
	
}


