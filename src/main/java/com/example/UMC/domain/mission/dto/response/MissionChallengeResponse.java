package com.example.UMC.domain.mission.dto.response;

import com.example.UMC.domain.enums.entity.MissionStatus;
import lombok.Builder;

@Builder
public record MissionChallengeResponse(
        Long userMissionId,
        Long missionId,
        Long userId,
        MissionStatus status
) {}
