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
	public JsonPageResponse getAccommodationByCityPageable(String city, int pageNumber) {
		Page<Accommodation> page=repository.findAllAccommodationByCityPageable(city,PageRequest.of(pageNumber,50));
		return createJsonPageResponse(page );
	}
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
	public JsonPageResponse<Accommodation> getAccommodations(String query, String category, String subCategory, int pageNumber, String orderBy, Sort.Direction direction) {
		Page<Accommodation> page=null;
		System.out.println("ECCOLI:"+orderBy+direction);
		if(!subCategory.equals(""))
		{
			if(query.length()>3)
				page=repository.findAccommodationByGenericAndSubCategory(query,subCategory,PageRequest.of(pageNumber,10,Sort.by(direction, orderBy)));
			else
				page=repository.findAccommodationBySubCategory(subCategory,PageRequest.of(pageNumber,10,Sort.by(direction, orderBy)));
		}
		else
			if (!category.equals(""))
			{
				if (query.length() > 3)
					page = repository.findAccommodationByGenericAndCategory(query, category, PageRequest.of(pageNumber, 10, Sort.by(direction, orderBy)));
				else
					page = repository.findAccommodationByCategory(category, PageRequest.of(pageNumber, 10, Sort.by(direction, orderBy)));
			}
			else
				page = repository.findAccommodationByGeneric(query, PageRequest.of(pageNumber, 10, Sort.by(direction, orderBy)));

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

