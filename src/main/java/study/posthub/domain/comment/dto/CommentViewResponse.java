package study.posthub.domain.comment.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import study.posthub.domain.comment.entity.Comment;

import java.time.LocalDateTime;

public record CommentViewResponse(
        Long id,
        String content,
        String author,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        LocalDateTime createdAt) {

    public CommentViewResponse(Comment comment) {
        this(comment.getId(), comment.getContent(), comment.getAuthor(), comment.getCreatedAt());
    }

    public static CommentViewResponse from(Comment comment) {
        return new CommentViewResponse(comment);
    }
}