package Pages_ServiceNow;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import testng.SuiteMethods;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class CIScopePage extends SuiteMethods{

	private final RemoteWebDriver driver;


	public CIScopePage(RemoteWebDriver driver) {
		this.driver = driver;

		switchToMainFrame();

		// Check that we're on the right page.
		if (!(isExistByXpath("CIscopeHeader_Xpath")|| isExistByXpath("NavBar2_Xpath"))) {
			Reporter_ServiceNow.reportStep("This is not the CI Scope Page.", "FAILURE");
		}
	}

	public CIScopePage switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}

	public CIScopePage enterAllFields(String name, String shortDescription, String filter, String owningGroup ){
		if (!enterByXpath("CIS_Name_Xpath", name))
			Reporter_ServiceNow.reportStep("The CI Scope Name - could not be entered","FAILURE");

		if (!enterByXpath("CIS_ShortDesc1_Xpath", shortDescription))
			Reporter_ServiceNow.reportStep("The CI Scope description could not be entered","FAILURE");

		if (!selectByVisibleTextByXpath("CIS_Filter_Xpath", filter))
			Reporter_ServiceNow.reportStep("The CI Scope Filter could not be selected","FAILURE");

		if (!enterAndChoose("CIS_OwningGroup_Xpath", owningGroup))
			Reporter_ServiceNow.reportStep("The Owning Group could not be selected","FAILURE");

		if (clickById("SubmitButton_Id"))
			Reporter_ServiceNow.reportStep("The Values: CI Scope Name: "+name+", CI Scope Description: "+shortDescription+", CI Scope Type: "+filter+", CI Scope Owning Group: "+owningGroup+" are entered and Submit button is clicked successfully","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The submit button could not be clicked","FAILURE");

		return this ;
	}

	public CIScopePage selectCIClassAndClickUpdate(String f1Section){

		if(!selectByVisibleTextByXpath("CIS_CIClass_Xpath",f1Section))
			Reporter_ServiceNow.reportStep("In CI Class section "+ f1Section+ " could not be selected","FAILURE");

		Wait(5000);

		if (clickById("CIS_UpdateButton_Id"))
			Reporter_ServiceNow.reportStep("The Value CI Class: "+ f1Section+ " is selected and The CI Scope is updated successfully.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Update button could not be clicked","FAILURE");

		return this;
	}	
	public CIScopePage verifyValues(String[] expectedValues){		

		//		clickByXpath("CIScope_TargetedCITab_Xpath");

		if(isExistByXpath("Targeted_NoRecords_Xpath")){
			//if(isExistByXpath("//*[text()='No records to display']", false)){		
			status = "INSUFFICIENT DATA";
			Reporter_ServiceNow.reportStep("Insufficient Data, hence failure.", "FAILURE");}

		scrollToElementByXpath("CIS_TargetCIFilterImage_Xpath");

		int column=getColumnIndex1("Targeted_TableHead_Xpath", "Class");

		scrollToElementByXpath("(//*[contains(@id,'hdr_x_tori2_opd_ci_scopes')]/following::a[@class='column_head list_hdrcell table-col-header'])["+column+"]", false);

		if(verifyallElementTexts1("(//table[contains(@id,'x_tori2_opd_ci_scopes')])/tbody/tr/td["+column+"]", expectedValues))
			Reporter_ServiceNow.reportStep("All Elements listed under Targetted CIs section is matched with "+convertStringArrayToString(expectedValues)+" as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("All Elements listed under Targetted CIs section is not matched with "+convertStringArrayToString(expectedValues)+", hence failure.", "FAILURE");
		return this;
	}

	public CIScopePage addNewFilterinAlertUsingSelect(String filterType, String filterCondition, String filterValue){

		if(!clickByXpath("CIS_TargetCIFilterImage_Xpath"))
			Reporter_ServiceNow.reportStep("The Filter Image is not clicked or not found.", "FAILURE");

		if(!addFilters("CIS_TargetCIFilterType_Xpath", filterType, "CIS_TargetCIFilterCondition_Xpath", filterCondition, "CIS_TargetCIFilterValue_Xpath", filterValue))
			Reporter_ServiceNow.reportStep("The Filter Value: "+filterType+" "+filterCondition+" "+filterValue+" "+"is not selected, hence failure.", "FAILURE");

		if(clickByXpath("CIS_TargetCIFilterRun_Xpath"))
			Reporter_ServiceNow.reportStep("The Filter Value: "+filterType+" "+filterCondition+" "+filterValue+" "+"is selected and run button clicked successfully.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Run Button is not clicked, hence failure..", "FAILURE");
		return this;
	}

	public CIScopePage clickUpdate(){
		if (clickById("CIS_UpdateButton_Id"))
			Reporter_ServiceNow.reportStep("The Update button is clicked successfully.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Update button could not be clicked","FAILURE");
		return this;
	}

	public String getCINumber() {

		String cinum=getAttributeByXpath("CIS_Number_Xpath", "value");
		if(cinum.equals(""))
			Reporter_ServiceNow.reportStep("The CI Scope number is blank.","FAILURE");
		return cinum;
	}
	public CIScopePage selectFilterandSave(String fSection, String fCondition,String fValue ){
		if(selectByIndexByXpath("CIS_CIClass_Xpath",1))
			Reporter_ServiceNow.reportStep("The First CI class is selected in CI Class section is successful.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("In CI Class section could not be selected","FAILURE");

		Wait(5000);

		if(!addNewFilterUsingSelect(fSection, fCondition, fValue))
			Reporter_ServiceNow.reportStep("In CI Filter section "+ fSection +" " + fCondition +" " + fValue +" could not be selected","FAILURE");

		if (clickById("CIS_UpdateButton_Id"))
			Reporter_ServiceNow.reportStep("The Filter Values: "+ fSection +" " + fCondition +" " + fValue +" is selcted and Update Button is clicked successfully.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Update button not clicked or not found.","FAILURE");

		return this;
	}	

	public CIScopePage verifyUpdateButtonAvl(){

		if (IsElementNotPresentById("CIS_UpdateButton_Id"))
			Reporter_ServiceNow.reportStep("The Update button is not found as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Update button has been found, hence failure.", "FAILURE");
		return this;
	}


	public CIScopePage verifyDisabledFields(){

		String[] elements={	"CIS_Number_Xpath",
				"CIS_Name1_Xpath",
				"CIS_ParentScope_Xpath",
				"CIS_Owning_group_Xpath",
				"CIS_shortDesc1_Xpath",
		"CIS_ScopeType_Xpath" };

		String[] values={	"Number", 
				"Name",
				"Parent Scope",
				"Owning Group",
				"Short Decription", 
		"Scope Type"  };

		verifyDisabledFieldsByXpath(elements, values);
		return this;
	}

	public CIScopePage verifyEnabledFields(){

		String[] elements={	"CIS_Number_Xpath",
				"CIS_Name1_Xpath",
				"CIS_ParentScope_Xpath",
				"CIS_Owning_group_Xpath", 
				"CIS_shortDesc1_Xpath",
		"CIS_ScopeType_Xpath" };

		String[] values={	"Number",
				"Name",
				"Parent Scope", 
				"Owning Group", 
				"Short Decription", 
		"Scope Type"  };

		verifyEnabledFieldsByXpath(elements, values);
		return this;
	}

	public CIScopesListPage clickBackButton() {

		if (clickByXpath("Back_Xpath"))
			Reporter_ServiceNow.reportStep("The Back button is clicked successfully.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Back button is not clicked or not found, hence failure.", "FAILURE");

		return new CIScopesListPage(driver);
	}

	public CIScopePage enterNameAndShortDesc(String name, String shortDescription){
		if (!enterByXpath("CIS_Name_Xpath", name))
			Reporter_ServiceNow.reportStep("The CI Scope Name - could not be entered","FAILURE");

		if (enterByXpath("CIS_ShortDesc1_Xpath", shortDescription))
			Reporter_ServiceNow.reportStep("The Values: CI Scope Name: "+name+", CI Scope Description: "+shortDescription+" are entered successfully","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The CI Scope description could not be entered","FAILURE");

		return this ;
	}

	public CIScopePage selectCIScopeType(String filter){

		if (selectByVisibleTextByXpath("CIS_Filter_Xpath", filter))
			Reporter_ServiceNow.reportStep("The CI Scope Type selected with value "+filter+" as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The CI Scope Filter could not be selected","FAILURE");

		return this ;
	}

	public CIScopePage enterOwmingGrop(String owningGroup ){
		if(enterAndChoose("CIS_OwningGroup_Xpath", owningGroup))
			Reporter_ServiceNow.reportStep("The Value "+owningGroup+" is entered in CI Scope Owning Group field successfully","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Owning Group could not be entered.","FAILURE");
		return this ;
	}

	public CIScopePage clickSubmit(){

		if (clickById("CIS_SubmitButton_Id"))
			Reporter_ServiceNow.reportStep("The Submit button is clicked successfully and New CI Registration is created as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The submit button could not be clicked","FAILURE");

		return this ;
	}
	public CIScopePage selectFilter(String fSection, String fCondition,String fValue ){
		if(selectByIndexByXpath("CIS_CIClass_Xpath",1))
			Reporter_ServiceNow.reportStep("The First CI class is selected in CI Class section is successful.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("In CI Class section could not be selected","FAILURE");

		Wait(5000);

		if(addNewFilterUsingSelect(fSection, fCondition, fValue))
			Reporter_ServiceNow.reportStep("The Filter Values: "+ fSection +" " + fCondition +" " + fValue +" is selcted successfully.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("In CI Filter section "+ fSection +" " + fCondition +" " + fValue +" could not be selected","FAILURE");

		return this;
	}	
	public CIScopePage selectCIScopeType(String f1Section, String CI_Filter){

		if(!selectByVisibleTextByXpath("CIS_CIClass_Xpath",f1Section))
			Reporter_ServiceNow.reportStep("In CI Class section "+ f1Section+ " could not be selected","FAILURE");

		if(selectByVisibleTextById("CI_TYPE_ID", CI_Filter))
			Reporter_ServiceNow.reportStep("The CI Scope type has been selected successfully with value "+CI_Filter,"SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The CI Scope type could not be selected","FAILURE");

		return this;
	}	
	public CIScopePage selectBusinessService(String CI_Service){

		if(enterAndChoose("CI_BusinessService_Xpath", CI_Service))
			Reporter_ServiceNow.reportStep("The CI Business Services has been selected with value "+CI_Service+" as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The CI Business Services has been not been selected","FAILURE");

		return this;
	}	
	//	public CIScopePage verifyCIPresent(){		
	//
	//		scrollToElementByXpath("CIS_TargetCIFilterImage_Xpath");
	//
	//		switchToMainFrame();		
	//
	//		List<WebElement> ele=getAllElementsByXpath("CIS_TargetCIs_Xpath");
	//
	//		if(ele.size()!=0)
	//			Reporter_ServiceNow.reportStep("The CIs is appeared under Targetted CIs section as expected.", "SUCCESS");
	//		else
	//			Reporter_ServiceNow.reportStep("The CIs is not appeared under Targetted CIs section , hence failure.", "FAILURE");
	//		return this;
	//	}

	public CIScopePage verifyCIPresent(){  

		scrollToElementByXpath("CIScope_TargetedCITab_Xpath");

		clickByXpath("CIScope_TargetedCITab_Xpath");

		switchToMainFrame();  

		List<WebElement> ele=getAllElementsByXpath("CIS_TargetCIs_Xpath");

		if(ele.size()!=0)
			Reporter_ServiceNow.reportStep("The CIs is appeared under Targetted CIs section as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The CIs is not appeared under Targetted CIs section , hence failure.", "FAILURE");
		return this;
	}

	public CIScopePage verifyBusinessServiceReadOnly(String user){		

		if(verifyAttributeTextById("CIScope_BusinesService_Xpath", "readonly", "readonly"))
			Reporter_ServiceNow.reportStep("No permission to change record by the user: "+user+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("User: "+user+" have the permission to save the record, hence failure", "FAILURE");
		return this;
	}
	public CIScopePage enterAllFieldsWithoutSubmit(String name, String shortDescription, String filter, String owningGroup ){
		if (!enterByXpath("CIS_Name_Xpath", name))
			Reporter_ServiceNow.reportStep("The CI Scope Name - could not be entered","FAILURE");

		if (!enterByXpath("CIS_ShortDesc1_Xpath", shortDescription))
			Reporter_ServiceNow.reportStep("The CI Scope description could not be entered","FAILURE");

		if (!selectByVisibleTextByXpath("CIS_Filter_Xpath", filter))
			Reporter_ServiceNow.reportStep("The CI Scope Filter could not be selected","FAILURE");

		if (enterAndChoose("CIS_OwningGroup_Xpath", owningGroup))
			Reporter_ServiceNow.reportStep("The Values: CI Scope Name: "+name+", CI Scope Description: "+shortDescription+", CI Scope Type: "+filter+", CI Scope Owning Group: "+owningGroup+" are entered successfully","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Owning Group could not be selected","FAILURE");

		return this ;
	}

	public CIScopePage selectApplication(String CI_Service){

		if(enterAndChoose("CI_Application_Xpath", CI_Service))
			Reporter_ServiceNow.reportStep("The CI Application has been selected with value: "+CI_Service+" as expected","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The CI Application has been not been selected","FAILURE");


		return this;
	}	
	public CIScopePage verifyApplicationReadOnly(String user){		

		if(verifyAttributeTextById("CIScope_ApplicationService_Xpath", "readonly", "readonly"))
			Reporter_ServiceNow.reportStep("No permission to change record by the user: "+user+" as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("User: "+user+" have the permission to save the record, hence failure", "FAILURE");
		return this;
	}

	public CIScopePage addClassColumn(String text){		

		boolean bValue=false;
		clickByXpath("CIScope_TargetedCITab_Xpath");

		waitUntillElementTobeVisible("TargettedCI_Setting_Xpath");

		if(!clickByXpath("TargettedCI_Setting_Xpath"))
			Reporter_ServiceNow.reportStep("The Setting Icon is not clicked or not available, hence failure", "FAILURE");

		Wait(5000);

		List<String> optionValues = getOptionsByXpath("ALERT_SlushSelected_Xpath");

		for (String ele : optionValues) {

			System.out.println(ele);
			if(ele.equals(text))
			{
				bValue=true;

				if(!clickById("Ok_Id"))
					Reporter_ServiceNow.reportStep("The Ok Button of the Personalized List Columns could not be clicked", "FAILURE");

				break;}
		}

		if(!bValue){

			if(!selectByVisibleTextByXpath("ALERT_SlushAvailable_Xpath", text))
				Reporter_ServiceNow.reportStep("The Value "+text+" could not be selected or not available, hence failure", "FAILURE");

			if(!clickByXpath("ALERT_MoveBreach_ToSelected_Xpath"))
				Reporter_ServiceNow.reportStep("The Move button could not be clicked.", "FAILURE");

			if(!clickById("Ok_Id"))
				Reporter_ServiceNow.reportStep("The Ok Button of the Personalized List Columns could not be clicked", "FAILURE");
		}

		return this;
	}
	public CIScopePage verifyValues1(String[] expectedValues){  

		  //  clickByXpath("CIScope_TargetedCITab_Xpath");

		  if(isExistByXpath("Targeted_NoRecords_Xpath")){
		   //if(isExistByXpath("//*[text()='No records to display']", false)){  
		   status = "INSUFFICIENT DATA";
		   Reporter_ServiceNow.reportStep("Insufficient Data, hence failure.", "FAILURE");}

		  scrollToElementByXpath("CIS_TargetCIFilterImage_Xpath");

		  int column=getColumnIndex1("Targeted_TableHead_Xpath", "Class");

		  column=column+2;
		  scrollToElementByXpath("(//*[contains(@id,'hdr_x_tori2_opd_ci_scopes')]/following::a[@class='column_head list_hdrcell table-col-header'])["+column+"]", false);

		//  waitUntillValueBecomeNotNull("(//table[contains(@id,'x_tori2_opd_ci_scopes')])/tbody/tr/td["+column+2+"]", false);
		  
		  if(verifyallElementTexts1("(//table[contains(@id,'x_tori2_opd_ci_scopes')])/tbody/tr/td["+column+"]", expectedValues))
		   Reporter_ServiceNow.reportStep("All Elements listed under Targetted CIs section is matched with "+convertStringArrayToString(expectedValues)+" as expected.", "SUCCESS");
		  else
		   Reporter_ServiceNow.reportStep("All Elements listed under Targetted CIs section is not matched with "+convertStringArrayToString(expectedValues)+", hence failure.", "FAILURE");
		  return this;
		 }

}


