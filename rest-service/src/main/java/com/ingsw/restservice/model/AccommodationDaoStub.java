package com.ingsw.restservice.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AccommodationDaoStub implements AccommodationDao{


	@Override
	public List<Accommodation> findAll() {
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

	@Override
	public List getAccommodationByCity(String city) {
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



}
