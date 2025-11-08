package com.example.UMC.domain.mission.exception.code;

import com.example.UMC.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION4004", "해당 미션을 찾을 수 없습니다."),
    MISSION_ALREADY_CHALLENGING(HttpStatus.CONFLICT, "MISSION4090", "이미 도전 중인 미션입니다."),
    MISSION_ALREADY_COMPLETED(HttpStatus.CONFLICT, "MISSION4091", "이미 완료한 미션입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
