package study.posthub.domain.comment.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.posthub.domain.comment.entity.Comment;


public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findByPostId(Long postId, Pageable pageable);

    @Query("SELECT c FROM Comment c WHERE c.post.id = :postId AND c.delYN = :delYN")
    Page<Comment> findByPostIdAndDelYN(@Param("postId") Long postId, @Param("delYN") long delYN, Pageable pageable);
}
