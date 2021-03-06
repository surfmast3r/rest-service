package com.ingsw.restservice.controller;

import com.ingsw.restservice.model.DTO.JsonPageResponse;
import com.ingsw.restservice.model.DTO.JsonResponse;
import com.ingsw.restservice.model.Review;
import com.ingsw.restservice.model.ReviewDao;
import com.ingsw.restservice.model.ReviewView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        if(reviewDao.deleteReview(id)) {
            return new ResponseEntity<>("Review is deleted", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
    }

	@GetMapping(value = "/review", params = "reviewId")
	public ResponseEntity<?> getReviewById(@RequestParam int reviewId) {

        Review review=reviewDao.getReviewById(reviewId);
        if(review!=null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
		
	}

	@RequestMapping(value = "/review/edit/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> changeReviewStatus(@PathVariable("id") int id, @RequestParam("status")String status) {
      	
		ReviewView review=reviewDao.changeReviewStatus(id,status);
		if(review!=null) {
			return new ResponseEntity<>(review, HttpStatus.OK);
		}
		else
			return new ResponseEntity<>("Review not updated", HttpStatus.NOT_FOUND);
		
   }



    @RequestMapping( method=RequestMethod.GET, value = "/review_view")
    @ResponseBody
    public ResponseEntity<Object> getReviewUserByAccommodation(@RequestParam(name="reviewId",required = false,defaultValue = "-1") long reviewId,
                                                                @RequestParam(name="accommodationId",required = false,defaultValue = "-1") long accommodationId,
                                                                @RequestParam(name="accommodationName",required = false) String accommodationName,
                                                                @RequestParam(name="content",required = false) String content,
                                                                @RequestParam(name="status",required = false) String status,
                                                                @RequestParam(name="page",required = false,defaultValue = "0") int page,
                                                                @RequestParam(name="orderBy", required = false, defaultValue = "id") String orderBy,
                                                                @RequestParam(name="direction", required = false, defaultValue = "DESC") String direction)
    {
        JsonPageResponse<ReviewView> reviewUserList;
        if(direction.equals("ASC"))
            reviewUserList=reviewDao.getReviewView(reviewId,accommodationId,accommodationName,content,status,page,orderBy, Sort.Direction.ASC);
        else
            reviewUserList=reviewDao.getReviewView(reviewId,accommodationId,accommodationName,content,status,page,orderBy, Sort.Direction.DESC);


        if(reviewUserList!=null)
        return new ResponseEntity<>(reviewUserList, HttpStatus.OK);
        else
        return new ResponseEntity<>(new JsonResponse(false,"Recensioni non trovate"), HttpStatus.BAD_REQUEST);

    }

    @GetMapping(value = "/single_review_view")
    public ReviewView getReviewViewById(@RequestParam(name="reviewId") int reviewId) {

        return reviewDao.getReviewView(reviewId);

    }

}
