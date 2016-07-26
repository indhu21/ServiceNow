package com.punchit.scripts.K16Demo;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.PutAction;
import utils.Reporter;
import utils_DataCreation.CreateAutoData;
import utils_DataCreation.readFrom_ConfigFile;
import utils_DataDeletion.Delete;
import utils_DataDeletion.PrepareDeleteQuery;
import wrapper.ServiceNowWrappers;

public class K16Demo_CMDB_CreateData extends SuiteMethods  {
	@Test
	public void createPreRequisite() throws FileNotFoundException, IOException, Exception{
    // Pre-requisities
try {
		
	//******************//
	//Create CI
	//******************//
	startTime = context.getStartDate().getTime(); // start time 
	
	int responsecode=CreateAutoData.CreateCI_Demo("K16Demo_CMDB_CreateData"); // create ci
	
	long executionTime = (System.currentTimeMillis() - startTime)/1000; // end time 
	
	if(responsecode!=200){
		
		Reporter.reportStep("CI could not be created .Web Servi Responce status is : " + responsecode +".","INFO", false);

		status="FAIL";
		
		PutAction.updatestatus(entityId, "FAIL" ,executionTime);
	}
	else
	{
		Reporter.reportStep("CI: " +"TESM DEMO CI"+ " is created successfully using Web Services . Web Services Responce status Received is " + responsecode +".","INFO", false);

		status="PASS";
		
		PutAction.updatestatus(entityId, "PASS" ,executionTime);
	}
	
	
	
} finally {
	
}

}


}
