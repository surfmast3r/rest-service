package com.ingsw.restservice.model;

import com.ingsw.restservice.model.DTO.JsonPageResponse;
import com.ingsw.restservice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.graalvm.compiler.asm.sparc.SPARCAssembler.Fcn.Page;

public class ReviewDaoSql implements ReviewDao {

    @Autowired
    private ReviewRepository repository;

    @Override
    public boolean deleteReview(long reviewId) {
        int response=repository.deleteReviewById(reviewId);
        return response>0;
    }

    @Override
    public JsonPageResponse<Review> getReviewList(int accommodationId, int pageNumber) {
        return (JsonPageResponse<Review>) repository.findReviewByAccommodation(accommodationId, PageRequest.of(pageNumber,10));
    }


    @Override
    public Review getReviewById(int id) {
        return null;
    }
    @Override
    public Review changeReviewStatus(int id, String status) {
        return null;
    }
}
