package com.ingsw.restservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ingsw.restservice.model.Review;
import com.ingsw.restservice.model.ReviewDao;
import com.ingsw.restservice.model.ReviewDaoStub;

@RestController	
public class ReviewController {

	private ReviewDao reviewDao= new ReviewDaoStub();
	
	@GetMapping(value = "/review", params = "accommodationId")
	public List<Review> getReviews(@RequestParam int accommodationId) {
		
		return reviewDao.getReviewList(accommodationId);
		
	}
	
	@GetMapping(value = "/review", params = "reviewId")
	public Review getReviewById(@RequestParam int reviewId) {
		
		return reviewDao.getReviewById(reviewId);
		
	}
	
	@RequestMapping(value = "/review/edit/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> changeReviewStatus(@PathVariable("id") int id, @RequestParam("status")String status) { 
      	
		Review review=reviewDao.changeReviewStatus(id,status);
		
		if(review!=null) {
			return new ResponseEntity<>(review, HttpStatus.OK);
		}
		else
			return new ResponseEntity<>("Review not updated", HttpStatus.NOT_FOUND);
		
   }
	
}
