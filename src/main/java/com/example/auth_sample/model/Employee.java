package com.example.auth_sample.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.Set;
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

    @ManyToMany
    @JoinTable(     // employee_role_mapping 테이블의 id 컬럼을 현재 테이블의 employee_id 컬럼에 매핑
        name = "employee_role_mapping",
        joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;
}
