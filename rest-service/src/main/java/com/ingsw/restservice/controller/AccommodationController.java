package com.ingsw.restservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
	@Qualifier("accommodationDaoSql")
	private AccommodationDao acDao;
	
	@RequestMapping(value = "/")
	public ResponseEntity<Object> welcomeMessage() { 
      
		return new ResponseEntity<>("CoVi19 up and running!!!", HttpStatus.OK);
		
   }
	
	@RequestMapping(method = RequestMethod.GET, value="/accommodation",params = "page")
	@ResponseBody	
	public List<Accommodation> getAccommodationsByRating(@RequestParam(defaultValue = "0") int page) {
	
		return acDao.getAccommodationOrderByRating(page);
		
	}

	@RequestMapping(method = RequestMethod.GET, value="/accommodation",params = "category")
	@ResponseBody
	public List<Accommodation> getAccommodationByCategory(String category) {
		return acDao.getAccommodationByCategory(category);
	}


	@RequestMapping(method = RequestMethod.GET, value="/accommodation",params = "subcategory")
	@ResponseBody
	public List<Accommodation> getAccommodationBySubCategory(@RequestParam String subcategory) {
		return acDao.getAccommodationBySubCategory(subcategory);
	}

	@RequestMapping(method = RequestMethod.GET, value="/accommodation",params = "generic")
	@ResponseBody
	public List<Accommodation> getAccommodationByGeneric(@RequestParam String generic) {
		return acDao.getAccommodationByGeneric(generic);
	}

	@RequestMapping(method = RequestMethod.GET, value="/accommodation/cat/{generic}/{category}")
	@ResponseBody
	public List<Accommodation> getAccommodationByGenericAndCategory(@PathVariable String generic,@PathVariable String category) {
		return acDao.getAccommodationByGenericAndCategory(generic,category);
	}

	@RequestMapping(method = RequestMethod.GET, value="/accommodation/sub/{generic}/{subcategory}")
	@ResponseBody
	public List<Accommodation> getAccommodationByGenericAndSubCategory(@PathVariable String generic,@PathVariable String subcategory) {
		return acDao.getAccommodationByGenericAndSubCategory(generic,subcategory);
	}

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
	public ResponseEntity<Object> getAccommodationById(@RequestParam int accommodationId) {

		//acDao = new AccommodationDaoSql();
		Accommodation accommodation=acDao.getAccommodationById(accommodationId);
		if(accommodation!=null)
			return new ResponseEntity<>(accommodation, HttpStatus.OK);
		else
			return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/accommodation/create")
	@ResponseBody	
	public ResponseEntity<Object> createAccommodation(@RequestBody Accommodation accommodation) {
	
		acDao.createAccommodation(accommodation);
	
		return  new ResponseEntity<>(accommodation, HttpStatus.CREATED) ;
	}
	
	@RequestMapping(value = "/accommodation/edit/", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateAccommodation(@RequestBody Accommodation accommodation) { 
      
		boolean response=acDao.editAccommodation(accommodation);
		
		if(response) {
			return new ResponseEntity<>("Accommodation is updated", HttpStatus.OK);
		}
		else
			return new ResponseEntity<>("Accommodation not updated", HttpStatus.NOT_FOUND);
		
   }
	
	@RequestMapping(value = "/accommodation/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") long id) { 
      if(acDao.deleteAccommodation(id)) {
    	  return new ResponseEntity<>("Accommodation is deleted", HttpStatus.OK);
      }
      else
    	  return new ResponseEntity<>("Accommodation not found", HttpStatus.NOT_FOUND);
   }
	
	
}
