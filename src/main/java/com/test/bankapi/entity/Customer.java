package com.test.bankapi.entity;

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
@Table(name = "customer")
public class Customer {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    @NotNull
    @Size(max = 100)
    @NotBlank
    @Column(nullable = false, length = 100)
    private String password;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
}
