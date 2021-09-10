package study.spring.springhelper.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Image {
	@SerializedName("documents")
	private List<Documents> documents;
	
	@Data 
	public class Documents {
		@SerializedName("collection")		private String collection;
		@SerializedName("thumbnail_url")	private String  thumbnail_url;
		@SerializedName("image_url")		private String image_url;
		@SerializedName("width")			private int width;
		@SerializedName("height")			private int height;
		@SerializedName("display_sitename")	private String displaySitename;
		@SerializedName("doc_url")			private String docUrl;
		@SerializedName("datetime")			private String datetime;
	}

}
