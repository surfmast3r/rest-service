package com.ingsw.restservice.controller;

import com.ingsw.restservice.model.Accommodation;
import com.ingsw.restservice.model.AccommodationDao;
import com.ingsw.restservice.model.DTO.EmptyJsonResponse;
import com.ingsw.restservice.model.DTO.JsonPageResponse;
import com.ingsw.restservice.model.DTO.JsonResponse;
import com.ingsw.restservice.model.SearchParamsAccommodation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


	@RequestMapping(method = RequestMethod.GET, value = "/accommodation")
	@ResponseBody
	public ResponseEntity<Object> getAccommodations(@RequestParam(name = "name", required = false, defaultValue = "") String name,
													@RequestParam(name = "city", required = false, defaultValue = "") String city,
													@RequestParam(name = "description", required = false, defaultValue = "") String description,
													@RequestParam(name = "category", required = false, defaultValue = "") String category,
													@RequestParam(name = "subCategory", required = false, defaultValue = "") String subCategory,
													@RequestParam(name = "orderBy", required = false, defaultValue = "name") String orderBy,
													@RequestParam(name = "direction", required = false, defaultValue = "DESC") String direction,
													@RequestParam(name = "latitude", required = false, defaultValue = "-200") Double latitude,
													@RequestParam(name = "longitude", required = false, defaultValue = "-200") Double longitude,
													@RequestParam(name = "minRating", required = false, defaultValue = "0") float minRating,
													@RequestParam(name = "maxRating", required = false, defaultValue = "5") float maxRating,
													@RequestParam(name = "page", required = false, defaultValue = "0") int page) {


		JsonPageResponse<Accommodation> accommodationList;
		SearchParamsAccommodation params = new SearchParamsAccommodation.Builder()
				.setCurrentName(name)
				.setCurrentDescription(description)
				.setCurrentCity(city)
				.setCurrentCategory(category)
				.setCurrentSubCategory(subCategory)
				.setOrderBy(orderBy)
				.setDirection(direction)
				.setLatitude(latitude)
				.setLongitude(longitude)
				.setMinRating(minRating)
				.setMaxRating(maxRating)
				.setCurrentPage(page)
				.create();
		if (direction.equals("ASC"))
			accommodationList = acDao.getAccommodations(params, Sort.Direction.ASC);

		else
			accommodationList = acDao.getAccommodations(params, Sort.Direction.DESC);

		if (accommodationList != null)
			return new ResponseEntity<>(accommodationList, HttpStatus.OK);
		else
			return new ResponseEntity<>(new JsonResponse(false, "Strutture non trovate"), HttpStatus.BAD_REQUEST);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/accommodation_generic")
	@ResponseBody
	public ResponseEntity<Object> getAccommodationByCity(@RequestParam(name = "query", required = false, defaultValue = "") String query,
														 @RequestParam(name = "category", required = false, defaultValue = "") String category,
														 @RequestParam(name = "subCategory", required = false, defaultValue = "") String subCategory,
														 @RequestParam(name = "direction", required = false, defaultValue = "DESC") String direction,
														 @RequestParam(name = "orderBy", required = false, defaultValue = "name") String orderBy,
														 @RequestParam(name = "page", required = false, defaultValue = "0") int page) {

		JsonPageResponse<Accommodation> accommodationList;
		SearchParamsAccommodation params = new SearchParamsAccommodation.Builder()
				.setCurrentDescription(query)
				.setCurrentCategory(category)
				.setCurrentSubCategory(subCategory)
				.setOrderBy(orderBy)
				.setCurrentPage(page)
				.create();

		if (direction.equals("ASC"))
			accommodationList = acDao.getAccommodationByGenericString(params, Sort.Direction.ASC);

		else
			accommodationList = acDao.getAccommodationByGenericString(params, Sort.Direction.DESC);

		if (accommodationList != null)
			return new ResponseEntity<>(accommodationList, HttpStatus.OK);
		else
			return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/accommodation_location")
	@ResponseBody
	public ResponseEntity<Object> getAccommodationByCoordinates(@RequestParam(name = "category", required = false, defaultValue = "") String category,
																@RequestParam(name = "latitude") Double latitude,
																@RequestParam(name = "longitude") Double longitude)
	{

	List<Accommodation> accommodationList;

	accommodationList=acDao.getAccommodationByCoordinates(category,latitude,longitude);

	if (accommodationList != null)
		return new ResponseEntity<>(accommodationList, HttpStatus.OK);
	else
		return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.OK);

	}






	@RequestMapping(method = RequestMethod.GET, value="/accommodation",params ="accommodationId")
	@ResponseBody	
	public ResponseEntity<Object> getAccommodationById(@RequestParam int accommodationId) {

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
