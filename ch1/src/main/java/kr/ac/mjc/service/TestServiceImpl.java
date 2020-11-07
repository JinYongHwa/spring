package kr.ac.mjc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.mjc.model.User;

@Service
public class TestServiceImpl implements TestService {

	
	@Autowired
	private TestDao testDao;
	
	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return testDao.getUsers();
	}

	
}

