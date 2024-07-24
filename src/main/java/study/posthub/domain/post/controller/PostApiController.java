package study.posthub.domain.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.posthub.domain.post.dto.PostRequest;
import study.posthub.domain.post.entity.Post;
import study.posthub.domain.post.service.PostService;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;

    /* CREATE */
    @PostMapping("/posts")
    public ResponseEntity<Post> AddPost(@RequestBody PostRequest request){
        Post savedPost = postService.savePost(request);
        return  ResponseEntity.status(HttpStatus.CREATED)
                    .body(savedPost);
    }
}
