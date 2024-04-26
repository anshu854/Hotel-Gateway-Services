package com.anu.hotel.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anu.hotel.Entities.Hotel;

public interface HotelRepo extends JpaRepository<Hotel,String> {

}
