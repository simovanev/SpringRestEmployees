package com.spring.cruddemo.dao;

import com.spring.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
}
