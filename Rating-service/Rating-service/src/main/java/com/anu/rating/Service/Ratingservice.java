package com.anu.rating.Service;

import java.util.List;

import com.anu.rating.Entities.Rating;



public interface Ratingservice {

	//create
		Rating create(Rating rating);
		
		//get all ratings
		List<Rating> getRatings();
		
		//get all by userId
		List<Rating> getRatingsByUserId(String userId);
		
		//get all by hotel
		List<Rating> getRatingsByHotelId(String hotelId);
	
}
