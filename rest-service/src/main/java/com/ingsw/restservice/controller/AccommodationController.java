package com.ingsw.restservice.controller;

import com.ingsw.restservice.model.DTO.EmptyJsonResponse;
import com.ingsw.restservice.model.DTO.JsonPageResponse;
import com.ingsw.restservice.model.DTO.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	@ResponseBody
	public ResponseEntity<Object> welcomeMessage() {
		return new ResponseEntity<>("CoVi19 up and running!!!", HttpStatus.OK);
   }


	@RequestMapping(method = RequestMethod.GET, value="/accommodation",params = {"query","category","subCategory","page"})
	@ResponseBody	
	public ResponseEntity<Object> getAccommodations(@RequestParam(defaultValue = "") String query,
													@RequestParam(defaultValue = "") String category,
													@RequestParam(defaultValue = "") String subCategory,
													@RequestParam(defaultValue = "0") int page) {

		JsonPageResponse<Accommodation>accommodationList=acDao.getAccommodations(query,category,subCategory,page);
		if(accommodationList!=null)
			return new ResponseEntity<>(accommodationList, HttpStatus.OK);
		else
			return new ResponseEntity<>(new JsonResponse(false,"Strutture non trovate"), HttpStatus.BAD_REQUEST);

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
	
		Accommodation ac= acDao.createAccommodation(accommodation);
	
		return  new ResponseEntity<>(ac, HttpStatus.CREATED) ;
	}
	
	@RequestMapping(value = "/accommodation/edit/", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Object> updateAccommodation(@RequestBody Accommodation accommodation) {
      
		boolean response=acDao.editAccommodation(accommodation);
		
		if(response) {
			return new ResponseEntity<>(new JsonResponse(true,"Struttura modificata"), HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(new JsonResponse(true,"Struttura non trovata"), HttpStatus.NOT_FOUND);
		
   }
	
	@RequestMapping(value = "/accommodation/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Object> delete(@PathVariable("id") long id) {
      if(acDao.deleteAccommodation(id)) {
    	  return new ResponseEntity<>("Accommodation is deleted", HttpStatus.OK);
      }
      else
    	  return new ResponseEntity<>("Accommodation not found", HttpStatus.NOT_FOUND);
   }
	

}
