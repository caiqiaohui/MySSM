package com.swjd.service;

import com.swjd.dao.IEmployeeDAO;
import com.swjd.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private IEmployeeDAO employeeDAO;
    @Override
    public boolean update(Employee employee) {
        int  row=employeeDAO.update(employee);
        if(row>0)
            return  true;
        else
            return false;
    }

    @Override
    public Employee getEmployee(Integer empNo) {
        Employee  employee=employeeDAO.queryForObject(empNo);
        return employee;
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeDAO.queryForList();
    }
}
