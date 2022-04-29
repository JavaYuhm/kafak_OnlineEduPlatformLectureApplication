package com.example.onlineeduplatformlecture.service.impl;

import com.example.onlineeduplatformlecture.dto.RatingAverageDto;
import com.example.onlineeduplatformlecture.dto.RatingDto;
import com.example.onlineeduplatformlecture.dto.RatingSaveDto;
import com.example.onlineeduplatformlecture.model.Rating;
import com.example.onlineeduplatformlecture.repository.RatingRepository;
import com.example.onlineeduplatformlecture.service.RatingService;

import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public Mono<RatingAverageDto> getAverageRate(Flux<RatingDto> ratings) {
        return ratings.collect(Collectors.averagingDouble(RatingDto::getRating))
                .map(RatingAverageDto::new);
    }

    @Override
    public Flux<RatingDto> getRatings(Long lectureId) {
        return ratingRepository.findByLectureId(lectureId)
                .map(r -> new RatingDto(r.getRatingId(), r.getUserId(), r.getRating(), r.getComment()));
    }

    @Override
    public Mono<RatingDto> getRating(Long ratingId) {
        return ratingRepository.findById(ratingId)
                .map(r -> new RatingDto(r.getRatingId(), r.getUserId(), r.getRating(), r.getComment()));
    }

    @Override
    public Mono<Rating> getRatingByUserIdAndLectureId(Long userId, Long lectureId) {
        return ratingRepository.findByUserIdAndLectureId(userId, lectureId);
    }

    @Override
    public Mono<RatingSaveDto> saveRate(Rating rating) {
        return ratingRepository.save(rating).map(r -> new RatingSaveDto(r.getRatingId()));
    }

    @KafkaListener(topics = "lecture-open", groupId = "lecture")
    public void consume(String lectureId) throws IOException {
        System.out.println(String.format("Consumed message : %s", lectureId));

        System.out.println("getRating : "+getRating(Long.parseLong(lectureId)));
    }
}
