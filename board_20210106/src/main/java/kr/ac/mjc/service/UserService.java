package kr.ac.mjc.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
		String password=user.getPassword();
		
		try {
			String encryptPassword=sha256(password);
			user.setPassword(encryptPassword);
			mybatis.insert("user.join",user);
			
			return true;
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
			return false;
		}
		
		
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
	
	public User login(User user) {
		
		String password=user.getPassword();
		String encryptPassword;
		try {
			encryptPassword = sha256(password);
			user.setPassword(encryptPassword);
			
			return mybatis.selectOne("user.login",user);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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











