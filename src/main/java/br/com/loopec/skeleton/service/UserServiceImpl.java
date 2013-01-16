package br.com.loopec.skeleton.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.loopec.skeleton.persistence.dao.UserDAO;
import br.com.loopec.skeleton.persistence.entity.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Inject
	private UserDAO userDAO;
	
	@Override
	public void addUser(User user) {
		userDAO.addUser(user);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public User findUserById(long id) {
		return userDAO.findUserById(id);
	}

}
