package study.posthub.domain.post.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.posthub.domain.post.dto.AddPostRequest;
import study.posthub.domain.post.entity.Post;
import study.posthub.domain.post.repository.PostRepository;
import study.posthub.domain.post.service.PostService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post savePost(AddPostRequest request) {
        return postRepository.save(request.toEntity());
    }

    @Override
    public void deletePost(Long id) {

    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));
    }
}
