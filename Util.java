package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Iterator;

import net.sf.json.JSONObject;

public class Util {

	public String ReadFile(String Path){
		BufferedReader reader = null;
		String laststr = "";
		
		try{
			FileInputStream fileInputStream = new FileInputStream(Path);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			
			while((tempString = reader.readLine()) != null){
				laststr += tempString;
			}
			
			reader.close();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return laststr;
	}
	
	public void printNode(Object object){
		Iterator<?> itKeys = ((JSONObject) object).keys();
		
		while(itKeys.hasNext()){
			String nodeName = itKeys.next().toString();
			
			System.out.println("\"" + nodeName + "\" = " + "" + "\"" + ((JSONObject) object).get(nodeName) + "\"");
		}
	}
	
	public void writeFile(String filePath, String sets) throws IOException {
	    FileWriter fw = new FileWriter(filePath);
	    PrintWriter out = new PrintWriter(fw);
	    out.write(sets);
	    out.println();
	    fw.close();
	    out.close();
	   }

	
}
