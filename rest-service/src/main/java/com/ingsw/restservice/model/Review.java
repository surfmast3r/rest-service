package com.ingsw.restservice.model;

import javax.persistence.*;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private int accommodationId;
    private String accommodationName;
	private String author;
    private String reviewText;
    private float rating;
    private String data;
    private Status status;
    
    public Review(Builder builder) {
    	this.id=builder.id;
    	this.accommodationId=builder.accommodationId;
    	this.accommodationName=builder.accommodationName;
        this.author=builder.author;
        this.reviewText=builder.reviewText;
        this.rating=builder.rating;
        this.data= builder.data;
        this.status=builder.status;
    }
    public int getId() {
        return id;
    }
    public int getAccommodationId() {
  		return accommodationId;
  	}
    
    public String getAccommodationName() {
  		return accommodationName;
  	}
    
    public String getAuthor() {
        return author;
    }

    public String getReviewText() {
        return reviewText;
    }

    public String getData() {
        return data;
    }

    public float getRating() {
        return rating;
    }
    
    public Status getStatus() {
		return status;
	}
    
    public void setId(int id) {
		this.id = id;
	}
	public void setAccommodationName(String accommodationName) {
		this.accommodationName = accommodationName;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public void setData(String data) {
		this.data = data;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
    
    public static class Builder {

    	private int id;
    	public int accommodationId;
        private String accommodationName;
		private String data;
        private String author;
        private String reviewText;
        private float rating;
        private Status status;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }
        public Builder setReviewText(String reviewText) {
            this.reviewText = reviewText;
            return this;
        }

        public Builder setRating(float rating) {
            this.rating = rating;
            return this;
        }

        public Builder setAccommodationId(int idAccommodation) {
            this.accommodationId = idAccommodation;
            return this;
        }
        
        public Builder setAccommodationName(String nameAccommodation) {
			this.accommodationName = nameAccommodation;
			return this;
		}
        
        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }
        public Builder setData(String data) {
            this.data = data;
            return this;
        }
        
        public Builder setStatus(Status status) {
        	this.status=status;
        	return this;
        }

        public Review build() {
            return new Review(this);
        }
    }
}
