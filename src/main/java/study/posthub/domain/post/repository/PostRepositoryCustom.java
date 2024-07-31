package study.posthub.domain.post.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.posthub.domain.post.dto.PostViewResponse;

public interface PostRepositoryCustom {

    Page<PostViewResponse> loadDefaultPosts(Pageable pageable);

    Page<PostViewResponse> loadPostsByTitle(String title, Pageable pageable);
}
