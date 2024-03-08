package com.bosonit.block10dockerizeapp.dao;

import com.bosonit.block10dockerizeapp.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeDao {
    List<Employee> findAll();

    void insertEmployee(Employee emp);

    void updateEmployee(Employee emp);

    void executeUpdateEmployee(Employee emp);

    public void deleteEmployee(Employee emp);
}

