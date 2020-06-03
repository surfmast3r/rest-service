package com.ingsw.restservice.model;

import com.ingsw.restservice.model.DTO.JsonPageResponse;

public interface ReviewDao {

     boolean deleteReview(long reviewId);
     Review getReviewById(int id);
     ReviewView changeReviewStatus(int id, String status);
     Review createReview(Review review);
     JsonPageResponse<ReviewView> getReviewUserByAccommodation(int accommodationId, int pageNumber);
     ReviewView getReviewView(long id);

     JsonPageResponse<ReviewView> getReviewView(long reviewId, long accommodationId, String accommodationName, String content, String status,int pageNumber);
}
