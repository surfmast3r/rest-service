package com.ingsw.restservice.model.DTO;

import java.io.Serializable;

public class ImageUploadResponse implements Serializable {
    private String message;
    private String imgUrl;

    public ImageUploadResponse( String msg, String url){
        message=msg;
        imgUrl=url;
    }

    public String getMessage() {
        return message;
    }

    public String getImgUrl() {
        return imgUrl;
    }

}
