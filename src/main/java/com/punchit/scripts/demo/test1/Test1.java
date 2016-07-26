package com.punchit.scripts.demo.test1;

import java.io.IOException;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testng.SuiteMethods;
import utils.DataInputProvider;
import wrapper.GenericWrappers;
import wrapper.ServiceNowWrappers;

public class Test1{
  
	@BeforeMethod
    public void test() {
  
		try {
			GenericWrappers hm=new ServiceNowWrappers();
			hm.launchApp("chrome", false);
			
			
		} finally {
			
			
		}
  
	}

}
