package com.example.UMC.domain.mission.service;

import com.example.UMC.domain.enums.entity.MissionStatus;
import com.example.UMC.domain.mission.dto.response.MissionChallengeResponse;
import com.example.UMC.domain.mission.entity.Mission;
import com.example.UMC.domain.mission.entity.UserMission;
import com.example.UMC.domain.mission.exception.MissionException;
import com.example.UMC.domain.mission.exception.code.MissionErrorCode;
import com.example.UMC.domain.mission.repository.MissionRepository;
import com.example.UMC.domain.mission.repository.UserMissionRepository;
import com.example.UMC.domain.user.entity.User;
import com.example.UMC.domain.user.exception.UserException;
import com.example.UMC.domain.user.exception.code.UserErrorCode;
import com.example.UMC.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MissionService {
    private final MissionRepository missionRepository;
    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository;

    /**
     * 미션 도전하기 API
     */
    @Transactional
    public MissionChallengeResponse challengeMission(Long userId, Long missionId) {

        //유저 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        //미션 확인
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        //유저가 이미 같은 미션을 도전 중이거나 완료했는지 확인
        var existing = userMissionRepository.findAllByUser(user, null)
                .stream()
                .filter(um -> um.getMission().getId().equals(missionId))
                .findFirst();

        if (existing.isPresent()) {
            var current = existing.get();
            if (current.getStatus() == MissionStatus.PROCESS)
                throw new MissionException(MissionErrorCode.MISSION_ALREADY_CHALLENGING);
            if (current.getStatus() == MissionStatus.COMPLETE)
                throw new MissionException(MissionErrorCode.MISSION_ALREADY_COMPLETED);
        }

        //새로운 UserMisson 생성
        UserMission newChanllenge=UserMission.builder()
                .user(user)
                .mission(mission)
                .region(mission.getRegion())
                .status(MissionStatus.PROCESS)
                .challengeAt(LocalDateTime.now())
                .build();

        UserMission saved = userMissionRepository.save(newChanllenge);

        //응답 반환
        return MissionChallengeResponse.builder()
                .userMissionId(saved.getId())
                .missionId(mission.getId())
                .userId(user.getId())
                .status(saved.getStatus())
                .build();
    }
}