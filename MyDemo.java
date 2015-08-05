package test;

import java.io.IOException;
import java.util.Iterator;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MyDemo {
	public static void main(String[] args) {
		String filePath = "E:\\SAP related\\SAP UI5\\Exercise from Max\\responseDetailsOfSalesorderItem.json";
		Util util = new Util();
		
		String JsonContext = util.ReadFile(filePath);
		
		JSONObject jsonObject = JSONObject.fromObject(JsonContext);
		
        /**
         *  Loop all the sub nodes
         **/
		Iterator<?> itKeys = jsonObject.keys();
		
		int iCount = 0;
		
		while(itKeys.hasNext()){
			String nodeName = itKeys.next().toString();
			
			JSONArray jsonArray = jsonObject.getJSONArray(nodeName);
			
			int size = jsonArray.size();
			
			for(int  i = 0; i < size; i++){
				JSONObject subJsonObject = jsonArray.getJSONObject(i);
				
		        /**
		         *  count how many times SD_DOC=0000000151 
		         **/
				if(subJsonObject.get("SD_DOC") != null){
					if(subJsonObject.get("SD_DOC").equals("0000000151")){
						iCount++;
					}					
				}

				
		        /**
		         *  Retrieve SD_DOC=0000000151, PARTN_ROLE=AG in sub node ORDER_PARTNERS_OUT  
		         **/
				if(nodeName.equals("ORDER_PARTNERS_OUT") )
				{
					if(subJsonObject.get("PARTN_ROLE").equals("AG")){
						//Print the sub node info
						util.printNode(subJsonObject);
						
				        /**
				         *  set LEVEL_NR to 100 when SD_DOC=0000000151, PARTN_ROLE=AG in sub node ORDER_PARTNERS_OUT  
				         **/
						subJsonObject.put("LEVEL_NR", "100");
					}
				} // if (nodeName.equals("ORDER_PARTNERS_OUT"))

			} // for(i)
			
		} // while(itKeys.hasNext())
		
		// Print the count of SD_DOC = "0000000151"
		System.out.println("Count of SD_DOC = \"0000000151\": " + iCount);
		
        /**
         *  Save to another json file  
         **/
		try {
			filePath = "E:\\SAP related\\SAP UI5\\Exercise from Max\\responseDetailsOfSalesorderItem_new.json";
			util.writeFile(filePath, jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

