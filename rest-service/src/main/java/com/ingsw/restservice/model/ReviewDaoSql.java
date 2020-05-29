package com.ingsw.restservice.model;

import com.ingsw.restservice.model.DTO.JsonPageResponse;
import com.ingsw.restservice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ReviewDaoSql implements ReviewDao {

    @Autowired
    private ReviewRepository repository;

    @Override public Review createReview(Review review) {
        System.out.println(review.getContent());
        return repository.save(review);
    }
    @Override public boolean deleteReview(long reviewId) {
        int response=repository.deleteReviewById(reviewId);
        return response>0;
    }
    @Override public JsonPageResponse<Review> getReviewList(int accommodationId, int pageNumber) {
        Page<Review> page = repository.findReviewByAccommodation(accommodationId, PageRequest.of(pageNumber, 10));
        return createJsonPageResponse(page );
    }
    @Override public Review getReviewById(int id) {
        return repository.findReviewById(id);
    }
    @Override public Review changeReviewStatus(int id, String status) {
        return repository.changeStatus(id,status);
    }


    private JsonPageResponse<Review> createJsonPageResponse(Page<Review> page){
        JsonPageResponse<Review> jsonPageResponse= new JsonPageResponse<>();
        jsonPageResponse.setTotalPages(page.getTotalPages());
        jsonPageResponse.setContent(page.getContent());
        jsonPageResponse.setPage(page.getNumber());
        jsonPageResponse.setOffset(page.getPageable().getOffset());
        jsonPageResponse.setPageSize(page.getSize());
        jsonPageResponse.setTotalElements(page.getTotalElements());
        return jsonPageResponse;

    }

}
