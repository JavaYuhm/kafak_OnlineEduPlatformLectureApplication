package com.example.onlineeduplatformlecture.dto;

public class RatingAverageDto {

    private final double average;

    public RatingAverageDto(Double average) {
        this.average = average;
    }

    public double getAverage() {
        return Math.round(this.average * 100) / 100.0;
    }
}
