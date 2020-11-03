package kr.ac.mjc.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.mjc.model.User;

@Repository
public class TestDao {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	
	public List<User> getUsers(){
		return mybatis.selectList("test.getUsers");
	}
}
