package study.posthub.domain.post.repository.impl;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import study.posthub.domain.post.dto.PostViewResponse;
import study.posthub.domain.post.repository.PostRepositoryCustom;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
                        post.likeCount,
                        post.createdAt))
                .from(post)
                .orderBy(Objects.requireNonNull(getOrderSpecifier(pageable)).toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long totalCount = result.size();

        return new PageImpl<>(result, pageable, totalCount);
    }

    @Override
    public Page<PostViewResponse> loadPostsByTitle(String title, Pageable pageable) {
        List<PostViewResponse> result = factory.select(Projections.constructor(PostViewResponse.class,
                        post.id,
                        post.title,
                        post.content,
                        post.author,
                        post.commentCount,
                        post.viewCount,
                        post.likeCount,
                        post.createdAt))
                .from(post)
                .where(post.title.contains(title),
                        post.delYN.eq(0L))
                .orderBy(Objects.requireNonNull(getOrderSpecifier(pageable)).toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long totalCount = result.size();

        return new PageImpl<>(result, pageable, totalCount);
    }

    private List<OrderSpecifier<?>> getOrderSpecifier(Pageable pageable) {
        List<OrderSpecifier<?>> orders = new ArrayList<>();

        if (pageable.getSort().isEmpty()) return null;

        for (Sort.Order order : pageable.getSort()) {
            Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;

            if (order.getProperty().equals("VIEW")) {
                orders.add(new OrderSpecifier<>(direction, post.viewCount));
            } else {
                orders.add(new OrderSpecifier<>(direction, post.createdAt));
            }
        }

        return orders;
    }
}
