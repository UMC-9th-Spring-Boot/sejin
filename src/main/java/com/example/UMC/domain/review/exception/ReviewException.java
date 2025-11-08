package com.example.UMC.domain.review.exception;

import com.example.UMC.global.apiPayload.code.BaseErrorCode;
import com.example.UMC.global.apiPayload.exception.GeneralException;

public class ReviewException extends GeneralException {
    public ReviewException(BaseErrorCode code) {
        super(code);
    }
}
