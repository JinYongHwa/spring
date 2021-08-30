package kr.ac.mjc.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.mjc.model.Board;
import kr.ac.mjc.model.Query;

@Service
public class BoardService {
	
	@Autowired
	private SqlSession mybatis;
	
	public void write(Board board) {
		mybatis.insert("board.write",board);
	}
	public Board getItem(int id) {
		Query query=new Query();
		query.setId(id);
		
		Board board=mybatis.selectOne("board.getItem");
		return board;
		
	}
}
