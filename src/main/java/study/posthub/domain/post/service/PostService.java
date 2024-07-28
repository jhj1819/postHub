package study.posthub.domain.post.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.posthub.domain.post.dto.PostRequest;
import study.posthub.domain.post.dto.PostViewResponse;
import study.posthub.domain.post.entity.Post;

public interface PostService {

    Page<PostViewResponse> getAllPosts(Pageable pageable); /* 전체 게시글 조회 */
    PostViewResponse savePost(String nickname, PostRequest addPostRequest); /* 게시글 작성 */
    PostViewResponse updatePost(String nickname, Long id, PostRequest addPostRequest); /* 게시글 수정 */
    void deletePost(String nickname, Long id); /* 게시글 삭제 */
    PostViewResponse getPostById(Long id); /* 게시글 단건 조회 */
    Page<PostViewResponse> getPostsByAuthor(String author, Pageable pageable); /* 작성자별 게시글 조회 */
    Post getOne(Long id); /* 게시글 단건 조회(엔티티) */
}
