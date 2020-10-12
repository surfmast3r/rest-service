package com.ingsw.restservice.model.DTO;

import java.io.Serializable;

public class JwtResponse implements Serializable {
	
	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwtToken;
	private final Integer userId;
	
	public JwtResponse(String jwtToken, Integer userId) {
		this.jwtToken = jwtToken;
		this.userId = userId;
	}
	public String getToken() {
		return this.jwtToken;
	}

	public Integer getUserId() {
		return userId;
	}
}
