package com.dl.lvak.insta.Models;

/**
 * Created by HemantSingh on 21/10/16.
 */
//
//	Location.java
//	Model file generated using JSONExport: https://github.com/Ahmed-Ali/JSONExport

import org.json.*;
import io.realm.*;
import io.realm.annotations.*;


@RealmClass
public class Location extends RealmObject{

    private String name;

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    /**
     * Creates instance using the passed realm and jsonObject to set the properties values
     */
    public static Location fromJson(Realm realm, JSONObject jsonObject){
        if(jsonObject == null){
            return null;
        }
        Location location = realm.createObject(Location.class);
        location.name = (String) jsonObject.opt("name");
        return location;
    }

    /**
     * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
     */
    public static JSONObject toJsonObject(Location location)
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", location.name);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }

}