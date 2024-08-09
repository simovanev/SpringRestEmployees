package com.spring.cruddemo.rest;

import com.spring.cruddemo.dao.EmployeeDAO;
import com.spring.cruddemo.entity.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeDAO employeeDAO;

    public EmployeeRestController(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeDAO.findAll();
    }
}
