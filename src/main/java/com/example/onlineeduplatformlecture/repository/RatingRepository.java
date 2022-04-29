package com.example.onlineeduplatformlecture.repository;

import com.example.onlineeduplatformlecture.model.Rating;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RatingRepository extends ReactiveCrudRepository<Rating, Long> {

    @Query("SELECT * FROM RATING WHERE lecture_id = $1")
    Flux<Rating> findByLectureId(Long lectureId);

    @Query("SELECT * FROM RATING WHERE user_id = $1 AND lecture_id = $2")
    Mono<Rating> findByUserIdAndLectureId(Long userId, Long lectureId);
}
