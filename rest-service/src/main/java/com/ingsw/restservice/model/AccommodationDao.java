package com.ingsw.restservice.model;

import java.awt.print.Pageable;
import java.util.List;

public interface AccommodationDao {

	public List<Accommodation> findAll();
	public List<Accommodation> getAccommodationByCity(String city);
	public Accommodation getAccommodationById(long id);
	public List<Accommodation> getAccommodationByName(String name);
	public void createAccommodation(Accommodation accommodation);
	public void editAccommodation(Accommodation accommodation);
	public void deleteAccommodation(int accommodationId);

	// Si richiama usando (new PageRequest(0,x)) per recuperare solo i primi x record (SPERO) :
	public List<Accommodation> findAccommodationOrderByRating(Pageable limit);


}
