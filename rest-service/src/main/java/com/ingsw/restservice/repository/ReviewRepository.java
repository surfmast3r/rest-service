package com.ingsw.restservice.repository;

import com.ingsw.restservice.model.Accommodation;
import com.ingsw.restservice.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Review r WHERE r.id=?1")
    int deleteReviewById(long reviewId);

    @Query("SELECT r " +
            "FROM Review r " +
            "WHERE r.accommodationId=:idAccommodation "+
            "ORDER BY r.rating DESC")
    Page<Review> findReviewByAccommodation(@Param("idAccommodation") int idAccommodation, Pageable limit);

}
