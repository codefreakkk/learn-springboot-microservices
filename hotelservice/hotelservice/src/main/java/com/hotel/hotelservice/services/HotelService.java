package com.hotel.hotelservice.services;

import com.hotel.hotelservice.models.Hotel;
import com.hotel.hotelservice.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public Hotel create(Hotel hotel) {
        System.out.println(hotel.getName());
        hotelRepository.save(hotel);
        return hotel;
    }

    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    public Hotel getById(int id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        return hotel.orElse(null);
    }
}
