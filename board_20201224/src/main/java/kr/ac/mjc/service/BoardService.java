package kr.ac.mjc.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.mjc.HomeController;
import kr.ac.mjc.model.AttachFile;
import kr.ac.mjc.model.Board;
import kr.ac.mjc.model.Navigator;
import kr.ac.mjc.model.Query;

@Service
public class BoardService {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardService.class);
	
	@Autowired
	private SqlSession mybatis;
	
	private Navigator navigator=new Navigator(10,10);
	
	public void write(Board board) {
		mybatis.insert("board.write",board);
		logger.info("{}",board.getId());
	}
	public Board getItem(int id) {
		Query query=new Query();
		query.setId(id);
		
		Board board=mybatis.selectOne("board.getItem",query);
		return board;
		
	}
	public List<Board> getList(int page){
		
		Query query=new Query();
		int skip=navigator.getSkip(page);
		query.setSkip(skip);
		query.setItemPerPage(navigator.getItemPerPage());
		
		List<Board> list=mybatis.selectList("board.getList",query);
		return list;
	}
	
	public Navigator getNavigator(int page) {
		Navigator nav=mybatis.selectOne("board.getCount");
		return navigator.getNav(page, nav.getCount());
	}
	
	public void upViewCount(int id) {
		Board board=getItem(id);
		board.setViewCount(board.getViewCount()+1);
		mybatis.update("board.setViewCount",board);
	}
	
	
	
	public void modify(Board board) {
		mybatis.update("board.modify",board);
	}
	
	public void remove(int id) {
		Board board=new Board();
		board.setId(id);
		mybatis.delete("board.remove",board);
	}
	
	public void insertAttachFile(AttachFile attachFile) {
		mybatis.insert("board.insertAttachFile",attachFile);
	}
	
	public List<AttachFile> getAttachFiles(Query query) {
		List<AttachFile> attachFiles=mybatis.selectList("board.getAttachFiles",query);
		return attachFiles;
	}
	public AttachFile getAttachFileItem(String id) {
		AttachFile attachFile=new AttachFile();
		attachFile.setId(id);
		
		return mybatis.selectOne("board.getAttachFileItem",attachFile);
	}
	
	public void removeAttach(Board board) {
		logger.info("{}",board.getId());
		logger.info("{}",board.getAttachIds());
		mybatis.delete("board.removeAttach",board);
	}
}
