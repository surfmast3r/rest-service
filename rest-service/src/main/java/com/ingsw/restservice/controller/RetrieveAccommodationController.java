package com.ingsw.restservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ingsw.restservice.model.*;

@RestController
public class RetrieveAccommodationController {

	@Autowired
	@Qualifier("accommodationDaoStub")
	private AccommodationDao acDao;
	
	
	@RequestMapping(method = RequestMethod.GET, value="/accommodation",params = "city")
	@ResponseBody	
	public List<Accommodation> getAccommodations(@RequestParam(defaultValue = "all") String city) {
	
		//acDao = new AccommodationDaoSql();
		if (city.equals("all"))
			return acDao.findAll();
		else
			return acDao.getAccommodationByCity(city);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/accommodation",params ="accommodationId")
	@ResponseBody	
	public Accommodation getAccommodationById(@RequestParam int accommodationId) {
	
		//acDao = new AccommodationDaoSql();
	
		return acDao.getAccommodationById(accommodationId);
	}
	
}
