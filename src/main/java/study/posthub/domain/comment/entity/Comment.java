package study.posthub.domain.comment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.posthub.domain.post.entity.Post;
import study.posthub.global.common.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Comment> childComments = new ArrayList<>();

    private String author;
    private String content;
    private long delYN;

    public void addPost(Post post) {
        this.post = post;
        post.getComments().add(this);
    }

    public void addComment(Comment parentComment) {
        this.parentComment = parentComment;
        parentComment.getChildComments().add(this);
    }

    public void deleteComment() {
        this.delYN = 1;
    }


    public boolean isAuthor(String author) {
        return java.util.Objects.equals(this.author, author);
    }
}

