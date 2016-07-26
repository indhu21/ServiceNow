package utils_DataDeletion;

public class PrepareDeleteQuery {
	
	// *************************************** //
    //	To verify if the input sys id is of 32 bit and 
	//  does not contains the characters : ";" , "!" , "**"
	// **********************************************//

	public static boolean ValidateInput(String[] input)
	{
		boolean flag=true;
		boolean char_flag=true;
		for(int i=0;i<input.length;i++)
		{
			int length= input[i].length(); 
			if(length != 32){
				flag=false;
				break;
			}
			if(flag==true){
				char[] chars = input[i].toCharArray();

				for (char c : chars) {
					if(!(Character.isLetter(c)||Character.isDigit(c))){
						char_flag= false;
						flag=false;
						break;
					}
				}

			}
			if(char_flag==false){
				break;  
			}
		}  	
		return flag; 
	}

	// *************************************** //
    //	Prepares a delete query for multiple inputs
	// **********************************************//
	
	public static String  PrepareMultiDeleteQuery(String[] inputs)
	{
		String query="{\"sysparm_query\":\"sys_idIN";
		for(int i=0;i<inputs.length;i++)
		{   
			query=query+inputs[i];
			if(i==inputs.length-1){
				query=query+"\"}";	
			}
			else{
				query=query+",";
			}
		}

		return query;
	}
	
	// *************************************** //
    //	Get Delete Parameters
	// **********************************************//
	public static String[] GetDeleteObjects(String TestCase,String Type)
	{
		int count;
		
		count=QueryDB_DeleteData.getcountofRecordsinMappingTable(TestCase,Type);
		String[] Objects=QueryDB_DeleteData.GetDefinitionIDfromMappingTable(TestCase,Type,count); // Get Definition ID
		String[] ObjectValues=QueryDB_DeleteData.GetValuesfromDefinitionTable(Objects);
		
		return ObjectValues;
		
	}
	
	public static void main(String args[]){
		
		String[] values=GetDeleteObjects("LC_CreatePreRequisite","CI Task");
		//if(ValidateInput(values)){
			
			String query=PrepareMultiDeleteQuery(values);
			System.out.println(query);
		//}
		
	}

}
