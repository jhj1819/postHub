package study.posthub.domain.post.dto;

import study.posthub.domain.post.entity.Post;

public record PostRequest(
        String title,
        String content,
        String author) {

    public Post toEntity(String author){
        return Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}