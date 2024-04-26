package com.anu.user.userservice.ExternalService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.anu.user.userservice.Entities.Rating;

@FeignClient(name = "RATINGSERVICE")
public interface RatingService {

	//get
	
	
	//post
	@PostMapping("/rating")
	ResponseEntity<Rating> createRating(Rating Values);
	
	//put
	@PutMapping("/rating/{ratingId}")
	ResponseEntity<Rating> updateRating(@PathVariable("ratingId") String ratingId ,Rating rating);
	
	//delete
	@DeleteMapping("/rating/{ratingId}")
	Void deleteRating(@PathVariable("ratingId") String ratingId);
	
}
