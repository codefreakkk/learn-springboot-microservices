package com.rating.ratingservice.controller;

import com.rating.ratingservice.models.Rating;
import com.rating.ratingservice.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Rating rating) {
        Rating r = ratingService.create(rating);
        return new ResponseEntity<>(r, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllRatings() {
        List<Rating> rating = ratingService.getAll();
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<?> getAllRatingByUserId(@PathVariable int userId) {
        List<Rating> rating = ratingService.getAllRatingByUserId(userId);
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }

    @GetMapping(value = "/hotel/{hotelId}")
    public ResponseEntity<?> getAllRatingByHotelId(@PathVariable int hotelId) {
        List<Rating> rating = ratingService.getAllRatingByHotelId(hotelId);
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }

}
