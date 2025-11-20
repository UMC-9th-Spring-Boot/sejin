package com.example.UMC.domain.mission.converter;

import com.example.UMC.domain.mission.dto.response.StoreMissionResponse;
import com.example.UMC.domain.mission.dto.response.UserMissionInProgressResponse;
import com.example.UMC.domain.mission.entity.Mission;
import com.example.UMC.domain.mission.entity.UserMission;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MissionConverter {

    public List<StoreMissionResponse> toStoreMissionList(Page<Mission> missions) {
        return missions.getContent().stream()
                .map(m -> StoreMissionResponse.builder()
                        .missionId(m.getId())
                        .title(m.getTitle())
                        .minSpend(m.getMinSpend())
                        .point(m.getPoint())
                        .build())
                .collect(Collectors.toList());
    }

    public List<UserMissionInProgressResponse> toUserMissionList(Page<UserMission> missions) {
        return missions.getContent().stream()
                .map(um -> UserMissionInProgressResponse.builder()
                        .userMissionId(um.getId())
                        .missionTitle(um.getMission().getTitle())
                        .status(um.getStatus())
                        .build())
                .collect(Collectors.toList());
    }
}

