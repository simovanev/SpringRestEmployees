package com.spring.cruddemo.service;

import com.spring.cruddemo.entity.Employee;
import com.spring.cruddemo.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> byId = employeeRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else throw new RuntimeException("Employee not found");
    }

    @Override

    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override

    public void delete(int id) {
        employeeRepository.deleteById(id);
    }
}
