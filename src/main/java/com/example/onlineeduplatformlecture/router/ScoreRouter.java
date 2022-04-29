package com.example.onlineeduplatformlecture.router;

import com.example.onlineeduplatformlecture.handler.ScoreHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@EnableWebFlux
public class ScoreRouter {

    @Bean
    public RouterFunction<ServerResponse> scoreRouters(ScoreHandler scoreHandler) {
        return RouterFunctions.route()
//                // Score
                .GET("/lectures/{lectureId}/score", scoreHandler::getScore)
                .POST("/lectures/{lectureId}/score", scoreHandler::setScore)
                .build();
    }

}
