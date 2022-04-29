-- exposed_yn INT(20) => INT(1) // 0 : NO, 1: YES
-- CONTENT : content BLOB => VARCHAR(100)
DROP TABLE IF EXISTS LECTURE;

CREATE TABLE IF NOT EXISTS LECTURE (
    lecture_id  int         AUTO_INCREMENT PRIMARY KEY NOT NULL,
    title       VARCHAR(45) not null,
    location    VARCHAR(45) not null,
    exposed_yn  int         DEFAULT 0,
    created_at  TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP   DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
    );

INSERT INTO LECTURE
VALUES
    (1, 'effective java', 'seoul', 0, '2022-04-25 13:32:50', '2022-04-25 13:32:50'),
    (2, ' What is Reactive?', 'seoul', 1, '2022-04-25 17:50:30', '2022-04-25 17:50:30');
--(1, '자바의 정석', '서울특별시 노원구', '0'),
--(2, '이펙티브 자바', '서울특별시 강남구', '1'),
--(3, '자바 알고리즘 코딩 테스트', '서울특별시 동작구', '1');

DROP TABLE IF EXISTS ENROLMENT;


CREATE TABLE IF NOT EXISTS ENROLMENT (
    enrolment_id    int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    lecture_id      int not null,
    user_id         int not null,
    created_at      TIMESTAMP  DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP  DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
    );

DROP TABLE IF EXISTS MATCHING;

CREATE TABLE IF NOT EXISTS MATCHING (
    matching_id int         AUTO_INCREMENT PRIMARY KEY NOT NULL,
    lecture_id  int         not null,
    user_id     int         not null,
    created_at  TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP   DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
    );

DROP TABLE IF EXISTS SCORE;

CREATE TABLE IF NOT EXISTS SCORE (
    score_id    int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    lecture_id  int not null,
    user_id     int not null,
    score       VARCHAR(45) not null,
    comment     VARCHAR(200) not null,
    created_at  TIMESTAMP  DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP  DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
    );
    
INSERT INTO SCORE (score_id, lecture_id, user_id, score, comment, created_at, updated_at)
VALUES (1, 1, 1, 'A', 'A?', now(), now());
INSERT INTO SCORE (score_id, lecture_id, user_id, score, comment, created_at, updated_at)
VALUES (2, 2, 1, 'F', '다음에 다시보자', now(), now());
INSERT INTO SCORE (score_id, lecture_id, user_id, score, comment, created_at, updated_at)
VALUES (3, 1, 2, 'B', 'B?', now(), now());
INSERT INTO SCORE (score_id, lecture_id, user_id, score, comment, created_at, updated_at)
VALUES (4, 1, 3, 'C', '인프라 쉣?', now(), now());

DROP TABLE IF EXISTS CONTENT;

CREATE TABLE IF NOT EXISTS CONTENT (

    content_id int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    lecture_id int not null,
    content VARCHAR(100) not null,
    exam_yn boolean not null,
    created_at  TIMESTAMP  DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP  DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
    );

INSERT INTO CONTENT
VALUES
    (1, 1, 'test Conetent', '0', '2022-04-25 13:32:50', '2022-04-25 13:32:50'),
    (2, 1, 'test Conetent2', '1', '2022-04-25 14:32:50', '2022-04-25 13:32:50');

