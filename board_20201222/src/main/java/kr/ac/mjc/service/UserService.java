package kr.ac.mjc.service;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.mjc.model.User;

@Service
public class UserService {
	
	@Autowired
	SqlSession mybatis;
	
	public Boolean join(User user) {
		User checkUser=mybatis.selectOne("user.checkEmail",user);
		
		if(checkUser!=null) {
			return false;
		}
		
		String id=UUID.randomUUID().toString();
		user.setId(id);
		mybatis.insert("user.join",user);
		return true;
	}
	
}
