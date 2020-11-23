package kr.ac.mjc.service;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.mjc.HomeController;
import kr.ac.mjc.model.Board;
import kr.ac.mjc.model.Navigator;
import kr.ac.mjc.model.Query;

@Service
public class BoardService {
	private static final Logger logger = LoggerFactory.getLogger(BoardService.class);
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	int itemPerPage=10;
	int navCount=10;
	private Navigator nav = new Navigator();

	public void insertBoard(Board board) {
		
		mybatis.insert("board.writeItem",board);
	}
	
	public List<Board> list(int page) {
		
		int skip=nav.getSkip(page);
		
		Query query=new Query();
		query.setSkip(skip);
		
		return mybatis.selectList("board.list",query);
		
	}
	public int count() {
		Query query=mybatis.selectOne("board.count");
		return query.getCount();
	}
	
	public Navigator getNavigator(int page,int count) {
		
		return Navigator.getNav(page, count);
	}
}
