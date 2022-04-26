package com.example.onlineeduplatformlecture.model;

import lombok.Data;

import org.springframework.data.annotation.Id;

@Data
public class Content {

    @Id
    private final int contentId;
    private final int lectureId;
    private final String content;
    private final boolean examYn;

    public Content(int contentId, int lectureId, String content, boolean examYn) {
        this.contentId = contentId;
        this.lectureId = lectureId;
        this.content = content;
        this.examYn = examYn;
    }

    public int getContentId() {
        return contentId;
    }

    public int getLectureId() {
        return lectureId;
    }

    public String getContent() {
        return content;
    }

    public boolean isExamYn() {
        return examYn;
    }
}
