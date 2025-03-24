package com.userservice.userservice.feignclient;

import com.userservice.userservice.models.Hotel;
import com.userservice.userservice.models.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATINGSERVICE")
public interface RatingServiceFeignClient {

    @GetMapping("/rating/user/{userId}")
    List<Rating> getAllRatingsForUser(@PathVariable("userId") int userId);
}
