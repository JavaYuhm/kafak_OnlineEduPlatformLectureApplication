package com.example.onlineeduplatformlecture.handler;

import com.example.onlineeduplatformlecture.dto.RatingAverageDto;
import com.example.onlineeduplatformlecture.dto.RatingDto;
import com.example.onlineeduplatformlecture.dto.RatingSaveDto;
import com.example.onlineeduplatformlecture.model.Rating;
import com.example.onlineeduplatformlecture.service.RatingService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class RatingHandler {

    private final RatingService ratingService;

    public RatingHandler(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    public Mono<ServerResponse> getRatingList(ServerRequest request) {

        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Long lectureId = Long.parseLong(request.pathVariable("lectureId"));

        Flux<RatingDto> ratings = ratingService.getRatings(lectureId);
        Mono<RatingAverageDto> averageDtoMono = ratingService.getAverageRate(ratings);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Flux.concat(ratings, averageDtoMono), RatingDto.class)
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> getRating(ServerRequest request) {

        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Long ratingId = Long.parseLong(request.pathVariable("ratingId"));

        Mono<RatingDto> ratingMono = ratingService.getRating(ratingId);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(ratingMono, RatingDto.class)
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> saveRating(ServerRequest request) {
        Mono<RatingSaveDto> ratingMono = request.bodyToMono(Rating.class)
                .onErrorResume(throwable -> {
                    System.out.println(throwable.getMessage());
                    return Mono.error(new RuntimeException(throwable));
                })
                .flatMap(ratingService::saveRate);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(ratingMono, RatingSaveDto.class);
    }
}
