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
	public void createAccommodation(Accommodation accommodation) {
		repository.createAccommodation( accommodation.getId(),accommodation.getDescription(),
										accommodation.getName(),
										accommodation.getLogoUrl(),accommodation.getLatitude(),
										accommodation.getLongitude(),
										accommodation.getCity(),accommodation.getAddress(),
										accommodation.getRating(),accommodation.getCategory(),
										accommodation.getSubCategory(),accommodation.getImages());
		}


	@Override
	public void editAccommodation(Accommodation accommodation) {
			repository.editAccommodation( accommodation.getId(),accommodation.getDescription(),
													accommodation.getName(),
													accommodation.getLogoUrl(),accommodation.getLatitude(),
													accommodation.getLongitude(),
													accommodation.getCity(),accommodation.getAddress(),
													accommodation.getCategory(),
													accommodation.getSubCategory(),accommodation.getImages());

	}


	@Override
	public void deleteAccommodation(int accommodationId) {
		repository.deleteAccommodationById(accommodationId);
	}


}
