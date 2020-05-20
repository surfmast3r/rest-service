package com.ingsw.restservice.model;

import java.util.ArrayList;
import java.util.List;

public class ReviewDaoStub implements ReviewDao {
	
	private List<Review> reviewList;
	public ReviewDaoStub() {
		reviewList=createReviewStubList();
	}

	@Override
	public List<Review> getReviewList(int accommodationId) {    
        return reviewList;
	}

	@Override
	public Review getReviewById(int id) {
		for (Review review : reviewList) {
			if(review.getId()==id) {
				return review;
			}
		}
		return null;
	}
	
	private String getReviewText(float rating) {
		String text;
		if(rating<3) {
			text="schifezz";
		}else
		if(rating>4) {
			text="buono";
		}else {
			text="mediocre";
		}
		return text;
	}

	@Override
	public Review changeReviewStatus(int id, String status) {
		for (Review review : reviewList) {
			if(review.getId()==id) {
				review.setStatus(Status.valueOf(status));
				return review;
			}
		}
		return null;
	}
	
	private List<Review> createReviewStubList() {
		List<Review> reviewList=new ArrayList<Review>();
        for (int i=0; i<10;i++){
        	float rating=(float) (1 + Math.random() * (5 - 1));
        	String reviewText=getReviewText(rating);
            String sDate=(i+1)+"/12/2020";
            reviewList.add(new Review.Builder()
            		.setId(i)
            		.setAccommodationId(i)
            		.setAccommodationName("Da Peppino "+i)
                    .setAuthor("Paolo")
                    .setReviewText(reviewText)
                    .setRating(rating)
                    .setData(sDate)
                    .setStatus(Status.PENDING)
                    .build()
            );
		
        }
        return reviewList;
	}

}
