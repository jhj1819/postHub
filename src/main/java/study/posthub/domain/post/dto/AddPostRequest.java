package study.posthub.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.posthub.domain.post.entity.Post;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddPostRequest {

    private String title;
    private String content;
    private String author;

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
