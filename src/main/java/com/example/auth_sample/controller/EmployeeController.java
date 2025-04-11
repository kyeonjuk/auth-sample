package com.example.auth_sample.controller;

import com.example.auth_sample.controller.dto.requestDto.PostEmployeeRequestDto;
import com.example.auth_sample.model.Employee;
import com.example.auth_sample.service.EmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name="Basics", description = "기본 관리 API")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping(value = "/employees",
    produces = MediaType.APPLICATION_JSON_VALUE)    // 호출 시 return 값 : json
    public ResponseEntity<List<Employee>> listAll() {
        return new ResponseEntity<>(employeeService.listEmployees(), HttpStatus.OK);
    }

    @PostMapping(value = "/employees",
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> create(@RequestBody PostEmployeeRequestDto dto) {

        Employee newEmployee = employeeService.createEmployee(dto);

        return new ResponseEntity<>(newEmployee, HttpStatus.OK);
    }
}
