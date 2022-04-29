package com.example.onlineeduplatformlecture.service.impl;

import com.example.onlineeduplatformlecture.model.Score;
import com.example.onlineeduplatformlecture.repository.ScoreRepository;
import com.example.onlineeduplatformlecture.service.ScoreService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository scoreRepository;

    public ScoreServiceImpl(ScoreRepository scoreRepository){
        this.scoreRepository = scoreRepository;
    }

    public Mono<Score> getScore(int lectureId,int userId){
        return scoreRepository.findScoreById(lectureId,userId);
    }
    // GET /lectures (강의 리스트 조회)
//    public Flux<Lecture> getLectureList(){
//        return scoreRepository.findAll();
//    }


    // GET /lectures/{lectureId} (강의 열람)

//    @Override
//    public Mono<Lecture> getLecture(int lectureId) {
//        return scoreRepository.findByLectureId(lectureId);
//    }



}
