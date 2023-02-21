package com.test.bankapi.entity;

import com.test.bankapi.enums.AccountType;
import com.test.bankapi.enums.StatusEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account",
        uniqueConstraints = @UniqueConstraint(name = "account_unique_number_constraint",
                columnNames = {"number"}))
public class Account {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @NotNull
    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, unique = true, length = 50)
    private String number;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @NotNull
    @Column(nullable = false)
    private Double maxDailyDebitAmount;

    @NotNull
    @Column(nullable = false)
    private Double initialBalance;

    @NotNull
    @Column(nullable = false)
    private Double balance;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
}
