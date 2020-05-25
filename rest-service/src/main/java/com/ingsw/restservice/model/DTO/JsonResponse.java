package com.ingsw.restservice.model.DTO;

import java.io.Serializable;

public class JsonResponse implements Serializable {

    private final String message;
    private Boolean response;

    public JsonResponse(Boolean response,String message) {
        this.response = response;
        this.message = message;
    }
    public String getMessage() {

        return this.message;
    }
    public Boolean getResponse() {

        return this.response;
    }
}
