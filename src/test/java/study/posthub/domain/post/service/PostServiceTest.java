package study.posthub.domain.post.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.posthub.domain.member.entity.Authority;
import study.posthub.domain.member.entity.Member;
import study.posthub.domain.post.dto.PostRequest;
import study.posthub.domain.post.dto.PostViewResponse;
import study.posthub.exception.custom.PostException;
import study.posthub.exception.errorCode.ErrorCode;
import study.posthub.global.security.oauth2.dto.SessionMember;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    PostService postService;

    @Test
    void getAllPosts() {
    }

    @Test
    void 게시글_생성_성공() throws Exception{
        //given
        SessionMember sessionMember = SessionMember.getInstance(Member.builder()
                .email("test@example.com")
                .nickname("tester")
                .authority(Authority.USER)
                .build());

        PostRequest postRequest = new PostRequest("title", "content", "tester");

        //when
        PostViewResponse postViewResponse = postService.savePost(sessionMember.nickname(), postRequest);

        //then
        assertThat(postViewResponse.title()).isEqualTo("title");
        assertThat(postViewResponse.content()).isEqualTo("content");
        assertThat(postViewResponse.author()).isEqualTo("tester");

    }

    @Test
    void 게시글_수정_성공() throws Exception {
        //given
        SessionMember sessionMember = SessionMember.getInstance(Member.builder()
                .email("test@example.com")
                .nickname("tester")
                .authority(Authority.USER)
                .build());
       PostViewResponse postViewResponse = postService.savePost(sessionMember.nickname(), new PostRequest("title", "content", "tester")); // 게시글 생성

        // when
        PostRequest postRequest = new PostRequest("title", "updated_content", "tester");
        PostViewResponse updatedPostViewResponse = postService.updatePost(sessionMember.nickname(), postViewResponse.id(), postRequest);

        // then
        assertThat(updatedPostViewResponse.title()).isEqualTo("title");
        assertThat(updatedPostViewResponse.content()).isEqualTo("updated_content");
        assertThat(updatedPostViewResponse.author()).isEqualTo("tester");
    }

    @Test
    void 권한X_게시글_수정() {
        //given
        SessionMember notAuthor = SessionMember.getInstance(Member.builder()
                .email("notAuthor@example.com")
                .nickname("notAuthor")
                .authority(Authority.USER)
                .build());

        PostViewResponse postViewResponse = postService.savePost("author", new PostRequest("title", "content", "tester")); // 다른 작성자가 쓴 게시글 생성
        PostRequest postRequest = new PostRequest("title", "updated_content", "tester");

        // when
        // then
        assertThatThrownBy(() -> postService.updatePost(notAuthor.nickname(), postViewResponse.id(), postRequest))
                .isInstanceOf(PostException.class) // 예외의 타입 확인
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.UNAUTHORIZED_POST); // 예외의 특정 필드 확인
    }

    @Test
    void 게시글_삭제_성공() {
        //given
        SessionMember sessionMember = SessionMember.getInstance(Member.builder()
                .email("notAuthor@example.com")
                .nickname("tester")
                .authority(Authority.USER)
                .build());
        PostViewResponse postViewResponse = postService.savePost(sessionMember.nickname(), new PostRequest("title", "content", "tester"));

        //when
        postService.deletePost(sessionMember.nickname(), postViewResponse.id());

        //then
        assertThat(postService.getOne(postViewResponse.id()).getDelYN()).isEqualTo(1L);
    }

    @Test
    void 권한X_게시글_삭제() {
        //given
        SessionMember notAuthor = SessionMember.getInstance(Member.builder()
                .email("notAuthor@example.com")
                .nickname("notAuthor")
                .authority(Authority.USER)
                .build());
        PostViewResponse postViewResponse = postService.savePost("author", new PostRequest("title", "content", "tester")); // 다른 작성자가 쓴 게시글 생성

        // when
        // then
        assertThatThrownBy(() -> postService.deletePost(notAuthor.nickname(), postViewResponse.id()))
                .isInstanceOf(PostException.class)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.UNAUTHORIZED_POST);

    }

    @Test
    void 삭제된_게시글_삭제() {
        //given
        SessionMember sessionMember = SessionMember.getInstance(Member.builder()
                .email("notAuthor@example.com")
                .nickname("tester")
                .authority(Authority.USER)
                .build());
        PostViewResponse postViewResponse = postService.savePost(sessionMember.nickname(), new PostRequest("title", "content", "tester"));

        postService.deletePost(sessionMember.nickname(), postViewResponse.id()); // 미리 게시글 삭제

        // when
        // then
        assertThatThrownBy(() -> postService.deletePost(sessionMember.nickname(), postViewResponse.id()))
                .isInstanceOf(PostException.class)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.ALREADY_DELETED);
    }

    @Test
    void getPostById() {
    }

    @Test
    void getPostsByAuthor() {
    }
}