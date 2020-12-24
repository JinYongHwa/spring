package kr.ac.mjc.service;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	public int checkEmail(String email) {
		Pattern pattern=Pattern.compile("^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$");
		Matcher m=pattern.matcher(email);
		if(!m.matches()) {
			return 401;
		}
		User user=new User();
		user.setEmail(email);
		User tempUser=mybatis.selectOne("user.checkEmail",user);
		if(tempUser!=null) {
			return 402;
		}
		return 200;
	}
}











