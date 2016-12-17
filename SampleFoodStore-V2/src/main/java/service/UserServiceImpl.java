package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	public User exist(String name, String password) {
		return userDao.exist(name, password);
	}
	
}
