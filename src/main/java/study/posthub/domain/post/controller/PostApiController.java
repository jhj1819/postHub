package study.posthub.domain.post.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.posthub.domain.post.dto.PostRequest;
import study.posthub.domain.post.dto.PostViewResponse;
import study.posthub.domain.post.service.PostService;
import study.posthub.global.common.LoginMember;
import study.posthub.global.security.oauth2.dto.SessionMember;

@RequiredArgsConstructor
@RestController
@Slf4j
public class PostApiController {

    private final PostService postService;

    /* CREATE */
    @PostMapping("/post")
    public ResponseEntity<PostViewResponse> AddPost(@LoginMember SessionMember member, @RequestBody PostRequest request){
        PostViewResponse postViewResponse = postService.savePost(member.nickname(), request);
        log.info("postViewResponse : {}", postViewResponse);

        return ResponseEntity.status(HttpStatus.CREATED)
                    .body(postViewResponse);
    }

    /* UPDATE */
    @PutMapping("/post/{id}")
    public ResponseEntity<PostViewResponse> UpdatePost(@LoginMember SessionMember member, @PathVariable Long id, @RequestBody PostRequest request){
        PostViewResponse postViewResponse = postService.updatePost(member.nickname(), id, request);
        log.info("postViewResponse : {}", postViewResponse);

        return ResponseEntity.status(HttpStatus.OK)
                .body(postViewResponse);
    }

    /* DELETE */
    @DeleteMapping("/post/{id}")
    public ResponseEntity<Void> DeletePost(@LoginMember SessionMember member, @PathVariable Long id){
        postService.deletePost(member.nickname(), id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
