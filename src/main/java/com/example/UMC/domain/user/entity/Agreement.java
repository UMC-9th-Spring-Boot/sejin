package com.example.UMC.domain.user.entity;

import com.example.UMC.global.commmon.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "agreement")
public class Agreement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agreement_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "is_privacy", nullable = false)
    private Boolean isPrivacy;

    @Column(name = "is_service", nullable = false)
    private Boolean isService;
}
