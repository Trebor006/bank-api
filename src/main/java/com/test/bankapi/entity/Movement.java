package com.test.bankapi.entity;

import com.test.bankapi.enums.MovementType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movement")
public class Movement {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime date;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MovementType type;

    @NotNull
    @Column(nullable = false)
    private Double value;

    @NotNull
    @Column(nullable = false)
    private Double balance;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
}
