package study.posthub.domain.post.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.posthub.global.common.BaseTimeEntity;

@Entity
@Getter
@NoArgsConstructor
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String author; // 작성자 (나중에 Member로 ?)

    private int commentCount; // 댓글수
    private int viewCount; // 조회수
    private int likeCount; // 좋아요 수

    @Builder
    public Post(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.commentCount = 0;
        this.viewCount = 0;
        this.likeCount = 0;
    }
}