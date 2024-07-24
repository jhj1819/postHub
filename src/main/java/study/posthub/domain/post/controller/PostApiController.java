package study.posthub.domain.post.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.posthub.domain.post.dto.PostRequest;
import study.posthub.domain.post.entity.Post;
import study.posthub.domain.post.service.PostService;
import study.posthub.global.common.LoginMember;
import study.posthub.global.security.oauth2.dto.SessionMember;

@RequiredArgsConstructor
@RestController
@Slf4j
public class PostApiController {

    private final PostService postService;

    /* CREATE */
    @PostMapping("/posts")
    public ResponseEntity<Post> AddPost(@LoginMember SessionMember member, @RequestBody PostRequest request){
        Post savedPost = postService.savePost(member.nickname(), request);
        return ResponseEntity.status(HttpStatus.CREATED)
                    .body(savedPost);
    }
}
