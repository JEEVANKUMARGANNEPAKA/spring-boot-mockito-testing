package com.jeevankumar.employee_service;

import com.jeevankumar.employee_service.entity.Employee;
import com.jeevankumar.employee_service.repositories.EmployeeRepository;
import com.jeevankumar.employee_service.service.EmployeeService;
import com.jeevankumar.employee_service.serviceImpl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(value = {MockitoExtension.class})
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;
    private  Employee employee;
    @BeforeEach
    public void beforeTest(){
       employee = Employee.builder()
              .id(1L)
              .firstName("Jeevan")
              .lastName("Kumar")
              .email("jeevan@gmail.com")
              .build();
    }
    @DisplayName("JUnit test for saveEmployee method")
    @Test
    public void saveEmployeeTest(){
        when(employeeRepository.findByEmail(employee.getEmail())).thenReturn(Optional.empty());
        when(employeeRepository.save(employee)).thenReturn(employee);
        System.out.println(employeeRepository);
        System.out.println(employeeService);
        Employee savedEmployee = employeeService.saveEmployee(employee);
        System.out.println(savedEmployee);
        assertEquals(savedEmployee.getEmail().equals(employee.getEmail()),true);
        assertThat(savedEmployee).isNotNull();
    }
    @DisplayName("Junit test find All Employee method")
    @Test
    public void findAllEmployeeTest(){
       Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("Hello")
                .lastName("World")
                .email("helloworld@gmail.com")
                        .build();
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee,employee1));
        List<Employee> employees = employeeService.getAllEmployees();
        System.out.println(employees.toString());
        assertEquals(2,employees.size());
    }
}
