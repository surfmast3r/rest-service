package com.ingsw.restservice.model;

import com.ingsw.restservice.model.DTO.JsonPageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ingsw.restservice.repository.AccommodationRepository;

import java.util.List;

@Service
public class AccommodationDaoSql implements AccommodationDao {

	private static final int PAGE_SIZE =10;
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

		if(params.getCurrentName().length()<3){
			params.setCurrentName(null);
		}

		if (params.getCurrentCity().length()==0){
			params.setCurrentCity(null);
		}
		if (params.getCurrentCategory().length()==0){
			params.setCurrentCategory(null);
		}
		if (params.getCurrentSubCategory().length()==0){
			params.setCurrentSubCategory(null);
		}
		System.out.println("QUERY STRING "+params.getCurrentName());
		page= repository.findAccommodationBySearchParams(
				params.getCurrentName(),
				params.getCurrentDescription(),
				params.getCurrentCity(),
				params.getCurrentCategory(),
				params.getCurrentSubCategory(),
				params.getLatitude(),
				params.getLongitude(),
				params.getMinRating(),
				params.getMaxRating(),
				PageRequest.of(params.getCurrentPage(), PAGE_SIZE, Sort.by(direction, params.getOrderBy())));

		return createJsonPageResponse(page );



	}
	@Override
	public JsonPageResponse<Accommodation> getAccommodationByGenericString(SearchParamsAccommodation params, Sort.Direction direction) {

		if (params.getCurrentCategory().length()==0){
			params.setCurrentCategory(null);
		}
		if (params.getCurrentSubCategory().length()==0){
			params.setCurrentSubCategory(null);
		}
		Page<Accommodation> page=repository.findAccommodationByGenericString(
				params.getCurrentDescription(),
				params.getCurrentCategory(),
				params.getCurrentSubCategory(),
				PageRequest.of(params.getCurrentPage(),PAGE_SIZE, Sort.by(direction, params.getOrderBy())));
		return createJsonPageResponse(page );
	}

	@Override
	public List<Accommodation> getAccommodationByCoordinates(String category, Double latitude, Double longitude) {

		if (category.length()==0){
			category=null;
		}
		List<Accommodation> list=repository.findAccommodationByCoordinates(category, latitude, longitude);
		return list;
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

