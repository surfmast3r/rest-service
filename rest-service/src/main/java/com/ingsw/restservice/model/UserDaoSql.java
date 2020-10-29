package com.ingsw.restservice.model;
import com.ingsw.restservice.config.UserRolesCV;
import com.ingsw.restservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDaoSql implements UserDetailsService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) {
		Users user = userRepo.findUserByNickname(username);
		if(user!=null){
			List<UserRolesCV> roles = new ArrayList<>();
			roles.add(new UserRolesCV(user.getUserRole()));
			return new User(user.getNickname(), user.getPwd(), roles);
		} else {
			return null;
		}
	}

	public Integer getUserIdByNickname(String nickname) {
		return userRepo.findUserByNickname(nickname).getId();
	}

	public Users getUserById(int id) {
		return userRepo.findUserById(id);
	}


	public Users save(Users user) {

		user.setPwd(bcryptEncoder.encode(user.getPwd()));
		user.setUserRole("ROLE_USER");
		user.setShowNickname(true);
		return userRepo.save(user);
	}

	public int setShowNickname(int id, boolean value) {
		return userRepo.setShowNickname(id, value);
	}


	public boolean validateUsername(String username) {
		return username.length() > 5 ;
	}
}
