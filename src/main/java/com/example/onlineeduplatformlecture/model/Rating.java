package com.example.onlineeduplatformlecture.model;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Rating {

    @Id
    private Long ratingId;
    private Long lectureId;
    private Long userId;
    private Double rating;
    private String comment;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Rating(Long lectureId, Long userId, Double rating, String comment) {
        this.lectureId = lectureId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
    }
}
