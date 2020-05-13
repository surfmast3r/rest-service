package com.ingsw.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ingsw.restservice.model.Users;
import com.ingsw.restservice.repository.UserRepository;

public class CustomAuthenticationManager implements AuthenticationManager{

	@Autowired
    UserRepository userRepo;
	
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String nickname = authentication.getPrincipal() + "";
		String password = authentication.getCredentials() + "";
		   
		    Users user;
     
	     //use a rest service to find the user. 
	     //Spring security provides user login name in authentication.getPrincipal()
		 user=userRepo.findUserByNickname(nickname);
      
         /*if (user == null) {
             throw new UsernameNotFoundException(String.format("Invalid credentials", authentication.getPrincipal()));
         } else if (!user.isEnabled()) {
             throw new UsernameNotFoundException(String.format("Not found enabled user for username ", user.getUsername()));
         }
         //check user password stored in authentication.getCredentials() against stored password hash
         if (StringUtils.isBlank(authentication.getCredentials().toString())
             || !passwordEncoder.isPasswordValid(user.getPasswordHash(), authentication.getCredentials().toString()) {
             throw new BadCredentialsException("Invalid credentials");
         }

         //doLogin makes whatever is necesary when login is made (put info in session, load other data etc..)*/
         return null ;
        
    }

	

}