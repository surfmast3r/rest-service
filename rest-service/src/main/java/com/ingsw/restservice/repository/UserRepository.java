package com.ingsw.restservice.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ingsw.restservice.model.Users;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface UserRepository extends CrudRepository<Users, Long>{

	@Query("SELECT user FROM Users user WHERE user.nickname = ?1")
	Users findUserByNickname(String nickname);

	@Query("SELECT user from Users user WHERE  user.id = ?1")
	Users findUserById(int id);

	@Transactional
	@Modifying
	@Query("UPDATE Users u SET u.showNickname=:value WHERE u.id=:id")
	int setShowNickname(
			@Param("id") int id,
			@Param ("value") boolean value);

}

