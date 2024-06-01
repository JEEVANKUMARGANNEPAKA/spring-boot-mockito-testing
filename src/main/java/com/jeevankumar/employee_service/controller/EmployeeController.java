package com.jeevankumar.employee_service.controller;

import com.jeevankumar.employee_service.entity.Employee;
import com.jeevankumar.employee_service.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping(value = "/saveEmployee")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        Employee savedEmployee = this.employeeService.saveEmployee(employee);
        return savedEmployee != null ?
                ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee):
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }
    @GetMapping(value = "/findAllEmployees")
    public ResponseEntity<List<Employee>> findAllEmployees(){
        List<Employee> employees = this.employeeService.getAllEmployees();
        return employees != null ?
                ResponseEntity.status(HttpStatus.OK).body(employees):
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

}
