package com.jyh.board.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyh.board.BoardController;
import com.jyh.board.model.User;

@Service
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	SqlSessionTemplate mybatis;
	
	public int join(User user) {
		User tmpUser=mybatis.selectOne("user.emailCheck",user);
		logger.info("tmpUser {}",tmpUser);
		if(tmpUser!=null) {
			return 401;		//기존에 가입되있는 이메일인경우
		}
		String password=user.getPassword();
		logger.info("password {}",password);
		try {
			password=sha256(password);
			logger.info("password {}",password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setPassword(password);
		mybatis.insert("user.insertUser",user);
		
		return 200;
		
	}
	public int login(User user) {
		String password=user.getPassword();
		try {
			password=sha256(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		user.setPassword(password);
		User tmpUser=mybatis.selectOne("user.loginCheck",user);
		if(tmpUser!=null) {
			return 200;		//로그인 성공시
		}
		else {
			return 401;		//아이디 또는 패스워드 틀렸을시
		}
		
	}
	
	
	
	
	public  String sha256(String msg)  throws NoSuchAlgorithmException {
	    MessageDigest md = MessageDigest.getInstance("SHA-256");
	    md.update(msg.getBytes());
	    return byteToHexString(md.digest());
	}

	public  String byteToHexString(byte[] data) {
	    StringBuilder sb = new StringBuilder();
	    for(byte b : data) {
		sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
	    }
	    return sb.toString();
	}
}
