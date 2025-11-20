package com.example.UMC.domain.mission.dto.response;

import com.example.UMC.domain.enums.entity.MissionStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserMissionInProgressResponse {
    private Long userMissionId;
    private String missionTitle;
    private MissionStatus status;
}

