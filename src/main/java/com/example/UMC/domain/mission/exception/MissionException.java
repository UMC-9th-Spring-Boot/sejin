package com.example.UMC.domain.mission.exception;

import com.example.UMC.global.apiPayload.code.BaseErrorCode;
import com.example.UMC.global.apiPayload.exception.GeneralException;

public class MissionException extends GeneralException {
    public MissionException(BaseErrorCode code) {
        super(code);
    }
}
