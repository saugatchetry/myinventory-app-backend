package com.shantanu.myinventory.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shantanu.myinventory.dao.UserDao;
import com.shantanu.myinventory.model.UserDetails;
import com.shantanu.myinventory.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	public List<UserDetails> getUserDetails() {
		return userDao.getUserDetails();

	}

}
