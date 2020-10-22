package com.ingsw.restservice.model.DTO;

import java.io.Serializable;

public class JwtFacebookResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwtToken;
    private final Integer userId;
    private final String username;


    public JwtFacebookResponse(String jwtToken, Integer userId, String username) {
            this.jwtToken = jwtToken;
            this.userId = userId;
            this.username = username;
    }

    public String getToken() {
        return this.jwtToken;
    }

    public String getUsername() {
        return this.username;
    }

    public Integer getUserId() {
        return userId;
    }
}


