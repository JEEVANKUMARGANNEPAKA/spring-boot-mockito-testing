package com.jeevankumar.employee_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeevankumar.employee_service.controller.EmployeeController;
import com.jeevankumar.employee_service.entity.Employee;
import com.jeevankumar.employee_service.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(value = {MockitoExtension.class})
public class EmployeeControllerTest {
    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private EmployeeController employeeController;
    @Autowired
    private MockMvc mockMvc;
    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }
    @DisplayName(value = "Junit saved method testing inside controller layer")
    @Test
    public void saveEmployeeTest() throws Exception {
        Employee employee = Employee.builder()
                .id(1L)
                .firstName("Jeevan")
                .lastName("Kumar")
                .email("jeevan@gmail.com")
                .build();
        when(employeeService.saveEmployee(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(post("/employees/saveEmployee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(employee)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("Jeevan"));

    }
    @DisplayName(value = "Junit testing of controller class with find All Employees method")
    @Test
    public void findAllEmployeesTest() throws Exception {
        Employee employee1 = Employee.builder().id(1L).firstName("Jeevan").build();
        Employee employee2 = Employee.builder().id(2L).firstName("Hello").build();
        List<Employee> employees = Arrays.asList(employee1,employee2);
        when(employeeService.getAllEmployees()).thenReturn(employees);
        mockMvc.perform(get("/employees/findAllEmployees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].firstName").value("Hello"));

    }
}
