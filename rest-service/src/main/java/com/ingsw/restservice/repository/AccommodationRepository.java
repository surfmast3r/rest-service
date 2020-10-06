package com.ingsw.restservice.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ingsw.restservice.model.Accommodation;


@Repository
public interface AccommodationRepository extends CrudRepository<Accommodation, Long>{

	@Transactional
	@Modifying
	@Query("DELETE FROM Accommodation a WHERE a.id=?1")
	int deleteAccommodationById(long accommodationId);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Accommodation a 	SET  a.description=:description," +
										"a.name=:name," +
										"a.logourl=:logourl," +
										"a.latitude=:latitude," +
										"a.longitude=:longitude," +
										"a.city=:city," +
										"a.address=:address," +
										"a.category=:category," +
										"a.subCategory=:subCategory," +
										"a.images=:images " +
									"WHERE a.id=:id")
	int editAccommodation(	@Param("id") long id,
					 @Param("description") String description,
					 @Param("name") String name,
					 @Param("logourl") String logourl,
					 @Param("latitude") Double latitude,
					 @Param("longitude") Double longitude,
					 @Param("city") String city,
					 @Param("address") String address,
					 @Param("category") String category,
					 @Param("subCategory") String subCategory,
					 @Param("images") String images);




	@Query("SELECT accommodation FROM Accommodation accommodation WHERE accommodation.id = ?1")
	Accommodation findAccommodationById(long id);


	@Query(" SELECT accommodation FROM Accommodation accommodation WHERE (" +
			"(accommodation.name LIKE CONCAT('%',:generic,'%') OR :generic is null)" +
			"AND (accommodation.description LIKE CONCAT('%',:generic,'%') OR :generic is null)" +
			"AND (accommodation.city LIKE CONCAT('%',:generic,'%') OR :generic is null)" +
			"AND (accommodation.category=:category OR :category is null)" +
			"AND (accommodation.subCategory=:subCategory OR :subCategory is null)"+
			"AND (accommodation.rating>=:minRating AND accommodation.rating <= :maxRating)"+
			"AND (((POWER((:latitude - accommodation.latitude),2) + POWER((:longitude - accommodation.longitude),2)) <  0.13) " +
			"OR (:latitude =-200 AND :longitude =-200) ))")
	Page<Accommodation> findAccommodationBySearchParams(
			@Param("generic")String generic,
			@Param("category")String category,
			@Param("subCategory")String subCategory,
			@Param("latitude")Double latitude,
			@Param("longitude")Double longitude,
			@Param("minRating")float minRating,
			@Param("maxRating")float maxRating,
			Pageable limit
	);

}




/*@Query("INSERT INTO Accommodation (id,description,name,logourl,latitude,longitude,city,address,rating,category,subCategory,images)" +
			" VALUES(:id,:description,:name,:logourl,:latitude,:longitude,:city,:address,:rating,:category,:subCategory,:images) ")
	void createAccommodation(	@Param("id") long id,
								@Param("description") String description,
								@Param("name") String name,
								@Param("logourl") String logourl,
								@Param("latitude") Double latitude,
								@Param("longitude") Double longitude,
								@Param("city") String city,
								@Param("address") String address,
								@Param("rating") Float rating,
								@Param("category") String category,
								@Param("subCategory") String subCategory,
								@Param("images") String images);

	 */