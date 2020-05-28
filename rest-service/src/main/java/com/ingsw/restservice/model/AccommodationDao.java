package com.ingsw.restservice.model;
import com.ingsw.restservice.model.DTO.JsonPageResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AccommodationDao {

	List<Accommodation> findAll();
	Accommodation getAccommodationById(long id);
	Accommodation createAccommodation(Accommodation accommodation);
	boolean editAccommodation(Accommodation accommodation);
	boolean deleteAccommodation(long accommodationId);
	JsonPageResponse<Accommodation> getAccommodationOrderByRating(int limit);
	JsonPageResponse<Accommodation> getAccommodations(String query,String category,String subCategory,int page);

	public JsonPageResponse getAccommodationByCityPageable(String city, int page);
}
