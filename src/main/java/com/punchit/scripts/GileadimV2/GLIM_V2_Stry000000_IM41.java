package com.punchit.scripts.GileadimV2;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.ListPage;
import pages.LoginPage;
import pages.MenuPage;
import testng.SuiteMethods;
import utils.DataInputProvider;

public class GLIM_V2_Stry000000_IM41 extends SuiteMethods {


	@Test(dataProvider = "GLIM_V2_Stry000000_IM41",groups="IncidentManagement")
	public void createIncident(String regUser, String regPwd, String UserName){


		try {

			launchApp(browserName, true);

			MenuPage home = 
					new LoginPage()
					.loginAs(regUser, regPwd);
			String[] filter={"SPARC Active = true", "Assigned To = "+regUser};

					home.expandIncidentMenu()
						.clickAssignedToMe()
						.verifyFilterText(filter)
						.verifyNonResolvedforthirdFilter(regUser.toUpperCase());

					home.clickAssignedToMeWithoutReport()
						.isNewButtonExists()
						.clickFunnelWithoutReport()
						.clickANDCondition()
						.addThirdFilter("State", "is not", "Open")
						.clickRun()
						.rightClickAndAssignToMe()
						.verifyAssignedToError(regUser.toUpperCase())
						.isFieldEditable()	
						.addPersonalizedListColumn()
						.isDeleteIncidentUsingActionsOnSelectedRows()
						.isFilterEditable();
					home.clickAssignedToMeWithoutReport()
						.verifyFilterTextAterReset(filter);
			
						switchToMenu();
			
			String[] filter1={"Work in Progress",UserName};
		
					home.expandIncidentMenu()
						.clickWIP()
						.verifyFilterText(filter1)
						.verifyNonResolvedforthirdFilter(regUser.toUpperCase());
					home.clickWIPWithoutReport()
						.isNewButtonExists()
						.rightClickAndAssignToMe()
						.verifyAssignedToError(UserName)
						.isFieldEditable()
						.addPersonalizedListColumn()
						.isDeleteIncidentUsingActionsOnSelectedRows()
						.isFilterEditable();
					home.clickWIPWithoutReport()
						.verifyFilterTextAterReset(filter1);
					switchToMenu();
					String[] filter3={"SPARC Active = true"};
					home.expandIncidentMenu()
						.clickOpen()
						.verifyFilterText(filter3)
						.verifyNonResolvedforsecondFilter(regUser.toUpperCase());
				ListPage list = 
					home.clickOpenWithoutReport()
						.clickFunnelWithoutReport()
						.clickANDCondition()
						.addSecondFilter("State", "is", "Open")
						.clickANDCondition()
						.addThirdFilterbyEnterAndChooseWithOutReport("Assignment Group", "is", "TESM_INC_GROUP")
						.clickRun()
						.isNewButtonExists();
				String incident = 
					list.getFirstlink();
					list.rightClickAndAssignToMe();
					home.searchIncidentFromMenu(incident)
						.isStateAssigned();
					home.clickOpenWithoutReport()	
						.isFieldEditable()
						.addPersonalizedListColumn()
						.isDeleteIncidentUsingActionsOnSelectedRows()
						.isFilterEditable();
					home.clickOpenWithoutReport()
						.verifyFilterTextAterReset(filter3);
						switchToMenu();

			String[] filter4={"Assignment Group = (TESM_INC_GROUP, TESM_INC_Reporting)", "Assigned To is empty"};
					home.expandIncidentMenu()
						.clickOpenUnAssigned()
						.verifyNonResolvedforthirdFilter(regUser.toUpperCase());
					home.clickOpenUnAssignedWithoutReport()
						.clickFunnelWithoutReport()
						.clickANDCondition()
						.addFourthFilter("State", "is", "Open")
						.clickRun()
						.verifyFilterText(filter4)
						.isNewButtonExists()
						.isFieldEditable();
					incident = 
					list.getFirstlink();
					list.rightClickAndAssignToMe();
					home.searchIncidentFromMenu(incident)
						.isStateAssigned();
					home.clickOpenWithoutReport()	
						.verifyAssigned(regUser.toUpperCase())
						.addPersonalizedListColumn()
						.isFilterEditable();
					home.clickOpenUnAssignedWithoutReport()
						.verifyFilterTextAterReset(filter4)
						.isDeleteIncidentUsingActionsOnSelectedRows();



			status = "PASS";
		} finally {
			// close the browser
			snW.quitBrowser();
		}

	}


	@DataProvider(name = "GLIM_V2_Stry000000_IM41")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLIM_V2_Stry000000_IM41");
		return arrayObject;
	}
}
