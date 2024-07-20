package study.posthub.domain.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import study.posthub.domain.post.entity.Post;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class PostViewResponse {

    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createAt;

    public PostViewResponse(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = post.getAuthor();
        this.createAt = post.getCreatedAt();
    }
}
