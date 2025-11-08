package com.example.UMC.domain.store.exception;

import com.example.UMC.global.apiPayload.code.BaseErrorCode;
import com.example.UMC.global.apiPayload.exception.GeneralException;

public class StoreException extends GeneralException {
    public StoreException(BaseErrorCode code) {
        super(code);
    }
}
