package com.example.UMC.domain.review.controller;

import com.example.UMC.domain.review.dto.request.ReviewCreateRequest;
import com.example.UMC.domain.review.dto.response.ReviewResponse;
import com.example.UMC.domain.review.entity.Review;
import com.example.UMC.domain.review.repository.ReviewQueryRepository;
import com.example.UMC.domain.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class ReviewController {

    private final ReviewQueryRepository reviewQueryRepository;
    private final ReviewService reviewService;

    /**
     * [POST] /api/stores/{storeId}/reviews
     * 가게에 리뷰 작성 API
     */
    @PostMapping("/{storeId}/reviews")
    public ResponseEntity<ReviewResponse> createReview(
            @RequestHeader("X-USER-ID") Long userId,
            @PathVariable Long storeId,
            @RequestBody @Valid ReviewCreateRequest request) {
        ReviewResponse response = reviewService.createReview(userId, storeId, request);
        return ResponseEntity.ok(response);
    }

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

