package com.ingsw.restservice.model;

import java.util.List;

public class ReviewDaoSql implements ReviewDao {
    @Override
    public List<Review> getReviewList(int accommodationId) {
        return null;
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
