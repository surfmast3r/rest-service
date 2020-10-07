package com.ingsw.restservice.model;

import java.util.List;

import com.ingsw.restservice.model.DTO.JsonPageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ingsw.restservice.repository.AccommodationRepository;

@Service
public class AccommodationDaoSql implements AccommodationDao {
	
	@Autowired
    private AccommodationRepository repository;
	@Override
	public Accommodation getAccommodationById(long id) {
		return repository.findAccommodationById(id);
	}
	@Override
	public Accommodation createAccommodation(Accommodation accommodation) {
		 return repository.save(accommodation);
		}
	@Override
	public boolean editAccommodation(Accommodation accommodation) {
			int response=repository.editAccommodation( accommodation.getId(),accommodation.getDescription(),
													accommodation.getName(),
													accommodation.getLogoUrl(),accommodation.getLatitude(),
													accommodation.getLongitude(),
													accommodation.getCity(),accommodation.getAddress(),
													accommodation.getCategory(),
													accommodation.getSubCategory(),accommodation.getImages());
			System.out.print("response: "+response);
			if(response>0) {
				return true;
			}
			return false;
			

	}
	@Override
	public boolean deleteAccommodation(long accommodationId) {
		int response=repository.deleteAccommodationById(accommodationId);
		return response > 0;
	}
	@Override
	public JsonPageResponse<Accommodation> getAccommodations(SearchParamsAccommodation params, Sort.Direction direction) {
		Page<Accommodation> page;

		if(params.getCurrentSearchString().length()<3){
			params.setCurrentSearchString(null);
		}

		if (params.getCurrentCategory().length()==0){
			params.setCurrentCategory(null);
		}
		if (params.getCurrentSubCategory().length()==0){
			params.setCurrentSubCategory(null);
		}
		page= repository.findAccommodationBySearchParams(params.getCurrentSearchString(),
				params.getCurrentCategory(),
				params.getCurrentSubCategory(),
				params.getLatitude(),
				params.getLongitude(),
				params.getMinRating(),
				params.getMaxRating(),
				PageRequest.of(params.getCurrentPage(), 10, Sort.by(direction, params.getOrderBy())));

		return createJsonPageResponse(page );



	}

	@Override
	public JsonPageResponse getAccommodationByCityPageable(String city, int pageNumber) {
		Page<Accommodation> page=repository.findAllAccommodationByCityPageable(city,PageRequest.of(pageNumber,50));
		return createJsonPageResponse(page );
	}

	private JsonPageResponse<Accommodation> createJsonPageResponse(Page<Accommodation> page){
		JsonPageResponse<Accommodation> jsonPageResponse= new JsonPageResponse<>();
		jsonPageResponse.setTotalPages(page.getTotalPages());
		jsonPageResponse.setContent(page.getContent());
		jsonPageResponse.setPage(page.getNumber());
		jsonPageResponse.setOffset(page.getPageable().getOffset());
		jsonPageResponse.setPageSize(page.getSize());
		jsonPageResponse.setTotalElements(page.getTotalElements());
		return jsonPageResponse;

	}


}

