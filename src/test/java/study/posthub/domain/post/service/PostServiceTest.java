//package study.posthub.domain.post.service;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.transaction.Transactional;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import study.posthub.domain.member.entity.Authority;
//import study.posthub.domain.member.entity.Member;
//import study.posthub.domain.post.dto.PostRequest;
//import study.posthub.domain.post.dto.PostViewResponse;
//import study.posthub.exception.custom.PostException;
//import study.posthub.exception.errorCode.ErrorCode;
//import study.posthub.global.security.oauth2.dto.SessionMember;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//
//@SpringBootTest
//@Transactional
//class PostServiceTest {
//
//    @Autowired ObjectMapper objectMapper;
//    @Autowired PostService postService;
//
//    @Test
//    void 게시글_생성_성공() {
//        //given
//        SessionMember currentUser = createSessionMember();
//        PostRequest postRequest = new PostRequest("title", "content"); //PostRequest에 author 의미없네..
//
//        //when
//        PostViewResponse postViewResponse = postService.savePost(currentUser.nickname(), postRequest);
//
//        //then
//        assertThat(postViewResponse.title()).isEqualTo("title");
//        assertThat(postViewResponse.content()).isEqualTo("content");
//        assertThat(postViewResponse.author()).isEqualTo("currentUser");
//    }
//
//    @Test
//    void 게시글_수정_성공() {
//        //given
//        SessionMember currentUser = createSessionMember();
//        PostViewResponse postViewResponse = postService.savePost(currentUser.nickname(), new PostRequest("title", "content")); // 게시글 생성
//
//        // when
//        PostRequest postRequest = new PostRequest("title", "updated_content");
//        PostViewResponse updatedPostViewResponse = postService.updatePost(currentUser.nickname(), postViewResponse.id(), postRequest);
//
//        // then
//        assertThat(updatedPostViewResponse.title()).isEqualTo("title");
//        assertThat(updatedPostViewResponse.content()).isEqualTo("updated_content");
//        assertThat(updatedPostViewResponse.author()).isEqualTo("currentUser");
//    }
//
//    @Test
//    void 권한X_게시글_수정() {
//        //given
//        SessionMember currentUser = createSessionMember();
//        PostViewResponse postViewResponse = postService.savePost("anotherUser", new PostRequest("title", "content")); // 다른 작성자가 쓴 게시글 생성
//        PostRequest postRequest = new PostRequest("title", "updated_content");
//
//        // when
//        // then
//        assertThatThrownBy(() -> postService.updatePost(currentUser.nickname(), postViewResponse.id(), postRequest))
//                .isInstanceOf(PostException.class) // 예외의 타입 확인
//                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.UNAUTHORIZED_POST); // 예외의 특정 필드 확인
//    }
//
//    @Test
//    void 게시글_삭제_성공() {
//        //given
//        SessionMember currentUser = createSessionMember();
//        PostViewResponse postViewResponse = postService.savePost(currentUser.nickname(), new PostRequest("title", "content")); // 게시글 생성
//
//        //when
//        postService.deletePost(currentUser.nickname(), postViewResponse.id());
//
//        //then
//        assertThat(postService.getOne(postViewResponse.id()).getDelYN()).isEqualTo(1L);
//    }
//
//    @Test
//    void 권한X_게시글_삭제() {
//        //given
//        SessionMember currentUser = createSessionMember();
//        PostViewResponse postViewResponse = postService.savePost("anotherUser", new PostRequest("title", "content")); // 다른 작성자가 쓴 게시글 생성
//
//        // when
//        // then
//        assertThatThrownBy(() -> postService.deletePost(currentUser.nickname(), postViewResponse.id()))
//                .isInstanceOf(PostException.class)
//                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.UNAUTHORIZED_POST);
//    }
//
//    @Test
//    void 삭제된_게시글_삭제() {
//        //given
//        SessionMember currentUser = createSessionMember();
//        PostViewResponse postViewResponse = postService.savePost(currentUser.nickname(), new PostRequest("title", "content")); // 게시글 생성
//        postService.deletePost(currentUser.nickname(), postViewResponse.id()); // 미리 게시글 삭제
//
//        // when
//        // then
//        assertThatThrownBy(() -> postService.deletePost(currentUser.nickname(), postViewResponse.id()))
//                .isInstanceOf(PostException.class)
//                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.ALREADY_DELETED);
//    }
//
//    private SessionMember createSessionMember() {
//        return SessionMember.getInstance(Member.builder()
//                .email("test@example.com")
//                .nickname("currentUser")
//                .authority(Authority.USER)
//                .build());
//    }
//}