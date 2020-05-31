package com.ingsw.restservice.model.DTO;

import javax.persistence.*;
import java.util.Date;


@Table(name = "reviewuser")
public class ReviewUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String username;
    private String content;
    private float rating;
    private String stato;
    private long idAccommodation;
    private long idUser;
    private Date creation_date;


    public ReviewUser(){
        //VACANTE
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public long getIdAccommodation() {
        return idAccommodation;
    }

    public void setIdAccommodation(long idAccommodation) {
        this.idAccommodation = idAccommodation;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }
}

