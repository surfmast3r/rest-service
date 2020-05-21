package com.ingsw.restservice.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Accommodation> getAccommodationByCity(String city) {
		return repository.findAllAccommodationByCity(city);
	}


	@Override
	public Accommodation getAccommodationById(long id) {
		return repository.findAccommodationById(id);
	}

	@Override
	public List<Accommodation> getAccommodationByName(String name) {
		return repository.findAccommodationByName(name);
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
			if(response>0) {
				return true;
			}
			return false;
			

	}


	@Override
	public boolean deleteAccommodation(int accommodationId) {
		int response=repository.deleteAccommodationById(accommodationId);
		if(response>0) {
			return true;
		}
		return false;
		
	}


}
