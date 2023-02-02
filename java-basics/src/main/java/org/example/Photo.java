package org.example;

import com.google.gson.annotations.SerializedName;

public class Photo{

	@SerializedName("albumId")
	private int albumId;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("url")
	private String url;

	@SerializedName("thumbnailUrl")
	private String thumbnailUrl;

	public int getAlbumId(){
		return albumId;
	}

	public int getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public String getUrl(){
		return url;
	}

	public String getThumbnailUrl(){
		return thumbnailUrl;
	}
}