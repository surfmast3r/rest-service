package com.ingsw.restservice.model;
import com.ingsw.restservice.model.DTO.JsonPageResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AccommodationDao {

	Accommodation getAccommodationById(long id);
	Accommodation createAccommodation(Accommodation accommodation);
	boolean editAccommodation(Accommodation accommodation);
	boolean deleteAccommodation(long accommodationId);
	JsonPageResponse<Accommodation> getAccommodations(String query,String category,String subCategory,int page);
	JsonPageResponse<Accommodation> getAccommodationByCityPageable(String city, int page);

}
