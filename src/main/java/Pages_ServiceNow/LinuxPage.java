package Pages_ServiceNow;


import java.util.List;

import org.apache.poi.xwpf.usermodel.ISDTContents;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.ColorUtils;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class LinuxPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;
	private  String name;

	public LinuxPage(RemoteWebDriver driver) {
		this.driver = driver;

		switchToMainFrame();		

		// Check that we're on the right page.
		if (!isExistByXpath("NavBar_Xpath")) {
			Reporter_ServiceNow.reportStep("This is not Linux Server Page", "FAILURE");
		}

	}

	public LinuxPage switchToMainFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Main");
		return this;
	}

	public LinuxPage VerifyGxpValue(String gxp)
	{
		if(getDefaultValueByXpath("Linux_GxpReadOnly_Xpath").equals(gxp))
			Reporter_ServiceNow.reportStep("The GxP value: "+gxp+" matched as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The GxP value :"+gxp+" could not matched, hence failure", "WARNING");
		return this;
	}

	public String getName()
	{
		name=getAttributeByXpath("Linux_NameReadOnly_Xpath", "value");
		if(name.equals(""))
			Reporter_ServiceNow.reportStep("The Name is blank for the Created CI, please check Snapshot", "FAILURE");
		return name;
	}
	
	public LinuxPage verifyDependsOnRelationshipAppears(String value) {
		Wait(5000);
		scrollToElementByXpath("SA_DependsOnRelationshipAppears_XPath");

		if(getTextByXpath("SA_DependsOnRelationshipAppears_XPath").contains(value))		
			Reporter_ServiceNow.reportStep("The change of value for the related CI appeared in the baseline as expected", "SUCCESS");
		else	
			Reporter_ServiceNow.reportStep("The change of value for the related CI could not appear in the baseline, hemce failure", "FAILURE");
		return this;
	}
	public LinuxPage VerifyGxpValue1(String gxp)
	{
		if(getDefaultValueByXpath("Linux_Gxp_Xpath").equals(gxp))
			Reporter_ServiceNow.reportStep("The GxP value: "+gxp+" matched as expected.", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The GxP value :"+gxp+" could not matched, hence failure", "FAILURE");
		return this;
	}
	
	public LinuxPage verifybaseline(){

		System.out.println(getTextByXpath("MSSQL_Baselin_Xpath"));
		
		scrollToElementByXpath("MSSQL_Baselin_Xpath");
		
		if(getTextByXpath("MSSQL_Baselin_Xpath").contains("GxP: No  was: Yes"))
			Reporter_ServiceNow.reportStep("The change of value for the related CI is appeares in the baseline as expected.", "FAILURE");
		else
			Reporter_ServiceNow.reportStep("The Baseline didnot matched with SOX: Yes  was: No, hence failure", "FAILURE");
		return this;
	}

}
