package com.example.auth_sample.controller.dto.requestDto;

import com.example.auth_sample.model.Employee;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostEmployeeRequestDto {

    private String firstName;
    private String lastName;
    private Long departmentId;
    private String kakaoNickName;

    public Employee toEmployee() {
        return Employee.builder()
            .firstName(firstName)
            .lastName(lastName)
            .departmentId(departmentId)
            .kakaoNickName(kakaoNickName)
            .build();
    }
}
