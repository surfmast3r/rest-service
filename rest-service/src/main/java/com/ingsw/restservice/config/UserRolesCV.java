package com.ingsw.restservice.config;

import org.springframework.security.core.GrantedAuthority;

public class UserRolesCV implements GrantedAuthority{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String authority;

	public UserRolesCV(String role) {
		authority=role;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	@Override
	public String getAuthority() {
		return authority;
	}
}
