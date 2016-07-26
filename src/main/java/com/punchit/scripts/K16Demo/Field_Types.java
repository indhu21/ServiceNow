package com.punchit.scripts.K16Demo;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import utils.Reporter;
import utils.Reporter_ServiceNow;
import wrapper.ServiceNowWrappers;

public class Field_Types extends ServiceNowWrappers {
	
	public Field_Types() {
		// TODO Auto-generated method stub
		 
		driver=super.getDriver();
		//switchToMain();
	}
	
		
	public static By Locator(String locatorTpye, String Locator_value) {
		By by;
		switch (locatorTpye) {
		case "id":
			by = By.id(Locator_value);
			break;
		case "name":
			by = By.name(Locator_value);
			break;
		case "xpath":
			by = By.xpath(Locator_value);
			break;
		case "css":
			by = By.cssSelector(Locator_value);
			break;
		case "linkText":
			by = By.linkText(Locator_value);
			break;
		case "partialLinkText":
			by = By.partialLinkText(Locator_value);
			break;
		default:
			by = null;
			break;
		}
		return by;
		
		}

	
	/**
	 * This method will enter the value to the Reference Field 
	 * 
	 * @param xpathVal -  by identifier for the field
	 * @param data - The data to be sent to the webelement
	 * @param identifierValue - The identifier value 
	 * @author Aman
	 */ 

	public static void Reference(String identifier,String identifierValue,String data,String Field_Name)
	{
		boolean bReturn = false;

		try{
			By locatorValue;
			locatorValue = Locator(identifier, identifierValue);
			WebElement ele = driver.findElement(locatorValue);
			ele.clear();
			Actions builder = new Actions(driver);   
			builder.sendKeys(ele, data)
			.pause(15000)
			.sendKeys(Keys.DOWN)
			.sendKeys(Keys.ENTER)
			.build().perform();
			bReturn = true; 
			if(bReturn)
				Reporter_ServiceNow.reportStep("The Field " + Field_Name + " is entered successfully", "SUCCESS");
			else 
				Reporter_ServiceNow.reportStep("The Field " + Field_Name + " could not be entered", "FAILURE");


		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (WebDriverException e1) {

		}

	}
	
	/**
	 * This method will enter the value to a Text Field  
	 * 
	 * @param xpathVal -  by identifier for the field
	 * @param data - The data to be sent to the webelement
	 * @param identifierValue - The identifier value 
	 * @author Aman
	 */ 
	
	public static void Text(String identifier,String identifierValue,String data,String Field_Name){
		boolean bReturn = false;
		try{
			By locatorValue;
			locatorValue = Locator(identifier, identifierValue);
			driver.findElement(locatorValue).clear();
			driver.findElement(locatorValue).sendKeys(data, Keys.ENTER);
			bReturn = true;
			
			if(bReturn)
				Reporter_ServiceNow.reportStep("The Field " + Field_Name + " is entered successfully", "SUCCESS");
			else 
				Reporter_ServiceNow.reportStep("The Field " + Field_Name + " could not be entered", "FAILURE");

		} catch (NoSuchElementException e) {
		} catch (WebDriverException e1) {
		}
		
	}
	
	
	
	public static void Select(String identifier,String identifierValue,String data,String Field_Name) {
		boolean bReturn = false;
		try{
			By locatorValue;
			locatorValue = Locator(identifier, identifierValue);
			new Select(driver.findElement(locatorValue)).selectByVisibleText(data);
			bReturn = true;
			if(bReturn)
				Reporter_ServiceNow.reportStep("The Field " + Field_Name + " is Selected successfully", "SUCCESS");
			else 
				Reporter_ServiceNow.reportStep("The Field " + Field_Name + " could not be Selected", "FAILURE");
		} catch (NoSuchElementException e) {
		} catch (WebDriverException e1) {
		}
		
	}
	
	
}
