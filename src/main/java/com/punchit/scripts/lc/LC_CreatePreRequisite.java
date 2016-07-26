package com.punchit.scripts.lc;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.Reporter;
import utils_DataCreation.readFrom_ConfigFile;
import utils_DataDeletion.Delete;
import utils_DataDeletion.PrepareDeleteQuery;
import wrapper.ServiceNowWrappers;

public class LC_CreatePreRequisite extends SuiteMethods {

	
	@Test
	public void createPreRequisite() throws FileNotFoundException, IOException, Exception{

		     // Pre-requisities
		try {
			
			//******************//
			//Delete Task
			//******************//
			
			snW = new ServiceNowWrappers(entityId);
			
			String[] values=PrepareDeleteQuery.GetDeleteObjects("LC_CreatePreRequisite","CI Task"); // Get the input Parameters
			
			String query="{\"sysparm_query\":\""+values[0]+"\"}";
			System.out.print(query);
			System.out.println("Table name is"+readFrom_ConfigFile.GetCMDBSparc_Table_names("GileadSparctest_TaksTableName"));
			Delete.delete(query, readFrom_ConfigFile.GetCMDBSparc_Table_names("GileadSparctest_TaksTableName"));
			
			//******************//
			//Delete Relationships
			//******************//
			
			String[] Relationshipsvalues=PrepareDeleteQuery.GetDeleteObjects("LC_CreatePreRequisite","CI_Relationships"); // Get the input Parameters
			
			 if(!PrepareDeleteQuery.ValidateInput(Relationshipsvalues)){
					//Reporter.reportStep("The Input Parameters for Deleting the Relationships are not Valid.Please Verify", "FAILURE");
				     status = "FAIL";
			 }
			
			String Relationshipquery=PrepareDeleteQuery.PrepareMultiDeleteQuery(Relationshipsvalues);
			System.out.println("Table name is"+readFrom_ConfigFile.GetCMDBSparc_Table_names("GileadSparctest_RelationshipsTableName"));
			System.out.print(Relationshipquery);
			Delete.delete(Relationshipquery, readFrom_ConfigFile.GetCMDBSparc_Table_names("GileadSparctest_RelationshipsTableName"));
		   
			//******************//
			//Delete CI
			//******************//
			
			String[] CIvalues=PrepareDeleteQuery.GetDeleteObjects("LC_CreatePreRequisite","CI"); // Get the input Parameters
			
			 if(!PrepareDeleteQuery.ValidateInput(CIvalues)){
					//Reporter.reportStep("The Input Parameters for Deleting the CI are not Valid.Please Verify", "FAILURE");
				 status = "FAIL";
			 }
			
			String CIquery=PrepareDeleteQuery.PrepareMultiDeleteQuery(CIvalues);
			
			Delete.delete(CIquery, readFrom_ConfigFile.GetCMDBSparc_Table_names("GileadSparctest_CiTableName"));
			
			//Reporter.reportStep("CI's , Relationships , Configurations Task's Data is Deleted Successfully", "SUCCESS");
			
			//******************//
			//Upload CI XML
			//******************//
			
			snW.launchApp(browserName, true);
				
	
			//	Reporter.reportStep("The browser:" + browserName + " could not be launched", "FAILURE");

			String[] credetials=readFrom_ConfigFile.GetCredentials_Delete_sparc();
			// Step 1: Log in to application
			if (!snW.login(credetials[0], credetials[1])){
				status = "FAIL";
			}
				//Reporter.reportStep("The login with username:"+ credetials[0] + " is successful", "SUCCESS");
			
			if(!snW.selectMenu("CMDB_Menu", "SystemApplications_Menu")){
				status = "FAIL";
			}
				//			Reporter.reportStep("Error While Creating CI's", "FAILURE");

			//Wait for the Alerts to load
			snW.Wait(3000);
			// Switch to the main frame
			snW.switchToFrame("Frame_Main");

			if(!snW.rightClickByLinkText("IX_NAME_Link")){
				status = "FAIL";
			}
							//Reporter.reportStep("Error While Creating CI's", "FAILURE");

			snW.clickByXpath("IX_ImportXML_Xpath");
     		
			snW.Wait(3000);
			
			driver.setFileDetector(new LocalFileDetector());
			
			//snW.getDriver().findElement(By.id("attachFile")).sendKeys("./Data/"+"Sparc_CIs.xml");
			snW.getDriver().findElement(By.id("attachFile")).sendKeys("D:/ServiceNow/Sparc_CIs.xml");
			snW.Wait(3000);
			
			Actions action = new Actions(snW.getDriver());
			action.sendKeys(Keys.ENTER).perform();
			System.out.println("File attached");

			if(!snW.clickByXpath("IX_UploadButton_Xpath")){
				status = "FAIL";
			}
				//Reporter.reportStep("Error While Creating CI's", "SUCCESS");
					
			
			snW.Wait(5000);

			//Reporter.reportStep("CI's are Created successfully", "SUCCESS");
			
			
			snW.getDriver().navigate().refresh();

			snW.Wait(5000);
			
			//******************//
			//Upload CI Relationships
			//******************//
			
			
			if(!snW.selectMenu("CMDB_Menu", "SystemApplications_Menu")){
				status = "FAIL";
			}
				
				//Reporter.reportStep("Error While Creating CI Relationsips", "FAILURE");

			//Wait for the Alerts to load
			snW.Wait(3000);
			// Switch to the main frame
			snW.switchToFrame("Frame_Main");

			if(!snW.rightClickByLinkText("IX_NAME_Link")){
				status = "FAIL";
			}
				//Reporter.reportStep("Error While Creating CI Relationsips", "FAILURE");

			snW.clickByXpath("IX_ImportXML_Xpath");

			driver.setFileDetector(new LocalFileDetector());
			//snW.getDriver().findElement(By.id("attachFile")).sendKeys("./Data/"+"Sparc_CIs_Relationship.xml");
			snW.getDriver().findElement(By.id("attachFile")).sendKeys("D:/ServiceNow/Sparc_CIs_Relationship.xml");

			Actions action1 = new Actions(snW.getDriver());
			action1.sendKeys(Keys.ENTER).perform();
			System.out.println("File attached");

			if(!snW.clickByXpath("IX_UploadButton_Xpath")){
				status = "FAIL";
			}
				//	Reporter.reportStep("Error While Creating CI Relationsips", "FAILURE");

			//Reporter.reportStep("CI Relationships are Created Successfully", "FAILURE");

			
			
			snW.Wait(5000);

			// go out of the frame
			snW.switchToDefault();

			// Log out
			if(!snW.clickByXpath("Logout_Xpath"))
				Reporter.reportStep("The logout Failed", "FAILURE");


			status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}

//	@DataProvider(name = "LC_CreatePreRequisite")
//	public Object[][] fetchData() throws IOException {
//		Object[][] arrayObject = DataInputProvider.getSheet("LC_CreatePreRequisite");
//		return arrayObject;
	}
