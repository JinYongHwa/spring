package kr.ac.mjc.model;

import java.util.UUID;

public class AttachFile {
	private String id;
	private String originalFileName;
	private String contentType;
	private long size;
	private int boardId;
	
	public AttachFile() {}
	
	public AttachFile(String originalFileName,String contentType,long size,int boardId) {
		this.id=UUID.randomUUID().toString();
		this.originalFileName=originalFileName;
		this.contentType=contentType;
		this.size=size;
		this.boardId=boardId;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	
	
}
