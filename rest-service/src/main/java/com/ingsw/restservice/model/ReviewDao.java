package com.ingsw.restservice.model;

import com.ingsw.restservice.model.DTO.JsonPageResponse;

import java.util.List;

public interface ReviewDao {

    boolean deleteReview(long reviewId);
	public Review getReviewById(int id);
	public Review changeReviewStatus(int id, String status);
    public JsonPageResponse<Review> getReviewList(int accommodationId, int pageNumber);
    }
