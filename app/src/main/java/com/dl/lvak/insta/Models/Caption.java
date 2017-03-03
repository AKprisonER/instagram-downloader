package com.dl.lvak.insta.Models;//
//	Caption.java
//	Model file generated using JSONExport: https://github.com/Ahmed-Ali/JSONExport

import org.json.*;
import io.realm.*;
import io.realm.annotations.*;


@RealmClass
public class Caption extends RealmObject{

	private String createdTime;
	private From from;
	private String id;
	private String text;

	public void setCreatedTime(String createdTime){
		this.createdTime = createdTime;
	}
	public String getCreatedTime(){
		return this.createdTime;
	}
	public void setFrom(From from){
		this.from = from;
	}
	public From getFrom(){
		return this.from;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return this.id;
	}
	public void setText(String text){
		this.text = text;
	}
	public String getText(){
		return this.text;
	}

	/**
	 * Creates instance using the passed realm and jsonObject to set the properties values
	 */
	public static Caption fromJson(Realm realm, JSONObject jsonObject){
		if(jsonObject == null){
			return null;
		}
		Caption caption = realm.createObject(Caption.class);
		caption.createdTime = (String) jsonObject.opt("created_time");
		caption.from = From.fromJson(realm, jsonObject.optJSONObject("from"));
		caption.id = (String) jsonObject.opt("id");
		caption.text = (String) jsonObject.opt("text");
		return caption;
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public static JSONObject toJsonObject(Caption caption)
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("created_time", caption.createdTime);
			jsonObject.put("from", From.toJsonObject(caption.from));
			jsonObject.put("id", caption.id);
			jsonObject.put("text", caption.text);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}