package Pages_ServiceNow;

import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class CreateIncidentPage extends ServiceNowWrappers {

	private final RemoteWebDriver driver;

	public CreateIncidentPage(RemoteWebDriver driver) {
		this.driver = driver;
		
		// Switch to the main frame
		switchToMainFrame();

		// Check that we're on the right page.
		if (!getTextByXpath("SPARCPORTAL_CN_RiseIncident_Xpath").contains("Raise Incident")) {
			Reporter_ServiceNow.reportStep("This is not the create incident Page", "FAILURE");
		}

	}

	public CreateIncidentPage switchToMainFrame() {

		// Switch to the main frame
		switchToFrame("Frame_Main");
		return this;
	}

	public CreateIncidentPage selectNoOfPeopleImpacted(String number) {

		if (selectByVisibleTextByXpath("SPARCPORTAL_CN_PeopleIMpact_Xpath", number)) {
			Reporter_ServiceNow.reportStep(
					"The value: " + number + " is selected successfully in number of people impacted field",
					"SUCCESS");
		} else
			Reporter_ServiceNow.reportStep("The value: " + number + " could not selected in number of people impacted field",
					"FAILURE");
		return this;
	}

	public CreateIncidentPage selectAreYouAbleToWork(String ableToWork) {

		if (selectByVisibleTextByXpath("SPARCPORTAL_CN_AbleToWork_Xpath", ableToWork))
			Reporter_ServiceNow.reportStep(
					"The value: " + ableToWork + " is selected in Are you able to work field successfully",
					"SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The value: " + ableToWork + "  could not be selected in Are you able to work field",
					"FAILURE");

		return this;

	}

	public CreateIncidentPage enterValueInIssueWith(String issue) {

		if (enterAndChoose("SPARCPORTAL_CN_IssueWith_Xpath", issue))
			Reporter_ServiceNow.reportStep("The value: " + issue + " is entered in I Have An Issue With field successfully",
					"SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The value: " + issue + " could not be entered I Have An Issue With field",
					"FAILURE");
		return this;
	}

	public CreateIncidentPage enterValueInShortDesc(String desc) {

		if (enterByXpath("SPARCPORTAL_CN_ShortDesc_Xpath", desc))
			Reporter_ServiceNow.reportStep("The value: " + desc + " is entered  in short description field successfully",
					"SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The value: " + desc + " could not be entered in short description field", "FAILURE");

		return this;

	}

	public IncidentPage clickSubmitNewIncident() {

		if (clickById("submit_button"))
			Reporter_ServiceNow.reportStep("The Submit button is clicked successfully and Incident Ticket is Created successfully",
					"SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Submit button could not been clicked", "FAILURE");

		return new IncidentPage(driver);

	}

	public IncidentPage RaiseIncident(String number, String issue, String ableToWork, String desc) {


		if (!selectByVisibleTextByXpath("SPARCPORTAL_CN_PeopleIMpact_Xpath", number))
			Reporter_ServiceNow.reportStep("The value: " + number + " could not selected in number of people impacted field",
					"FAILURE");
		
		if (!selectByVisibleTextByXpath("SPARCPORTAL_CN_AbleToWork_Xpath", ableToWork))
			Reporter_ServiceNow.reportStep("The value: " + ableToWork + "  could not be selected in Are you able to work field",
					"FAILURE");
		
		if (!enterAndChoose("SPARCPORTAL_CN_IssueWith_Xpath", issue))
			Reporter_ServiceNow.reportStep("The value: " + issue + " could not be entered I Have An Issue With field",
					"FAILURE");
		
		if (!enterByXpath("SPARCPORTAL_CN_ShortDesc_Xpath", desc))
			Reporter_ServiceNow.reportStep("The value: " + desc + " could not be entered in short description field", "FAILURE");
		if (clickById("submit_button"))
			Reporter_ServiceNow.reportStep("The "+number+" , "+ableToWork+", "+issue+", "+desc+" are entered and Incident Ticket is Created successfully",
					"SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Submit button could not been clicked", "FAILURE");
		return new IncidentPage(driver);
//		
//		return selectNoOfPeopleImpacted(number).enterValueInIssueWith(issue).selectAreYouAbleToWork(ableToWork)
//				.enterValueInShortDesc(desc).clickSubmitNewIncident();
	}
/*	public CreateIncidentPage uploadFile(String fileName) {
		boolean bFail = true;

		if (clickByXpath("SPARCPORTAL_AddAttachment_Xpath")) {
			try {
				String filePath = System.getProperty("user.dir") + "/data/" + fileName + ".xlsx";
				enterById("ChooseFiles_Id", filePath);

				clickByXpath("AttachFile_Xpath");
				Wait(10000);

				isExistByXpath("IsAttachmentUploaded");
				clickByXpath("CREATEINC_CloseUpload_Xpath");

				bFail = false;
				scrollToElementByXpath("CREATEINC_ManageAttachments_Xpath");
				Wait(1000);
				Reporter_ServiceNow.reportStep("The Attachment is Uploaded successfully ", "SUCCESS");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (bFail)
			Reporter_ServiceNow.reportStep("The Attachment could not be Uploaded  ", "FAILURE");

		return this;
	}
*/
	public CreateIncidentPage verifyAffectedUserValue(String attrValue) {

		if (verifyAttributeTextByXpath("CREATEINC_AffectedUserValue_Xpath", "data-original-title",
				attrValue.toUpperCase()))
			Reporter_ServiceNow.reportStep("The User: " + attrValue + " is automatically filled in Affected User as expected ",
					"SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The User: " + attrValue
					+ " is differ from the automatically filled in Affected User unexpectedly ", "FAILURE");

		return this;

	}

	public CreateIncidentPage enterAffectedUser(String affecteduser) {

		if (enterAndChoose("CREATEINC_AffectedUserValue_Xpath", affecteduser))
			Reporter_ServiceNow.reportStep("The User: " + affecteduser + " is entered in Affected user field  successfully",
					"SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The User: " + affecteduser + " could not be entered in Affected user field",
					"FAILURE");

		return this;

	}

	public CreateIncidentPage verifyAffectedUserColour(String colour) {

		if (verifyAttributeTextByXpath("CREATEINC_AffectedUserValue_Xpath", "style", "color: " + colour + ";"))
			Reporter_ServiceNow.reportStep("The Affected User colour is " + colour + " as expected ", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Affected User colour is not " + colour+", hence failed.", "FAILURE");

		return this;

	}

	public CreateIncidentPage hoverViewIcon(String userName) {
		  {
		   if (mouseOverByXpath("CREATEINC_HOVER_View_Xpath")) {
//		    Wait(1000);
		    Reporter_ServiceNow.reportStep("The Mouse over is successfully achieved on View Icon ", "SUCCESS");
		   } else
		    Reporter_ServiceNow.reportStep("The Mouse over action was failed", "FAILURE");
		  }
		  System.out.println(getAttributeByXpath("CREATEINC_HOVER_View_User_Xpath", "value"));
		  if (getAttributeByXpath("CREATEINC_HOVER_View_User_Xpath", "value").contains(userName))
		   Reporter_ServiceNow.reportStep("The User Information of " + userName + " displayed successfully", "SUCCESS");
		  else
		   Reporter_ServiceNow.reportStep("The User Information of " + userName + " is not on mouse Over", "FAILURE");

		  return this;
		 }

	public CreateIncidentPage verifyValidAndInvalidIssue(String valid, String invalid) {

		if (!enterAndChoose("SPARCPORTAL_CN_IssueWith_Xpath", invalid))
			Reporter_ServiceNow.reportStep("The value: " + invalid
					+ " entered in 'I Have An Issue With' field is an Invalid Issue as expected ", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep(
					"The value: " + invalid + " entered 'I Have An Issue With' field is not an Invalid Issue",
					"FAILURE");

		if (enterAndChoose("SPARCPORTAL_CN_IssueWith_Xpath", valid))
			Reporter_ServiceNow.reportStep(
					"The value: " + valid + " entered in 'I Have An Issue With' field is an Valid Issue as expected ",
					"SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The value: " + valid + " entered 'I Have An Issue With' field is not an Valid Issue",
					"FAILURE");

		return this;

	}

	public CreateIncidentPage verifyNumberOfPeopleImpactedOptions() {

		String[] allOptions = { "-- None --", "Me", "My Group", "My Department", "My Location"};
		if (getTextByXpath("SPARCPORTAL_CN_PeopleIMpact_Xpath").contains("None"))
			Reporter_ServiceNow.reportStep("The Default value of Number of people impacted field is None as expected ", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("Unexpectedly default value of Number of people impacted field is not None  ",
					"FAILURE");

		if (verifyListContents("SPARCPORTAL_CN_PeopleIMpact_Xpath", allOptions)) {
			clickByXpath("SPARCPORTAL_CN_PeopleIMpact_Xpath");

			Reporter_ServiceNow.reportStep(
					"The Options: None, Me, My Group, My Department, My Location are matched with actual options as expected ",
					"SUCCESS");
		}

		else
			Reporter_ServiceNow.reportStep(
					"The Options: None, Me, My Group, My Department, My Location are could not matched with actual options ",
					"FAILURE");

		return this;

	}

	public CreateIncidentPage verifyAbleToWorkOptions() {

		String[] allOptions = { "-- None --", "Yes", "No" };

		if (getTextByXpath("SPARCPORTAL_CN_AbleToWork_Xpath").contains("None"))
			Reporter_ServiceNow.reportStep("The Default value of 'Are you able to work field' field is None as expected ",
					"SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Default value of 'Are you able to work field' field is None could not be matched",
					"FAILURE");

		if (verifyListContents("SPARCPORTAL_CN_AbleToWork_Xpath", allOptions)) {
			Wait(1000);
			clickByXpath("SPARCPORTAL_CN_AbleToWork_Xpath");
			Reporter_ServiceNow.reportStep("The Options: None, Yes, No are matched with actual options as expected ", "SUCCESS");
		} else
			Reporter_ServiceNow.reportStep("The Options: None, Yes, No are could not matched with actual options ", "FAILURE");

		return this;

	}

	public CreateIncidentPage enterValueInMoreInfo(String desc) {

		if (enterByXpath("SPARCPORTAL_CN_MoreInfo_Xpath", desc))
			Reporter_ServiceNow.reportStep(
					"The value: " + desc + " is entered  in More Info / Detail of issue field successfully",
					"SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The value: " + desc + " could not be entered in More Info / Detail of issue field",
					"FAILURE");

		return this;

	}

	public CreateIncidentPage isVipFlagExistsNearAffectedUser() {

		if (isExistByXpath("CREATEINC_VIP_Flag_Xpath"))
			Reporter_ServiceNow.reportStep("The VIP Flag is displayed next to Affected User as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The VIP Flag is not displayed next to Affected User", "FAILURE");

		return this;

	}

	public CreateIncidentPage RemoveAttachment() {
		boolean bFail = true;
		if (clickByXpath("SPARCPORTAL_AddAttachment_Xpath")) {
			try {
				clickByXpath("CREATEINC_SelectAttach_Xpath");
				clickByXpath("CREATEINC_RemoveAttachment_Xpath");
				Wait(5000);
				clickByXpath("CREATEINC_CloseUpload_Xpath");
				Wait(1000);
				bFail = false;
				Reporter_ServiceNow.reportStep("The Attachment is removed successfully ", "SUCCESS");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (bFail)
			Reporter_ServiceNow.reportStep("The Attachment could not be removed  ", "FAILURE");

		return this;
	}

	public CreateIncidentPage verifyUploadErrorMessage(String fileName, String errorMessage) {

		if (clickByXpath("SPARCPORTAL_AddAttachment_Xpath")) {
			try {
				String[] folder = getAbsolutePath().substring(1).split("/");
				String folderName = folder[0];
				for (int i = 1; i < folder.length - 2; i++) {
					folderName = folderName + "/" + folder[i];
				}

				driver.setFileDetector(new LocalFileDetector());
				enterById("ChooseFiles_Id", folderName + "/data/" + fileName + ".xlsx");

				// clickByXpath("AttachFile_Xpath");

				if (driver.switchTo().alert().getText().contains(errorMessage)) {
					alertAccept();
					Wait(2000);
					Reporter_ServiceNow.reportStep("The error message :" + errorMessage + " appeared as expected", "SUCCESS");
				} else
					Reporter_ServiceNow.reportStep("The error message :" + errorMessage + " did not appear", "FAILURE");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return this;

	}

	public CreateIncidentPage verifyAttachmentButtonexists() {

		if (isExistByXpath("SPARCPORTAL_AddAttachment_Xpath"))
			Reporter_ServiceNow.reportStep("The Attachment button is available as expected", "SUCCESS");
		else
			Reporter_ServiceNow.reportStep("The Attachment button could not be found", "FAILURE");
		return this;

	}
	public CreateIncidentPage uploadFile(String fileName) {
		  boolean bFail = true;

		  if (clickByXpath("SPARCPORTAL_AddAttachment_Xpath")) {
		   try {
		        
		     String[] folder = getAbsolutePath().substring(1).split("/");
		     String folderName = folder[0];
		     for (int i = 1; i < folder.length - 2; i++) {
		      folderName = folderName + "/" + folder[i];
		     }
		     driver.setFileDetector(new LocalFileDetector());
		     enterById("ChooseFiles_Id", ("./data/"+fileName+".xlsx"));
		    
//		    String filePath = getAbsolutePath()+ "/data/" +fileName+".xlsx";
//		    enterById("ChooseFiles_Id", filePath);

		    clickByXpath("AttachFile_Xpath");
		    Wait(10000);

		    isExistByXpath("IsAttachmentUploaded");
		    clickByXpath("CREATEINC_CloseUpload_Xpath");

		    bFail = false;
		    scrollToElementByXpath("CREATEINC_ManageAttachments_Xpath");
		    Wait(1000);
		    Reporter_ServiceNow.reportStep("The Attachment is Uploaded successfully ", "SUCCESS");

		   } catch (Exception e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   }
		  }
		  if (bFail)
		   Reporter_ServiceNow.reportStep("The Attachment could not be Uploaded  ", "FAILURE");

		  return this;
		 }



}
