package br.com.loopec.skeleton.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.loopec.skeleton.persistence.entity.User;

@Repository("userDAO")
@Transactional
public class JPAUserDAO implements UserDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void addUser(User user) {
		em.persist(user);
	}

	@Override
	public User findUserById(long id) {
		return (User) em.createQuery("SELECT u FROM User u WHERE u.id = :id").
				setParameter("id", id).getSingleResult();
	}

}
