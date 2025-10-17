package com.example.UMC.domain.review.controller;

import com.example.UMC.domain.review.entity.Review;
import com.example.UMC.domain.review.repository.ReviewQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewQueryRepository reviewQueryRepository;

    // 태스트 컨틀로러

    /**
     *  현재 이코드는 무한순회가 도는중 유저 - 리뷰 가 양방향 매핑이라 그래서 entity구조 그대로 하고 무한순회 안돌게
     *  DTO사용이 필요해 보임.
     */
    @GetMapping
    public Page<Review> getReviews(
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Long regionId,
            @RequestParam(required = false) Integer star,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return reviewQueryRepository.findReviews(storeId, storeName, regionId, star, pageable);
    }
}

