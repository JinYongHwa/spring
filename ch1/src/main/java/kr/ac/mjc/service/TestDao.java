package kr.ac.mjc.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.mjc.UserController;
import kr.ac.mjc.model.User;

@Repository
public class TestDao {
	private static final Logger logger = LoggerFactory.getLogger(TestDao.class);
	@Autowired
	private SqlSessionTemplate mybatis;
	
	
	public List<User> getUsers(){
		return mybatis.selectList("test.getUsers");
	}
	public User login(User user){
		User resultUser=mybatis.selectOne("test.login",user);
		return resultUser;
	}
	public boolean checkId(User user){
		User resultUser=mybatis.selectOne("test.checkId",user);
		return resultUser==null;
	}
	public boolean join(User user){
		int status=mybatis.insert("test.join",user);
		logger.info("status {}",status);
		return status==1;
	}
}
