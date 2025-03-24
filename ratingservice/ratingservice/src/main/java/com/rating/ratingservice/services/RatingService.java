package com.rating.ratingservice.services;

import com.rating.ratingservice.models.Rating;
import com.rating.ratingservice.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public Rating create(Rating rating) {
        return ratingRepository.save(rating);
    }

    public List<Rating>  getAll() {
        return ratingRepository.findAll();
    }

    public List<Rating> getAllRatingByUserId(int userId) {
        return ratingRepository.findByUserId(userId);
    }

    public List<Rating> getAllRatingByHotelId(int hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
