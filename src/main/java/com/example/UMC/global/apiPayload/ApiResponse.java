package com.example.UMC.global.apiPayload;

import com.example.UMC.global.apiPayload.code.BaseErrorCode;
import com.example.UMC.global.apiPayload.code.BaseSucessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    private final Boolean isSucess;
    private final String code;
    private final String message;
    private final T result;

    // 성공 응답
    public static <T> ApiResponse<T> onSucess(BaseSucessCode code, T result){
        return new ApiResponse<>(true, code.getCode(), code.getMessage(), result);
    }

    //실패 응답
    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result) {
        return new ApiResponse<>(false, code.getCode(), code.getMessage(), result);
    }
}
