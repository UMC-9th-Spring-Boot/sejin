package com.example.UMC.domain.mission.repository;

import com.example.UMC.domain.mission.entity.Mission;
import com.example.UMC.domain.store.entity.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    /**
     * 4. 홈 화면용 미션 목록 조회
     * - 현재 지역(region)에서 도전 가능한 미션
     * - 페이징 포함
     */
    @Query("""
        SELECT m
        FROM Mission m
        JOIN FETCH m.store s
        WHERE s.region = :region
        ORDER BY m.endsAt ASC
    """)
    Page<Mission> findAllByRegion(@Param("region") Region region, Pageable pageable);
}
