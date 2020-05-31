package com.ingsw.restservice.controller;

import java.util.List;

import com.ingsw.restservice.model.*;
import com.ingsw.restservice.model.DTO.JsonPageResponse;
import com.ingsw.restservice.model.DTO.JsonResponse;
import com.ingsw.restservice.model.DTO.ReviewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController	
public class ReviewController {

	@Autowired
	@Qualifier("reviewDaoSql")
	private ReviewDao reviewDao;

	@RequestMapping( method=RequestMethod.GET ,value = "/review", params = {"accommodationId","page"} )
	@ResponseBody
	public ResponseEntity<Object> getReviews(@RequestParam int accommodationId, int page) {
		JsonPageResponse<Review> reviewList=reviewDao.getReviewList(accommodationId,page);
		if(reviewList!=null)
			return new ResponseEntity<>(reviewList, HttpStatus.OK);
		else
			return new ResponseEntity<>(new JsonResponse(false,"Recensioni non trovate"), HttpStatus.BAD_REQUEST);

	}

	@RequestMapping(method = RequestMethod.POST, value="/review/create")
	@ResponseBody
	public ResponseEntity<Object> createReview(@RequestBody Review review) {
		Review newReview=reviewDao.createReview(review);
		return  new ResponseEntity<>(newReview, HttpStatus.CREATED) ;
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




    /*@RequestMapping( method=RequestMethod.GET, value = "/review", params = {"userId","pagenumber"} )
    @ResponseBody
    public ResponseEntity<Object> getReviewUserByUser(@RequestParam int userId,int pagenumber) {
        JsonPageResponse<ReviewUser> reviewUserList=reviewDao.getReviewUserByUser(userId,pagenumber);
        if(reviewUserList!=null)
            return new ResponseEntity<>(reviewUserList, HttpStatus.OK);
        else
            return new ResponseEntity<>(new JsonResponse(false,"Recensioni non trovate"), HttpStatus.BAD_REQUEST);

    }*/

}
