package study.posthub.domain.comment.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.posthub.domain.comment.dto.CommentRequest;
import study.posthub.domain.comment.dto.CommentViewResponse;
import study.posthub.domain.comment.entity.Comment;
import study.posthub.domain.comment.repository.CommentRepository;
import study.posthub.domain.comment.service.CommentService;
import study.posthub.domain.post.entity.Post;
import study.posthub.domain.post.repository.PostRepository;
import study.posthub.exception.custom.PostException;
import study.posthub.exception.errorCode.ErrorCode;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    public CommentViewResponse saveComment(String nickname, Long postId, CommentRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostException(ErrorCode.NOT_FOUND_POST));

        post.increaseCommentCount();
        Comment comment = request.toEntity(nickname);
        comment.addPost(post);

        commentRepository.save(comment);

        return CommentViewResponse.from(comment);
    }

    @Override
    public CommentViewResponse saveReply(String nickname, Long postId, Long parentId, CommentRequest request) {

        Comment comment = request.toEntity(nickname);

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostException(ErrorCode.NOT_FOUND_POST));
        comment.addPost(post);

        Comment parentComment = commentRepository.findById(parentId)
                .orElseThrow(() -> new PostException(ErrorCode.NOT_FOUND_POST));
        comment.addComment(parentComment);

        commentRepository.save(comment);

        return CommentViewResponse.from(comment);
    }

    @Override
    public void deleteComment(String nickname, Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new PostException(ErrorCode.NOT_FOUND_COMMENT));

        authorizeCommentAuthor(nickname, comment); // 댓글 작성자인지 확인

        commentRepository.delete(comment);
    }

    @Override
    public CommentViewResponse getCommentById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new PostException(ErrorCode.NOT_FOUND_COMMENT));

        return CommentViewResponse.from(comment);
    }

    @Override
    public Page<CommentViewResponse> getCommentsByPostId(Long postId, Pageable pageable) {
        Page<Comment> page = commentRepository.findByPostId(postId, pageable);

        return page.map(CommentViewResponse::from); // CommentViewResponse로 변환
    }

    @Override
    public Page<CommentViewResponse> getCommentsByParentId(Long ParentId, Pageable pageable) {
        return null;
    }

    private static void authorizeCommentAuthor(String nickname, Comment comment){
        if(!comment.isAuthor(nickname)){
            throw new PostException(ErrorCode.UNAUTHORIZED_COMMENT);
        }
    }
}
