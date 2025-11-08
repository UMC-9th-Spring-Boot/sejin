package com.example.UMC.domain.review.exception.code;

import com.example.UMC.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW4004", "해당 리뷰를 찾을 수 없습니다."),
    DUPLICATE_REVIEW(HttpStatus.CONFLICT, "REVIEW4090", "이미 리뷰를 작성했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
