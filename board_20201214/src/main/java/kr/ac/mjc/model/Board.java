package kr.ac.mjc.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Board {
	
	private int id;
	private String title;
	private String text;
	private Date writeDate;
	private int viewCount;
	private String writer;
	
	
	
	public Board() {
		super();
		writeDate=new Date();
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
	public String getFormattedText() {
		if(this.text==null) {
			return "";
		}
		return this.text.replaceAll("\n","<br/>");
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	
	public Date getWriteDate() {
		return writeDate;
	}
	public String getFormattedWriteDate() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(writeDate);
	}
	
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	
	
	

}
