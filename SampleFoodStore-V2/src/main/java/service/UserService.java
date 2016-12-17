package service;

import entity.User;

public interface UserService {

	public User exist(String name, String password);
	
}
