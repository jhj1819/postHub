package study.posthub.domain.comment.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.posthub.domain.comment.dto.CommentRequest;
import study.posthub.domain.comment.dto.CommentViewResponse;
import study.posthub.domain.post.dto.PostRequest;
import study.posthub.domain.post.dto.PostViewResponse;

public interface CommentService {

    CommentViewResponse saveComment(String nickname, Long postId, CommentRequest commentRequest); /* 댓글 작성 */
    CommentViewResponse saveReply(String nickname, Long postId, Long parentId, CommentRequest commentRequest); /* 대댓글 작성 */
    void deleteComment(Long id); /* 댓글 삭제 */
    CommentViewResponse getCommentById(Long id); /* 댓글 단건 조회 */
    Page<CommentViewResponse> getCommentsByPostId(Long postId, Pageable pageable); /* 글별 게시글 조회 */
    Page<CommentViewResponse> getCommentsByParentId(Long ParentId, Pageable pageable); /* 부모별 게시글 조회 */

}
