package com.punchit.scripts.gileadfss;

import java.io.IOException;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.FSSTaskPage;
import pages.ListPage;
import pages.LoginSparcLCPage;
import pages.MenuPage;
import pages.MyItemsPage;
import testng.SuiteMethods;
import utils.DataInputProvider;
import utils.Reporter;
import wrapper.ServiceNowWrappers;

public class GLFSS_APO_TC extends SuiteMethods{
	
	@Test(dataProvider = "GLFSS_APO_TC",groups="GLFSS")
	public void createNewTicket(String regUser, String regPwd, String affectedUser, String category, String type,
								String item, String shortDescription, String num, String EndUser, String EregPwd, String comments,
								String comments1, String workNotes, String priority, String columnvalue1, String state,
								String columnvalue2, String assGroup, String columnvalue3, String manregUser, String mregPwd,
								String workNotes1, String assGrop1, String state1, String coments1, String closureCode) 
								
										
										
										throws COSVisitorException, IOException, InterruptedException {

		try {
		
			snW.launchApp(browserName, true);
		
			MenuPage home = 
						new LoginSparcLCPage()
						.loginAs(regUser, regPwd);

			FSSTaskPage open = 
					home.clickFSSTaskandNewButton();
			String fssTask = 
					home.getFSSTaskNumber();
					open.enterAffectedUser(affectedUser)
						.enterCategoryTypeItem(category, type, item)
						.enterShortDescription(shortDescription)
						.enterInvoiceNumber(num)
						.clickSubmit()
						.clickCreatedFSSTask(fssTask);
					home.clickSparcLCPageLogout();
					
			FSSTaskPage fss=new LoginSparcLCPage()
						.loginAsSparcportal(EndUser, EregPwd)
						.clickMyItems()
						.clickMyRequestTab()
						.clickFSSTaskForMyRequest(fssTask);
		
					 fss.navigateToLoin()
						.clickSparcLCPageLogout();
					
					
					new LoginSparcLCPage()
						.loginAs(regUser, regPwd);	
					home.clickOpenRecords()
						.clickCreatedFSSTask(fssTask)
						.clickNotesTab()
						.enterCustomerComments(comments)
						.clickSaveButton()
						.verifyLatestCustomerComments(comments);
					home.clickSparcLCPageLogout();
					
						new LoginSparcLCPage()
						.loginAsSparcportal(EndUser, EregPwd)
						.clickMyItems()
						.clickMyRequestTab()
						.clickFSSTaskForMyRequest(fssTask)
						.clickActivityLogTab()
						.verifyCommentsInActivityLogTab(comments, regUser)
						.clickNotesTab()
						.enterCustomerComments(comments1)
						.clickSaveButton();
						
					fss.navigateToLoin()
					   .clickSparcLCPageLogout();	
	
						new LoginSparcLCPage()
						.loginAs(regUser, regPwd);	
					home.clickOpenRecords()
						.clickCreatedFSSTask(fssTask)
						.clickActivityLogTab()
						.verifyCommentsInActivityLogTab(comments1, EndUser)
						.clickNotesTab()
						.clickCustomerWatchList()
						.enterCustomerWatchList(EndUser)
						.clickLock()
						.clickSaveButton()
						.verifyCustomerWatchList(EndUser);
					
					home.clickSparcLCPageLogout();	
						
						new LoginSparcLCPage()
						.loginAsSparcportal(EndUser, EregPwd)
						.clickMyItems()
						.clickWatchItemsTab()
						.clickFSSTaskForWatchedItems(fssTask);
						
					fss.navigateToLoin()
					   .clickSparcLCPageLogout();
						
					home.clickSparcLCPageLogout();	
					
						new LoginSparcLCPage()
						.loginAs(regUser, regPwd);	
					home.clickOpenRecords()
						.clickCreatedFSSTask(fssTask)
						.clickNotesTab()
						.enterWorkNotes(workNotes)
						.clickSaveButton()
						.verifyLatestWorkNotes(workNotes);
				
					home.clickSparcLCPageLogout();	
						
						new LoginSparcLCPage()
						.loginAs(EndUser, EregPwd)
						.clickMyItems()
						.clickViewMessages();
						
					fss.navigateToLoin()
					   .clickSparcLCPageLogout();
						
						
					home.clickSparcLCPageLogout();	
						
						new LoginSparcLCPage()
						.loginAs(regUser, regPwd);	
					home.clickOpenRecords()
						.clickCreatedFSSTask(fssTask)
						.upgradePriority(priority)
						.clickUpdateButton()
						.verifycolumnValue(columnvalue1, priority);
				
					home.clickSparcLCPageLogout();
					
						new LoginSparcLCPage()
						.loginAs(regUser, regPwd);	
					home.clickOpenRecords()
						.clickCreatedFSSTask(fssTask)
						.updateState(state)
						.clickUpdateButton()
						.verifycolumnValue(columnvalue2, state);
				
					home.clickSparcLCPageLogout();

			
						new LoginSparcLCPage()
						.loginAs(regUser, regPwd);	
					home.clickOpenRecords()
						.clickCreatedFSSTask(fssTask)
						.updateAssignmentGroup(assGroup)
						.clickUpdateButton()
						.verifycolumnValue(columnvalue3, assGroup);
				
					home.clickSparcLCPageLogout();

					
						new LoginSparcLCPage()
						.loginAs(manregUser, mregPwd);	
					home.clickMyGroupWorksInFSS()
						.clickCreatedFSSTask(fssTask);
						
					home.clickSparcLCPageLogout();
				
						new LoginSparcLCPage()
						.loginAs(manregUser, mregPwd);	
					home.clickMyGroupWorksInFSS()
						.clickCreatedFSSTask(fssTask)
						.clickNotesTab()
						.enterWorkNotes(workNotes1)
						.updateAssignmentGroup(assGrop1)
						.clickUpdateButton()
						.validateFSSTaskNumberisNotVisible(fssTask, manregUser);
					
					home.clickSparcLCPageLogout();
					
						
						new LoginSparcLCPage()
						.loginAs(regUser, regPwd);	
					home.clickOpenRecords()
						.clickCreatedFSSTask(fssTask)
						.updateState(state1)
						.verifyAllMandatoryFields()
						.clickNotesTab()
						.enterCustomerComments(coments1)
						.clickClosureTab()
						.selectClosureCode(closureCode)
						.clickUpdateButton()
						.validateFSSTaskNumberisNotVisible(fssTask, regUser);
					
					home.clickSparcLCPageLogout();
					
			status = "PASS";
		}
		finally {
			// close the browser
			//snW.quitBrowser();
		}
	}
	@DataProvider(name = "GLFSS_APO_TC")
	public Object[][] fetchData() throws IOException {
		Object[][] arrayObject = DataInputProvider.getSheet("GLFSS_APO_TC");
		return arrayObject;
	}
}