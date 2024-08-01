package study.posthub.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import study.posthub.domain.comment.dto.CommentRequest;
import study.posthub.domain.comment.service.CommentService;
import study.posthub.domain.member.dto.MemberRequest;
import study.posthub.domain.member.service.MemberService;
import study.posthub.domain.post.dto.PostRequest;
import study.posthub.domain.post.service.PostService;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final PostService postService;
    private final MemberService memberService;
    private final CommentService commentService;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 100; ++i) {
            PostRequest post = new PostRequest("title" + (i+1), "content" + (i+1));
            postService.savePost("author" + (i+1), post);
        }

        for (int i = 0; i < 5; ++i) {
            CommentRequest comment = new CommentRequest("content" + (i+1), "author" + (i+1));
            commentService.saveComment("author" + (i+1), 1L, comment);
        }

        MemberRequest member = new MemberRequest("ww@mail", "123456", "phone");
        memberService.register(member);
    }
}
