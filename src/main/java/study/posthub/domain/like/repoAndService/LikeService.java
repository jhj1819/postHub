package study.posthub.domain.like.repoAndService;

public interface LikeService {

    Long like(Long postId, Long memberId);

    void unlike(Long postId, Long memberId);
}
