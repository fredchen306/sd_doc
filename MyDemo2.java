package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public class MyDemo2 {
	public static void main(String[] args) {
		String filePath = "E:\\SAP related\\SAP UI5\\Exercise from Max\\responseDetailsOfSalesorderItem.json";
		Util util = new Util();
		
		String JsonContext = util.ReadFile(filePath);
		
		JsonToolkit jsonTool = new JsonToolkit(JsonContext);
		
//      提取出SD_DOC=0000000151, PARTN_ROLE=AG的ORDER_PARTNERS_OUT子节点
		Map<String, String> filterMap = new HashMap<String, String>();
		filterMap.put("SD_DOC", "0000000151");
		filterMap.put("PARTN_ROLE", "AG");
		
		List<JSONObject> list = jsonTool.getSubNodeInfo("ORDER_PARTNERS_OUT", filterMap);
		System.out.println(list.toString());

		
//      统计一下SD_DOC=0000000151一共出现了几次
		int count = 0;
		count = jsonTool.getCountByValue("SD_DOC", "0000000151");
		System.out.println(count);
		
//      将SD_DOC=0000000151, PARTN_ROLE=AG的ORDER_PARTNERS_OUT子节点的LEVEL_NR设置为100, 并将结果保存在另一个json文件中
		for(int indexList = 0; indexList < list.size(); indexList++)  
        {  
			jsonTool.setJSONValue(list.get(indexList), "LEVEL_NR", "100");  
        }
		jsonTool.writeJSONFile("E:\\SAP related\\SAP UI5\\Exercise from Max\\responseDetailsOfSalesorderItem_new2.json");
	}
}
