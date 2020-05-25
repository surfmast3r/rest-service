package com.ingsw.restservice.model;

import java.util.List;

public interface AccommodationDao {

	List<Accommodation> findAll();
	List<Accommodation> getAccommodationByCity(String city,int page);
	Accommodation getAccommodationById(long id);
	List<Accommodation> getAccommodationByName(String name,int page);
	Accommodation createAccommodation(Accommodation accommodation);
	boolean editAccommodation(Accommodation accommodation);
	boolean deleteAccommodation(long accommodationId);
	List<Accommodation> getAccommodationOrderByRating(int limit);
	List<Accommodation> getAccommodations(String query,String category,String subCategory,int page);
	List<Accommodation> getAccommodationBySubCategory(String subcategory,int page);
	List<Accommodation> getAccommodationByGeneric(String generic,int page);
	List<Accommodation> getAccommodationByGenericAndCategory(String generic,String category,int page);
	List<Accommodation> getAccommodationByGenericAndSubCategory(String generic,String subcategory,int page);
}
