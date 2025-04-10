package com.example.auth_sample.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "123", description = "auto increment pk")
    private Long id;

    @Schema(example = "길동", description = "이름")
    private String firstName;

    @Schema(example = "홍", description = "성")
    private String lastName;

    @Schema(example = "123", description = "부서 ID")
    private Long departmentId;
}
