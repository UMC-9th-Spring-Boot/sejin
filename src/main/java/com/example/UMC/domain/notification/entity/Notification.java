package com.example.UMC.domain.notification.entity;


import com.example.UMC.global.commmon.BaseEntity;
import com.example.UMC.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "notification")
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "is_notification", nullable = false)
    private Boolean isNotification;

    @Column(name = "is_read", nullable = false)
    private Boolean isRead;
}

