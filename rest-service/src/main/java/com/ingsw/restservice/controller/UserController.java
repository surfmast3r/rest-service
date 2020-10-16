package com.ingsw.restservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.ingsw.restservice.config.JwtTokenUtil;
import com.ingsw.restservice.model.DTO.JwtRequest;
import com.ingsw.restservice.model.DTO.JwtResponse;
import com.ingsw.restservice.model.Users;
import com.ingsw.restservice.model.UserDaoSql;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserDaoSql userDetailsService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody Users user){
		return ResponseEntity.ok(userDetailsService.save(user));
	}
	
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token, userDetailsService.getUserIdByNickname(authenticationRequest.getUsername())));
	}

	@RequestMapping(value = "/account_details", method = RequestMethod.GET)
	public ResponseEntity<?> getAccountDetails(@RequestParam int id, HttpServletRequest request){
		Users u = userDetailsService.getUserById(id);
		if (u==null) return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);

		final String requestTokenHeader = request.getHeader("Authorization");
		String role=null;
		String jwtToken;
		int idRequest;
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			idRequest=userDetailsService.getUserIdByNickname(jwtTokenUtil.getUsernameFromToken(jwtToken));
			role=jwtTokenUtil.getRoleFromToken(jwtToken);
			System.out.println(role);
			if(role.equals("ROLE_ADMIN") || idRequest==id)
				return new ResponseEntity<>(u, HttpStatus.OK);
			}
			return new ResponseEntity<>("Unauthorized",HttpStatus.UNAUTHORIZED);
		}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
}
