package com.ingsw.restservice.model;

import java.util.List;

public interface AccommodationDao {

	public List getAllAccommodation();
	public List getAccommodationByCity(String city);
}
