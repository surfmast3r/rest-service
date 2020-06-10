package com.ingsw.restservice.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String content;
    private float rating;
    private String stato="PENDING";
    private long idAccommodation;
    private long idUser;
    private LocalDateTime creation_date= LocalDateTime.now();

    public Review(){
        //VACANTE
    };
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

    public LocalDateTime getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDateTime creation_date) {
        this.creation_date = creation_date;
    }



    public Review(Builder builder) {
    	this.id=builder.id;
    	this.content=builder.content;
    	this.idAccommodation=builder.idAccommodation;
    	this.idUser=builder.idUser;
        this.rating=builder.rating;
        this.creation_date= builder.creation_date;
        this.stato=builder.stato;
    }
    
    public static class Builder {

        private long id;
        private String content;
        private float rating;
        private String stato;
        private long idAccommodation;
        private long idUser;
        private LocalDateTime creation_date;



        public Builder setId(long id) {
            this.id = id;
            return this;
        }


        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setRating(float rating) {
            this.rating = rating;
            return this;
        }


        public Builder setStato(String stato) {
            this.stato = stato;
            return this;
        }


        public Builder setIdAccommodation(long idAccommodation) {
            this.idAccommodation = idAccommodation;
            return this;
        }


        public Builder setIdUser(long idUser) {
            this.idUser = idUser;
            return this;
        }


        public Builder setCreation_date(LocalDateTime creation_date) {
            this.creation_date = creation_date;
            return this;
        }



        public Review build() {
            return new Review(this);
        }
    }
}
