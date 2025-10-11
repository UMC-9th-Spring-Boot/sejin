package com.example.UMC.domain.mission;

import com.example.UMC.domain.enums.MissionStatus;
import com.example.UMC.domain.store.Region;
import com.example.UMC.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_misson")
public class UserMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_misson_id")
    private Long id;

    @Column(name = "verrification_code", length = 16)
    private String verificationCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private MissionStatus status;

    @Column(name = "challenge_at")
    private LocalDateTime challengeAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "misson_id", nullable = false)
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resion_id", nullable = false)
    private Region region;
}
