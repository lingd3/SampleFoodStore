package dao;

import entity.User;

public interface UserDao {

	public User exist(String name, String password);
	
}
