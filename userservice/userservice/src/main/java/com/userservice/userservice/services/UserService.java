package com.userservice.userservice.services;

import java.util.List;
import java.util.Optional;

import com.userservice.userservice.feignclient.HotelServiceFeignClient;
import com.userservice.userservice.feignclient.RatingServiceFeignClient;
import com.userservice.userservice.models.Hotel;
import com.userservice.userservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userservice.userservice.models.User;
import com.userservice.userservice.repository.UserRepository;

@Service
public class UserService {
 
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RatingServiceFeignClient ratingServiceFeignClient;

    @Autowired
    private HotelServiceFeignClient hotelServiceFeignClient;

    public void createUser(User user) throws Exception {
        if (user != null) {
            userRepository.save(user);
        } else {
            throw new Exception("User is null");
        }
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserById(int id)  {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return null;
        }
        User u = user.get();
        List<Rating> ratings = ratingServiceFeignClient.getAllRatingsForUser(u.getId());
        System.out.println("ratings: " + ratings);

        for (Rating rating : ratings) {
            int hotelId = rating.getHotelId();
            Hotel hotel = hotelServiceFeignClient.getHotelById(hotelId);
            rating.setHotel(hotel);
        }

        // set ratings
        u.setRatings(ratings);
        return u;
    }
    
}
