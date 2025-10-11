package com.example.UMC.domain.user.repository;


import com.example.UMC.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 2.마이페이지 조회용 — user_id로 단일 유저 조회
     * (CASE, JOIN 없이 단순 조회만)
     */
    Optional<User> findById(Long userId);

    // 기준으로 조회 (로그인/마이페이지 공용으로 활용 가능)
    Optional<User> findByEmail(String email);

     // 이름 + 이메일 같이 조건 조회 (확장형)
    Optional<User> findByNameAndEmail(String name, String email);
}
