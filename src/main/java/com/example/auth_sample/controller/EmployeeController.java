package com.example.auth_sample.controller;

import com.example.auth_sample.model.Employee;
import com.example.auth_sample.service.EmployeeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping(value = "/employees",
    produces = MediaType.APPLICATION_JSON_VALUE)    // 호출 시 return 값 : json
    public ResponseEntity<List<Employee>> listAll() {
        return new ResponseEntity<>(employeeService.listEmployees(), HttpStatus.OK);
    }
}
