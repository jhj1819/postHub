package study.posthub.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.posthub.domain.post.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
}