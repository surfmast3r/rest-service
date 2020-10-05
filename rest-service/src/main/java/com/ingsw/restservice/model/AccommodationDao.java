package com.ingsw.restservice.model;
import com.ingsw.restservice.model.DTO.JsonPageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface AccommodationDao {

	Accommodation getAccommodationById(long id);
	Accommodation createAccommodation(Accommodation accommodation);
	boolean editAccommodation(Accommodation accommodation);
	boolean deleteAccommodation(long accommodationId);
	JsonPageResponse<Accommodation> getAccommodations(SearchParamsAccommodation params, Sort.Direction direction);
	JsonPageResponse<Accommodation> getAccommodationByCityPageable(String city, int page);

}
