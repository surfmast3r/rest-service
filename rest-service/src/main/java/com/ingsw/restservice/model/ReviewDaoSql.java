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

    private static final int PAGE_SIZE = 10;

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
        else            return null;
    }


    @Override public ReviewView getReviewView(long id) {
        return reviewViewRepository.findReviewById(id);
    }

    @Override
    public JsonPageResponse<ReviewView> getReviewView(long reviewId, long accommodationId, String accommodationName, String content, String status, int pageNumber) {
        Page<ReviewView> page = reviewViewRepository.findReviewBySearchParams(reviewId, accommodationId,accommodationName,content,status,PageRequest.of(pageNumber, PAGE_SIZE));
        return createJsonPageResponse(page);
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
