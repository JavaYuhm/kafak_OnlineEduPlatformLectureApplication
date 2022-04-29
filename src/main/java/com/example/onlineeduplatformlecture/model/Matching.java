package com.example.onlineeduplatformlecture.model;


import lombok.*;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Matching {

    @Id
    private Long matchingId;
    private Long lectureId;
    private Long userId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Matching(Long lectureId, Long userId) {
        this.lectureId = lectureId;
        this.userId = userId;
    }
}
