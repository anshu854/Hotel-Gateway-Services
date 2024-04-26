package com.anu.hotel.Service;

import java.util.List;

import com.anu.hotel.Entities.Hotel;



public interface HotelService {

	//create
		Hotel create(Hotel hotel);
		
		//get all
		List<Hotel> geHotels();
		
		//get single
		Hotel getHotel(String HotelId);
	
}
