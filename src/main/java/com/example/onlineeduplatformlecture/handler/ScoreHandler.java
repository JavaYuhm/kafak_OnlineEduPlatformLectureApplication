package com.example.onlineeduplatformlecture.handler;

import com.example.onlineeduplatformlecture.model.Rating;
import com.example.onlineeduplatformlecture.model.Score;
import com.example.onlineeduplatformlecture.repository.ScoreRepository;
import com.example.onlineeduplatformlecture.service.ScoreService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ScoreHandler {

    private final ScoreRepository scoreRepository;
    private final ScoreService scoreService;

    public ScoreHandler(ScoreRepository scoreRepository,
            ScoreService scoreService) {
        this.scoreRepository = scoreRepository;
        this.scoreService = scoreService;
    }

    public Mono<ServerResponse> getScore(ServerRequest serverRequest) {
        int lectureId = Integer.parseInt(serverRequest.pathVariable("lectureId"));
        Flux<Score> Scores = scoreRepository.findAll()
                .filter(score -> score.getLectureId()==lectureId);

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(Scores, Rating.class);
//        Mono<Score> scoreMono = scoreRepository.findScoreById(lectureId,1);
//        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
//        scoreMono.subscribe(System.out::println);
//        return scoreMono
//                .flatMap(score -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(scoreMono, Score.class))
//                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> setScore(ServerRequest serverRequest) {
        Mono<Score> scoreMono = serverRequest.bodyToMono(Score.class)
                .onErrorResume(throwable -> {
                    System.out.println(throwable.getMessage());
                    return Mono.error(new RuntimeException(throwable));
                });

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(
                scoreMono.flatMap(this.scoreRepository::save), Score.class);
    }
}
