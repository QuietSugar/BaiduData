package com.maybe.main;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class jsonToJava2 {
	public static void main(String[] args) {
		String res = FormUpload.formUpload("http://api.juheapi.com/bus/line?key=509b18bcf8e54a28f865c47671dc514e&city=北京&q=1路");
		JSONObject object;
		try {
			object = new JSONObject(res);
			JSONArray result = object.getJSONArray("result");
			for(int i=0; i<result.length(); i++){
				JSONObject entityObj = result.getJSONObject(i); 
				System.out.println(entityObj.get("_id")+"***"+entityObj.get("info"));
				JSONArray stats = entityObj.getJSONArray("stats");
				for(int j=0; j<stats.length(); j++){
					JSONObject entity = stats.getJSONObject(j); 
					System.out.println(entity.get("_id")+"***"+entity.get("lat")+"***"+entity.get("lng"));
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
