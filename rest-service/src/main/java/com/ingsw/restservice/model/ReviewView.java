package com.ingsw.restservice.model;

import org.springframework.data.annotation.Immutable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Immutable
@Table(name = "review_view")
public class ReviewView implements Serializable{

    @Id
    @Column(name="id")
    private long id;
    @Column(name="nome")
    private String nome;
    @Column(name="user_id")
    private long userId;
    @Column(name="accommodation_id")
    private long accommodationId;
    @Column(name="accommodation_name")
    private String accommodationName;
    @Column(name="content")
    private String content;
    @Column(name="stato")
    private String stato;
    @Column(name="rating")
    private float rating;
    @Column(name="creation_date")
    private Date creationDate;


    public ReviewView(){

    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public long getAccommodationId() {
        return accommodationId;
    }

    public void setAccommodationId(long accommodationId) {
        this.accommodationId = accommodationId;
    }

    public String getAccommodationName() {
        return accommodationName;
    }

    public void setAccommodationName(String accommodationName) {
        this.accommodationName = accommodationName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }



}

