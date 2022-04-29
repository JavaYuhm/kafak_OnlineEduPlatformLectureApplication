package com.example.onlineeduplatformlecture.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RatingDto {

    private Long ratingId;
    private Long userId;
    private Double rating;
    private String comment;
}
