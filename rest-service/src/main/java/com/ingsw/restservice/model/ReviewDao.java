package com.ingsw.restservice.model;

import com.ingsw.restservice.model.DTO.JsonPageResponse;
import com.ingsw.restservice.model.DTO.ReviewUser;

import java.util.List;

public interface ReviewDao {

    boolean deleteReview(long reviewId);
    JsonPageResponse<Review> getReviewList(int accommodationId, int pageNumber);
    Review getReviewById(int id);
	Review changeReviewStatus(int id, String status);
	Review createReview(Review review);
    /*JsonPageResponse<ReviewUser> getReviewUserByUser(int userId, int pageNumber);*/
}
