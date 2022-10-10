package com.lihengyu.ssm.service;

import com.github.pagehelper.PageInfo;
import com.lihengyu.ssm.pojo.Employee;

import java.util.List;

/**
 * @author shkstart
 * @create 2022-07-26-16:43
 */
public interface EmployeeService {
    /**
     * 查询所有的员工信息
     * @return
     */
    List<Employee> getAllEmployees();

    PageInfo<Employee> getEmployeeByPage(Integer pageNum);

    Employee getEmployeeById(Integer id);

    void addEmployee(Employee employee);

    void deleteEmployee(Integer id);

    void updateEmployee(Employee employee);

}
