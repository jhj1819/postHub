package study.posthub.domain.post.service;

import study.posthub.domain.post.dto.AddPostRequest;
import study.posthub.domain.post.entity.Post;

import java.util.List;

public interface PostService {

    List<Post> getAllPosts(); /* 전체 게시글 조회 */
    Post savePost(AddPostRequest addPostRequest); /* 게시글 작성 */
    void deletePost(Long id); /* 게시글 삭제 */
    Post getPostById(Long id); /* 게시글 단건 조회 */
}
