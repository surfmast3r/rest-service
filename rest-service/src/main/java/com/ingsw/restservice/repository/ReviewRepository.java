package com.ingsw.restservice.repository;

import com.ingsw.restservice.model.Review;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Review r WHERE r.id=:idReview")
    int deleteReviewById(@Param("idReview")long reviewId);

    @Transactional
    @Modifying//(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE Review r SET r.stato=:status WHERE r.id=:idReview")
    int changeStatus (@Param("idReview") long idReview,@Param("status") String status );

    @Query("SELECT r FROM Review r WHERE r.id=:idReview")
    Review findReviewById(@Param ("idReview") long idReview);


}
