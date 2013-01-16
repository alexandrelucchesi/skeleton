package br.com.loopec.skeleton.persistence.dao;

import br.com.loopec.skeleton.persistence.entity.User;

public interface UserDAO {
	
	public void addUser(User user);
		
	public User findUserById(long id);
	
}
