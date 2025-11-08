package com.example.UMC.domain.review.dto.response;

public record ReviewResponse(
   Long reviewId,
   Long storeId,
   Long userId,
   Integer rating,
   String content
) {}
