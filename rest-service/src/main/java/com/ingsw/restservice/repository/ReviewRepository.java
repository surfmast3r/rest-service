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
    @Modifying//(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE FROM Review r WHERE r.id=?1")
    int deleteReviewById(long reviewId);

    @Query("SELECT r " +
            "FROM Review r " +
            "WHERE r.idAccommodation=:idAccommodation "+
            "ORDER BY r.rating DESC")
    Page<Review> findReviewByAccommodation(@Param("idAccommodation") long idAccommodation, Pageable limit);

    @Transactional
    @Modifying//(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE Review r SET r.stato=:status WHERE r.id=:idReview")
    Review changeStatus (@Param("idReview") long idReview,@Param("status") String status );

    @Query("SELECT r FROM Review r WHERE r.id=:idReview")
    Review findReviewById(@Param ("idReview") long idReview);


    @Query("SELECT "+
    "FROM ( 	SELECT u.nickname as username, r.id as id , r.content, r.rating, r.idAccommodation, u.creation_date " +
    "           FROM USERS u JOIN Review r ON u.IdUser=r.IdUser "+
    "           WHERE u.showNickname=true) UNION " +
    "         ( SELECT u.name ,r.id , r.content, r.rating, r.idAccommodation, u.creation_date "+
    "           FROM USERS u JOIN Review r ON u.IdUser=r.IdUser "+
    "           WHERE u.showNickname==false) "+
    "           ORDER BY id ")
    Page<Review> findReviewUsers();

}
