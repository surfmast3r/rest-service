package com.ingsw.restservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ingsw.restservice.model.Review;
import com.ingsw.restservice.model.ReviewDao;
import com.ingsw.restservice.model.ReviewDaoStub;

@RestController	
public class RetreiveReviewController {

	private ReviewDao reviewDao;
	
	@GetMapping("/review")
	public List<Review> getReviews(@RequestParam(value = "accommodation") String accommodationId) {
		
		reviewDao = new ReviewDaoStub();
		return reviewDao.getReviewList(Integer.valueOf(accommodationId));
		
		
	}
}
