package com.swjd.dao;

import com.swjd.pojo.Employee;

import java.util.List;

public interface IEmployeeDAO {
    int insert(Employee employee);
    int delete(Integer id);
    int update(Employee employee);
    Employee  queryForObject(Integer id);
    List<Employee> queryForList();

}
