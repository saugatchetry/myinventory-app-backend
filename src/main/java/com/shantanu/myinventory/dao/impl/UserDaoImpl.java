package com.shantanu.myinventory.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shantanu.myinventory.dao.UserDao;
import com.shantanu.myinventory.model.UserDetails;

@Component
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<UserDetails> getUserDetails() {
		Criteria criteria = sessionFactory.openSession().createCriteria(UserDetails.class);
		return criteria.list();
	}

}
