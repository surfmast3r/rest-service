package com.ingsw.restservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ingsw.restservice.model.Review;
import com.ingsw.restservice.model.ReviewDao;
import com.ingsw.restservice.model.ReviewDaoStub;

@RestController	
public class ReviewController {

	private ReviewDao reviewDao;
	
	@GetMapping(value = "/review", params = "accommodationId")
	public List<Review> getReviews(@RequestParam int accommodationId) {
		
		reviewDao = new ReviewDaoStub();
		return reviewDao.getReviewList(accommodationId);
		
		
	}
	@GetMapping(value = "/review", params = "reviewId")
	public Review getReviewById(@RequestParam int reviewId) {
		
		reviewDao = new ReviewDaoStub();
		return reviewDao.getReviewById(reviewId);
		
		
	}
}
