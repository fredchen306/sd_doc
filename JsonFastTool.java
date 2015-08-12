package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;


public class JsonFastTool {
	private JSONObject jsonObject;
	
	public JsonFastTool(String JsonContext) throws JSONException {
		jsonObject = JSON.parseObject(JsonContext);
		
	}
	
	
	private JSONArray getArrayByName(String name) {  
 
	    JSONArray jsonArray = jsonObject.getJSONArray(name);  

		return jsonArray;  
	}  
	
	private List<JSONObject> getJSONObjByArray(JSONArray jsonArray, Map<String, String> filterMap) {
		List<JSONObject> jsonObjList = new ArrayList<JSONObject>();
		
        for(int indexArray = 0; indexArray < jsonArray.size(); indexArray++){
        	JSONObject subJsonObject = jsonArray.getJSONObject(indexArray);  
			
        	if(checkFilterMatch(subJsonObject, filterMap)){
            	jsonObjList.add(subJsonObject);  
        		
        	}
        }  

		return jsonObjList;
	}
	
	public List<JSONObject> getSubNodeInfo(String name, Map<String, String> filterMap) {

		List<JSONObject> jsonObjList = new ArrayList<JSONObject>();
		
		JSONArray jsonArray = getArrayByName(name);
		
		jsonObjList = getJSONObjByArray(jsonArray, filterMap);
		
		return jsonObjList;
	}
	
	public int getCountByValue(String fieldName, String fieldValue){
		int count = 0;
		Map<String, String> filterMap = new HashMap<String, String>();
		filterMap.put(fieldName, fieldValue);
		
		for(java.util.Map.Entry<String,Object> entry:jsonObject.entrySet()){  
			String nodeName = entry.getKey();
			JSONArray jsonArray = jsonObject.getJSONArray(nodeName);
			
			for(int indexArray = 0; indexArray < jsonArray.size(); indexArray++){
				JSONObject subJsonObject = jsonArray.getJSONObject(indexArray);
				
	        	if(checkFilterMatch(subJsonObject, filterMap)){
	        		count++;  
	        	}
			}
        }  
		
		return count;
	}
	
	
	public void setJSONValue(JSONObject jsonObject, String fieldName, String fieldValue){
		jsonObject.put(fieldName, fieldValue);
	}
	
	public void writeJSONFile(String path){
		try {
			Util util = new Util();
			util.writeFile(path, jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private Boolean checkFilterMatch(JSONObject jsonObject, Map<String, String> filterMap) {
			
		Boolean march = Boolean.TRUE;
		
		for(Entry<String, String> entry:filterMap.entrySet()){ 
			if(jsonObject.get(entry.getKey()) != null) {
				if(jsonObject.get(entry.getKey()).equals(entry.getValue())) {
					 
				}else{
					march = Boolean.FALSE;
				}
			}else{
				march = Boolean.FALSE;
			}
		}
		
		return march;
	}

}
