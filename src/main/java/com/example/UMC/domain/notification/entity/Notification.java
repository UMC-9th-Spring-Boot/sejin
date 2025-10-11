package com.example.UMC.domain.notification;


import com.example.UMC.domain.BaseEntity;
import com.example.UMC.domain.user.User;
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

