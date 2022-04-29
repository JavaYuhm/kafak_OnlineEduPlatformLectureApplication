DROP TABLE IF EXISTS SCORE;
CREATE TABLE IF NOT EXISTS SCORE (
    score_id     INT(20) AUTO_INCREMENT PRIMARY KEY NOT NULL,
    lecture_id   INT(20) not null,
    user_id      INT(20) not null,
    score        VARCHAR(45) not null,
    comment      VARCHAR(200) not null,
    created_at   TIMESTAMP not null,
    updated_at   TIMESTAMP not null
);

INSERT INTO SCORE (score_id, lecture_id, user_id, score, comment, created_at, updated_at)
VALUES (1, 1, 1, 'A', 'A?', now(), now());
INSERT INTO SCORE (score_id, lecture_id, user_id, score, comment, created_at, updated_at)
VALUES (2, 2, 1, 'F', '다음에 다시보자', now(), now());
INSERT INTO SCORE (score_id, lecture_id, user_id, score, comment, created_at, updated_at)
VALUES (3, 1, 2, 'B', 'B?', now(), now());
INSERT INTO SCORE (score_id, lecture_id, user_id, score, comment, created_at, updated_at)
VALUES (4, 1, 3, 'C', '인프라 쉣?', now(), now());