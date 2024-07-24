package study.posthub.domain.post.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.posthub.domain.post.dto.PostRequest;
import study.posthub.domain.post.dto.PostViewResponse;
import study.posthub.domain.post.entity.Post;

import java.util.List;

public interface PostService {

    Page<PostViewResponse> getAllPosts(Pageable pageable); /* 전체 게시글 조회 */
    Post savePost(PostRequest addPostRequest); /* 게시글 작성 */
    void deletePost(Long id); /* 게시글 삭제 */
    Post getPostById(Long id); /* 게시글 단건 조회 */
}
