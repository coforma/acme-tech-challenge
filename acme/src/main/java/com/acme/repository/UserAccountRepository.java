package com.acme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.acme.model.UserAccount;


@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long>  {

	@Query("SELECT u FROM UserAccount u WHERE u.name = :name")
	UserAccount getUserByName(String name);
}
