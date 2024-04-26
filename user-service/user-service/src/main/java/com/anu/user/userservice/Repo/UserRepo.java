package com.anu.user.userservice.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anu.user.userservice.Entities.User;

public interface UserRepo extends JpaRepository<User,String> {

}
