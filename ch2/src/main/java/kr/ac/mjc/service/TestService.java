package kr.ac.mjc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.mjc.model.User;

@Service
public class TestService {

	
	@Autowired
	private TestDao testDao;
	
	
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return testDao.getUsers();
	}
	public User login(User user) {
		return testDao.login(user);
	}
	
	public boolean checkId(User user) {
		return testDao.checkId(user);
	}
	
	public boolean join(User user) {
		return testDao.join(user);
	}

	
}

