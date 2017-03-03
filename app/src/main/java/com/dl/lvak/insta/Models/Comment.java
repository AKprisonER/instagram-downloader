package com.dl.lvak.insta.Models;//
//	Comment.java
//	Model file generated using JSONExport: https://github.com/Ahmed-Ali/JSONExport

import org.json.*;
import io.realm.*;
import io.realm.annotations.*;


@RealmClass
public class Comment extends RealmObject{

	private int count;
	private RealmList<From> data;

	public void setCount(int count){
		this.count = count;
	}
	public int getCount(){
		return this.count;
	}
	public void setData(RealmList<From> data){
		this.data = data;
	}
	public RealmList<From> getData(){
		return this.data;
	}

	/**
	 * Creates instance using the passed realm and jsonObject to set the properties values
	 */
	public static Comment fromJson(Realm realm, JSONObject jsonObject){
		if(jsonObject == null){
			return null;
		}
		Comment comment = realm.createObject(Comment.class);
		comment.count = jsonObject.optInt("count");
		JSONArray dataJsonArray = jsonObject.optJSONArray("data");
		if(dataJsonArray != null){
			for (int i = 0; i < dataJsonArray.length(); i++) {
				JSONObject dataObject = dataJsonArray.optJSONObject(i);
				From dataValue = From.fromJson(realm, dataObject);
				if(dataValue != null){
					comment.getData().add(dataValue);
				}
			}
		}
		return comment;
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public static JSONObject toJsonObject(Comment comment)
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("count", comment.count);
			if(comment.data != null && comment.data.size() > 0){
				JSONArray dataJsonArray = new JSONArray();
				for(From dataElement : comment.data){
					dataJsonArray.put(From.toJsonObject(dataElement));
				}
				jsonObject.put("data", dataJsonArray);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}