package com.example.onlineeduplatformlecture.repository;

import com.example.onlineeduplatformlecture.model.Lecture;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface LectureRepository extends ReactiveCrudRepository<Lecture, Long> {
    Mono<Lecture> findByLectureId(int lectuerId);

    @Query("UPDATE LECTURE SET LECTURE.exposed_yn = $2 WHERE LECTURE.lecture_id")
    Mono<Lecture> updateExpose(long lectureId,int exposedYn);
}
