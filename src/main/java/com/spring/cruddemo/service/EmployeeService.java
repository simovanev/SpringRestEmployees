package com.spring.cruddemo.service;

import com.spring.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int id);

    void save(Employee employee);

    void delete(int id);
}

