package com.swjd.dao;

import com.swjd.pojo.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeDAO implements IEmployeeDAO {
    @Resource(name="jdbcTemplate")
    private JdbcTemplate  jdbcTemplate;
    @Override
    public int insert(Employee employee) {
        String sql="insert into employee(name,gender,birthday) values(?,?,?)";
        int row=jdbcTemplate.update(sql,employee.getName(),employee.getGender(),employee.getBirthday());
        return row;
    }

    @Override
    public int delete(Integer id) {
        String sql="delete from employee  where id=?";
        int row=jdbcTemplate.update(sql,id);
        return row;
    }

    @Override
    public int update(Employee employee) {

        int row=0;

        final List<Object> args=new ArrayList<>();

        StringBuffer  sql=new StringBuffer("update employee set ");
        if(employee.getName()!=null){
            sql.append("name=?,");
            args.add(employee.getName());
        }
        if(employee.getGender()!=null) {
            sql.append("gender=?,");
            args.add(employee.getGender());
        }
        if(employee.getBirthday()!=null) {
            sql.append("birthday=?,");
            args.add(employee.getBirthday());
        }
        StringBuffer newSQL=new StringBuffer(sql.substring(0,sql.lastIndexOf(",")));
        newSQL.append(" where id=?");
        args.add(employee.getId());

        row=jdbcTemplate.update(newSQL.toString(), new PreparedStatementSetter() {
           @Override
           public void setValues(PreparedStatement preparedStatement) throws SQLException {
               for (int i=0;i<args.size();i++){
                   preparedStatement.setObject(i+1,args.get(i));
               }
           }
       });
        return row;
    }

    @Override
    public Employee queryForObject(Integer id) {
        String sql="select * from  employee where id=?";
        Employee  employee=jdbcTemplate.queryForObject(sql, new RowMapper<Employee>() {
            @Override
            public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
                Employee  employee=new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setGender(resultSet.getString("gender"));
                employee.setBirthday(resultSet.getString("birthday"));
                return employee;
            }
        },id);
        return employee;
    }

    @Override
    public List<Employee> queryForList() {
        List<Employee>  employees=new ArrayList<>();
        String  sql="select * from employee";
        List<Map<String, Object>> employeeList=jdbcTemplate.queryForList(sql);
        for (Map<String,Object>  map:employeeList){
            Employee employee=new Employee();
            Integer  id=Integer.valueOf(map.get("id").toString());
            String  name=(String)map.get("name");
            String  gender=(String)map.get("gender");
            String birthday=(String)map.get("birthday");
            employee.setId(id);
            employee.setName(name);
            employee.setGender(gender);
            employee.setBirthday(birthday);
            employees.add(employee);
        }
        return employees;
    }
}
