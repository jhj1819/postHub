package study.posthub.domain.like.repoAndService;

import org.springframework.data.jpa.repository.JpaRepository;
import study.posthub.domain.like.entity.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {

    boolean existsByPostIdAndAndMemberId(Long postId, Long memberId);
}
