package study.posthub.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.posthub.domain.comment.dto.CommentRequest;
import study.posthub.domain.comment.dto.CommentViewResponse;
import study.posthub.domain.comment.service.CommentService;
import study.posthub.global.common.LoginMember;
import study.posthub.global.security.oauth2.dto.SessionMember;

@RequiredArgsConstructor
@RestController
@Slf4j
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentViewResponse> saveComment(@LoginMember SessionMember member, @PathVariable Long postId, @RequestBody CommentRequest request) {
        CommentViewResponse commentViewResponse = commentService.saveComment(member.nickname(), postId, request);
        log.info("saveComment");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentViewResponse);
    }

    @PostMapping("/post/{postId}/{commentId}/reply")
    public ResponseEntity<CommentViewResponse> saveReply(@LoginMember SessionMember member, @PathVariable Long postId, @PathVariable Long commentId ,  @RequestBody CommentRequest request) {
        CommentViewResponse commentViewResponse = commentService.saveReply(member.nickname(), postId, commentId, request);
        log.info("saveReply");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentViewResponse);
    }

    @DeleteMapping("/post/{postId}/{commentId}")
    public ResponseEntity<Void> deleteComment(@LoginMember SessionMember member, @PathVariable Long postId, @PathVariable Long commentId) {
        commentService.deleteComment(member.nickname(), commentId);
        log.info("deleteComment");
        return ResponseEntity.noContent().build();
    }

}
