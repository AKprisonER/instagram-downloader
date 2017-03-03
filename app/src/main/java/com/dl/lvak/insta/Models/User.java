package com.dl.lvak.insta.Models;

import org.json.*;
import io.realm.*;
import io.realm.annotations.*;




/**
 * Created by HemantSingh on 21/10/16.
 */
@RealmClass
public class User extends RealmObject {
    private RealmList<ImageData> items;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id ;



    public void setItems(RealmList<ImageData> items){
        this.items = items;
    }
    public void addItem(ImageData item){
        this.items.add(item);
    }
    public RealmList<ImageData> getItems(){
        return this.items;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }

    /**
     * Creates instance using the passed realm and jsonObject to set the properties values
     */
    public static User fromJson(Realm realm, JSONObject jsonObject){
        if(jsonObject == null){
            return null;
        }
        User rootClass = realm.createObject(User.class);
        JSONArray itemsJsonArray = jsonObject.optJSONArray("items");
        if(itemsJsonArray != null){
            for (int i = 0; i < itemsJsonArray.length(); i++) {
                JSONObject itemsObject = itemsJsonArray.optJSONObject(i);
                ImageData itemsValue = ImageData.fromJson(realm, itemsObject);
                if(itemsValue != null){
                    rootClass.getItems().add(itemsValue);
                }
            }
        }
        rootClass.status = (String) jsonObject.opt("status");
        return rootClass;
    }

    /**
     * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
     */
    public static JSONObject toJsonObject(User rootClass)
    {
        JSONObject jsonObject = new JSONObject();
        try {
            if(rootClass.items != null && rootClass.items.size() > 0){
                JSONArray itemsJsonArray = new JSONArray();
                for(ImageData itemsElement : rootClass.items){
                    itemsJsonArray.put(ImageData.toJsonObject(itemsElement));
                }
                jsonObject.put("items", itemsJsonArray);
            }
            jsonObject.put("status", rootClass.status);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }


}
