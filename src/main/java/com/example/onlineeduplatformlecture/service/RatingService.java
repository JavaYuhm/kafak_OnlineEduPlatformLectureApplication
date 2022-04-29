package com.example.onlineeduplatformlecture.service;

import com.example.onlineeduplatformlecture.dto.RatingAverageDto;
import com.example.onlineeduplatformlecture.dto.RatingDto;
import com.example.onlineeduplatformlecture.dto.RatingSaveDto;
import com.example.onlineeduplatformlecture.model.Rating;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RatingService {

    Mono<RatingAverageDto> getAverageRate(Flux<RatingDto> ratings);

    Flux<RatingDto> getRatings(Long lectureId);

    Mono<RatingDto> getRating(Long ratingId);

    Mono<Rating> getRatingByUserIdAndLectureId(Long userId, Long lectureId);

    Mono<RatingSaveDto> saveRate(Rating rating);
}
