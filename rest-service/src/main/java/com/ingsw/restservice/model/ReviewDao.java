package com.ingsw.restservice.model;

import java.util.List;

public interface ReviewDao {

	public List<Review> getReviewList(int accommodationId);
	public Review getReviewById(int id);
	public Review changeReviewStatus(int id, String status);
}
