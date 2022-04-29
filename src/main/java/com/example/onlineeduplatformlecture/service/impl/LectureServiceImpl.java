package com.example.onlineeduplatformlecture.service.impl;

import com.example.onlineeduplatformlecture.model.Lecture;
import com.example.onlineeduplatformlecture.repository.LectureRepository;
import com.example.onlineeduplatformlecture.service.LectureService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LectureServiceImpl implements LectureService {

    private final LectureRepository lectureRepository;

    public LectureServiceImpl(LectureRepository lectureRepository){
        this.lectureRepository = lectureRepository;
    }

    // GET /lectures (강의 리스트 조회)
    public Flux<Lecture> getLectureList(){
        return lectureRepository.findAll();
    }


    // GET /lectures/{lectureId} (강의 열람)

    @Override
    public Mono<Lecture> getLecture(Long lectureId) {
        return lectureRepository.findById(lectureId);
    }



}
