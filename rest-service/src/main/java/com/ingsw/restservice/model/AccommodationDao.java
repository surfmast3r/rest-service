package com.ingsw.restservice.model;

import java.util.List;

public interface AccommodationDao {

	public List<Accommodation> findAll();
	public List<Accommodation> getAccommodationByCity(String city);
	public Accommodation getAccommodationById(long id);
	public List<Accommodation> getAccommodationByName(String name);
	public Accommodation createAccommodation(Accommodation accommodation);
	public boolean editAccommodation(Accommodation accommodation);
	public boolean deleteAccommodation(int accommodationId);
}
