package com.example.UMC.domain.review.repository;

import com.example.UMC.domain.review.entity.QReview;
import com.example.UMC.domain.review.entity.Review;
import com.example.UMC.domain.store.entity.QRegion;
import com.example.UMC.domain.store.entity.QStore;
import com.example.UMC.domain.user.entity.QUser;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
@Primary //컨트롤러 테스트 용도로 잠시 붙힘
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Review> findReviews(Long storeId, String storeName, Long regionId, Integer star, Pageable pageable) {
        // Q클래스들 import
        QReview review = QReview.review;
        QStore store = QStore.store;
        QUser user = QUser.user;
        QRegion region = QRegion.region;

        BooleanBuilder where = new BooleanBuilder();

        if (storeId != null) {
            where.and(store.id.eq(storeId));
        } else if (storeName != null && !storeName.isBlank()) {
            where.and(store.name.containsIgnoreCase(storeName));
        }

        if (regionId != null) {
            where.and(region.id.eq(regionId));
        }

        if (star != null) {
            where.and(review.rating.eq(star));
        }

        List<Review> content = queryFactory
                .selectFrom(review)
                .join(review.user, user).fetchJoin()
                .join(review.store, store).fetchJoin()
                .join(review.region, region).fetchJoin()
                .where(where)
                .orderBy(review.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(review.count())
                .from(review)
                .join(review.user, user)
                .join(review.store, store)
                .join(review.region, region)
                .where(where)
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }
}

