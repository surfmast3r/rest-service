package com.ingsw.restservice.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ingsw.restservice.repository.AccommodationRepository;

@Service
public class AccommodationDaoSql implements AccommodationDao {
	
	@Autowired
    private AccommodationRepository repository;

	@Override
	public List<Accommodation> findAll() {
		return (List<Accommodation>) repository.findAll();
	}


	@Override
	public List<Accommodation> getAccommodationByCity(String city, int page) {
		return repository.findAllAccommodationByCity(city,PageRequest.of(page,50));
	}


	@Override
	public Accommodation getAccommodationById(long id) {
		return repository.findAccommodationById(id);
	}

	@Override
	public List<Accommodation> getAccommodationByName(String name, int page) {
		return repository.findAccommodationByName(name,PageRequest.of(page,50));
	}


	@Override
	public Accommodation createAccommodation(Accommodation accommodation) {
		/*
		repository.createAccommodation( accommodation.getId(),accommodation.getDescription(),
										accommodation.getName(),
										accommodation.getLogoUrl(),accommodation.getLatitude(),
										accommodation.getLongitude(),
										accommodation.getCity(),accommodation.getAddress(),
										accommodation.getRating(),accommodation.getCategory(),
										accommodation.getSubCategory(),accommodation.getImages());*/
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
		if(response>0) {
			return true;
		}
		return false;
		
	}

	@Override
	public List<Accommodation> getAccommodationOrderByRating(int page) {
		return repository.findAccommodationOrderByRating(PageRequest.of(page,50));
	}

	@Override
	public List<Accommodation> getAccommodations(String query, String category, String subCategory, int page) {
		if(!subCategory.equals("")){
			if(query.length()>3)
				return	repository.findAccommodationByGenericAndSubCategory(query,subCategory,PageRequest.of(page,50));
			else
				return	repository.findAccommodationBySubCategory(subCategory,PageRequest.of(page,50));
		}
		else if(!category.equals("")){
			if(query.length()>3)
				return	repository.findAccommodationByGenericAndCategory(query,category,PageRequest.of(page,50));
			else
				return repository.findAccommodationByCategory(category,PageRequest.of(page,50));

		}

		return repository.findAccommodationByGeneric(query,PageRequest.of(page,50));

	}

	@Override
	public List<Accommodation> getAccommodationBySubCategory(String subcategory, int page) {
		return repository.findAccommodationBySubCategory(subcategory,PageRequest.of(page,50));
	}

	@Override
	public List<Accommodation> getAccommodationByGeneric(String generic, int page) {
		return repository.findAccommodationByGeneric(generic,PageRequest.of(page,50));
	}

	@Override
	public List<Accommodation> getAccommodationByGenericAndCategory(String generic, String category, int page) {
		return repository.findAccommodationByGenericAndCategory(generic,category,PageRequest.of(page,50));
	}

	@Override
	public List<Accommodation> getAccommodationByGenericAndSubCategory(String generic, String subcategory,int page) {
		return repository.findAccommodationByGenericAndSubCategory(generic,subcategory,PageRequest.of(page,50));
	}


}
