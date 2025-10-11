package com.example.UMC.domain.mission.repository;


import com.example.UMC.domain.mission.entity.UserMission;
import com.example.UMC.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    /**
     * 3. 내 미션 목록 조회 (진행 중 / 완료 포함)
     * - 페이징 지원
     * - User 기준
     */
    @Query("""
        SELECT um
        FROM UserMission um
        JOIN FETCH um.mission m
        JOIN FETCH m.store s
        WHERE um.user = :user
        ORDER BY um.completeAt DESC
    """)
    Page<UserMission> findAllByUser(@Param("user") User user, Pageable pageable);
}

