package study.posthub.domain.post.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import study.posthub.global.common.BaseTimeEntity;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String author; // 작성자 (나중에 Member로 ?)
    private int commentCount; // 댓글수
    private int viewCount; // 조회수
    private int like_cnt; // 좋아요 수
}