package study.posthub.domain.post.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.posthub.domain.post.dto.PostRequest;
import study.posthub.domain.post.dto.PostViewResponse;
import study.posthub.domain.post.entity.Post;
import study.posthub.domain.post.repository.PostRepository;
import study.posthub.domain.post.service.PostService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public Page<PostViewResponse> getAllPosts(Pageable pageable) {
        return postRepository.loadDefaultPosts(pageable);
    }

    @Override
    public Post savePost(String nickname, PostRequest request) {
        Post post = request.toEntity(nickname);

        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long id) {

    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));
    }
}
