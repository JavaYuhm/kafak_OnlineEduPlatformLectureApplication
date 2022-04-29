### Spring Boot Kafka 설정

``` yml
  kafka:
    consumer:
      bootstrap-servers: localhost:9092
      group-id: lecture
    producer:
      bootstrap-servers: localhost:9092
```

![image](https://user-images.githubusercontent.com/99159721/165893837-1ff0604c-d565-4c42-a5ce-3df4a227f256.png)


강의 열람 시 -> getRating(강의 별점 호출 Api 쪽에 Message 전송)

### Docker 명령어

docker container ls

docker exec -it

kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic lecture-open

![image](https://user-images.githubusercontent.com/99159721/165893739-d8e42434-078d-4aa3-a432-bba12e0bb9aa.png)



### Lecture

<details>
  <summary>
    <code>GET</code> 
    <code><b>/lecture</b></code> 
    <code>(강의 리스트 조회)</code>
  </summary>

##### Header

> | name   | Description      | Required |
  > |--------|------------------|-----------|
> | `Authorization` |   사용자 인증 수단, 액세스 토큰 값     | O |
##### Responses

> | name   |  type      | Description      | Required |
  > |--------|------------|----------------|---|
> | `elements` |  Integer[]  | 강의ID 목록     | O |

</details>

<details>
  <summary>
    <code>GET</code> 
    <code><b>/lectures/{lectureId}</b></code> 
    <code>(강의 열람)</code>
  </summary>

##### Header

> | name   | Description      | Required |
> |--------|------------------|-----------|
> | `Authorization` |   사용자 인증 수단, 액세스 토큰 값     | O |
##### Parameters

> | name   |  type      | Description      | Required |
> |--------|------------|----------------|---|
> | `lectureId` |  Integer  | 강의 ID     | O |

##### Responses

> | name   |  type      | Description      | Required |
> |--------|------------|----------------|---|
> | `title` |  String  | 강의명     | O |
> | `location` |  String  | 강의 장소     | O |

</details>

<details>
  <summary>
    <code>GET</code> 
    <code><b>/lectures/{lectureId}/contents</b></code> 
    <code>(콘텐츠 리스트 조회)</code>
  </summary>

##### Header

> | name   | Description      | Required |
> |--------|------------------|-----------|
> | `Authorization` |   사용자 인증 수단, 액세스 토큰 값     | O |
##### Responses

> | name   |  type      | Description      | Required |
> |--------|------------|----------------|---|
> | `elements` |  Integer[]  | 콘텐츠 ID 목록     | O |

</details>



<details>
  <summary>
    <code>GET</code> 
    <code><b>/lectures/{lectureId}/contents/{contentId}</b></code> 
    <code>(콘텐츠 열람)</code>
  </summary>

##### Header

> | name   | Description      | Required |
> |--------|------------------|-----------|
> | `Authorization` |   사용자 인증 수단, 액세스 토큰 값     | O |
##### Responses

> | name   |  type      | Description      | Required |
> |--------|------------|----------------|---|
> | `content` |  String  | 콘텐츠     | O |
> | `examYn` |  String  | 시험유무     |  |

</details>

<details>
  <summary>
    <code>GET</code> 
    <code><b>/lectures/{lectureId}/score</b></code> 
    <code>(성적 조회)</code>
  </summary>

##### Header

> | name   | Description      | Required |
> |--------|------------------|-----------|
> | `Authorization` |   사용자 인증 수단, 액세스 토큰 값     | O |
##### Responses

> | name   |  type      | Description      | Required |
> |--------|------------|----------------|---|
> | `score` |  String  | 성적     | O |
> | `comment` |  String  | 코멘트     |  |

</details>


<details>
  <summary>
    <code>GET</code> 
    <code><b>/lectures/{lectureId}/rating</b></code> 
    <code>(별점 리스트 조회)</code>
  </summary>

##### Header

> | name   | Description      | Required |
> |--------|------------------|-----------|
> | `Authorization` |   사용자 인증 수단, 액세스 토큰 값     | O |
##### Responses

> | name   |  type      | Description      | Required |
> |--------|------------|----------------|---|
> | `elements` |  Integer[]  | 별점 ID 목록    | O |
> | `average_rating` |  String  | 별점 평균     |  |

</details>


<details>
  <summary>
    <code>GET</code> 
    <code><b>/lectures/{lectureId}/rating/{ratingId}</b></code> 
    <code>(별점 조회)</code>
  </summary>

##### Header

> | name   | Description      | Required |
> |--------|------------------|-----------|
> | `Authorization` |   사용자 인증 수단, 액세스 토큰 값     | O |
##### Responses

> | name   |  type      | Description      | Required |
> |--------|------------|----------------|---|
> | `rating` |  Float  | 별점     | O |
> | `comment` |  String  | 코멘트     |  |

</details>

<br>

<details>
  <summary>
    <code>POST</code> 
    <code><b>/lectures</b></code> 
    <code>(강의 개설)</code>
  </summary>

##### Header

> | name | Description      | Required |
------|--------|------------------|-----------|
> | `Authorization`  |   사용자 인증 수단, 액세스 토큰 값     | O |
##### Parameters

> | name |  type      | Description      | Required |
------|--------|------------|----------------|---|
> | `title`  |  String  | 강의명     | O |
> | `location`  |  String  | 강의장소     |  |

##### Responses

> | name           |  type      | Description      | Required |
----------------|--------|------------|----------------|---|
> | `lectureId` |  Integer  | 강의 ID     | O |

</details>


<details>
  <summary>
    <code>POST</code> 
    <code><b>/lectures/{lectureId}/enrolment</b></code> 
    <code>(수강 신청)</code>
  </summary>

##### Header

> | name   | Description      | Required |
> |--------|------------------|-----------|
> | `Authorization` |   사용자 인증 수단, 액세스 토큰 값     | O |
##### Parameters

> | name   |  type      | Description      | Required |
> |--------|------------|----------------|---|
> | `userId` |  Integer  | 회원 ID     | O |

##### Responses

> | name   |  type      | Description      | Required |
> |--------|------------|----------------|---|
> | `enrolmentId` |  Integer  | 수강신청 ID     | O |

</details>

<details>
  <summary>
    <code>POST</code> 
    <code><b>/lectures/{lectureId}/matching</b></code> 
    <code>(강사 매칭)</code>
  </summary>

##### Header

> | name   | Description      | Required |
> |--------|------------------|-----------|
> | `Authorization` |   사용자 인증 수단, 액세스 토큰 값     | O |
##### Parameters

> | name   |  type      | Description      | Required |
> |--------|------------|----------------|---|
> | `lectureId` |  Integer  | 강의 ID     | O |
> | `userId` |  Integer  | 강사 회원 ID     | O |

##### Responses

> | name   |  type      | Description      | Required |
> |--------|------------|----------------|---|
> | `matchingId` |  Integer  | 매칭 ID     | O |

</details>

<details>
  <summary>
    <code>POST</code> 
    <code><b>/lectures/{lectureId}/contents</b></code> 
    <code>(컨텐츠 등록)</code>
  </summary>

##### Header

> | name   | Description      | Required |
> |--------|------------------|-----------|
> | `Authorization` |   사용자 인증 수단, 액세스 토큰 값     | O |
##### Parameters

> | name   |  type      | Description      | Required |
> |--------|------------|----------------|---|
> | `content` |  String  | 컨텐츠     | O |
> | `examYn` |  String  | 시험유무     |  |

##### Responses

> | name   |  type      | Description      | Required |
> |--------|------------|----------------|---|
> | `contentId` |  Integer  | 콘텐츠 ID     | O |

</details>

<details>
  <summary>
    <code>POST</code> 
    <code><b>/lectures/{lectureId}/score/{userId}</b></code> 
    <code>(성적 입력)</code>
  </summary>

##### Header

> | name   | Description      | Required |
> |--------|------------------|-----------|
> | `Authorization` |   사용자 인증 수단, 액세스 토큰 값     | O |
##### Parameters

> | name   |  type      | Description      | Required |
> |--------|------------|----------------|---|
> | `score` |  String  | 성적     | O |
> | `comment` |  String  | 코멘트     |  |

##### Responses

> | name   |  type      | Description      | Required |
> |--------|------------|----------------|---|
> | `scoreId` |  Integer  | 성적 ID     | O |

</details>

<details>
  <summary>
    <code>POST</code> 
    <code><b>/lectures/{lectureId}/rating</b></code> 
    <code>(별점 입력)</code>
  </summary>

##### Header

> | name   | Description      | Required |
> |--------|------------------|-----------|
> | `Authorization` |   사용자 인증 수단, 액세스 토큰 값     | O |
##### Parameters

> | name   |  type      | Description      | Required |
> |--------|------------|----------------|---|
> | `rating` |  Float  | 별점     | O |
> | `comment` |  String  | 코멘트     |  |

##### Responses

> | name   |  type      | Description      | Required |
> |--------|------------|----------------|---|
> | `ratingId` |  Integer  | 별점 ID     | O |

</details>

<br>

<details>
  <summary>
    <code>PATCH</code> 
    <code><b>/lectures/{lectureId}</b></code> 
    <code>(강의 노출 상태 변경)</code>
  </summary>

##### Header

> | name   | Description      | Required |
> |--------|------------------|-----------|
> | `Authorization` |   사용자 인증 수단, 액세스 토큰 값     | O |
##### Parameters

> | name   |  type      | Description      | Required |
> |--------|------------|----------------|---|
> | `lectureId` |  Integer  | 강의 ID     | O |

##### Responses

> | name   |  type      | Description      | Required |
> |--------|------------|----------------|---|
> | `exposedYn` |  String  | 강의 노출 유무  | O |

</details>
