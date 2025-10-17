package com.example.UMC.domain.review.repository;

import com.example.UMC.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryRepository {

    Page<Review> findReviews(
            Long storeId,         // 가게ID 필터
            String storeName,     // 가게명 필터
            Long regionId,        // 지역ID 필터
            Integer star,         // 별점 필터
            Pageable pageable     // 페이징
    );
}
