package com.example.onlineeduplatformlecture.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Content {

    @Id
    @Column(value = "content_id")
    private  int contentId;

    @Column(value = "lecture_id")
    private  int lectureId;
    @Column(value = "content")
    private  String content;
    @Column(value = "exam_yn")
    private  boolean examYn;

    @CreatedDate
    LocalDateTime createdAt;

    @LastModifiedDate
    LocalDateTime updatedAt;
    public Content(int contentId, int lectureId, String content, boolean examYn) {
        this.contentId = contentId;
        this.lectureId = lectureId;
        this.content = content;
        this.examYn = examYn;
    }

}
