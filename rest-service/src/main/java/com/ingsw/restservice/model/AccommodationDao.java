package com.ingsw.restservice.model;

import java.util.List;

public interface AccommodationDao {

	public List<Accommodation> findAll();
	public List<Accommodation> getAccommodationByCity(String city);
	public Accommodation getAccommodationById(long id);
	public void createAccommodation(Accommodation accommodation);
	public boolean editAccommodation(int accommodationId, Accommodation accommodation);
	public boolean deleteAccommodation(int accommodationId);
}
