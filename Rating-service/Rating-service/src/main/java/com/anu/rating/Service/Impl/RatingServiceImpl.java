package com.anu.rating.Service.Impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anu.rating.Entities.Rating;
import com.anu.rating.Repo.RatingRepo;
import com.anu.rating.Service.Ratingservice;

@Service
public class RatingServiceImpl implements Ratingservice {

	@Autowired
	private RatingRepo rRepo;
	
	@Override
	public Rating create(Rating rating) {
		rating.setRatingId(UUID.randomUUID().toString());
		return rRepo.save(rating);
	}
	
	@Override
	public List<Rating> getRatings(){
		return rRepo.findAll();
	}
	
	@Override
	public List<Rating> getRatingsByUserId(String userId){
		return rRepo.findByUserId(userId);
	}
	
	@Override
	public List<Rating> getRatingsByHotelId(String hotelId){
		return rRepo.findByHotelId(hotelId);
	}
	
}
