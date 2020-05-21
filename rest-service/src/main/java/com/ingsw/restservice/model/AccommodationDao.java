package com.ingsw.restservice.model;

import java.util.List;

public interface AccommodationDao {

	public List<Accommodation> findAll();
	public List<Accommodation> getAccommodationByCity(String city);
	public Accommodation getAccommodationById(long id);
	public List<Accommodation> getAccommodationByName(String name);
	public void createAccommodation(Accommodation accommodation);
	public void editAccommodation(Accommodation accommodation);
	public void deleteAccommodation(int accommodationId);
}
