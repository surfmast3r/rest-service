package com.ingsw.restservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ingsw.restservice.model.Users;


@Repository
public interface UserRepository extends CrudRepository<Users, Long>{

	@Query("SELECT user FROM Users user WHERE user.nickname = ?1")
	Users findUserByNickname(String nickname);


}
