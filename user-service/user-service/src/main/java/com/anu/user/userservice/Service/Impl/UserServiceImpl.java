package com.anu.user.userservice.Service.Impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.anu.user.userservice.Entities.Hotel;
import com.anu.user.userservice.Entities.Rating;
import com.anu.user.userservice.Entities.User;
import com.anu.user.userservice.Exception.ResourceNotFoundException;
import com.anu.user.userservice.ExternalService.HotelService;
import com.anu.user.userservice.Repo.UserRepo;
import com.anu.user.userservice.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo urepo;

	@Autowired
	private RestTemplate rTemplate;
	
	@Autowired
	private HotelService hService;
	
	private org.slf4j.Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public com.anu.user.userservice.Entities.User saveUser(com.anu.user.userservice.Entities.User user) {
		String randomid = UUID.randomUUID().toString();
		user.setUserId(randomid);
		return urepo.save(user);
	}

	@Override
	public List<User> getAllUser(){
		return urepo.findAll();
	}

	@Override
	public User getUser(String UserId) {
		//getting data from database
		User user = urepo.findById(UserId).orElseThrow(() ->new ResourceNotFoundException("User Not Found!! the ID that you entered!! "+UserId)) ;
		//fetch rating data by running url
		//http://localhost:8083/rating/users/fe51b513-d0d6-4ce3-b247-38680ead3881
		//ArrayList<Rating> ratingofuser = rTemplate.getForObject("http://localhost:8083/rating/users/"+user.getUserId(),ArrayList.class);
		System.out.println("User id : " +user.getUserId());
		
		Rating[] ratingofuser = rTemplate.getForObject("http://RATINGSERVICE/rating/users/"+user.getUserId(),Rating[].class);
		logger.info("{}",ratingofuser);
		List<Rating> ratings = Arrays.stream(ratingofuser).toList();
		List<Rating> ratinglisList = ratings.stream().map(rating ->{
			//api call to hotel rating to get the hotel
			//http://localhost:8082/hotel/538349ef-283a-49ed-8953-dcee8c748b27
			//ResponseEntity<Hotel> hotelEntity = rTemplate.getForEntity("http://HOTELSERVICE/hotel/"+rating.getHotelId(),Hotel.class);
			//Hotel hotel = hotelEntity.getBody();
			Hotel hotel = hService.getHotel(rating.getHotelId());
			//logger.info("Response Entity Code: {}",hotelEntity.getStatusCode());
			//set hotel to rating
			rating.setHotel(hotel);
			
			//return the rating
			return rating;
			}).collect(Collectors.toList());
		user.setRatings(ratinglisList);
		return user;
	}
	
}
