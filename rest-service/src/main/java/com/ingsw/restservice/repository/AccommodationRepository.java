package com.ingsw.restservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ingsw.restservice.model.Accommodation;


@Repository
public interface AccommodationRepository extends CrudRepository<Accommodation, Long>{

	@Query("SELECT accommodation FROM Accommodation accommodation WHERE accommodation.city = ?1")
	List<Accommodation> findAllAccommodationByCity(String city);
	
	@Query("SELECT accommodation FROM Accommodation accommodation WHERE accommodation.id = ?1")
	Accommodation findAccommodationById(long id);
}
