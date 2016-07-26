package testng;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.http.HttpException;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import ServiceNow_Integration.DataBase_Interaction;
import utils.QueryDB;
import utils.QueryDB_1;
import utils.Reporter;
import utils.Reporter1;
import wrapper.ServiceNowWrappers;

public class SuiteMethods_1 {
	protected String browserName = "firefox";
	protected String entityId;
	protected String status = "FAIL";
	protected ITestContext context;
	
	// Create Instance
	protected ServiceNowWrappers snW;

	@BeforeClass
	public void getBrowserName(ITestContext context) {
		this.context = context;
        entityId = context.getCurrentXmlTest().getSuite().getName();
//		entityId = "ad69d2cac83c1640b74a306bb2c446f7";
		//821a235cc88dd640b74a306bb2c4461d
	    //entityId = "f3118dc54f4a92003fbdad4f0310c795";
		snW = new ServiceNowWrappers(entityId);

		//browserName = QueryDB_1.getBrowserName(entityId);
		Reporter1.setResult(ServiceNowWrappers.testcaseName);
		Reporter1.reportStep("The testcase execution started on :"+snW.getCurrentTime() +".","INFO", false);
			
	}
	
	@AfterClass(alwaysRun = true)
	public void updateResult(ITestContext context) throws IOException, AddressException, MessagingException, InterruptedException {
		String errorMsg = Reporter1.getErrorDescription();
		long executionTime = (System.currentTimeMillis() - context.getStartDate().getTime())/1000;
		if(status.equals("FAIL") && errorMsg.trim().equals(""))
			status = "ERROR";
				
		System.out.println(context.getCurrentXmlTest().getSuite().getName());
		System.out.println(status);
		System.out.println(errorMsg);
		System.out.println(executionTime);
		
		String statusMsg = status;
		if(status.equals("FAIL"))
			statusMsg = "FAIL and the error message is :"+errorMsg;
			
		Reporter1.reportStep("The testcase execution completed on :"+snW.getCurrentTime()+". "
				+ "\n The time taken to execute this testcase is :"+executionTime+" seconds. "
						+ "\n The status is: "+statusMsg,"INFO", false);
		
		QueryDB_1.updateResult(entityId, status,errorMsg, executionTime);
		
		// close the browser
		snW.quitBrowser();
	}


}
