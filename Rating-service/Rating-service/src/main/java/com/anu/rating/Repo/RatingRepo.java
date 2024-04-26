package com.anu.rating.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anu.rating.Entities.Rating;

public interface RatingRepo extends JpaRepository<Rating,String> {

	//custom methods
		List<Rating> findByUserId(String userId);
		
		List<Rating> findByHotelId(String hotelId);
	
}
