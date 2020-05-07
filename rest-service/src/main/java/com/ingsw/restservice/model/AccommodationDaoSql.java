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
	public List getAccommodationByCity(String city) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Accommodation getAccommodationById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void createAccommodation(Accommodation accommodation) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean editAccommodation(int accommodationId, Accommodation accommodation) {
		return false;
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean deleteAccommodation(int accommodationId) {
		// TODO Auto-generated method stub
		return false;
	}


}
