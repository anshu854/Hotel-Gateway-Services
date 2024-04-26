package com.anu.user.userservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import com.anu.user.userservice.Entities.Rating;
import com.anu.user.userservice.ExternalService.RatingService;

@SpringBootTest
@Service
class UserserviceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService rService;
	
//	@Test
//	void createRating() {
//		Rating rating = Rating.builder().rating(5).userId("").hotelId("").feedback("This is created by feign client").build();
//		Rating saveRating =  rService.createRating(rating);
//		System.out.println("New Rating : " +saveRating);
//	}
	
}
