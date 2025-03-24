package com.userservice.userservice.feignclient;

import com.userservice.userservice.models.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTELSERVICE")
public interface HotelServiceFeignClient {

    @GetMapping("/hotel/{id}")
    Hotel getHotelById(@PathVariable("id") int id);
}
