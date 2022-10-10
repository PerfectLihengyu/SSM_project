package com.lihengyu.ssm.mapper;

import com.lihengyu.ssm.pojo.Employee;

import java.util.List;

/**
 * @author shkstart
 * @create 2022-07-26-17:37
 */
public interface EmployeeMapper {
    /**
     * 查询所有员工信息
     * @return
     */
    List<Employee> getAllEmployees();

    /**
     * 按id查询员工信息
     * @param id
     * @return
     */
    Employee getEmployeeById(Integer id);

    /**
     * 添加员工
     * @param employee
     */
    void addEmployee(Employee employee);

    /**
     * 删除员工
     * @param id
     */
    void deleteEmployee(Integer id);

    void updateEmployee(Employee employee);
}
