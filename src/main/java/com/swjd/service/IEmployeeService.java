package com.swjd.service;

import com.swjd.pojo.Employee;

import java.util.List;

public interface IEmployeeService {
    public  boolean  update(Employee employee);
    public  Employee  getEmployee(Integer empNo);
    public List<Employee> getEmployees();
}
