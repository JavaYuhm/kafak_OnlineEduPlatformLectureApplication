package com.example.onlineeduplatformlecture.router;

import com.example.onlineeduplatformlecture.handler.ContentHandler;
import com.example.onlineeduplatformlecture.handler.LectureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@EnableWebFlux
public class ContentRouter {

    @Bean
    public RouterFunction<ServerResponse> contentRouters(ContentHandler contentHandler) {
        return RouterFunctions.route()
//                // Content
                .GET("/lectures/{lectureId}/contents", contentHandler::getContentList)
                .POST("/lectures/{lectureId}/contents", contentHandler::uploadContent)
                .GET("/lectures/{lectureId}/contents/{contentId}", contentHandler::getContent)
                .build();
    }

}
