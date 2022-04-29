package com.example.onlineeduplatformlecture.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table("SCORE")
public class Score {
    @Id
    private int scoreId;
    private int lectureId;
    private int userId;
    private String score;
    private String comment;

}
