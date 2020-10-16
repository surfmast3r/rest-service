package com.ingsw.restservice.model;

import com.ingsw.restservice.config.UserRolesCV;
import com.ingsw.restservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDaoSql implements UserDetailsService {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepo.findUserByNickname(username);
		List<UserRolesCV> roles=new ArrayList<>();
		roles.add(new UserRolesCV(user.getUserRole()));
		if (user.getNickname().equals(username)) {
			return new User(user.getNickname(),user.getPwd(),roles);
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

	public Integer getUserIdByNickname(String nickname){
		return userRepo.findUserByNickname(nickname).getId();
	}

	public Users getUserById(int id){
		return userRepo.findUserById(id);
	}
	
	
	public Users save(Users user) {
		Users newUser = new Users();
		newUser.setNickname(user.getNickname());
		newUser.setNome(user.getNome());
		newUser.setCognome(user.getCognome());
		newUser.setPwd(bcryptEncoder.encode(user.getPwd()));
		newUser.setEmail(user.getEmail());
		newUser.setUserRole("ROLE_USER");
		newUser.setShowNickname(true);
		return userRepo.save(newUser);
	}
}
