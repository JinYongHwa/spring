package com.jyh.board.service;



import java.util.List;
import java.util.UUID;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jyh.board.model.AttachFile;
import com.jyh.board.model.Board;
import com.jyh.board.model.Navigator;

@Service
public class BoardService {
	
	@Autowired
	SqlSessionTemplate mybatis;
	
	Navigator navigator=new Navigator(10,10);
	
	public void write(Board board){
		mybatis.insert("board.write",board);
	}
	
	public Board getBoard(int id) {
		Board board=new Board();
		board.setId(id);
		board=mybatis.selectOne("board.view",board);
		return board;
	}
	public void viewCountUp(int id) {
		Board board=getBoard(id);
		int viewCount=board.getViewCount();
		board.setViewCount(viewCount+1);
		mybatis.update("board.viewCountUp",board);
	}
	
	
	public void modify(Board board) {
		mybatis.update("board.modify",board);
	}
	
	public List<Board> getBoardList(int page){
		Navigator nav=new Navigator();
		nav.setPage(page);
		List<Board> list=mybatis.selectList("board.list",nav);
		return list;
	}
	public Navigator getNavigator(int page) {
		Navigator navigator=mybatis.selectOne("board.count");
		navigator=navigator.getNav(page, navigator.getCount());
		return navigator;
	}
	
	
	public void delete(int id) {
		Board board=new Board();
		board.setId(id);
		mybatis.delete("board.delete",board);
	}
	
	public AttachFile insertFile(MultipartFile file,int boardId) {
		AttachFile attachFile=new AttachFile();
		String uuid=UUID.randomUUID().toString();
		attachFile.setBoardId(boardId);
		attachFile.setId(uuid);
		attachFile.setSize(file.getSize());
		attachFile.setType(file.getContentType());
		attachFile.setOriginalname(file.getOriginalFilename());
		mybatis.insert("board.insertFile",attachFile);
		return attachFile;
	}
	public List<AttachFile> getAttachFiles(int boardId){
		Board board=new Board();
		board.setId(boardId);
		List<AttachFile> files= mybatis.selectList("board.attachFiles",board);
		return files;
	}
	public AttachFile getAttachFile(String id) {
		AttachFile attachFile=new AttachFile();
		attachFile.setId(id);
		return mybatis.selectOne("board.attachFile",attachFile);
	}
	
}









