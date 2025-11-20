package com.example.UMC.domain.review.converter;

import com.example.UMC.domain.review.dto.response.MyReviewResponse;
import com.example.UMC.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewConverter {

    public List<MyReviewResponse> toMyReviews(Page<Review> reviewPage) {

        return reviewPage.getContent()
                .stream()
                .map(r -> MyReviewResponse.builder()
                        .reviewId(r.getId())
                        .rating(r.getRating())
                        .content(r.getContent())
                        .storeName(r.getStore().getName())
                        .createdAt(r.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }
}

