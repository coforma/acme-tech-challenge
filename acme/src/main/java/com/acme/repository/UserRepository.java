package com.acme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.acme.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>  {

	@Query("SELECT u FROM User u WHERE u.name = :name")
	User getUserByName(String name);
}
