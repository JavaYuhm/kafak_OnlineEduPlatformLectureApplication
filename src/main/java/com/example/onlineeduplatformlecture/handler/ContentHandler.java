package com.example.onlineeduplatformlecture.handler;

import com.example.onlineeduplatformlecture.model.Content;
import com.example.onlineeduplatformlecture.repository.ContentRepository;
import com.example.onlineeduplatformlecture.service.ContentService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ContentHandler {
    private final ContentRepository contentRepository;
    private final ContentService contentService;

    public ContentHandler(
            ContentRepository contentRepository,
            ContentService contentService) {
        this.contentRepository = contentRepository;
        this.contentService = contentService;
    }

    public Mono<ServerResponse> getContentList(ServerRequest serverRequest){

        Long lectureId  = Long.parseLong(serverRequest.pathVariable("lectureId"));
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Flux<Content> contentFlux = contentService.getContentList(lectureId);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(contentFlux, Content.class)
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> getContent(ServerRequest serverRequest){

        Long lectureId  = Long.parseLong(serverRequest.pathVariable("lectureId"));
        Long contentId  = Long.parseLong(serverRequest.pathVariable("contentId"));

        Mono<Content> contentMono = contentService.getContent(lectureId,contentId);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(contentMono, Content.class)
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue("데이터 오류"));

    }

    public Mono<ServerResponse> uploadContent(ServerRequest request){
        Mono<Content> contentMono = request.bodyToMono(Content.class)
                .onErrorResume(throwable -> {
                    System.out.println(throwable.getMessage());
                    return Mono.error(new RuntimeException(throwable));
                });

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(
                contentMono.flatMap(this.contentRepository::save), Content.class);
    }
}
