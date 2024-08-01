package study.posthub.domain.comment.dto;

import study.posthub.domain.comment.entity.Comment;

public record CommentRequest(
        String content,
        String author) {

    public Comment toEntity(String author){
        return Comment.builder()
                .content(content)
                .author(author)
                .build();
    }
}