package study.posthub.domain.comment.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import study.posthub.domain.comment.entity.Comment;


public interface CommentRepository extends JpaRepository<Comment, Long> {
}
