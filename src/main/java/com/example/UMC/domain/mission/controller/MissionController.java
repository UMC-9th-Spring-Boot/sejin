package com.example.UMC.domain.mission.controller;

import com.example.UMC.domain.mission.dto.response.MissionChallengeResponse;
import com.example.UMC.domain.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/my/missions")
public class MissionController {

    private final MissionService missionService;

    /**
     * [POST] /api/my/missions/{missionId}/challenge
     * 미션 도전하기
     */
    @PostMapping("/{missionId}/challenge")
    public ResponseEntity<MissionChallengeResponse> challengeMission(
            @RequestHeader("X-USER-ID") Long userId,
            @PathVariable Long missionId
    ) {
        MissionChallengeResponse response = missionService.challengeMission(userId, missionId);
        return ResponseEntity.ok(response);
    }
}
