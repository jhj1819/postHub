package study.posthub.domain.like.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import study.posthub.domain.like.repoAndService.LikeService;
import study.posthub.global.common.LoginMember;
import study.posthub.global.security.oauth2.dto.SessionMember;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LikeController {

    private final LikeService likeService;

    // 좋아요 누르기
    @PostMapping("/post/{postId}/like")
    public ResponseEntity<Long> like(@LoginMember SessionMember member, @PathVariable Long postId) {
        Long likedCount = likeService.like(postId, member.id());

        return ResponseEntity.ok(likedCount);
    }

    // 좋아요 취소 기능 미구현
}
