package com.example.onlineeduplatformlecture.repository;

import com.example.onlineeduplatformlecture.model.Score;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ScoreRepository extends ReactiveCrudRepository<Score, Long> {
    @Query("SELECT SCORE.* FROM SCORE WHERE SCORE.score_id = $1 AND SCORE.user_id = $2")
    Mono<Score> findScoreById(int lectureId, int userId);
}
