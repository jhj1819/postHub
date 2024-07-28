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
    public PostViewResponse updatePost(String nickname, Long id, PostRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostException(ErrorCode.NOT_FOUND_POST));

        authorizePostAuthor(nickname, post); // 게시글 작성자인지 확인
        post.update(request.title(), request.content());

        return PostViewResponse.from(post);
    }

    @Override
    public void deletePost(String nickname, Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostException(ErrorCode.NOT_FOUND_POST));

        authorizePostAuthor(nickname, post); // 게시글 작성자인지 확인
        if (post.isDeleted()) {
            throw new PostException(ErrorCode.ALREADY_DELETED);
        }
        post.delete();
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

    private static void authorizePostAuthor(String nickname, Post post){
        if(!post.isAuthor(nickname)){
            throw new PostException(ErrorCode.UNAUTHORIZED_POST);
        }
    }

}
