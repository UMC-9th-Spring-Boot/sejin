package com.example.UMC.domain.mission.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoreMissionResponse {
    private Long missionId;
    private String title;
    private Integer minSpend;
    private Integer point;
}

