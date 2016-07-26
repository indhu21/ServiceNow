package pages;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.Reporter;

public class IncidentPage_New extends IncidentPage{


	private final RemoteWebDriver driver;

	private String startTime="";
	private String endTime=""; 

	public IncidentPage_New(RemoteWebDriver driver) {

		super(driver);
		this.driver = driver;
		switchToMainFrame();
	}

	public IncidentPage runSLACalculations(String slA) throws ParseException {


		scrollToElementByXpath("ALERT_TaskSLATableHead_Xpath");

		ArrayList <String> slalist = new ArrayList <String>();


		int i=0;
		int slaColumn = 
				getColumnIndex1("ALERT_TaskSLATableHead_Xpath", "SLA");

		slaColumn = slaColumn+2;

		List<WebElement> slaValues = getAllElementsByXpath(""
				+ "//*[text()='Task SLAs']/following::table[@id='incident.task_sla.task_table']/tbody/tr/td["+slaColumn+"]", false);

		for (WebElement sla : slaValues) {

			if(sla.getText().equals(slA))
				i++;

		}

		System.out.println(slalist);

		if(!clickByXpath("//*[text()='Task SLAs']/following::table[@id='incident.task_sla.task_table']/tbody/tr["+i+"]/td[3]", false))
			Reporter.reportStep("The Preview button is not clicked, hence failed.", "FAILURE");

		startTime = getTextById("Inc_SLAStartTime_Id");
		
		endTime = getTextById("Inc_SLAEndTime_Id");
		
		String actuDff = calculateSLADiffAndAdd(startTime, endTime);
		
		if(!enterById("Inc_SLAStartTime_Id", actuDff))
			Reporter.reportStep("The Start Time could not be entered, hence failed.", "FAILURE");
		
		if(!clickByXpath("Inc_RunSLACacu_Xpath"))
			Reporter.reportStep("The Run SLA Caculation could not be clicked, hence failed.", "FAILURE");
				
		return this;

	}
public IncidentPage verifyHasBreached(String slA) {
		
		
		scrollToElementByXpath("Inc_SLASettingIcon_Xpath");
		
		if(!clickByXpath("Inc_SLASettingIcon_Xpath"))
			Reporter.reportStep("The Setting Icon is not clicked or not found", "FAILURE");
		
			List<String> selectedOpetions=getOptionsByXpath("SA_SelectedList_Xpath");

				if(!selectedOpetions.contains("Has breached")){
					if(!selectByVisibleTextByXpath("SA_AvailableList_Xpath", "Has breached")) 
						Reporter.reportStep("The Value: Has breached is not selected from available list.", "FAILURE");
					if(!clickByXpath("RightArrow_Xpath"))
						Reporter.reportStep("The Right Arrow is not clicked or not found", "FAILURE");}
			if(!clickByXpath("SaveReport_Xpath"))
				Reporter.reportStep("The Save Button is not clicked or not found", "FAILURE");
		
			int i=0;
			int slaColumn = 
					getColumnIndex1("ALERT_TaskSLATableHead_Xpath", "SLA");

			slaColumn = slaColumn+2;

			List<WebElement> slaValues = getAllElementsByXpath(""
					+ "//*[text()='Task SLAs']/following::table[@id='incident.task_sla.task_table']/tbody/tr/td["+slaColumn+"]", false);

			for (WebElement sla : slaValues) {

				if(sla.getText().equals(slA))
					i++;

			}
			int hasbreached = 
					getColumnIndex1("ALERT_TaskSLATableHead_Xpath", "Has breached");
			
				hasbreached=hasbreached+2;
			
			String hasValue = getTextByXpath("//*[text()='Task SLAs']/following::"
					+ "table[@id='incident.task_sla.task_table']/tbody/tr["+i+"]/td["+hasbreached+"]", false);

			if(hasValue.equals("true"))
				Reporter.reportStep("The Has Breached value True as expected.", "SUCCESS");
			else
				Reporter.reportStep("The Has Breached value False, hence failure", "FAILURE");
			
			return this;
}
}






