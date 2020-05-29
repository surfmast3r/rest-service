package com.ingsw.restservice.controller;

import java.util.List;

import com.ingsw.restservice.model.*;
import com.ingsw.restservice.model.DTO.JsonPageResponse;
import com.ingsw.restservice.model.DTO.JsonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController	
public class ReviewController {

	private ReviewDao reviewDao= new ReviewDaoSql();
	
	@RequestMapping( method=RequestMethod.GET ,value = "/review", params = {"accommodationId","page"} )
	@ResponseBody
	public ResponseEntity<Object> getReviews(@RequestParam int accommodationId, int page) {
		JsonPageResponse<Review> reviewList=reviewDao.getReviewList(accommodationId,page);
		if(reviewList!=null)
			return new ResponseEntity<>(reviewList, HttpStatus.OK);
		else
			return new ResponseEntity<>(new JsonResponse(false,"Recensioni non trovate"), HttpStatus.BAD_REQUEST);

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
