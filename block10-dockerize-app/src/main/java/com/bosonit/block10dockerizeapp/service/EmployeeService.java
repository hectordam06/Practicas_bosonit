package com.bosonit.block10dockerizeapp.service;

import com.bosonit.block10dockerizeapp.dao.EmployeeDao;
import com.bosonit.block10dockerizeapp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    public void insertEmployee(Employee emp) {
        employeeDao.insertEmployee(emp);
    }

    public void updateEmployee(Employee emp) {
        employeeDao.updateEmployee(emp);
    }

    public void executeUpdateEmployee(Employee emp) {
        employeeDao.executeUpdateEmployee(emp);
    }

    public void deleteEmployee(Employee emp) {
        employeeDao.deleteEmployee(emp);
    }
}