package com.cabBooking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cabBooking.Entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{

	public User findUserByNameIgnoreCase(String username);
}
