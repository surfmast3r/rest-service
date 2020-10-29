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

	private static final Integer INVALID_USERNAME = 1;
	private static final Integer VALIDATED = 0;
	private static final Integer INVALID_PASSWORD = 2;
	private static final Integer INVALID_EMAIL = 3;
	private static final String EMAIL_REGEX = "^(.+)@(.+)$";
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
		Users user = userRepo.findUserByNickname(nickname);
		if (user!=null)
		{
			return user.getId();
		}
		return -1;

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

	public Integer validateUserData(Users user) {
		String username= user.getNickname();
		String pwd = user.getPwd();
		String email = user.getEmail();

		if(username.length()<6||username.length()>20)
			return INVALID_USERNAME ;

		if(pwd.length()<8||pwd.length()>20){
			return INVALID_PASSWORD;
		}

		if(!email.matches(EMAIL_REGEX))
			return INVALID_EMAIL;

		return VALIDATED;
	}
}
