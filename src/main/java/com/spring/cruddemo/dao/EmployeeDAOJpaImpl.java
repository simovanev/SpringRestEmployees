package com.spring.cruddemo.dao;

import com.spring.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {
    private EntityManager manager;

    public EmployeeDAOJpaImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> query = manager.createQuery(
                "SELECT e FROM Employee e", Employee.class);
        return query.getResultList();
    }
}
