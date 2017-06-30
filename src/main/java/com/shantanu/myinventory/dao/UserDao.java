package com.shantanu.myinventory.dao;

import java.util.List;

import com.shantanu.myinventory.model.UserDetails;

public interface UserDao {
	
	List<UserDetails> getUserDetails();

}