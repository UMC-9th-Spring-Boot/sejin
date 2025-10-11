package com.example.UMC.domain.user.repository;

import com.example.UMC.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DashboardRepository extends JpaRepository<User, Long> {

    /**
     * 4. 홈화면 대시보드 조회
     * - 최근 리뷰한 가게 지역 + 포인트 + 알람 여부 + 완료 미션 수
     */
    @Query("""
        SELECT 
            s.region.name,
            u.point,
            CASE WHEN EXISTS (
                SELECT n FROM Notification n 
                WHERE n.user = u AND n.isRead = false
            ) THEN true ELSE false END AS alram,
            (SELECT COUNT(um) 
             FROM UserMission um 
             WHERE um.user = u 
               AND um.status = com.example.UMC.domain.enums.entity.MissionStatus.COMPLETE)
        FROM User u
        JOIN Review r ON r.user = u
        JOIN Store s ON r.store = s
        WHERE u.id = :userId
        ORDER BY r.createdAt DESC
    """)
    Object findDashboardInfo(@Param("userId") Long userId);
}
