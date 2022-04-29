package com.example.onlineeduplatformlecture.router;


import com.example.onlineeduplatformlecture.handler.LectureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@EnableWebFlux
public class LectureRouter {

    @Bean
    public RouterFunction<ServerResponse> lecturesRouter(LectureHandler lectureHandler) {
        return RouterFunctions.route()
                // Lecture
                .GET("/lectures", lectureHandler::getLectureList)
                .GET("/lectures/{lectureId}", lectureHandler::getLecture)
                .POST("/lectures", lectureHandler::createLecture)
                .PATCH("/lectures/{lectureId}", lectureHandler::changeExposeLecture)
                //Enrolment & Matching
                .POST("/lectures/{lectureId}/enrolment", lectureHandler::enrollLecture)
                .POST("/lectures/{lectureId}/matching", lectureHandler::matchTeacher)
                .build();
    }

}
