package kr.ac.mjc.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.mjc.UserController;

public class Board {
	private static final Logger logger = LoggerFactory.getLogger(Board.class);
	private int id;
	private String title;
	private String text;
	private Date writeDate;
	private int viewCount;
	private String writer;
	private String userId;
	
	private List<MultipartFile> files=new ArrayList<MultipartFile>();
	private List<AttachFile> attachFiles=new ArrayList<AttachFile>();
	private List<String> attachIds=new ArrayList<String>();
	
	private int page;
	
	public Board() {
		super();
		writeDate=new Date();
		logger.info("{}",files);
		
	}
	
	
	
	public List<String> getAttachIds() {
		return attachIds;
	}



	public void setAttachIds(List<String> attachIds) {
		this.attachIds = attachIds;
	}



	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<AttachFile> getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(List<AttachFile> attachFiles) {
		this.attachFiles = attachFiles;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}



	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}



	public int getPage() {
		return page;
	}



	public void setPage(int page) {
		this.page = page;
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
