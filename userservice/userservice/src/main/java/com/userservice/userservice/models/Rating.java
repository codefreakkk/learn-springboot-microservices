package com.userservice.userservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Rating {

    private int id;
    private int userId;
    private int hotelId;
    private String feedback;
    private Hotel hotel;
}
