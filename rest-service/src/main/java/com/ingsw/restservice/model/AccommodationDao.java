package com.ingsw.restservice.model;

import java.util.List;

public interface AccommodationDao {

	List<Accommodation> findAll();
	List<Accommodation> getAccommodationByCity(String city);
	Accommodation getAccommodationById(long id);
	List<Accommodation> getAccommodationByName(String name);
	Accommodation createAccommodation(Accommodation accommodation);
	boolean editAccommodation(Accommodation accommodation);
	boolean deleteAccommodation(long accommodationId);
	List<Accommodation> getAccommodationOrderByRating(int limit);
	List<Accommodation> getAccommodationByCategory(String category);
	List<Accommodation> getAccommodationBySubCategory(String subcategory);
	List<Accommodation> getAccommodationByGeneric(String generic);
	List<Accommodation> getAccommodationByGenericAndCategory(String generic,String category);
	List<Accommodation> getAccommodationByGenericAndSubCategory(String generic,String subcategory);
}
