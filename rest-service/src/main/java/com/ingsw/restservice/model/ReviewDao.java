package com.ingsw.restservice.model;

import com.ingsw.restservice.model.DTO.JsonPageResponse;
import org.springframework.data.domain.Sort;

public interface ReviewDao {

     boolean deleteReview(long reviewId);
     Review getReviewById(int id);
     ReviewView changeReviewStatus(int id, String status);
     Review createReview(Review review);
     ReviewView getReviewView(long id);
     JsonPageResponse<ReviewView> getReviewView(long reviewId, long accommodationId, String accommodationName, String content, String status,int pageNumber, String orderBy, Sort.Direction direction);
}
