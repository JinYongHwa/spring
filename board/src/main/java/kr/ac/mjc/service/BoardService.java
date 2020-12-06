package kr.ac.mjc.service;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.mjc.BoardController;
import kr.ac.mjc.model.Board;
import kr.ac.mjc.model.Navigator;
import kr.ac.mjc.model.Query;

@Service
public class BoardService {
	private static final Logger logger = LoggerFactory.getLogger(BoardService.class);
	
	@Autowired
	private SqlSessionTemplate mybatis;

	private Navigator nav = new Navigator(10,10);

	public void insertBoard(Board board) {
		
		mybatis.insert("board.writeItem",board);
	}
	
	public List<Board> list(int page) {
		
		int skip=nav.getSkip(page);
		
		Query query=new Query();
		query.setSkip(skip);
		query.setItemPerPage(nav.getItemPerPage());
		return mybatis.selectList("board.list",query);
		
	}
	public Board getBoard(int id) {
		Board board=(Board) mybatis.selectOne("board.getBoard", id);
		return board;
	}
	public void modify(Board board) {
		mybatis.update("board.modify",board);
	}
	public void delete(int id) {
		mybatis.delete("board.delete",id);
	}
	
	public int count() {
		Query query=mybatis.selectOne("board.count");
		return query.getCount();
	}
	
	public Navigator getNavigator(int page,int count) {
		return nav.getNav(page, count);
	}
	
	
}
