package com.anu.user.userservice.Controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anu.user.userservice.Entities.User;
import com.anu.user.userservice.Service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService uServices;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	//create user
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		User user1 = uServices.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
//	int retryCount=1;
	//get SINGLE USER
	@GetMapping("/{userId}")
//	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallBack")
//	@Retry(name = "ratingHotelService" , fallbackMethod = "ratingHotelFallBack")
	@RateLimiter(name = "UserRateLimiter" , fallbackMethod = "ratingHotelFallBack")
	public ResponseEntity<User> getSingleUser(@PathVariable("userId") String userId){
		logger.info("Get Single User Handler : UserController");
	//	logger.info("Retry Count {}",retryCount);
	//	retryCount++;
		User user = uServices.getUser(userId);
		return ResponseEntity.ok(user);
	}

	//creating fall back method using circuit breaker
	public ResponseEntity<User> ratingHotelFallBack(String userId, Exception ex){
		logger.info("Fallback is executed because service is down  ", ex.getMessage());
	//	logger.info("Retry Count {}",retryCount);
	//	retryCount++;
		User user = User.builder().email("Dummy@email.com").userName("Dummy")
				.about("This User is created Dummy beacause some service is down").userId("123456").build();
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	//get multiple user
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
		List<User> user3 = uServices.getAllUser();
		return ResponseEntity.ok(user3);
		
	}
	
}
