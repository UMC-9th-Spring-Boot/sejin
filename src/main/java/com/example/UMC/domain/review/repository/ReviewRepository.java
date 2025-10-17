package com.example.UMC.domain.review.repository;

import com.example.UMC.domain.review.entity.Review;
import com.example.UMC.domain.store.entity.Store;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryRepository {



    /**
     * 1. 리뷰 조회
     * 특정 가게(store)의 리뷰를 최신순으로 조회
     * (작성자(User) 엔티티까지 한 번에 가져오기)
     */
    @EntityGraph(attributePaths = {"user"}) // Review의 user 필드 fetch join
    List<Review> findAllByStoreOrderByCreatedAtDesc(Store store);

}
