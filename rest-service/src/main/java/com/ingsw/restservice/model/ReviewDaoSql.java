package com.ingsw.restservice.model;

import com.ingsw.restservice.model.DTO.JsonPageResponse;
import com.ingsw.restservice.repository.ReviewRepository;
import com.ingsw.restservice.repository.ReviewViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ReviewDaoSql implements ReviewDao {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewViewRepository reviewViewRepository;

    @Override public Review createReview(Review review) {
        return reviewRepository.save(review);
    }
    @Override public boolean deleteReview(long reviewId) {
        int response= reviewRepository.deleteReviewById(reviewId);
        return response>0;
    }
    @Override public Review getReviewById(int id) {
        return reviewRepository.findReviewById(id);
    }
    @Override public ReviewView changeReviewStatus(int id, String status) {
        int response=reviewRepository.changeStatus(id,status);
        if(response>0)  return getReviewView(id);
        else            return  null;
    }


    @Override public JsonPageResponse<ReviewView> getReviewUserByAccommodation(int accomodationId, int pageNumber) {
        Page<ReviewView> page = reviewViewRepository.findReviewByUser(accomodationId, PageRequest.of(pageNumber, 10));
        return createJsonPageResponse(page );
    }
    @Override public ReviewView getReviewView(long id) {
        return reviewViewRepository.findReviewByUser(id);
    }


    private <T> JsonPageResponse<T> createJsonPageResponse(Page<T> page){
        JsonPageResponse<T> jsonPageResponse= new JsonPageResponse<>();
        jsonPageResponse.setTotalPages(page.getTotalPages());
        jsonPageResponse.setContent(page.getContent());
        jsonPageResponse.setPage(page.getNumber());
        jsonPageResponse.setOffset(page.getPageable().getOffset());
        jsonPageResponse.setPageSize(page.getSize());
        jsonPageResponse.setTotalElements(page.getTotalElements());
        return jsonPageResponse;
    }

}
