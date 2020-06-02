package com.ingsw.restservice.repository;

import com.ingsw.restservice.model.ReviewView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ReviewViewRepository extends CrudRepository<ReviewView, Long> {

    @Query(" SELECT a FROM ReviewView a WHERE a.accommodationId=:idAccommodation ORDER BY a.rating DESC")
    Page<ReviewView> findReviewByUser(@Param("idAccommodation") long idAccommodation, Pageable limit);

    @Query(" SELECT a FROM ReviewView a WHERE a.id=:idReview")
    ReviewView findReviewByUser(@Param("idReview") long idReview);



}
