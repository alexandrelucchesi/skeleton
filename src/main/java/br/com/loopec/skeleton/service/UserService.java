package br.com.loopec.skeleton.service;

import br.com.loopec.skeleton.persistence.entity.User;

public interface UserService {

	public void addUser(User user);
	
	public User findUserById(long id);
	
}
