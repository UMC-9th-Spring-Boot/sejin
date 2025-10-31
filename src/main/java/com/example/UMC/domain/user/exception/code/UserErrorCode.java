package com.example.UMC.domain.user.exception.code;

import com.example.UMC.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER4001", "해당 사용자를 찾을 수 없습니다."),
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "USER4002","이미 존재하는 이메일입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
