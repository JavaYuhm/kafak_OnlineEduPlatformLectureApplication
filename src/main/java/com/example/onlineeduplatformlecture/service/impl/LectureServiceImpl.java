package com.example.onlineeduplatformlecture.service.impl;

import com.example.onlineeduplatformlecture.model.Lecture;
import com.example.onlineeduplatformlecture.repository.LectureRepository;
import com.example.onlineeduplatformlecture.service.LectureService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LectureServiceImpl implements LectureService {

    private static final String TOPIC = "lecture-open";
    private final LectureRepository lectureRepository;
    private final KafkaTemplate<String, String> lectureKafkaTemplate;
    public LectureServiceImpl(LectureRepository lectureRepository, KafkaTemplate kafkaTemplate){
        this.lectureRepository = lectureRepository;
        this.lectureKafkaTemplate = kafkaTemplate;
    }

    // GET /lectures (강의 리스트 조회)
    public Flux<Lecture> getLectureList(){
        return lectureRepository.findAll();
    }


    // GET /lectures/{lectureId} (강의 열람)

    @Override
    public Mono<Lecture> getLecture(Long lectureId) {
        Mono<Lecture> lectureMono = lectureRepository.findById(lectureId);
        sendMessage(lectureId);
        return lectureMono;
    }

    public void sendMessage(Long lectureId) {
        this.lectureKafkaTemplate.send(TOPIC, lectureId.toString());
    }



}
