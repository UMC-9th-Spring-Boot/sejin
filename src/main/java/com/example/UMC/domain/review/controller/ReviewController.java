package com.example.UMC.domain.review.controller;

import com.example.UMC.domain.review.dto.request.ReviewCreateRequest;
import com.example.UMC.domain.review.dto.response.MyReviewResponse;
import com.example.UMC.domain.review.dto.response.ReviewResponse;
import com.example.UMC.domain.review.entity.Review;
import com.example.UMC.domain.review.repository.ReviewQueryRepository;
import com.example.UMC.domain.review.service.ReviewService;
import com.example.UMC.global.annotation.ValidPage;
import com.example.UMC.global.apiPayload.ApiResponse;
import com.example.UMC.global.apiPayload.code.GeneralSucessCode;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
@Validated
public class ReviewController {

    private final ReviewQueryRepository reviewQueryRepository;
    private final ReviewService reviewService;

    /**
     * [POST] /api/stores/{storeId}/reviews
     * 가게에 리뷰 작성 API
     */
    @PostMapping("/stores/{storeId}")
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
    @GetMapping("/stores/test")
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

    @GetMapping("/me")
    @Operation(summary = "내가 작성한 리뷰 목록 조회")
    public ApiResponse<List<MyReviewResponse>> getMyReviews(
            @RequestParam Long userId,
            @RequestParam(defaultValue = "1") @ValidPage Integer page
    ) {
        int pageIndex = page - 1;
        List<MyReviewResponse> result = reviewService.getMyReviews(userId, pageIndex);

        return ApiResponse.onSucess(GeneralSucessCode.OK, result);
    }
}

