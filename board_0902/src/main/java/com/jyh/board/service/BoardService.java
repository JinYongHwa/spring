package com.jyh.board.service;



import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
}









