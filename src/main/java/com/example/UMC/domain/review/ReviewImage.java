package com.example.UMC.domain.review;

import com.example.UMC.domain.store.Region;
import com.example.UMC.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "review_img")
public class ReviewImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_img_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resion_id", nullable = false)
    private Region region;

    @Column(name = "review_img_url", nullable = false, length = 255)
    private String imageUrl;
}
