package com.example.auth_sample.service;

import com.example.auth_sample.controller.dto.requestDto.PostEmployeeRequestDto;
import com.example.auth_sample.model.Employee;
import com.example.auth_sample.repository.EmployeeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;


    public List<Employee> listEmployees() {
        return employeeRepository.findAll();
    }

    public boolean existsByKakaoNickName(String kakaoNickName) {
        return employeeRepository.existsByKakaoNickName(kakaoNickName);
    }

    public Employee createEmployee(PostEmployeeRequestDto dto) {

        // 중복 처리 (보통은 이메일로 중복 가입 방지)
        if (existsByKakaoNickName(dto.getKakaoNickName())) {
            throw new DuplicateKeyException("이미 가입된 카카오 닉네임 입니다.");
        }

        Employee employee = dto.toEmployee();
        employeeRepository.save(employee);

        return employee;
    }

}
