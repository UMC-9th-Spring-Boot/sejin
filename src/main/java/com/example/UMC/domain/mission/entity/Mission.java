package com.example.UMC.domain.mission;

import com.example.UMC.domain.store.Store;
import com.example.UMC.domain.store.Region;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "misson")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "misson_id")
    private Long id;

    @Column(name = "title", nullable = false, length = 120)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "min_spend")
    private Integer minSpend;

    @Column(name = "point")
    private Integer point;

    @Column(name = "starts_at")
    private LocalDateTime startsAt;

    @Column(name = "ends_at")
    private LocalDateTime endsAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resion_id", nullable = false)
    private Region region;
}
