package com.ingsw.restservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ingsw.restservice.model.*;

@RestController
public class AccommodationController {

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
	
	@RequestMapping(method = RequestMethod.POST, value="/accommodation/create")
	@ResponseBody	
	public ResponseEntity<Object> createAccommodation(@RequestBody Accommodation accommodation) {
	
		acDao.createAccommodation(accommodation);
	
		return  new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/accommodation/edit/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProduct(@PathVariable("id") int id, @RequestBody Accommodation accommodation) { 
      
		boolean response=acDao.editAccommodation(id,accommodation);
		
		if(response) {
			return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
		}
		else
			return new ResponseEntity<>("Product not updated", HttpStatus.NOT_FOUND);
		
   }
	
	@RequestMapping(value = "/accommodation/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") int id) { 
      if(acDao.deleteAccommodation(id)) {
    	  return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
      }
      else
    	  return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
   }
}
