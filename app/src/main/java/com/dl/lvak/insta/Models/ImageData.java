package com.dl.lvak.insta.Models;//
//	ImageData.java
//	Model file generated using JSONExport: https://github.com/Ahmed-Ali/JSONExport

import org.json.*;
import io.realm.*;
import io.realm.annotations.*;


@RealmClass
public class ImageData extends RealmObject{

	private String alt_media_url;
	private boolean canDeleteComments;
	private boolean canViewComments;
	private Caption caption;
	private String code;
	private Comment comments;
	private String createdTime;
	private String id;
	private Image images;
	private Comment likes;
	private String link;
	private Location location;
	private String type;
	private From user;
	private boolean userHasLiked;

	public void setAlt_media_url(String alt_media_url){
		this.alt_media_url = alt_media_url;
	}
	public String getAlt_media_url(){
		return this.alt_media_url;
	}
	public void setCanDeleteComments(boolean canDeleteComments){
		this.canDeleteComments = canDeleteComments;
	}
	public boolean isCanDeleteComments()
	{
		return this.canDeleteComments;
	}
	public void setCanViewComments(boolean canViewComments){
		this.canViewComments = canViewComments;
	}
	public boolean isCanViewComments()
	{
		return this.canViewComments;
	}
	public void setCaption(Caption caption){
		this.caption = caption;
	}
	public Caption getCaption(){
		return this.caption;
	}
	public void setCode(String code){
		this.code = code;
	}
	public String getCode(){
		return this.code;
	}
	public void setComments(Comment comments){
		this.comments = comments;
	}
	public Comment getComments(){
		return this.comments;
	}
	public void setCreatedTime(String createdTime){
		this.createdTime = createdTime;
	}
	public String getCreatedTime(){
		return this.createdTime;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return this.id;
	}
	public void setImages(Image images){
		this.images = images;
	}
	public Image getImages(){
		return this.images;
	}
	public void setLikes(Comment likes){
		this.likes = likes;
	}
	public Comment getLikes(){
		return this.likes;
	}
	public void setLink(String link){
		this.link = link;
	}
	public String getLink(){
		return this.link;
	}
	public void setLocation(Location location){
		this.location = location;
	}
	public Object getLocation(){
		return this.location;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getType(){
		return this.type;
	}
	public void setUser(From user){
		this.user = user;
	}
	public From getUser(){
		return this.user;
	}
	public void setUserHasLiked(boolean userHasLiked){
		this.userHasLiked = userHasLiked;
	}
	public boolean isUserHasLiked()
	{
		return this.userHasLiked;
	}

	/**
	 * Creates instance using the passed realm and jsonObject to set the properties values
	 */
	public static ImageData fromJson(Realm realm, JSONObject jsonObject){
		if(jsonObject == null){
			return null;
		}
		ImageData imageData = realm.createObject(ImageData.class);
		imageData.alt_media_url = (String) jsonObject.opt("alt_media_url");
		imageData.canDeleteComments = jsonObject.optBoolean("can_delete_comments");
		imageData.canViewComments = jsonObject.optBoolean("can_view_comments");
		imageData.caption = Caption.fromJson(realm, jsonObject.optJSONObject("caption"));
		imageData.code = (String) jsonObject.opt("code");
		imageData.comments = Comment.fromJson(realm, jsonObject.optJSONObject("comments"));
		imageData.createdTime = String.valueOf(jsonObject.opt("created_time"));
		imageData.id = String.valueOf(jsonObject.opt("id"));
		imageData.images = Image.fromJson(realm, jsonObject.optJSONObject("images"));
		imageData.likes = Comment.fromJson(realm, jsonObject.optJSONObject("likes"));
		imageData.link = (String) jsonObject.opt("link");
		imageData.location = (Location) jsonObject.opt("location");
		imageData.type = (String) jsonObject.opt("type");
		imageData.user = From.fromJson(realm, jsonObject.optJSONObject("user"));
		imageData.userHasLiked = jsonObject.optBoolean("user_has_liked");
		return imageData;
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public static JSONObject toJsonObject(ImageData imageData)
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("alt_media_url", imageData.alt_media_url);
			jsonObject.put("can_delete_comments", imageData.canDeleteComments);
			jsonObject.put("can_view_comments", imageData.canViewComments);
			jsonObject.put("caption", Caption.toJsonObject(imageData.caption));
			jsonObject.put("code", imageData.code);
			jsonObject.put("comments", Comment.toJsonObject(imageData.comments));
			jsonObject.put("created_time", imageData.createdTime);
			jsonObject.put("id", imageData.id);
			jsonObject.put("images", Image.toJsonObject(imageData.images));
			jsonObject.put("likes", Comment.toJsonObject(imageData.likes));
			jsonObject.put("link", imageData.link);
			jsonObject.put("location", imageData.location);
			jsonObject.put("type", imageData.type);
			jsonObject.put("user", From.toJsonObject(imageData.user));
			jsonObject.put("user_has_liked", imageData.userHasLiked);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}