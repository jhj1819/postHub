package study.posthub.domain.post.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.posthub.domain.post.dto.PostViewResponse;
import study.posthub.domain.post.entity.Keyword;

public interface PostRepositoryCustom {

    Page<PostViewResponse> loadDefaultPosts(Pageable pageable);

    Page<PostViewResponse> loadPostsByKeyword(Keyword keyword, String query, Pageable pageable);
}
