package com.ingsw.restservice.model;

import java.util.List;

public interface AccommodationDao {

	public List<Accommodation> findAll();
	public List getAccommodationByCity(String city);
	public Accommodation getAccommodationById(int id);
}
