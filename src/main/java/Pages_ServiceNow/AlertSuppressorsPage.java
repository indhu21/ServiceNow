package Pages_ServiceNow;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class AlertSuppressorsPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;


	public AlertSuppressorsPage(RemoteWebDriver driver) {
		this.driver = driver;

		switchToMainFrame();

		// Check that we're on the right page.
		if (!isExistByXpath("NavBar_Xpath")) {
			Reporter_ServiceNow.reportStep("This is not the Alert Suppressors page.", "FAILURE");
		}
	}

	public AlertSuppressorsPage switchToMainFrame(){

		switchToFrame("Frame_Main");
		return this;
	}

	public AlertSuppressorsPage veriyfSuppressProfileName(String profileName){

		String sSuppProfile = getAttributeById("ALERT_Suppress_Target_Id","value");

		if(sSuppProfile.contains(profileName))
			Reporter_ServiceNow.reportStep("The Alert Suppression Target name does matches with "+profileName+" as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Alert Suppress Target name does not match with "+profileName+" the Alert Profile","WARNING");
		return this;
	}

	public AlertSuppressorsPage enterAllMandatoryFields(String alertProfile, String shortDesc, String scheduleType) {

		if(!enterById("ALERT_Suppress_Name_Id",alertProfile))
			Reporter_ServiceNow.reportStep("The Alert Suppress Name is not entered","FAILURE");

		if(!enterById("ALERT_Suppress_ShortDesc_Id", shortDesc+alertProfile))
			Reporter_ServiceNow.reportStep("The Alert Suppress Short Description is not entered","FAILURE");

		if(selectByVisibleTextById("ALERT_Suppress_Type_Id", scheduleType))
			Reporter_ServiceNow.reportStep("The values: Alert Suppress Name: "+alertProfile+",  Short Description: "+shortDesc+alertProfile+", Schedule Type: "+scheduleType+" are entered in respective fields as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Alert Suppress Schedule Type is not selected","FAILURE");

		return this;

	}

	public AlertSuppressorsPage enterStartandEndTime(String startHr,String startMn, String startSc, String endHr, String endMn, String endSc) {

		if(!clickById("Alert_SuppresDay_Id"))
			Reporter_ServiceNow.reportStep("The Alert Suppress Days is not clicked","FAILURE");

		if(!enterById("ALERT_Suppress_StartHour_Id", startHr))
			Reporter_ServiceNow.reportStep("The Alert Suppress Start Hour could not be entered","FAILURE");

		if(!enterById("ALERT_Suppress_StartMin_Id", startMn))
			Reporter_ServiceNow.reportStep("The Alert Suppress Start Minutes could not be entered","FAILURE");

		if(!enterById("ALERT_Suppress_StartSec_Id", startSc))
			Reporter_ServiceNow.reportStep("The Alert Suppress Start Seconds could not be entered","FAILURE");

		if(!enterById("ALERT_Suppress_StopHour_Id", endHr))
			Reporter_ServiceNow.reportStep("The Alert Suppress End Hour could not be entered","FAILURE");

		if(!enterById("ALERT_Suppress_StopMin_Id", endMn))
			Reporter_ServiceNow.reportStep("The Alert Suppress End Minutes could not be entered","FAILURE");

		if(enterById("ALERT_Suppress_StopSec_Id", endSc))
			Reporter_ServiceNow.reportStep("The Alert Suppress Start Time: "+startHr+" "+startMn+" "+startSc+" and End Time: "+endHr+" "+endMn+" "+endSc+" are entered as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Alert Suppress End Seconds could not be entered","FAILURE");

		return this;
	}

	public AlertsSuppressorsListPage clickSubmitButton(String alertProfile) {

		if(clickById("Submit_Id"))
			Reporter_ServiceNow.reportStep("The Submit Button clicked and Alert Suppression:"+alertProfile+" saved as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Submit button is not clicked or not found.","FAILURE");

		return new AlertsSuppressorsListPage(driver);
	}


	public AlertSuppressorsPage selectSuppressTarget(String alertProfile, String supresTab) {

		if(!clickByXpath("Alert_SuppressTargetSpy_Xpath"))
			Reporter_ServiceNow.reportStep("The Suppression Target Spy Class is not clicked or not found.","FAILURE");
		
		waitUntillElementTobeVisible("Alert_SuppressTable_Xpath");
		
		if(!selectByVisibleTextByXpath("Alert_SuppressTable_Xpath", supresTab))
			Reporter_ServiceNow.reportStep("The Table Name Alert Profiles is not selected  or not found.","FAILURE");
		
		if(enterAndChoose1("DocumentText_Xpath", alertProfile))
			Reporter_ServiceNow.reportStep("The First value is selected in Document field successfully.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The First Value is not selected or not found.","FAILURE");
		
		if(!clickById("Ok_Id"))
			Reporter_ServiceNow.reportStep("The OK Button is not clicked or not found.","FAILURE");
			
		return this;
	}
	public AlertSuppressorsPage enterNameAndShortDesc(String alertProfile, String shortDesc) {

		if(!enterById("ALERT_Suppress_Name_Id",alertProfile))
			Reporter_ServiceNow.reportStep("The Alert Suppress Name is not entered","FAILURE");

		if(!enterById("ALERT_Suppress_ShortDesc_Id", shortDesc+alertProfile))
			Reporter_ServiceNow.reportStep("The Alert Suppress Short Description is not entered","FAILURE");
		else
			Reporter_ServiceNow.reportStep("The values: Alert Suppress Name: "+alertProfile+",  Short Description: "+shortDesc+alertProfile+" are entered in respective fields as expected.","SUCCESS");
		
		return this;
	}
	public AlertSuppressorsPage selectScheduleType(String scheduleType) {

		if(selectByVisibleTextById("ALERT_Suppress_Type_Id", scheduleType))
			Reporter_ServiceNow.reportStep("The value: "+scheduleType+" is selected in Schedule Type as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Alert Suppress Schedule Type is not selected","FAILURE");
		return this;
	}
	public AlertsSuppressorsListPage clickSubmitButton() {

		if(clickById("Submit_Id"))
			Reporter_ServiceNow.reportStep("The Submit Button clicked and Alert Suppression Record saved as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Submit button is not clicked or not found.","FAILURE");

		return new AlertsSuppressorsListPage(driver);
	}
	public AlertSuppressorsPage enterStartAndStopTime(String startTime, String stopTime) {

		if(!enterByXpath("AlertSuppressors_ScheduledStart_Xpath", startTime))
			Reporter_ServiceNow.reportStep("The Start Time is not entered, hence failure.","FAILURE");
		
		if(enterByXpath("AlertSuppressors_ScheduledStop_Xpath", stopTime))
			Reporter_ServiceNow.reportStep("The Start Time: "+startTime+", End Time: "+stopTime+" is entered successfully.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Start Time is not entered, hence failure.","FAILURE");
		
		return this;
	}
	public AlertSuppressorsPage verifyAlertSuppDatas(String alertProfile, String target, String shortDesc, 
			String scheduleType,String startTime, String stopTime) {

		if(!getAttributeById("ALERT_Suppress_Name_Id","value").equals(alertProfile))
			Reporter_ServiceNow.reportStep("The Alert Suppress Name is not matched, hence failure..","FAILURE");

		if(!getAttributeById("ALERT_Suppress_ShortDesc_Id","value").equals(shortDesc+alertProfile))
			Reporter_ServiceNow.reportStep("The Sort Description is not matched, hence failure","FAILURE");
		
		if(!getDefaultValueById("ALERT_Suppress_Type_Id").equals(scheduleType))
			Reporter_ServiceNow.reportStep("The Alert Suppress Schedule Type is not matched","FAILURE");
			
//		if(!getAttributeByXpath("AlertSuppressors_ScheduledStart_Xpath", "value").equals(startTime))
//			Reporter_ServiceNow.reportStep("The Start Time is not matched, hence failure.","FAILURE");
//		
		System.out.println(getDefaultValueById("ALERT_Suppress_Target_Id"));
		if(getAttributeById("ALERT_Suppress_Target_Id","value").contains(target))
			Reporter_ServiceNow.reportStep("The Values:  Name: "+alertProfile+", Schedule Type: "+scheduleType+", Suppression Target: "+target+", "
					+ " is matched as expected..","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Alert Suppress Target is not matched, hence failure.","FAILURE");
//		
//		if(getAttributeByXpath("AlertSuppressors_ScheduledStop_Xpath","value").equals(stopTime))
//			Reporter_ServiceNow.reportStep("The Values:  Name: "+alertProfile+", Schedule Type: "+scheduleType+", Suppression Target: "+target+", "
//					+ "Start Time: "+startTime+", End Time: "+stopTime+" is entered successfully.","SUCCESS");
//		else
//			Reporter_ServiceNow.reportStep("The Start Time is not matched, hence failure.","FAILURE");
		
		return this;
	}
	public AlertSuppressorsPage selectSuppressTargets(String alertProfile, String supresTab) {

		if(!clickByXpath("Alert_SuppressTargetSpy_Xpath"))
			Reporter_ServiceNow.reportStep("The Suppression Target Spy Class is not clicked or not found.","FAILURE");
		
		if(!selectByVisibleTextByXpath("Alert_SuppressTable_Xpath", supresTab))
			Reporter_ServiceNow.reportStep("The Table Name Alert Profiles is not selected  or not found.","FAILURE");
		
		if(enterAndChoose("DocumentText_Xpath", alertProfile))
			Reporter_ServiceNow.reportStep("The first Value is selected in Document field successfully.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The first Value is not  selected  or not found.","FAILURE");
		
		if(!clickById("Ok_Id"))
			Reporter_ServiceNow.reportStep("The OK Button is not clicked or not found.","FAILURE");
			
		return this;
	}
	public AlertSuppressorsPage enterStartandEndTimeWithoutClickDay(String startHr,String startMn, String startSc, String endHr, String endMn, String endSc) {

		if(!enterById("ALERT_Suppress_StartHour_Id", startHr))
			Reporter_ServiceNow.reportStep("The Alert Suppress 'Start Hour' could not be entered","FAILURE");

		if(!enterById("ALERT_Suppress_StartMin_Id", startMn))
			Reporter_ServiceNow.reportStep("The Alert Suppress 'Start Minutes' could not be entered","FAILURE");

		if(!enterById("ALERT_Suppress_StartSec_Id", startSc))
			Reporter_ServiceNow.reportStep("The Alert Suppress 'Start Seconds' could not be entered","FAILURE");

		if(!enterById("ALERT_Suppress_StopHour_Id", endHr))
			Reporter_ServiceNow.reportStep("The Alert Suppress 'End Hour' could not be entered","FAILURE");

		if(!enterById("ALERT_Suppress_StopMin_Id", endMn))
			Reporter_ServiceNow.reportStep("The Alert Suppress 'End Minutes' could not be entered","FAILURE");

		if(enterById("ALERT_Suppress_StopSec_Id", endSc))
			Reporter_ServiceNow.reportStep("The Alert Suppress Start Time: "+startHr+" "+startMn+" "+startSc+" and End Time: "+endHr+" "+endMn+" "+endSc+" are entered as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Alert Suppress 'End Seconds' could not be entered","FAILURE");

		return this;
	}
	public AlertSuppressorsPage enterSchudule(String schedule) {

		if(enterById("Sys_Sch_Id", schedule))
			Reporter_ServiceNow.reportStep("The System Schedule value entered with value "+schedule+" as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The System Schedule value not entered with value "+schedule+".","FAILURE");
		
		return this;
	}	
	public AlertSuppressorsPage rightClickAndSave() {

		if(!rightClickByXpath("Alert_Head"))
			Reporter_ServiceNow.reportStep("The right click not performed, hence failure.","FAILURE");
		
		if(clickByXpath("AlertSuppSave_Xpath"))
			Reporter_ServiceNow.reportStep("The Alert Suppressors saved as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Alert Suppressors not saved, hence failure.","FAILURE");
		
		return this;
	}	
	public AlertSuppressorsPage verifyAlertSuppTarget(String target) {

		System.out.println(getAttributeById("ALERT_Suppress_Target_Id","value"));
		if(getAttributeById("ALERT_Suppress_Target_Id","value").contains(target))
			Reporter_ServiceNow.reportStep("The Value: "+target+",  is matched in Suppression Target field as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Alert Suppress Target is not matched, hence failure.","FAILURE");
		return this;
	}
	public AlertSuppressorsPage verifyAlertSuppTargets(String target) {

		System.out.println(getDefaultValueById("ALERT_Suppress_Target_Id"));
		if(getAttributeById("ALERT_Suppress_Target_Id","value").contains(target))
			Reporter_ServiceNow.reportStep("The value is matched in Suppression Target field as expected.","SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Alert Suppress Target is not matched, hence failure.","FAILURE");
		return this;
	}
}


