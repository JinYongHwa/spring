package kr.ac.mjc.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Board {
	
	private int id;
	private String title;
	private String text;
	private Date date;
	private String writer;
	
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
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
	public String getText() {
		return text;
	}
	public String getHtml() {
		if(text!=null) {
			return text.replaceAll("\n", "<br/>");	
		}
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getDate() {
		
		return date;
	}
	public String getFormattedDate() {
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return df.format(getDate());
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	

}
