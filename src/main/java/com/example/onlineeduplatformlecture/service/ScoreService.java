package com.example.onlineeduplatformlecture.service;

import com.example.onlineeduplatformlecture.model.Score;
import reactor.core.publisher.Mono;

public interface ScoreService {

    Mono<Score> getScore(int lectureId, int userId);
}
