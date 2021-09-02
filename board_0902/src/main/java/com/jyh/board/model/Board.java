package com.jyh.board.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Board {
	
	private int id;
	private String title;
	private String body;
	private Date createDate;
	private int userId;
	private int viewCount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public String getBodyBr() {
		if(body==null) {
			return null;
		}
		return body.replaceAll("\n", "<br>");
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public String getCreateDate() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedCreateDate=sdf.format(this.createDate);
		return formattedCreateDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	
	
	
	
	
}
