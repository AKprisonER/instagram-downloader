package com.dl.lvak.insta.Models;//
//	From.java
//	Model file generated using JSONExport: https://github.com/Ahmed-Ali/JSONExport

import org.json.*;
import io.realm.*;
import io.realm.annotations.*;


@RealmClass
public class From extends RealmObject{

	private String full_name;
	private String id;
	private String profile_picture;
	private String username;

	public void setFull_name(String full_name){
		this.full_name = full_name;
	}
	public String getFull_name(){
		return this.full_name;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return this.id;
	}
	public void setProfilePicture(String profilePicture){
		this.profile_picture = profilePicture;
	}
	public String getProfilePicture(){
		return this.profile_picture;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public String getUsername(){
		return this.username;
	}

	/**
	 * Creates instance using the passed realm and jsonObject to set the properties values
	 */
	public static From fromJson(Realm realm, JSONObject jsonObject){
		if(jsonObject == null){
			return null;
		}
		From from = realm.createObject(From.class);
		from.full_name = String.valueOf(jsonObject.opt("full_name"));
		from.id = (String) jsonObject.opt("id");
		from.profile_picture = (String) jsonObject.opt("profile_picture");
		from.username = (String) jsonObject.opt("username");
		return from;
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public static JSONObject toJsonObject(From from)
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("full_name", from.full_name);
			jsonObject.put("id", from.id);
			jsonObject.put("profile_picture", from.profile_picture);
			jsonObject.put("username", from.username);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}