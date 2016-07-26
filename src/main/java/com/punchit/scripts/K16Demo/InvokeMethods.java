package com.punchit.scripts.K16Demo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InvokeMethods {


	/**
	 * This method will Invoke the Methods and Exceute them  
	 * 
	 * @param Object_identifier -  Selenium Identifier type for the field 
	 * @param Object_identifierValue - Selenium Identifier value for the field
	 * @param data - The identifier filed value
	 * @param Field_type - Invoke Method name
	 * @param Field_Name - Field Name to be displayed in Reoprter. 
	 * @author Aman
	 */ 

	public static void runReflectionMethod(String Object_identifier,String Object_identifierValue,String data,String Field_type,String Field_Name) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

		Field_Types obj = new Field_Types();
		Method[] method = obj.getClass().getMethods();
		try {
			for (int i = 0; i < method.length; i++)
			{
				if (method[i].getName().equals(Field_type)) {
					System.out.println("Executing the Action Keyword" + method[i]);
					method[i].invoke(Field_type,Object_identifier, Object_identifierValue,data,Field_Name);
					break;
				}
			}
		} catch (IllegalArgumentException e) {
			System.err
			.format("Method invoked with wrong number of arguments%n");
			System.out.println(e);
		} catch (IllegalAccessException e) {
			System.err
			.format("Can not access a member of class with modifiers private%n");
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	/**
	 * function calls the reflection methods
	 *  Accepts the hash map and key to hash map 
	 *  data.get(2) : identifier 
	 *  data.get(3) : identifier value
	 *  data.get(1) : Field Data
	 *  data.get(4) : Field Type
	 *  key         : field name
	 */
	public static void CreateField(String key,HashMap<String,List<String>> values) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException{
		
		try{
			
			List<String> data= values.get(key); 
			runReflectionMethod(data.get(1),data.get(2),data.get(0),data.get(3),key);
			
		}
		catch(Exception e){
			System.out.println(e);
		}
		
	}
	

	public static void call(String[][] values) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

		
		for(int i=0; i <values.length; i++){
				runReflectionMethod(values[i][0], values[i][1], values[i][2], 
						values[i][3], values[i][4]);

		}
	}	
	

}
