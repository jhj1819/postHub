package study.posthub.domain.post.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.posthub.domain.post.dto.PostRequest;
import study.posthub.domain.post.dto.PostViewResponse;
import study.posthub.domain.post.entity.Post;
import study.posthub.domain.post.repository.PostRepository;
import study.posthub.domain.post.service.PostService;
import study.posthub.exception.custom.PostException;
import study.posthub.exception.errorCode.ErrorCode;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public Page<PostViewResponse> getAllPosts(Pageable pageable) {
        return postRepository.loadDefaultPosts(pageable);
    }

    @Override
    public PostViewResponse savePost(String nickname, PostRequest request) {
        Post post = request.toEntity(nickname);
        postRepository.save(post);

        return PostViewResponse.from(post);
    }

    @Override
    public void deletePost(Long id) {

    }

    @Override
    public PostViewResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostException(ErrorCode.NOT_FOUND_POST));

        return PostViewResponse.from(post);
    }

    @Override
    public Page<PostViewResponse> getPostsByAuthor(String author, Pageable pageable) {
        return null;
    }
}
