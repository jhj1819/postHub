package study.posthub.domain.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import study.posthub.domain.post.entity.Post;
import study.posthub.domain.post.repository.PostRepository;

@Component
public class DetaInitializer implements CommandLineRunner {

    @Autowired private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 100; ++i) {
            Post post = new Post("hello" + (i+1), "content" + (i+1), "author" + (i+1));
            postRepository.save(post);
        }
    }
}
