package com.example.UMC.domain.mission.controller;

import com.example.UMC.domain.mission.dto.response.MissionChallengeResponse;
import com.example.UMC.domain.mission.dto.response.StoreMissionResponse;
import com.example.UMC.domain.mission.dto.response.UserMissionInProgressResponse;
import com.example.UMC.domain.mission.service.MissionService;
import com.example.UMC.global.annotation.ValidPage;
import com.example.UMC.global.apiPayload.ApiResponse;
import com.example.UMC.global.apiPayload.code.GeneralSucessCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/my/missions")
@Validated
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

    @GetMapping("/stores/{storeId}")
    @Operation(summary = "특정 가게의 미션 목록 조회")
    public ApiResponse<List<StoreMissionResponse>> getStoreMissions(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "1") @ValidPage Integer page
    ) {
        int pageIndex = page - 1;
        return ApiResponse.onSucess(
                GeneralSucessCode.OK,
                missionService.getStoreMissions(storeId, pageIndex)
        );
    }

    @GetMapping("/me/in-progress")
    @Operation(summary = "내가 진행중인 미션 조회")
    public ApiResponse<List<UserMissionInProgressResponse>> getUserInProgress(
            @RequestParam Long userId,
            @RequestParam(defaultValue = "1") @ValidPage Integer page
    ) {
        int pageIndex = page - 1;
        return ApiResponse.onSucess(
                GeneralSucessCode.OK,
                missionService.getUserInProgress(userId, pageIndex)
        );
    }
}
