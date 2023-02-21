package com.test.bankapi.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person",
        uniqueConstraints = @UniqueConstraint(name = "person_unique_identification_constraint",
                columnNames = {"identification"}))
public class Person {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String gender;

    @NotNull
    @Min(value = 18)
    @Column(nullable = false)
    private Integer age;

    @NotNull
    @NotBlank
    @Size(max = 20)
    @Column(nullable = false, unique = true, length = 20)
    private String identification;

    @NotNull
    @NotBlank
    @Size(max = 200)
    @Column(nullable = false, length = 200)
    private String address;

    @NotNull
    @NotBlank
    @Size(max = 20)
    @Column(nullable = false, length = 20)
    private String phone;
}
