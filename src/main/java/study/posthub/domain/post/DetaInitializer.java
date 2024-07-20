package study.posthub.domain.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import study.posthub.domain.post.entity.Post;
import study.posthub.domain.post.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class DetaInitializer implements CommandLineRunner {

    @Autowired private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        Post post1 = new Post();
        post1.setTitle("Hello");
        post1.setContent("This is content");
        post1.setAuthor("Mark");
        post1.setCommentCount(4);
        post1.setViewCount(20);
        post1.setCreatedAt(LocalDateTime.now());

        Post post2 = new Post();
        post2.setTitle("algorithm");
        post2.setContent("algorithm content");
        post2.setAuthor("David");
        post2.setCommentCount(16);
        post2.setViewCount(101);
        post2.setCreatedAt(LocalDateTime.now());

        Post post3 = new Post();
        post3.setTitle("Controller");
        post3.setContent("Controller content");
        post3.setAuthor("Json");
        post3.setCommentCount(4);
        post3.setViewCount(10);
        post3.setCreatedAt(LocalDateTime.now());

        postRepository.saveAll(Arrays.asList(post1, post2, post3));
    }

}
