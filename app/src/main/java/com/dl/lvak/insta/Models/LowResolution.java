package com.dl.lvak.insta.Models;//
//	LowResolution.java
//	Model file generated using JSONExport: https://github.com/Ahmed-Ali/JSONExport

import org.json.*;
import io.realm.*;
import io.realm.annotations.*;


@RealmClass
public class LowResolution extends RealmObject{

	private int height;
	private String url;
	private int width;

	public void setHeight(int height){
		this.height = height;
	}
	public int getHeight(){
		return this.height;
	}
	public void setUrl(String url){
		this.url = url;
	}
	public String getUrl(){
		return this.url;
	}
	public void setWidth(int width){
		this.width = width;
	}
	public int getWidth(){
		return this.width;
	}

	/**
	 * Creates instance using the passed realm and jsonObject to set the properties values
	 */
	public static LowResolution fromJson(Realm realm, JSONObject jsonObject){
		if(jsonObject == null){
			return null;
		}
		LowResolution lowResolution = realm.createObject(LowResolution.class);
		lowResolution.height = jsonObject.optInt("height");
		lowResolution.url = String.valueOf(jsonObject.opt("url"));
		lowResolution.width = jsonObject.optInt("width");
		return lowResolution;
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public static JSONObject toJsonObject(LowResolution lowResolution)
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("height", lowResolution.height);
			jsonObject.put("url", lowResolution.url);
			jsonObject.put("width", lowResolution.width);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}