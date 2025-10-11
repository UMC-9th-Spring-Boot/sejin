package com.example.UMC.domain.store;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "resion")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resion_id")
    private Long id;

    @Column(name = "resion_name", nullable = false, length = 255)
    private String name;
}
