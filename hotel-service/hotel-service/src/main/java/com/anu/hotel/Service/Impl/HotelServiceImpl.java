package com.anu.hotel.Service.Impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anu.hotel.Entities.Hotel;
import com.anu.hotel.Service.HotelService;
import com.anu.hotel.exception.ResourceNotFoundException;
import com.anu.hotel.repo.HotelRepo;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepo hRepo;
	
	@Override
	public Hotel create(Hotel hotel) {
		String hId = UUID.randomUUID().toString();
		hotel.setHotelId(hId);
		return hRepo.save(hotel);
	}
	
	@Override
	public List<Hotel> geHotels(){
		return hRepo.findAll();
	}
	
	@Override
	public Hotel getHotel(String hotelId) {
		return hRepo.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException(hotelId+" hotel id is not available"));
	}
	
}
