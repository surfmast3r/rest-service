package com.ingsw.restservice.repository;

import com.ingsw.restservice.model.ReviewView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ReviewViewRepository extends CrudRepository<ReviewView, Long> {


    @Query(" SELECT a FROM ReviewView a WHERE a.id=:idReview")
    ReviewView findReviewById(@Param("idReview") long idReview);

    @Query(" SELECT a FROM ReviewView a WHERE ((a.id=:reviewId OR :reviewId=-1)" +
            "AND (a.accommodationId=:accommodationId OR :accommodationId=-1)" +
            "AND (a.accommodationName LIKE CONCAT('%',:accommodationName ,'%') OR :accommodationName is null)" +
            "AND (a.content LIKE CONCAT('%',:content ,'%') OR :content is null)" +
            "AND (a.stato=:stato OR :stato is null))")
    Page<ReviewView> findReviewBySearchParams(
            @Param("reviewId") long reviewId,
            @Param("accommodationId") long accommodationId,
            @Param("accommodationName") String accommodationName,
            @Param("content") String content,
            @Param("stato") String status,
            Pageable limit
    );



}
