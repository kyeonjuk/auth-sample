package com.example.auth_sample.service;

import com.example.auth_sample.controller.dto.requestDto.PostEmployeeRequestDto;
import com.example.auth_sample.model.Employee;
import com.example.auth_sample.repository.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;


    public List<Employee> listEmployees() {
        return employeeRepository.findAll();
    }

    public Employee createEmployee(PostEmployeeRequestDto dto) {

        Employee employee = dto.toEmployee();
        employeeRepository.save(employee);

        return employee;
    }

}
