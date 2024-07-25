package study.posthub.domain.comment.dto;


import study.posthub.domain.comment.entity.Comment;

public record CommentViewResponse(
        String content,
        String author,
        Long postId) {

    public Comment toEntity(String author){
        return Comment.builder()
                .content(content)
                .author(author)
                .build();
    }
}