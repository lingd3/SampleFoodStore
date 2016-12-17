package dao;

import entity.User;

public interface UserDao {

	//判断用户是否存在
	public User exist(String name, String password);
	
}
