package study.posthub.domain.post.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import study.posthub.domain.post.dto.PostViewResponse;
import study.posthub.domain.post.repository.PostRepositoryCustom;

import java.util.List;

import static study.posthub.domain.post.entity.QPost.post;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory factory;

    @Override
    public Page<PostViewResponse> loadDefaultPosts(Pageable pageable) {
        List<PostViewResponse> result = factory.select(Projections.constructor(PostViewResponse.class,
                        post.id,
                        post.title,
                        post.content,
                        post.author,
                        post.commentCount,
                        post.viewCount,
                        post.createdAt))
                .from(post)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long totalCount = result.size();

        return new PageImpl<>(result, pageable, totalCount);
    }
}
