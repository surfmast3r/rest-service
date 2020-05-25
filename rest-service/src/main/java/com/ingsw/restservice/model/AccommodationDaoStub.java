package com.ingsw.restservice.model;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;



@Service
public class AccommodationDaoStub implements AccommodationDao{

	List<Accommodation> accommodationList = new ArrayList<Accommodation>();
	
	public AccommodationDaoStub() {
		
		accommodationList=createAccommodationList("Napoli");
	}
	 

	@Override
	public List<Accommodation> findAll() {
		return accommodationList;
	}

	@Override
	public List<Accommodation> getAccommodationByCity(String city, int page) {
		return null;
	}


	public List<Accommodation> getAccommodationByCity(String city) {
		
		ArrayList<Accommodation> accommodationByCity= new ArrayList<Accommodation>();
		for(int i=0;i<accommodationList.size();i++) {
			if(accommodationList.get(i).getCity().equals(city.toUpperCase())) {
				accommodationByCity.add(accommodationList.get(i));
				
			}
		}
		return accommodationByCity;
	}

	@Override
	public Accommodation getAccommodationById(long id) {
		
		return findAccommodation(id);
	}

	@Override
	public List<Accommodation> getAccommodationByName(String name, int page) {
		return null;
	}


	public List<Accommodation> getAccommodationByName(String name) {
		return null;
	}

	@Override
	public Accommodation createAccommodation(Accommodation accommodation) {
		accommodationList.add(accommodation);
		return accommodation;
		
	}

	@Override
	public boolean editAccommodation(Accommodation accommodation) {
		return false;

	}

	@Override
	public boolean deleteAccommodation(long accommodationId) {
		return false;

	}

	@Override
	public List<Accommodation> getAccommodationOrderByRating(int limit) {
		return null;
	}

	@Override
	public List<Accommodation> getAccommodations(String query, String category, String subCategory,int page) {
		return null;
	}

	@Override
	public List<Accommodation> getAccommodationBySubCategory(String subcategory, int page) {
		return null;
	}

	@Override
	public List<Accommodation> getAccommodationByGeneric(String generic, int page) {
		return null;
	}

	@Override
	public List<Accommodation> getAccommodationByGenericAndCategory(String generic, String category, int page) {
		return null;
	}

	@Override
	public List<Accommodation> getAccommodationByGenericAndSubCategory(String generic, String subcategory, int page) {
		return null;
	}


	/*
	@Override
	public boolean deleteAccommodation(int accommodationId) {
		return removeAccommodation(accommodationId);
	}
	
	@Override
	public boolean editAccommodation(int accommodationId, Accommodation accommodation) {
	
		boolean response=removeAccommodation(accommodationId);
		if(response) {
			createAccommodation(accommodation);
			return true;
		}else
			return false;
		
		
	}
	*/
	private List<Accommodation> createAccommodationList(){
		List<Accommodation> accommodationList = new ArrayList<Accommodation>();
		for(int i=0;i<10;i++)
		{
			double lat = 40.857362 + Math.random() * (40.857362 - 40.870000);
            double longitude = 14.261627 + Math.random() * (14.261627 - 14.300000);
			accommodationList.add(new Accommodation.Builder()
					//.setId(i)
                    .setName("Da Peppino"+i)
                    .setDescription("Descrizione ristorante da Peppino "+i)
                    .setCategory("RESTAURANT")
                    .setSubCategory("PIZZERIA")
                    .setCity("Napoli")
                    .setAddress("Via Bernardo Cavallino 27")
                    .setLatitude(lat)
                    .setLongitude(longitude)
                    .setImages("https://www.oasidellapizza.it/wp-content/uploads/revslider/steweysfullslider/5.jpg")
                    .setRating((float) (1 + Math.random() * (5 - 1)))
                    .build());
		}
		return accommodationList;
		
	}
	
	private List<Accommodation> createAccommodationList(String city){
		List<Accommodation> accommodationList = new ArrayList<Accommodation>();
		for(int i=0;i<10;i++)
		{
			double lat = 40.857362 + Math.random() * (40.857362 - 40.870000);
            double longitude = 14.261627 + Math.random() * (14.261627 - 14.300000);
			accommodationList.add(new Accommodation.Builder()
					.setId(i)
                    .setName("Da Peppino"+i)
                    .setDescription("Descrizione ristorante da Peppino "+i)
                    .setCategory("RESTAURANT")
                    .setSubCategory("PIZZERIA")
                    .setCity(city.toUpperCase())
                    .setAddress("Via Bernardo Cavallino 27")
                    .setLatitude(lat)
                    .setLongitude(longitude)
                    .setImages("https://www.oasidellapizza.it/wp-content/uploads/revslider/steweysfullslider/5.jpg")
                    //.setImages(new ArrayList<String>(Arrays.asList("https://www.oasidellapizza.it/wp-content/uploads/revslider/steweysfullslider/5.jpg")))
                    .setRating((float) (1 + Math.random() * (5 - 1)))
                    .build());
		}
		return accommodationList;
		
	}


		
	private Accommodation findAccommodation(long id) {
		
		for(int i=0;i<accommodationList.size();i++) {
			if(accommodationList.get(i).getId()==id) {
				return accommodationList.get(i);
			}
		}
		
		return null;
	}
	/*
	private boolean removeAccommodation(int id) {
		
		for(int i=0;i<accommodationList.size();i++) {
			if(accommodationList.get(i).getId()==id) {
				accommodationList.remove(i);
				return true;
			}
		}
		
		return false;
	}
*/

}
