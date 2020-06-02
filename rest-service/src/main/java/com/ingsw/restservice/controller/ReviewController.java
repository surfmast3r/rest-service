package com.ingsw.restservice.controller;

import com.ingsw.restservice.model.*;
import com.ingsw.restservice.model.DTO.JsonPageResponse;
import com.ingsw.restservice.model.DTO.JsonResponse;
import com.ingsw.restservice.model.ReviewView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@RestController	
public class ReviewController {

	@Autowired
	@Qualifier("reviewDaoSql")
	private ReviewDao reviewDao;

	@RequestMapping(method = RequestMethod.POST, value="/review/create")
	@ResponseBody
	public ResponseEntity<Object> createReview(@RequestBody Review review) {
            Review newReview=reviewDao.createReview(review);
		return  new ResponseEntity<>(newReview, HttpStatus.CREATED) ;
	}

    @RequestMapping(value = "/review/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Object> delete(@PathVariable("id") long id) {
        if(reviewDao.deleteReview(id)) {
            return new ResponseEntity<>("Review is deleted", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
    }

	@GetMapping(value = "/review", params = "reviewId")
	public Review getReviewById(@RequestParam int reviewId) {
		
		return reviewDao.getReviewById(reviewId);
		
	}

	@RequestMapping(value = "/review/edit/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> changeReviewStatus(@PathVariable("id") int id, @RequestParam("status")String status) { 
      	
		ReviewView review=reviewDao.changeReviewStatus(id,status);
		if(review!=null) {
			return new ResponseEntity<>(review, HttpStatus.OK);
		}
		else
			return new ResponseEntity<>("Review not updated", HttpStatus.NOT_FOUND);
		
   }



    @RequestMapping( method=RequestMethod.GET, value = "/review_view", params = {"accommodationId","page"} )
    @ResponseBody
    public ResponseEntity<Object> getReviewUserByAccommodation(@RequestParam int accommodationId,int page) {
        JsonPageResponse<ReviewView> reviewUserList=reviewDao.getReviewUserByAccommodation(accommodationId,page);
        if(reviewUserList!=null)
            return new ResponseEntity<>(reviewUserList, HttpStatus.OK);
        else
            return new ResponseEntity<>(new JsonResponse(false,"Recensioni non trovate"), HttpStatus.BAD_REQUEST);

    }

    @GetMapping(value = "/review_view", params = "reviewId")
    public ReviewView getReviewViewById(@RequestParam int reviewId) {

        return reviewDao.getReviewView(reviewId);

    }

}
