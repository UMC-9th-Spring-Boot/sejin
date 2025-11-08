package com.example.UMC.domain.review.service;

import com.example.UMC.domain.review.dto.request.ReviewCreateRequest;
import com.example.UMC.domain.review.dto.response.ReviewResponse;
import com.example.UMC.domain.review.entity.Review;
import com.example.UMC.domain.review.repository.ReviewRepository;
import com.example.UMC.domain.store.entity.Store;
import com.example.UMC.domain.store.exception.StoreException;
import com.example.UMC.domain.store.exception.code.StoreErrorCode;
import com.example.UMC.domain.store.repository.StoreRepository;
import com.example.UMC.domain.user.entity.User;
import com.example.UMC.domain.user.exception.UserException;
import com.example.UMC.domain.user.exception.code.UserErrorCode;
import com.example.UMC.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public ReviewResponse createReview(Long userId, Long storeId, ReviewCreateRequest request) {

        //유저 존재 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        //가게 검증
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        //리뷰 생성
        Review review = Review.builder()
                .store(store)
                .user(user)
                .region(store.getRegion())
                .rating(request.rating())
                .content(request.content())
                .build();

        Review saved = reviewRepository.save(review);

        //응답 DTO 변환
        return new ReviewResponse(
                saved.getId(),
                store.getId(),
                user.getId(),
                saved.getRating(),
                saved.getContent()
        );
    }
}
