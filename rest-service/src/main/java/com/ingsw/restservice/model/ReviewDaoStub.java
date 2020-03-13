package com.ingsw.restservice.model;

import java.util.ArrayList;
import java.util.List;

public class ReviewDaoStub implements ReviewDao {

	@Override
	public List<Review> getReviewList(int accommodationId) {
		List<Review> reviewList=new ArrayList<Review>();
        for (int i=0; i<10;i++){
        	float rating=(float) (1 + Math.random() * (5 - 1));
        	String reviewText=getReviewText(rating);
            String sDate=(i+1)+"/12/2020";
            reviewList.add(new Review.Builder()
                    .setAuthor("Paolo")
                    .setReviewText(reviewText)
                    .setRating(rating)
                    .setData(sDate)
                    .build()
            );

        }

        return reviewList;
	}

	@Override
	public Review getReviewById(int id) {
		// TODO Auto-generated method stub
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

}
