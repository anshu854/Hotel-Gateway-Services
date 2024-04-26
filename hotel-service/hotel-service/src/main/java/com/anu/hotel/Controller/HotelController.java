package com.anu.hotel.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anu.hotel.Entities.Hotel;
import com.anu.hotel.Service.HotelService;


@RestController
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	private HotelService hService;
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping
	public ResponseEntity<Hotel> create(@RequestBody Hotel hotel){
		Hotel h1 = hService.create(hotel);
		return ResponseEntity.status(HttpStatus.CREATED).body(h1);
	}
	
	@PreAuthorize("hasAuthority('SCOPE_internal')")
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getHotel(@PathVariable("hotelId") String hotelId){
		Hotel h = hService.getHotel(hotelId);
		return ResponseEntity.ok(h);
	}
	
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@GetMapping
	public ResponseEntity<List<Hotel>> getHotels(){
		return ResponseEntity.ok(hService.geHotels());
	}
	
}
