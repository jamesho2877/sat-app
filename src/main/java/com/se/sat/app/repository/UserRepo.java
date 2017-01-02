package com.se.sat.app.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.se.sat.app.entity.User;

@Repository
@Transactional
public class UserRepo {

	private static final Logger log = LoggerFactory.getLogger(UserRepo.class);

	private SessionFactory sessionFactory;

	@Autowired
	public UserRepo(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void insert(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}

	public List<User> findAll() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		List<User> users = (List<User>) criteria.list();
		return users;
	}

	public User findUserByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.like("username", "%" + username + "%"));
		User result = (User) criteria.uniqueResult();
		return result;
	}

}
