package com.ingsw.restservice.model;

public class Review {

	private String author;
    private String reviewText;
    private float rating;
    private String data;
    
    public Review(Builder builder) {
        this.author=builder.author;
        this.reviewText=builder.reviewText;
        this.rating=builder.rating;
        this.data= builder.data;
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
    
    public static class Builder {

        public String data;
        private String author;
        private String reviewText;
        private float rating;

        public Builder setReviewText(String reviewText) {
            this.reviewText = reviewText;
            return this;
        }

        public Builder setRating(float rating) {
            this.rating = rating;
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

        public Review build() {
            return new Review(this);
        }
    }
}
