package com.hotel.hotelservice.controller;

import com.hotel.hotelservice.models.Hotel;
import com.hotel.hotelservice.services.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Hotel hotel) {
        Hotel h = hotelService.create(hotel);
        return new ResponseEntity<>(h, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Hotel> hotels = hotelService.getAll();
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        Hotel hotel = hotelService.getById(id);
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }
}
