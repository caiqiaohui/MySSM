package com.swjd.test;

import com.swjd.dao.IEmployeeDAO;
import com.swjd.pojo.Employee;
import com.swjd.service.IEmployeeService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class JdbcTest extends TestCase {
    @Autowired
    private IEmployeeService employeeService;
    @Test
    public  void testUpdate(){
        Employee  employee=new Employee();
        employee.setId(1);
        employee.setName("小蔡");
        employee.setGender("女");

        employee.setBirthday("2020-1-1");
        boolean result=employeeService.update(employee);
        assertEquals(true,result);

    }
    @Test
    public   void testQuery(){
        Employee  employee=employeeService.getEmployee(1);
        System.out.println(employee);
    }
@Test
    public  void testQueryList(){
    List<Employee>  employeeList=employeeService.getEmployees();
    for (Employee employee:employeeList){
        System.out.println(employee);
    }
    }

}
