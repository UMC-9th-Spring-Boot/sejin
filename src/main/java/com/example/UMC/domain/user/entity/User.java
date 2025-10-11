package com.example.UMC.domain.user;

import com.example.UMC.domain.BaseEntity;
import com.example.UMC.domain.mission.UserMission;
import com.example.UMC.domain.notification.Notification;
import com.example.UMC.domain.review.Review;
import com.example.UMC.domain.review.ReviewReply;
import com.example.UMC.domain.enums.*;
import com.example.UMC.domain.likes.UserLikeFood;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, length = 10)
    private Gender gender;

    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "birth", length = 20)
    private String birth;

    @Column(name = "address", length = 255)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 10)
    private Status status;

    @Column(name = "privacy_agree", nullable = false)
    private Boolean privacyAgree;

    @Column(name = "id", nullable = false, length = 100)
    private String loginId;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "point", nullable = false)
    private Integer point;

    //연관관계

    // 약관 동의 1:N
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Agreement> agreements;

    // 알림 1:N
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Notification> notifications;

    // 리뷰 1:N
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Review> reviews;

    // 리뷰 답글 1:N
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<ReviewReply> reviewReplies;

    // 선호 음식 (User_Like_Food) 1:N
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserLikeFood> likeFoods;

    // 유저 미션 (User_Mission) 1:N
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserMission> missions;
}
