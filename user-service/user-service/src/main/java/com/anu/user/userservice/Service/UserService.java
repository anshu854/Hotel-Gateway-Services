package com.anu.user.userservice.Service;

import java.util.List;

import com.anu.user.userservice.Entities.User;

public interface UserService {

	//create user
		User saveUser(User user);

		//get all users
		List<User> getAllUser();

		//get Single User
		User getUser(String UserId);
	
}
