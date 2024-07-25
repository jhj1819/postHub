package study.posthub.domain.post.dto;

import study.posthub.domain.post.entity.Post;

import java.time.LocalDateTime;

public record PostViewResponse(
        Long id,
        String title,
        String content,
        String author,
        int commentCount,
        int viewCount,
        LocalDateTime createdAt) {

    public PostViewResponse(Post post) {
        this(post.getId(), post.getTitle(), post.getContent(), post.getAuthor(), post.getCommentCount(), post.getViewCount(), post.getCreatedAt());
    }

    public static PostViewResponse from(Post post) {
        return new PostViewResponse(post);
    }
}