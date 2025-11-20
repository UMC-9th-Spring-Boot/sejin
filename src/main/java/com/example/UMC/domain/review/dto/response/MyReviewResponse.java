package com.example.UMC.domain.review.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MyReviewResponse {
    private Long reviewId;
    private Integer rating;
    private String content;
    private String storeName;
    private LocalDateTime createdAt;
}
