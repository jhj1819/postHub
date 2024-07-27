package study.posthub.domain.like.repoAndService.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.posthub.domain.like.entity.Like;
import study.posthub.domain.like.repoAndService.LikeRepository;
import study.posthub.domain.like.repoAndService.LikeService;
import study.posthub.domain.post.entity.Post;
import study.posthub.domain.post.repository.PostRepository;
import study.posthub.exception.custom.PostException;
import study.posthub.exception.errorCode.ErrorCode;

@Service
@RequiredArgsConstructor
@Slf4j
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    @Override
    public Long like(Long postId, Long memberId) {

        // 좋아요를 누르기 전에 이미 좋아요를 눌렀는지 확인
        if (likeRepository.existsLikeByPostIdAndMemberId(postId, memberId)) {
            throw new PostException(ErrorCode.ALREADY_LIKED);
        }

        Like like = new Like(postId, memberId);

        // 좋아요 개수 증가
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostException(ErrorCode.NOT_FOUND_POST));

        post.increaseLikeCount();

        likeRepository.save(like);
        postRepository.save(post);

        return post.getLikeCount();
    }

    @Override
    public void unlike(Long postId, Long memberId) {
        // 싫어요 기능 미구현
    }
}
