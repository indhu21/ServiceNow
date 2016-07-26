package pages;


import java.util.List;

import org.apache.poi.xwpf.usermodel.ISDTContents;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.ColorUtils;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class LinuxPage extends ServiceNowWrappers{

	private final RemoteWebDriver driver;
	private  String name;

	public LinuxPage(RemoteWebDriver driver) {
		this.driver = driver;

		switchToMainFrame();		

		// Check that we're on the right page.
		if (!isExistByXpath("NavBar_Xpath")) {
			Reporter.reportStep("This is not Linux Server Page", "FAILURE");
		}

	}

	public LinuxPage switchToMainFrame(){

		// Switch to the menu frame
		switchToFrame("Frame_Main");
		return this;
	}

	public LinuxPage VerifyGxpValue(String gxp)
	{
		if(getDefaultValueByXpath("Linux_Gxp_Xpath").equals(gxp))
			Reporter.reportStep("The GxP value: "+gxp+" matched as expected.", "SUCCESS");
		else
			Reporter.reportStep("The GxP value :"+gxp+" could not matched, hence failure", "FAILURE");
		return this;
	}

	public String getName()
	{
		name=getAttributeByXpath("Linux_NameReadOnly_Xpath", "value");
		if(name.equals(""))
			Reporter.reportStep("The Name is blank for the Created CI, please check Snapshot", "FAILURE");
		return name;
	}
	
	public LinuxPage verifyDependsOnRelationshipAppears(String value) {
		Wait(5000);
		scrollToElementByXpath("SA_DependsOnRelationshipAppears_XPath");

		if(getTextByXpath("SA_DependsOnRelationshipAppears_XPath").contains(value))		
			Reporter.reportStep("The change of value for the related CI appeared in the baseline as expected", "SUCCESS");
		else	
			Reporter.reportStep("The change of value for the related CI could not appear in the baseline, hemce failure", "FAILURE");
		return this;
	}
	public LinuxPage VerifyGxpValue1(String gxp)
	{
		if(getDefaultValueByXpath("Linux_Gxp_Xpath").equals(gxp))
			Reporter.reportStep("The GxP value: "+gxp+" matched as expected.", "SUCCESS");
		else
			Reporter.reportStep("The GxP value :"+gxp+" could not matched, hence failure", "FAILURE");
		return this;
	}
	
	public LinuxPage verifybaseline(){

		System.out.println(getTextByXpath("MSSQL_Baselin_Xpath"));
		
		scrollToElementByXpath("MSSQL_Baselin_Xpath");
		
//		if(!getTextByXpath("MSSQL_Baselin_Xpath").contains("SOX: Yes"))
//			Reporter.reportStep("The change of value for the related CI is appeares in the baseline as expected.", "FAILURE");
			
		if(getTextByXpath("MSSQL_Baselin_Xpath").contains("GxP: No  was: Yes"))
			Reporter.reportStep("The change of value for the related CI is appeares in the baseline as expected.", "SUCCESS");
		else
			Reporter.reportStep("The Baseline didnot matched with SOX: Yes  was: No, hence failure", "FAILURE");
		return this;
	}

}
