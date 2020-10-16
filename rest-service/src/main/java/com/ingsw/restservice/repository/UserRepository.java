package com.ingsw.restservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ingsw.restservice.model.Users;


@Repository
public interface UserRepository extends CrudRepository<Users, Long>{

	@Query("SELECT user FROM Users user WHERE user.nickname = ?1")
	Users findUserByNickname(String nickname);

	@Query("SELECT user from Users user WHERE  user.id = ?1")
	Users findUserById(int id);

	@Query("UPDATE Users u SET u.showNickname = ?2 WHERE u.id = ?1")
	Boolean setShowNickname(int id, boolean value);

}

