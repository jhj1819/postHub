package study.posthub.domain.post.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.posthub.domain.comment.entity.Comment;
import study.posthub.global.common.BaseTimeEntity;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Comment> comments;

    private String title;
    private String content;
    private String author; // 작성자
    private Long commentCount; // 댓글수
    private Long viewCount; // 조회수
    private Long likeCount; // 좋아요 수

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void increaseLikeCount() {
        this.likeCount++;
    }
}