package com.ingsw.restservice.repository;

import java.util.List;

import javax.transaction.Transactional;

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


	@Query("SELECT a " +
			"FROM Accommodation a " +
			"ORDER BY a.rating DESC")
	List<Accommodation> findAccommodationOrderByRating(Pageable limit);


	@Query(" SELECT a" +
			" FROM Accommodation a" +
			" WHERE a.category=:category" +
			" ORDER BY a.id DESC")
	List<Accommodation>findAccommodationByCategory(@Param("category") String category);

	@Query(" SELECT a" +
			" FROM Accommodation a" +
			" WHERE a.subCategory=:subcategory" +
			" ORDER BY a.id DESC")
	List<Accommodation>findAccommodationBySubCategory(@Param("subcategory") String subcategory);


	@Query("SELECT accommodation FROM Accommodation accommodation WHERE accommodation.city = ?1")
	List<Accommodation> findAllAccommodationByCity(String city);

	@Query("SELECT accommodation FROM Accommodation accommodation WHERE accommodation.id = ?1")
	Accommodation findAccommodationById(long id);

	@Query("SELECT accommodation FROM Accommodation accommodation WHERE accommodation.name LIKE CONCAT('%',:name,'%')")
	List<Accommodation> findAccommodationByName(@Param("name") String name);

	@Query("  SELECT accommodation " +
			" FROM Accommodation accommodation " +
			" WHERE accommodation.name LIKE CONCAT('%',:generic,'%') OR" +
			" 		accommodation.city LIKE CONCAT('%',:generic,'%') OR" +
			"       accommodation.description LIKE CONCAT('%',:generic,'%') " +
			" ORDER BY accommodation.id DESC ")
	List<Accommodation> findAccommodationByGeneric(@Param("generic") String generic);

	@Query("  SELECT accommodation " +
			" FROM Accommodation accommodation " +
			" WHERE (accommodation.name LIKE CONCAT('%',:generic,'%') OR" +
			" 		accommodation.city LIKE CONCAT('%',:generic,'%') OR" +
			"       accommodation.description LIKE CONCAT('%',:generic,'%') )AND" +
			"				accommodation.category=:category  " +
			" ORDER BY accommodation.id DESC ")
	List<Accommodation> findAccommodationByGenericAndCategory(@Param("generic")String generic,@Param("category")String category);

	@Query("  SELECT accommodation " +
			" FROM Accommodation accommodation " +
			" WHERE (accommodation.name LIKE CONCAT('%',:generic,'%') OR" +
			" 		accommodation.city LIKE CONCAT('%',:generic,'%') OR" +
			"       accommodation.description LIKE CONCAT('%',:generic,'%') )AND" +
			"				accommodation.subCategory=:subcategory  " +
			" ORDER BY accommodation.id DESC ")
	List<Accommodation> findAccommodationByGenericAndSubCategory(@Param("generic")String generic,@Param("subcategory")String subcategory);
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