package com.example.onlineeduplatformlecture.handler;

import com.example.onlineeduplatformlecture.model.Enrolment;
import com.example.onlineeduplatformlecture.model.Lecture;
import com.example.onlineeduplatformlecture.model.Matching;
import com.example.onlineeduplatformlecture.repository.LectureRepository;
import com.example.onlineeduplatformlecture.repository.MatchingRepository;
import com.example.onlineeduplatformlecture.repository.EnrolmentRepository;
import com.example.onlineeduplatformlecture.service.EnrolmentService;
import com.example.onlineeduplatformlecture.service.LectureService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;

@Component
public class LectureHandler {

    private final LectureRepository lectureRepository;

    private final MatchingRepository matchingRepository;
    private final EnrolmentRepository enrolmentRepository;
    private final LectureService lectureService;
    private final EnrolmentService enrolmentService;

    public LectureHandler(
            LectureRepository lectureRepository,
            MatchingRepository matchingRepository,
            EnrolmentRepository enrolmentRepository,
            LectureService lectureService,
            EnrolmentService enrolmentService
    ) {
        this.lectureRepository = lectureRepository;
        this.matchingRepository = matchingRepository;
        this.enrolmentRepository = enrolmentRepository;
        this.lectureService = lectureService;
        this.enrolmentService = enrolmentService;

    }

    public Mono<ServerResponse> getLectureList(ServerRequest serverRequest) {
        Flux<Lecture> lectureList = lectureService.getLectureList();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(lectureList, Lecture.class);
    }

    public Mono<ServerResponse> getLecture(ServerRequest serverRequest) {

        Long lectureId = Long.parseLong(serverRequest.pathVariable("lectureId"));
        Mono<Lecture> lectureMono = lectureService.getLecture(lectureId);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(lectureMono, Lecture.class);
    }

    public Mono<ServerResponse> createLecture(ServerRequest request) {
        Mono<Lecture> lectureMono = request.bodyToMono(Lecture.class)
                .onErrorResume(throwable -> {
                    System.out.println(throwable.getMessage());
                    return Mono.error(new RuntimeException(throwable));
                });

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(
                lectureMono.flatMap(this.lectureRepository::save), Lecture.class);
    }

    public Mono<ServerResponse> enrollLecture(ServerRequest serverRequest) {
        Mono<Enrolment> enrolmentMono = serverRequest.bodyToMono(Enrolment.class)
                .onErrorResume(throwable -> {
                    System.out.println(throwable.getMessage());
                    return Mono.error(new RuntimeException(throwable));
                });

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(
                enrolmentMono.flatMap(this.enrolmentRepository::save), Enrolment.class);
    }

    public Mono<ServerResponse> matchTeacher(ServerRequest request) {
        Mono<Matching> lectureMono = request.bodyToMono(Matching.class)
                .onErrorResume(throwable -> {
                    System.out.println(throwable.getMessage());
                    return Mono.error(new RuntimeException(throwable));
                });

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(
                lectureMono.flatMap(this.matchingRepository::save), Matching.class);
    }

    public Mono<ServerResponse> changeExposeLecture(ServerRequest serverRequest) {
        int lectureId = Integer.parseInt(serverRequest.pathVariable("lectureId"));
        Mono<Lecture> lectureMono = lectureRepository.findById((long) lectureId)
                .flatMap(x -> Mono.just(
                        new Lecture(x.getLectureId(), x.getTitle(), x.getLocation(), 1,
                                LocalDateTime.now(), LocalDateTime.now())));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(
                lectureMono.flatMap(lectureRepository::save), Lecture.class);
    }

}
